package com.springboot.bootdemo.xgame.socket;

import com.springboot.bootdemo.base.GameServerSocket;
import com.springboot.bootdemo.base.networkbase.IMsgReceiver;

public class DBServerSocket extends GameServerSocket {

    public DBServerSocket (int port, String serverName, IMsgReceiver msgReceiver){
        super(port,serverName,msgReceiver);
    }
}
