package com.hai.springcloud.eurekaclient.controller;

import com.hai.springcloud.eurekaclient.dao.IPlayerDao;
import com.hai.springcloud.eurekaclient.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/player")
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private IPlayerDao playerDao;

    @GetMapping("{playerIndex}")
    public Player getPlayerByIndex(@PathVariable("playerIndex")int playerIndex){
//        Player player = restTemplate.getForObject("http://localhost:8500/player/"+playerIndex,Player.class);
        Player player = playerDao.selectPlayerById(playerIndex);
        return player;
    }
}
