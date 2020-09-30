package com.springboot.bootdemo.xgame.listener;

import com.springboot.bootdemo.base.accepter.MinaLoopAccepter;
import com.springboot.bootdemo.base.networkbase.MsgLoopBase;
import com.springboot.bootdemo.gayeThread.DBThread;
import com.springboot.bootdemo.service.impl.PlayerService;
import com.springboot.bootdemo.xgame.common.LicenseUtil;
import com.springboot.bootdemo.xgame.socket.DBServerSocket;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


public class InitSystemListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(InitSystemListener.class);

    private DBServerSocket gameSocketServer = null;

    @Autowired
    private PlayerService playerService;

    private boolean debug =true;

    private DBThread dbThread = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        //授权
        if(!debug){
            try {
                if(LicenseUtil.isRishtLicense(servletContext)) return;
            } catch (Exception e) {
                LOG.error("授权失败！");
                System.out.print("授权失败！");
                e.printStackTrace();
                return;
            }
        }


        LOG.info("系统初始化开始.......");
        MsgLoopBase minaAccepter = new MinaLoopAccepter();

        gameSocketServer = new DBServerSocket(8888,"gaye",minaAccepter);


        dbThread = new DBThread();
        dbThread.start();
        playerService.loadInitPlayers();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("系统结束.....");

    }
}
