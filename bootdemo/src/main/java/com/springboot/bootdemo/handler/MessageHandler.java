package com.springboot.bootdemo.handler;

import com.springboot.bootdemo.netty.IOSession;
import io.netty.channel.Channel;

/**
 * @author xiaohai
 * @date 2023/3/8 21:32
 */
public interface MessageHandler<T> {

    public void handler(T msg, Channel channel);
}
