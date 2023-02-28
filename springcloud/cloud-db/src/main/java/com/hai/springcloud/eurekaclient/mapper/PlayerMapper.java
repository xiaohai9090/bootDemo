package com.hai.springcloud.eurekaclient.mapper;

import com.hai.springcloud.domain.Player;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerMapper {

    void addPlayer(Player player);

    List<Player> selectPlayer();

    Player selectPlayerById(int playerIndex);

    List<Player> selectPlayersByTeamId(int teamId);

    void updatePlayerInfo(Player player);
}
