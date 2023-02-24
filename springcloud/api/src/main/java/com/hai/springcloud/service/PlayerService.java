package com.hai.springcloud.service;

import com.hai.springcloud.domain.Player;

public interface PlayerService {

    public Player selectPlayerById(int playerIndex);

    String providerTest();
}
