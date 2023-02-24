package com.springboot.bootdemo.service.impl;

import com.springboot.bootdemo.service.TestService;
import org.springframework.stereotype.Service;

@Service("service2")
public class TestServiceImpl2 implements TestService {
    @Override
    public void run() {
        System.out.println(2);
    }
}
