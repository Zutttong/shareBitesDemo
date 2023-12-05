package com.example.sharebitesdemo.controller;

import com.example.sharebitesdemo.ShareBitesDemoApplication;
import com.example.sharebitesdemo.bean.User;
import com.example.sharebitesdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest(classes = ShareBitesDemoApplication.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {
    @Autowired
    private UserService userService;
    @Test
    public void getUser() {
        String id="Alex";
        User user=userService.getUser(id);
        System.out.println(user);
    }
}