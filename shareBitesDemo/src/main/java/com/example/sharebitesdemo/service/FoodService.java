package com.example.sharebitesdemo.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sharebitesdemo.bean.Food;
import com.example.sharebitesdemo.bean.User;
import com.example.sharebitesdemo.mapper.FoodMapper;
import com.example.sharebitesdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    FoodMapper foodMapper;
    @Autowired
    UserMapper userMapper;


    public boolean addFood(Food food) {
        System.out.println(food.getId());
        foodMapper.addFood(food.getId(),food.getName(),food.getExpirationTime(),food.getPostTime(),food.getImage(),food.getOwner(),food.getLongitude(),food.getLatitude());
        return true;
    }

    public boolean UpdateFoodsStatus(String status,String id){
        foodMapper.UpdateFoodsStatus(status,id);
        return true;
    }

    public JSONObject[] getUserFood(String owner,String status){
        return foodMapper.getUserFood(owner,status);
    }

    public JSONObject[] getNearFood(double lng,double lat){
        return foodMapper.getNearFood(lng,lat);
    }

    public JSONObject getDetailFood(String id){
        return foodMapper.getDetailFood(id);
    }
}