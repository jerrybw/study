package com.excel.java.controller;

import com.excel.java.bean.Video;
import com.excel.java.service.VideoService;
import com.excel.java.util.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by 向博文 on 2017/10/13.
 */
@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Value("${videoUrl}")
    private String videoUrl;

    @Value("${videoAddress}")
    private String videoAddress;

    @Value("${videoBakAddress}")
    private String videoBakAddress;

    private Logger logger = Logger.getLogger(VideoController.class);

    @RequestMapping(value = "/video",method = RequestMethod.GET)
    @ResponseBody
    public Object listVideo(){
        logger.info("接收到获取视频列表的请求");
        List<Video> videos = videoService.getAllVideos();
        logger.info("返回值:"+videos);
        return videos;
    }

    @RequestMapping(value = "/getVideo/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object getVideoById(@PathVariable("id") int id){
        logger.info("接收到获取id为"+id+"的视频的请求");
        Video video = videoService.findVideoById(id);
        logger.info("返回值:"+video);
        return video;
    }

    @RequestMapping(value="/uploadVideo",method = RequestMethod.POST)
    public String uploadVideo(@RequestParam("file") MultipartFile file,String videoTimeDouble,Video video, HttpServletRequest request) {
        logger.info("上传视频。。。。");
        String[] split1 = videoTimeDouble.split("\\.");
        int realVideoTime = Integer.parseInt(split1[0]);
        video.setVideoTime(realVideoTime);
        long uploadTime = System.currentTimeMillis();
        String fileName = file.getOriginalFilename();
        video.setVideoName(fileName);
        String[] split = fileName.split("\\.");
        fileName = split[0] +"&"+ uploadTime + "." + split[1];
        String filePath = videoAddress + "resources/video/";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            logger.info("上传视频失败");
            logger.info(e.fillInStackTrace());
            return "redirect:index.html";
        }
        filePath = videoBakAddress + "resources/video/";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            logger.info("上传备份视频失败");
            logger.info(e.fillInStackTrace());
        }
//        String videoUrl = UrlUtil.getUrl("videoUrl");
        video.setVideoUrl(videoUrl + fileName);
        video.setUploadTime(uploadTime);
        videoService.saveVideo(video);
        logger.info("上传视频成功");
        return "redirect:index.html";
    }

}
