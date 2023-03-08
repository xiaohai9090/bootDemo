package com.springboot.bootdemo.handler;

import com.springboot.bootdemo.base.common.DefaultMsgHandler;
import com.springboot.bootdemo.base.common.MsgCmdConstant;
import com.springboot.bootdemo.msg.ClubMsg;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;

/**
 * @author xiaohai
 * @date 2023/3/6 20:15
 */
@DefaultMsgHandler(cmd = MsgCmdConstant.CLUB_MSG)
public class ClubChannelHandler implements MessageHandler<ClubMsg>{

    private static final Logger LOG = Logger.getLogger(ClubChannelHandler.class);

    @Override
    public void handler(ClubMsg msg, Channel channel) {
        LOG.debug("club process" + msg.getClubName());

        channel.writeAndFlush(msg);
    }
}
