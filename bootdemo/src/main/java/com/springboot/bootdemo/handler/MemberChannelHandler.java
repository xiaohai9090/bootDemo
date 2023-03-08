package com.springboot.bootdemo.handler;

import com.springboot.bootdemo.base.common.DefaultMsgHandler;
import com.springboot.bootdemo.base.common.MsgCmdConstant;
import com.springboot.bootdemo.msg.MemberMsg;
import com.springboot.bootdemo.netty.IOSession;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;

/**
 * @author xiaohai
 * @date 2023/3/6 19:58
 */
@DefaultMsgHandler(cmd = MsgCmdConstant.MEMBER_MSG)
public class MemberChannelHandler implements MessageHandler<MemberMsg> {

    private static Logger logger = Logger.getLogger(MemberChannelHandler.class);

    @Override
    public void handler(MemberMsg msg, Channel channel) {

    }
}
