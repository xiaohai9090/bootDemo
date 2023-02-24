package com.springboot.bootdemo.base;

import com.springboot.bootdemo.base.networkbase.IMsgReceiver;
import com.springboot.bootdemo.msg.MessageMsg;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class GameSocketHandle extends IoHandlerAdapter {

    private IMsgReceiver msgRec;

    //发生异常回调，可打印异常
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }


    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String string = message.toString();
        // 收到客户端消息为quit时，关闭该会话
        if (string.trim().equalsIgnoreCase("quit")){
            session.closeNow();
            return;
        }
        //向客户端发送消息，会调用messageSent
//        session.write("回复消息：" + message);
        System.out.println("messageReceived");
//        if (msgRec != null) {
//            msgRec.receiveMsg((MessageMsg) message);
//        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("sessionIdle");
    }

    public void setMsgRec(IMsgReceiver msgRec) {
        this.msgRec = msgRec;
    }
}
