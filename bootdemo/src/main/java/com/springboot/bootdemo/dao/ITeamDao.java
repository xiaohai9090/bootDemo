package com.springboot.bootdemo.dao;

import com.springboot.bootdemo.domain.Team;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ITeamDao {

    @Insert("insert into team(TEAM_NAME,TEAM_LEADER_INDEX) values(#{teamName},#{teamLeaderIndex})")
    public void insertTeam(Team team);

    public List<Team> getTeamList();

    @Select("select * from team where TEAM_ID = #{teamId}")
    @Results({
            @Result(property = "teamId",column = "TEAM_ID"),
            @Result(property = "teamName",column = "TEAM_NAME"),
            @Result(property = "teamLeaderId",column = "TEAM_LEADER_INDEX")
    })
    public Team getTeamById(int teamId);
}
