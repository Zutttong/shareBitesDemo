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
    @Autowired // 自动注入RedisTemplate实例，用于操作Redis数据库
    RedisTemplate redisTemplate;
    @Autowired // 自动注入FoodMapper实例，用于操作与食物相关的数据库操作
    FoodMapper foodMapper;
    @Autowired // 自动注入UserMapper实例，用于操作与用户相关的数据库操作
    UserMapper userMapper;

    // 提供添加食物信息的服务
    public boolean addFood(Food food) {
        // 打印食物的ID（通常用于调试）
        System.out.println(food.getId());
        // 调用FoodMapper的addFood方法将食物信息添加到数据库
        foodMapper.addFood(food.getId(),food.getName(),food.getExpirationTime(),food.getPostTime(),food.getImage(),food.getOwner(),food.getLongitude(),food.getLatitude());
        // 返回true表示添加操作成功
        return true;
    }

    // 提供更新食物状态的服务
    public boolean UpdateFoodsStatus(String status,String id){
        // 调用FoodMapper的UpdateFoodsStatus方法更新特定ID的食物状态
        foodMapper.UpdateFoodsStatus(status,id);
        // 返回true表示更新操作成功
        return true;
    }

    // 根据拥有者和状态获取食物信息
    public JSONObject[] getUserFood(String owner,String status){
        // 调用FoodMapper的getUserFood方法查询食物信息并返回
        return foodMapper.getUserFood(owner,status);
    }

    // 根据地理位置获取附近的食物信息
    public JSONObject[] getNearFood(double lng,double lat){
        // 调用FoodMapper的getNearFood方法查询附近的食物信息并返回
        return foodMapper.getNearFood(lng,lat);
    }

    // 获取特定ID的食物详细信息
    public JSONObject getDetailFood(String id){
        // 调用FoodMapper的getDetailFood方法查询食物的详细信息并返回
        return foodMapper.getDetailFood(id);
    }
}
