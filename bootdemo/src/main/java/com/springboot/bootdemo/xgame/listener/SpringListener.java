package com.springboot.bootdemo.xgame.listener;

import com.springboot.bootdemo.base.accepter.MinaLoopAccepter;
import com.springboot.bootdemo.base.networkbase.MsgLoopBase;
import com.springboot.bootdemo.gayeThread.AsyncHandleLogicThread;
import com.springboot.bootdemo.gayeThread.DBThread;
import com.springboot.bootdemo.service.impl.PlayerService;
import com.springboot.bootdemo.xgame.common.LicenseUtil;
import com.springboot.bootdemo.xgame.socket.DBServerSocket;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SpringListener implements CommandLineRunner {

    private static final Logger LOG = Logger.getLogger(InitSystemListener.class);

    private DBServerSocket gameSocketServer = null;

    @Autowired
    private PlayerService playerService;

    private boolean debug =true;

    private DBThread dbThread = null;


    @Override
    public void run(String... args) throws Exception {

        dbThread = new DBThread();
        dbThread.start();

        // 启动线程,3个
        for (int i = 0; i < 3; i++) {
            AsyncHandleLogicThread logicThread = new AsyncHandleLogicThread();
            logicThread.start();
        }


        MsgLoopBase minaAccepter = new MinaLoopAccepter();
        gameSocketServer = new DBServerSocket(8888,"gaye",minaAccepter);

        playerService.loadInitPlayers();
    }
}
