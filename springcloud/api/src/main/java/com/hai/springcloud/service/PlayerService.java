package com.hai.springcloud.service;

import com.hai.springcloud.domain.Player;

public interface PlayerService {

    public Player selectPlayerById(int playerIndex);


    void addPlayer(Player player);

    String providerTest();


    void updatePlayerInfo(Player player);
}
