package com.springboot.bootdemo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Team {
    private int teamId;
    private String teamName;
    private int teamLeaderId;

    private Map<Integer,Player> playerMap;
    private Map<Integer,Player> getPlayerMap(){
        if (this.playerMap == null){
            synchronized (this) {
                if (this.playerMap == null) {
                    this.playerMap = new ConcurrentHashMap<Integer, Player>();
                }
            }
        }
        return this.playerMap;
    }

    public void setPlayers(Collection<Player> players){
        this.getPlayerMap().clear();
        for (Player player : players){
            this.getPlayerMap().put(player.getPlayerIndex(),player);
        }
    }

    public Player getPlayerById(int playerId){
        Player player = this.getPlayerMap().get(playerId);
        return player;
    }

    public List<Player> allPlayers(){
        return new ArrayList<>(getPlayerMap().values());
    }


    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


    public int getTeamLeaderId() {
        return teamLeaderId;
    }

    public void setTeamLeaderId(int teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }
}
