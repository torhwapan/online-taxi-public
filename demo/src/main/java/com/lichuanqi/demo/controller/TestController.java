package com.lichuanqi.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test")
    public String test(){

        redisTemplate.opsForValue().set("李传奇","redis测试-String",30, TimeUnit.SECONDS);
        System.out.println("??????");
        return "test api passenger";
    }

}
