package com.springboot.bootdemo.base.networkbase;

import com.springboot.bootdemo.msg.MessageMsg;

public interface IMsgReceiver {

    void receiveMsg(MessageMsg msg);
}
