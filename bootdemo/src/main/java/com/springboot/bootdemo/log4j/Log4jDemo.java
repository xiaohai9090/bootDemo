package com.springboot.bootdemo.log4j;


import com.springboot.bootdemo.domain.Something;
import org.apache.log4j.Logger;

public class Log4jDemo {
    public static Logger logger = Logger.getLogger(Log4jDemo.class);

    public static void main(String[] args) {

        Something st = null;

        try{
            System.out.println(st.getName());
        }catch (Exception e){
            logger.error(e);
        }





        System.out.println("有没有？？？？");
    }


}
