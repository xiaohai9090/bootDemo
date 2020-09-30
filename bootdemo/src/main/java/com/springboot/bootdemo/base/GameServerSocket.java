package com.springboot.bootdemo.base;


import com.springboot.bootdemo.base.networkbase.IMsgReceiver;
import com.springboot.bootdemo.base.webSocket.MyPrefixedStringCodecFactory;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class GameServerSocket {

    /** 端口 */
    private int gameSocketPort = 0;
    /** 服务器名称 */
    private String serverName;
    private IMsgReceiver msgReceiver;

    /** 最大进程数 */
    private static final int maxProcessors = 8;

    private IoAcceptor acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() > maxProcessors ? maxProcessors:Runtime.getRuntime().availableProcessors());

    public GameServerSocket(int port, String serverName, IMsgReceiver msgReceiver){
        this.init(port,serverName,msgReceiver);
    }

    private void init(int port,String serverName,IMsgReceiver msgReceiver){
        this.gameSocketPort = port;
        this.serverName = serverName;
        this.msgReceiver = msgReceiver;

        initSocket();
    }

    public void initSocket(){

        try {
            GameSocketHandle gameSocketHandle = new GameSocketHandle();
            gameSocketHandle.setMsgRec(this.msgReceiver);

            //设置log拦截器
            acceptor.getFilterChain().addLast("log",new LoggingFilter());
            //设定消息编码规则拦截器
            acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new MyPrefixedStringCodecFactory(Charset.forName("UTF-8"))));
            acceptor.setHandler(gameSocketHandle);
            acceptor.bind(new InetSocketAddress(this.gameSocketPort));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
