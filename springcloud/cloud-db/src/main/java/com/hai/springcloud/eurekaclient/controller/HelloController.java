package com.hai.springcloud.eurekaclient.controller;

import com.hai.springcloud.domain.Player;
import com.hai.springcloud.eurekaclient.mapper.PlayerMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${server.port}")
    private String port;

    @GetMapping("{playerIndex}")
    public Player getPlayerByIndex(@PathVariable("playerIndex")int playerIndex){
//        Player player = restTemplate.getForObject("http://localhost:8500/player/"+playerIndex,Player.class);
        Player player = playerMapper.selectPlayerById(playerIndex);
        return player;
    }


    @GetMapping("/payment/{id}")
    public String client1(@PathVariable("id")int id) {
        return "serverport: " + port + "\t id:" + id;
    }

}
