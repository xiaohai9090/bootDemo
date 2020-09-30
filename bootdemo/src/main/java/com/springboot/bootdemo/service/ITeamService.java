package com.springboot.bootdemo.service;

import com.springboot.bootdemo.domain.Team;

public interface ITeamService {

    public void insertTeam(Team team);

    Team getTeamById(int teamId);

}
