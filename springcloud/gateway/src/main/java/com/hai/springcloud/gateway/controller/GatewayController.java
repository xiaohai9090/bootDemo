package com.hai.springcloud.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gate")
public class GatewayController {


    @RequestMapping("aaaa")
    public String hello() {
        return "fuck!!!!";
    }
}
