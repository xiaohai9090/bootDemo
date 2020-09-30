package com.springboot.bootdemo.service.impl;

import com.springboot.bootdemo.dao.IDemoDao;
import com.springboot.bootdemo.dao.IPlayerDao;
import com.springboot.bootdemo.dao.ITeamDao;
import com.springboot.bootdemo.domain.Player;
import com.springboot.bootdemo.domain.Team;
import com.springboot.bootdemo.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class teamService implements ITeamService {

    @Autowired
    private ITeamDao teamDao;

    @Autowired
    private IPlayerDao playerDao;

    private final Map<Integer,Team> teamMap = new HashMap<>();

    @Override
    public Team getTeamById(int teamId) {
        if (!teamMap.containsKey(teamId)) {
            Team team = teamDao.getTeamById(teamId);
            teamMap.put(team.getTeamId(),team);
            List<Player> playerList = playerDao.selectPlayersByTeamId(teamId);
            team.setPlayers(playerList);
        }
        Team team = teamMap.get(teamId);
        return team;
    }

    @Override
    public void insertTeam(Team team) {
        teamDao.insertTeam(team);
        teamMap.put(team.getTeamId(),team);
    }
}
