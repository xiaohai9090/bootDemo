package com.springboot.bootdemo.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;

@PropertySource({"classpath:config/config.properties"})
public class BaseObject implements Serializable {

    @Value("${name}")
    private String name;

    @Value("${age}")
    private int age;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name=").append(this.name);
        sb.append(",age=").append(this.age);
        return sb.toString();
    }
}
