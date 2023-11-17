package com.example.sharebitesdemo.controller;

import com.example.sharebitesdemo.bean.User;
import com.example.sharebitesdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.sharebitesdemo.utils.Json;

import java.lang.reflect.Type;
@RequestMapping("/api")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("register")
    public JSONObject register(@RequestBody User user){
        boolean results =userService.register(user);
        Json json;
        if (results) {
            json = new Json("注册成功", "", 0);
        } else {
            json = new Json("失败", "", 1);
        }
        return json.getJson();
    };

    @PostMapping("login")
    public JSONObject login(@RequestBody User user){
        User users=userService.login(user);
        Json json;
        if(users==null){
            json=new Json("账号或密码错误","",1);
            return json.getJson();
        }
        users.setPassword("");
        json=new Json("登录成功",users,0);
        return json.getJson();
    }

    @PostMapping("editProfile")
    public void editProfile(User user){
        userService.editProfile(user);
    }

    @GetMapping("getUser")
    public JSONObject getUser(String id){
        User user=userService.getUser(id);
        Json json;
        if(user==null){
            json=new Json("查找失败","",1);
            return json.getJson();
        }
        user.setPassword("");
        json=new Json("获取成功",user,0);
        return json.getJson();
    }

    @PostMapping("updateRating")
    public void updateRating(String id, String type){
        userService.updateRating(id, type);
    }
}
