package com.example.sharebitesdemo.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CommentsMapper {

    // 提交评论
    @Insert("insert into comments set nickname=#{nickname},content=#{content},createTime=#{create_time},userId=#{userId},foodId=#{foodId}")
    void postContent(String nickname,String content,Long create_time,String userId,String foodId);

    // 获取全部评论
    @Select("select * from comments where foodId =#{foodId}")
    JSONObject[] getComments(String foodId);

    // 获取全部评论数量
    @Select("select count(*) as count from comments where foodId=#{foodId}")
    int getCommentsNum(String foodId);

}
