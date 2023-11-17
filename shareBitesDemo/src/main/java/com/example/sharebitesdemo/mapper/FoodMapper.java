package com.example.sharebitesdemo.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.sharebitesdemo.bean.Food;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodMapper {

    @Insert("insert into food set id=#{id},name=#{name},expiration_time=#{expiration_time},post_time=#{post_time},image=#{image},owner=#{owner},longitude=#{longitude},latitude=#{latitude}")
    void addFood(String id,String name, Long expiration_time,Long post_time,String image,String owner,double longitude,double latitude);

    @Update("update food set status = #{status} where id=#{id}")
    void UpdateFoodsStatus(String status,String id);

    @Select("select * from food where owner=#{owner} and status=#{status}")
    JSONObject[] getUserFood(String owner, String status);

    @Select("select * from food where longitude between (#{longitude} - 0.027) and (#{longitude} + 0.027) and latitude between (#{latitude} - 0.027) and (#{latitude}  + 0.027) and status='listing'")
    JSONObject[] getNearFood(double longitude,double latitude);

    @Select("select * from food where id=#{id}")
    JSONObject getDetailFood(String id);
}
