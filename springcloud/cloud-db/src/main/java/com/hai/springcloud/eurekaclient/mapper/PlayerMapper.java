package com.hai.springcloud.eurekaclient.mapper;

import com.hai.springcloud.domain.Player;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayerMapper {

    public void addPlayer(Player player);


    public List<Player> selectPlayer();

    public Player selectPlayerById(int playerIndex);

    public List<Player> selectPlayersByTeamId(int teamId);
}