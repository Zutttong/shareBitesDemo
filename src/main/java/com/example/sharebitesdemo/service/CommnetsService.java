package com.example.sharebitesdemo.service;


import com.alibaba.fastjson.JSONObject;
import com.example.sharebitesdemo.mapper.CommentsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CommnetsService {
    @Resource
    private CommentsMapper commentsMapper;

    // 添加评论
    public Boolean postContent(String userId,String nickname,String content,String foodId){
        Long create_time=System.currentTimeMillis();
        commentsMapper.postContent(nickname,content,create_time,userId,foodId);
        return true;
    }


    // 获取全部评论
    public JSONObject[] getComments(String foodId){
        return commentsMapper.getComments(foodId);
    }

    // 获取全部评论数量
    public int getCommentsNum(String foodId){
        return commentsMapper.getCommentsNum(foodId);
    }

}

