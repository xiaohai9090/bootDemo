package com.springboot.bootdemo.service.impl;

import com.springboot.bootdemo.dao.IPlayerDao;
import com.springboot.bootdemo.domain.Player;
import com.springboot.bootdemo.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private IPlayerDao playerDao;

    private final Map<Integer,Player> players = new ConcurrentHashMap<>();

    public void loadInitPlayers(){
        List<Player> playerList = this.selectPlayer();
        if (playerList != null &&playerList.size() > 0){
            for (Player player : playerList){
                players.put(player.getPlayerIndex(),player);
            }
        }
    }

    @Override
    public void addPlayer(Player player) {
        playerDao.addPlayer(player);
        players.put(player.getPlayerIndex(),player);
    }

    @Override
    public List<Player> selectPlayer() {
        List<Player> playerList = playerDao.selectPlayer();
        return playerList;
    }

    @Override
    public Player selectPlayerById(int playerIndex) {
        Player player = players.get(playerIndex);
        return player;
    }

    @Override
    public void updatePlayer(int playerIndex, int money) {
        playerDao.updatePlayer(playerIndex,money);

        System.out.println(1 / 0);
    }
}
