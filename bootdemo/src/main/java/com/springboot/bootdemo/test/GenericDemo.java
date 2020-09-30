package com.springboot.bootdemo.test;

import com.springboot.bootdemo.domain.Generic;
import com.springboot.bootdemo.domain.Student;
import com.springboot.bootdemo.domain.Teacher;

import java.util.Date;

public class GenericDemo {
    public static void main(String[] args) {
        Generic<Integer> generic = new Generic<Integer>(12);
        Generic<String> stringGeneric = new Generic<>("sssss");
        System.out.println(generic.getKey());
        System.out.println(stringGeneric.getKey());


    }


    protected void getOne(){

    }
}
