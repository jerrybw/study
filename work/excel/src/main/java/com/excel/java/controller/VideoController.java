package com.excel.java.controller;

import com.excel.java.bean.Video;
import com.excel.java.service.VideoService;
import com.excel.java.util.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    private Logger logger = Logger.getLogger(VideoController.class);

    @RequestMapping(value = "/video",method = RequestMethod.GET)
    @ResponseBody
    public Object listVideo(){
        logger.info("接收到获取视频列表请求");
        List<Video> videos = videoService.getAllVideos();
        logger.info("返回值:"+videos);
        return videos;
    }

    @RequestMapping(value="/uploadVideo",method = RequestMethod.POST)
    public String uploadVideo(@RequestParam("file") MultipartFile file,String videoTimeDouble,Video video, HttpServletRequest request) {
        String[] split1 = videoTimeDouble.split("\\.");
        int realVideoTime = Integer.parseInt(split1[0]);
        video.setVideoTime(realVideoTime);
        long updateTime = System.currentTimeMillis();
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        video.setVideoName(fileName);
        String[] split = fileName.split("\\.");
        fileName = split[0] +"&"+ updateTime + "." + split[1];
        String filePath = videoAddress + "resources/video/";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
        }
//        String videoUrl = UrlUtil.getUrl("videoUrl");
        video.setVideoUrl(videoUrl + fileName);
        video.setUpdateTime(updateTime);
        videoService.saveVideo(video);
        return "redirect:index.html";
    }

}
