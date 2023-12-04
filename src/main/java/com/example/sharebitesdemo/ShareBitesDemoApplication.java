package com.example.sharebitesdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.sharebitesdemo.mapper")
@SpringBootApplication
public class ShareBitesDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareBitesDemoApplication.class, args);
        System.out.println("http:127.0.0.1:3007");
    }

}
