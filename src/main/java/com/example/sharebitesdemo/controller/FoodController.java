package com.example.sharebitesdemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sharebitesdemo.bean.Food;
import com.example.sharebitesdemo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import com.example.sharebitesdemo.utils.Json;

import java.util.List;
@RestController
@RequestMapping("/api")
public class FoodController {

    @Autowired
    private FoodService foodService;
    @PostMapping("addFood")
    public JSONObject addFood(@RequestBody Food food){
        boolean result = foodService.addFood(food);
        Json json;
        if(result){
            json=new Json("success","",0);
        }else{
            json=new Json("error","",1);
        }
        return json.getJson();
    }

    @PutMapping("update/food/status")   //PutMapping是更新资源
    public JSONObject UpdateFoodsStatus(String status,String id){
        System.out.println(status);
        boolean result = foodService.UpdateFoodsStatus(status,id);
        Json json;
        if(result){
            json=new Json("success","",0);
        }else{
            json=new Json("error","",1);
        }
        return json.getJson();
    }

    @GetMapping("get/user/food")
    public JSONObject getUserFood(String owner,String status){
        JSONObject[] js=foodService.getUserFood(owner,status);
        JSONObject json = new JSONObject();
        json.put("data", js);
        json.put("status", 0);
        return json;
    }
    @GetMapping("get/near/food")
    public JSONObject getNearFood(double lng,double lat){
        JSONObject[] js=foodService.getNearFood(lng,lat);
        JSONObject json = new JSONObject();
        json.put("data", js);
        json.put("status", 0);
        return json;
    }

    @GetMapping("get/detail/food")
    public JSONObject getUserFood(String id){
        JSONObject js=foodService.getDetailFood(id);
        JSONObject json = new JSONObject();
        json.put("data", js);
        json.put("status", 0);
        return json;
    }

}
