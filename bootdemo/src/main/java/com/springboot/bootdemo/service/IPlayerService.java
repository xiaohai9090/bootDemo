package com.springboot.bootdemo.service;

import com.springboot.bootdemo.domain.Player;

import java.util.List;

public interface IPlayerService {

    public void addPlayer(Player player);

    public List<Player> selectPlayer();

    public Player selectPlayerById(int playerIndex);

//    public List<Player> selectPlayersByTeamId(int teamId);
}
