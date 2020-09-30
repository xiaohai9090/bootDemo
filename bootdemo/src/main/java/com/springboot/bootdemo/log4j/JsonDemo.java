package com.springboot.bootdemo.log4j;

import com.springboot.bootdemo.domain.Something;
import org.apache.log4j.Logger;

public class JsonDemo {

    private static Logger logger = Logger.getLogger(JsonDemo.class);

    public static void main(String[] args) {
        int i = 10;
        int j = result(i);
        System.out.println(i);
    }

    private static int result(int i){
        i += 20;

        return 50;
    }
}
