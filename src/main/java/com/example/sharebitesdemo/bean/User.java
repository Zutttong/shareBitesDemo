package com.example.sharebitesdemo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
@Data
public class User {
    private String nickname;
    private String account;
    private String password;
    @TableId(type= IdType.ASSIGN_ID) //id不能为空
    private String id;
    private Integer rating;
    private String reportedTimes;
}
