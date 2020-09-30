package com.springboot.bootdemo.base.accepter;

import com.springboot.bootdemo.base.networkbase.IMsgReceiver;
import com.springboot.bootdemo.base.networkbase.MsgLoopBase;
import com.springboot.bootdemo.gayeThread.AsyncHandleLogicThread;
import com.springboot.bootdemo.gayeThread.DBThread;
import com.springboot.bootdemo.msg.MessageMsg;

public class MinaLoopAccepter extends MsgLoopBase {
    @Override
    public void receiveMsg(MessageMsg msg) {
        if (msg.getMsgThreadId() == 10010){
            AsyncHandleLogicThread.pushMsg(msg);
        }else {
            DBThread.pushMsg(msg);
        }
    }
}
