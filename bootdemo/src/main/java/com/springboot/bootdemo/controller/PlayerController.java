package com.springboot.bootdemo.controller;


import com.springboot.bootdemo.domain.Player;
import com.springboot.bootdemo.domain.Something;
import com.springboot.bootdemo.domain.Team;
import com.springboot.bootdemo.service.IPlayerService;
import com.springboot.bootdemo.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @Autowired
    private ITeamService teamService;

    @ResponseBody
    @RequestMapping("/insertPlayer")
    public String insertPlayer(String playerName,int playerIndex,int money,int teamId){
        Player player = new Player();
        player.setPlayerIndex(playerIndex);
        player.setPlayerName(playerName);
        player.setMoney(money);
        player.setLoginTime(new Date());
        player.setAreaId(1001);
        player.setTeamId(teamId);

        try {
            playerService.addPlayer(player);
            return "保存成功";
        }catch (Exception e){
            e.printStackTrace();
            return "保存失败";
        }
    }


    @ResponseBody
    @RequestMapping
    public List<Player> selectPlayerList(){
        List<Player> playerList = playerService.selectPlayer();
        return playerList;
    }


    @ResponseBody
    @RequestMapping("/getPlayer")
    public Player getPlayerByIndex(int playerIndex){
        Player player = playerService.selectPlayerById(playerIndex);
        return player;
    }

    @RequestMapping("/getTeamById")
    public String getTeamById(int teamId, Model model){
        Team team = teamService.getTeamById(teamId);
        model.addAttribute("team",team);
        return "Team";
    }


    @ResponseBody
    @RequestMapping("/getPlayersByTeamId")
    public List<Player> getPlayersByTeamId(int teamId){
        Team team = teamService.getTeamById(teamId);
        List<Player> playerList = team.allPlayers();
        return playerList;
    }

}
