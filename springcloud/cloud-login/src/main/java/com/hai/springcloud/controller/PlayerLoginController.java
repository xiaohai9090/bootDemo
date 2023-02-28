package com.hai.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.hai.springcloud.Constant.Constants;
import com.hai.springcloud.domain.Player;
import com.hai.springcloud.enums.RedisKeyEnums;
import com.hai.springcloud.msgObj.LoginMsg;
import com.hai.springcloud.redis.RedisCache;
import com.hai.springcloud.service.PlayerService;
import com.hai.springcloud.utils.DateUtil;
import com.hai.springcloud.utils.IdUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaohai
 * @date 2023/2/27 21:02
 */
@RestController("login")
public class PlayerLoginController {

    private final static long EXPIRE_TIME = Constants.PLAYER_EXPIRE * 24;

    @DubboReference(interfaceClass = PlayerService.class)
    private PlayerService playerService;

    private String key = RedisKeyEnums.PLAYER_CACHE.getKey();


    @Autowired
    private RedisCache redisCache;


    public String login(LoginMsg msg) {

        int playerIndex = msg.getPlayerIndex();
        Player player = redisCache.getCacheObject(String.valueOf(playerIndex));
        if (player == null) {
            // 创建玩家
            player = createPlayer(msg);
            playerService.addPlayer(player);
        } else {

            player.setLoginTime(DateUtil.now());
            playerService.updatePlayerInfo(player);
        }


        redisCache.setCacheObject(key + player.getPlayerIndex(), player, EXPIRE_TIME, TimeUnit.HOURS);

        return JSON.toJSONString(player);
    }


    private Player createPlayer(LoginMsg msg) {
        Player player = new Player();
//        player.setPlayerIndex();  todo
        player.setPlayerName(msg.getPlayerName());
        player.setLoginTime(DateUtil.now());
        return player;
    }

}
