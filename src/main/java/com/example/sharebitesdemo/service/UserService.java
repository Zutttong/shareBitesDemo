package com.example.sharebitesdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sharebitesdemo.bean.User;
import com.example.sharebitesdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 标记这个类为服务层组件，由Spring容器管理
public class UserService {
    @Autowired // 自动注入UserMapper接口的实现类实例
    UserMapper userMapper;

    public Boolean register(User user) { // 提供注册服务
        user.setRating(3); // 注册用户默认评分设置为3
        Integer res = userMapper.insert(user); // 调用UserMapper的insert方法添加用户到数据库，返回影响的行数
        return res > 0; // 如果影响行数大于0，返回true表示注册成功
    }

    public User login(User user) { // 提供登录服务
        var account = user.getAccount(); // 从User对象中获取账户名
        var tempPassword = user.getPassword(); // 从User对象中获取密码
        // 调用UserMapper的selectOne方法查询账户名和密码匹配的用户，使用QueryWrapper构建查询条件
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("account", account).eq("password", tempPassword));
        return user1; // 返回查询到的用户对象，如果没有找到匹配项则返回null
    }

    public void editProfile(User user) { // 提供编辑用户资料的服务
        userMapper.updateById(user); // 调用UserMapper的updateById方法更新用户资料
    }

    public void resetPassword(User user) { // 提供重置密码服务的桩方法，当前没有实现
    }

    public User getUser(String id) { // 根据用户ID获取用户信息的服务
        return userMapper.selectById(id); // 调用UserMapper的selectById方法查询用户
    }

    public void updateRating(String id, String type) { // 提供更新用户评分的服务
        User user = this.getUser(id); // 先获取用户信息
        if(type.equals("0")){ // 如果传入类型为"0"，则增加用户评分
            user.setRating(user.getRating() + 1);
        }else{ // 否则减少用户评分
            user.setRating(user.getRating() - 1);
        }
        userMapper.updateById(user); // 更新用户信息到数据库
    }
}
