package com.excel.java.service;

import com.excel.java.bean.Video;
import com.excel.java.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 向博文 on 2017/10/13.
 */
@Service
public class VideoService {

    @Autowired
    private VideoMapper videoMapper;

    public List<Video> getAllVideos(){
        return videoMapper.getAllVideos();
    }

    public int saveVideo(Video video){
        return videoMapper.insertVideo(video);
    }
}
