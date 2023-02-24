package com.springboot.bootdemo.service.impl;

import com.springboot.bootdemo.service.TestService;
import org.springframework.stereotype.Service;

@Service("service1")
public class TestServiceImpl1 implements TestService {

    @Override
    public void run() {
        System.out.println(1);
    }
}
