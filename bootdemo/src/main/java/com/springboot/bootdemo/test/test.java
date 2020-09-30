package com.springboot.bootdemo.test;

import com.springboot.bootdemo.domain.BaseObject;
import com.springboot.bootdemo.domain.Student;
import com.springboot.bootdemo.test.ExtendClass.FatherLei;
import com.springboot.bootdemo.test.ExtendClass.SonLei;
import org.apache.mina.core.buffer.IoBuffer;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.LinkedBlockingQueue;

public class test {

    public static void main(String[] args) throws IOException {

        BaseObject baseObject = new BaseObject();
        System.out.println(baseObject.toString());

        // 1. 获取数据源 和 目标传输地的输入输出流（此处以数据源 = 文件为例）
//        FileInputStream fin = new FileInputStream("C:\\Users\\Administrator\\Desktop\\aa.txt");
//        FileOutputStream fout = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\bbb.txt");

        // 2. 获取数据源的输入输出通道
//        FileChannel fcin = fin.getChannel();
//        FileChannel fcout = fout.getChannel();

    }

    private void ioDemo() throws IOException {
        // 1. 获取数据源 和 目标传输地的输入输出流（此处以数据源 = 文件为例）
        FileInputStream fin = new FileInputStream("C:\\Users\\Administrator\\Desktop\\aa.txt");
        FileOutputStream fout = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\bbb.txt");

        // 2. 获取数据源的输入输出通道
        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        // 3. 创建 缓冲区 对象：Buffer（共有2种方法）
        // 方法1：使用allocate()静态方法
        ByteBuffer buff = ByteBuffer.allocate(256);
        // 上述方法创建1个容量为256字节的ByteBuffer
        // 注：若发现创建的缓冲区容量太小，则重新创建一个大小合适的缓冲区

        // 方法2：通过包装一个已有的数组来创建
        // 注：通过包装的方法创建的缓冲区保留了被包装数组内保存的数据

        // 额外：若需将1个字符串存入ByteBuffer，则如下
        String sendString="你好,服务器. ";
        ByteBuffer sendBuff = ByteBuffer.wrap(sendString.getBytes("UTF-16"));

//        // 4. 从通道读取数据 & 写入到缓冲区
//        // 注：若 以读取到该通道数据的末尾，则返回-1
//        fcin.read(buff);
//
//        // 5. 传出数据准备：将缓存区的写模式 转换->> 读模式
//        buff.flip();
//
//        // 6. 从 Buffer 中读取数据 & 传出数据到通道
//        fcout.write(buff);
//
//        // 7. 重置缓冲区
//        // 目的：重用现在的缓冲区,即 不必为了每次读写都创建新的缓冲区，在再次读取之前要重置缓冲区
//        // 注：不会改变缓冲区的数据，只是重置缓冲区的主要索引值
//        buff.clear();

        while (true) {

            // 4. 从通道读取数据 & 写入到缓冲区
            // 注：若 以读取到该通道数据的末尾，则返回-1
            int r = fcin.read(sendBuff);
            if (r == -1) {
                break;
            }
            // 5. 传出数据准备：调用flip()方法
            sendBuff.flip();

            // 6. 从 Buffer 中读取数据 & 传出数据到通道
            fcout.write(sendBuff);

            // 7. 重置缓冲区
            sendBuff.clear();

        }
    }

    private static void blockThread(){
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue();
        blockingQueue.add(23);
        blockingQueue.add(56);
        blockingQueue.add(124);
        blockingQueue.add(46);
        blockingQueue.add(6797);
        blockingQueue.add(345);
        blockingQueue.add(234);
        blockingQueue.add(656);
        blockingQueue.add(345);


        while (true){
            try {
                int a = blockingQueue.take();
                System.out.println(a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void run(){
        Student student = new Student();
        student.setName("name1");
        student.setId(22);
        Student stu = new Student();
        BeanUtils.copyProperties(student,stu);
        stu.setName("name2");
        System.out.println(stu.getName());
        System.out.println(student.getName());
    }

    private void timeRun(){
        int i = 499 % 500;
        System.out.println(i);

        long a = System.nanoTime();
        run();
        long b = System.nanoTime();
        System.out.println(b-a);
    }
}
