package com.springboot.bootdemo.handler;

import com.springboot.bootdemo.msg.NetMsgBase;
import com.springboot.bootdemo.netty.HandlerManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;

/**
 * @author xiaohai
 * @date 2023/3/8 21:01
 */
public class NetMsgBaseHandler extends SimpleChannelInboundHandler<NetMsgBase> {

    private static final Logger LOG = Logger.getLogger(NetMsgBaseHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NetMsgBase netMsgBase) throws Exception {

        MessageHandler defaultHandler = HandlerManager.getDefaultHandler(netMsgBase.cmd);
        if (defaultHandler != null) {
//            channelHandlerContext
            Channel channel = channelHandlerContext.channel();
            defaultHandler.handler(netMsgBase, channel);
        }
    }

    // 断开连接处理
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LOG.debug("连接已断开");
    }
}
