package com.example.sharebitesdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sharebitesdemo.bean.User;
import com.example.sharebitesdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public Boolean register(User user) {
        user.setRating(3);
        Integer res= userMapper.insert(user);
        return res>0;
    }

    public User login(User user) {
        var account = user.getAccount();
        var tempPassword = user.getPassword();
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("account", account).eq("password", tempPassword));
        return user1;
    }

    public void editProfile(User user) {
        userMapper.updateById(user);
    }

    public void resetPassword(User user) {
    }

    public User getUser(String id) {
        return userMapper.selectById(id);
    }

    public void updateRating(String id, String type) {

        User user = this.getUser(id);
        if(type.equals("0")){
            user.setRating(user.getRating()+1);
        }else{
            user.setRating(user.getRating()-1);
        }
        userMapper.updateById(user);
    }
}
