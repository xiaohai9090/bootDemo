package com.hai.springcloud.eurekaclient.mqReceiver;

import com.hai.springcloud.Constant.MqConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiaohai
 * @date 2023/3/1 15:09
 */
@Component
@RabbitListener(queues = MqConstants.TOPIC_ROUTING)
public class TopicReceiver {

    @RabbitHandler
    public void process(Map msg) {
        System.out.println(msg.toString());
    }
}
