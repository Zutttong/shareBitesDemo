package com.example.sharebitesdemo.controller;

import com.example.sharebitesdemo.bean.Comments;
import com.example.sharebitesdemo.service.CommnetsService;
import com.example.sharebitesdemo.utils.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

@RequestMapping("/api")
@RestController
public class CommentsController {
    @Autowired
    private CommnetsService commnetsService;

    @RequestMapping(value = "/add/comments", method = RequestMethod.POST)
    public JSONObject postContent(@RequestBody Comments comments) {
        boolean result = commnetsService.postContent(comments.getUserId(),comments.getNickname(),comments.getContent(),comments.getFoodId());
        Json json;
        if (result) {
            json = new Json("Review success", "", 0);
        } else {
            json = new Json("Comment failure", "", 1);
        }
        return json.getJson();
    }

    @RequestMapping(value = "/get/comments", method = RequestMethod.GET)
    public JSONObject getComments(String foodId) {
        JSONObject[] js = commnetsService.getComments(foodId);
        int count=commnetsService.getCommentsNum(foodId);
        JSONObject json = new JSONObject();
        json.put("message", "获取成功");
        json.put("data", js);
        json.put("count", count);
        json.put("status", 0);
        return json;
    }

}
