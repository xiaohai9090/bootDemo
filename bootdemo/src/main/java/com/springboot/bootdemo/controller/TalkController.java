package com.springboot.bootdemo.controller;

import com.springboot.bootdemo.domain.Player;
import com.springboot.bootdemo.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/talkPage")
public class TalkController {

    @Autowired
    private IPlayerService playerService;

    @RequestMapping("/loginPage")
    public String loginPage(){
        return "loginIndex";
    }


    @RequestMapping("/login")
    public String login(int playerIndex, Model model){
        Player player = playerService.selectPlayerById(playerIndex);
        if (player != null){
            model.addAttribute("player",player);
            return "talkPage";
        }
        return "index";
    }
}
