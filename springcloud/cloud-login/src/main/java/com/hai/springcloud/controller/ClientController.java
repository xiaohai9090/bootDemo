package com.hai.springcloud.controller;


import com.hai.springcloud.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class ClientController {

    @Autowired
    private RedisCache redisCache;

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world!";
    }

    @GetMapping("/payment/{id}")
    public String client1(@PathVariable("id")int id) {
        return "serverport: " + port + "\t id:" + id;
    }


    public void login() {

    }
}
