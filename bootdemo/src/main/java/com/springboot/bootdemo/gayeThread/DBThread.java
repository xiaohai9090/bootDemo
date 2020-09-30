package com.springboot.bootdemo.gayeThread;

import com.springboot.bootdemo.domain.Something;
import com.springboot.bootdemo.msg.MessageMsg;

import java.util.concurrent.LinkedBlockingQueue;

public class DBThread extends Thread {

    private static LinkedBlockingQueue<MessageMsg> blockingQueue = new LinkedBlockingQueue<>();

    public static void pushMsg(MessageMsg msg){
        blockingQueue.add(msg);
    }

    @Override
    public void run() {
        try {
            MessageMsg msg = blockingQueue.take();
            System.out.println(msg.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
