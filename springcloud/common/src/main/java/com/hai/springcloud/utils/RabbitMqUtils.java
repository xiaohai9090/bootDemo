package com.hai.springcloud.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xiaohai
 * @date 2023/2/28 19:55
 */
public class RabbitMqUtils {


    public void connetcMq() {

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.10.128");
            factory.setUsername("admin");
            factory.setPassword("123456");

            Connection connection = factory.newConnection();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
