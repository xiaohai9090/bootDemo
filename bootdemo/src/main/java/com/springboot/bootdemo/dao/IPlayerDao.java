package com.springboot.bootdemo.dao;

import com.springboot.bootdemo.domain.Player;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface IPlayerDao extends IDemoDao {

    @Insert("insert into player(PLAYER_INDEX,PLAYER_NAME,LOGIN_TIME,AREA_ID,MONEY,TEAM_ID) values(#{playerIndex},#{playerName},#{loginTime},#{areaId},#{money},#{teamId})")
    public void addPlayer(Player player);

    @Select("select * from player")
    @Results({
            @Result(property = "playerIndex",column = "PLAYER_INDEX"),
            @Result(property = "playerName",column = "PLAYER_NAME"),
            @Result(property = "loginTime",column = "LOGIN_TIME"),
            @Result(property = "areaId",column = "AREA_ID"),
            @Result(property = "money",column = "MONEY"),
            @Result(property = "teamId",column = "TEAM_ID"),
    })
    public List<Player> selectPlayer();

    public Player selectPlayerById(int playerIndex);

    @Select("select * from player where TEAM_ID = #{teamId}")
    @Results({
            @Result(property = "playerIndex",column = "PLAYER_INDEX"),
            @Result(property = "playerName",column = "PLAYER_NAME"),
            @Result(property = "loginTime",column = "LOGIN_TIME"),
            @Result(property = "areaId",column = "AREA_ID"),
            @Result(property = "money",column = "MONEY"),
            @Result(property = "teamId",column = "TEAM_ID"),
    })
    public List<Player> selectPlayersByTeamId(int teamId);


    @Update("update player set MONEY = #{money} where PLAYER_INDEX = #{playerIndex}")
    void updatePlayer(@Param("playerIndex") int playerIndex,@Param("money")int money);
}
