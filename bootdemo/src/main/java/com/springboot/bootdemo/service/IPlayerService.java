package com.springboot.bootdemo.service;

import com.springboot.bootdemo.domain.Player;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPlayerService {

    public void addPlayer(Player player);

    public List<Player> selectPlayer();

    public Player selectPlayerById(int playerIndex);

//    public List<Player> selectPlayersByTeamId(int teamId);

    @Transactional(rollbackFor = {Exception.class})
    void updatePlayer(int playerIndex,int money) throws Exception;
}
