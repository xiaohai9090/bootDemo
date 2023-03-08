package com.hai.springcloud.coumster.controller;

import com.hai.springcloud.Constant.MqConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaohai
 * @date 2023/3/1 13:16
 */
@RestController
@RequestMapping("/mq")
public class CoumsterController {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @GetMapping("/direct")
    public String directMethod() {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("playerIndex", 123456);
        paramMap.put("playerName", "张三");
        paramMap.put("exchange", "直连交换机");
        rabbitTemplate.convertAndSend(MqConstants.DIRECT_EXANGE, MqConstants.DIRECT_ROUTING_KEY, paramMap);

        return "finish!";
    }


    @GetMapping("/topic")
    public String topExchange() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("playerIndex", 123456);
        paramMap.put("playerName", "张三");
        paramMap.put("exchange", "主题交换机");
        rabbitTemplate.convertAndSend(MqConstants.TOPIC_EXANGE, MqConstants.ROUTING_KEY, paramMap);

        return "OK!";
    }
}
