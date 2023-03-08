package com.springboot.bootdemo.xgame.listener;

import com.springboot.bootdemo.base.common.ReflectASMObjectManager;
import com.springboot.bootdemo.gayeThread.DBThread;
import com.springboot.bootdemo.netty.HandlerManager;
import com.springboot.bootdemo.netty.NettyServer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class SpringListener {

    private static final Logger LOG = Logger.getLogger(SpringListener.class);


    @PostConstruct
    public void initSystem() {

        ReflectASMObjectManager.registerAllMsgConstructorAccess("com.springboot.bootdemo.msg");

        HandlerManager.initDefaultHandler();

        NettyServer.initNettyServer();
    }
}
