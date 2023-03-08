package com.springboot.bootdemo.netty;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author xiaohai
 * @date 2023/3/8 21:33
 */
public class IOSession {

    private long sessionId;

    private ChannelHandlerContext ctx;

    private String ip;


    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
