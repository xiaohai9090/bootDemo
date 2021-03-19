package com.hai.springcloud.coumster.controller;

import com.hai.springcloud.eurekaclient.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("{playerIndex}")
    public Player getPlayer(@PathVariable("playerIndex") int playerIndex){
        String url = "http://eureka-client/player/"+playerIndex;
        return restTemplate.getForObject(url,Player.class);
    }
}
