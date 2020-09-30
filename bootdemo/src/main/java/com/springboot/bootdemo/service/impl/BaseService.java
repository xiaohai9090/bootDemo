package com.springboot.bootdemo.service.impl;

import com.springboot.bootdemo.service.IBaseService;
import org.springframework.stereotype.Service;

@Service
public class BaseService implements IBaseService {

    @Override
    public int getNum() {
        return 0;
    }

    @Override
    public void baseDemo() {

    }

    @Override
    public String getStr() {
        return null;
    }
}
