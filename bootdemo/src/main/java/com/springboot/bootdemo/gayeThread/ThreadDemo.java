package com.springboot.bootdemo.gayeThread;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xiaohai
 * @date 2023/2/28 13:46
 */
public class ThreadDemo {

    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    String str = queue.take();

                    if ("1".equals(str)) {
                        Thread.sleep(3000);
                    }

                    System.out.println(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        try {
            for (int i = 0; i < 10; i++) {
                if (i == 2) {
                    queue.put("1");
                } else {
                    queue.put("aaaaaaa");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
