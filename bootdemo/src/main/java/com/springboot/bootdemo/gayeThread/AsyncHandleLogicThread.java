package com.springboot.bootdemo.gayeThread;

import com.springboot.bootdemo.msg.MessageMsg;

import java.util.concurrent.LinkedBlockingQueue;

public class AsyncHandleLogicThread extends Thread {

    private static LinkedBlockingQueue<Object> LINKED_MSG_QUEUE = new LinkedBlockingQueue<>();

    public static void pushMsg(MessageMsg messageMsg){
        LINKED_MSG_QUEUE.add(messageMsg);
    }


    @Override
    public void run() {
        while (true){
            try {
                MessageMsg messageMsg = (MessageMsg) LINKED_MSG_QUEUE.take();

                System.out.println(messageMsg.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
