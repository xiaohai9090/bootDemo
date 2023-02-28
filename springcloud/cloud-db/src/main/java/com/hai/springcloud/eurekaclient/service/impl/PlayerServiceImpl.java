package com.hai.springcloud.eurekaclient.service.impl;

import com.hai.springcloud.domain.Player;
import com.hai.springcloud.eurekaclient.mapper.PlayerMapper;
import com.hai.springcloud.service.PlayerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService(interfaceClass = PlayerService.class, version = "1.0.0", group = "com.hai.springcloud.service.PlayerService")
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public Player selectPlayerById(int playerIndex) {
        return playerMapper.selectPlayerById(playerIndex);
    }

    @Override
    public void addPlayer(Player player) {
        playerMapper.addPlayer(player);
    }

    @Override
    public void updatePlayerInfo(Player player) {
        playerMapper.updatePlayerInfo(player);
    }

    @Override
    public String providerTest() {
        System.out.println("success.............");
        return "hello dubbo!";
    }
}
