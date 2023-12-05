package com.example.sharebitesdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.sharebitesdemo.ShareBitesDemoApplication;
import com.example.sharebitesdemo.service.FoodService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = ShareBitesDemoApplication.class)
@RunWith(SpringRunner.class)
class FoodControllerTest {
    @Autowired
    FoodService foodService;
    @Test
    void addFood() {


    }

    @Test
    void updateFoodsStatus() {
    }

    @Test
    void getUserFood() {
    }

    @Test
    void testGetUserFood() {
    }


    @Test
    void testGetNearFood() {
        double lng=279.66769684765615;
        double lat=35.820537916467465;
        JSONObject[] js=foodService.getNearFood(lng,lat);
        System.out.println("hello world");
    }
}