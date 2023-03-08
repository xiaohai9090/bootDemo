package com.springboot.bootdemo.domain;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;


/**
 * @author xiaohai
 * @date 2023/3/6 21:18
 */
public class ObjectSerializer {

    private ByteBuf buffer;

    private boolean readMode;

    public ObjectSerializer(ByteBuf buffer, boolean readMode) {
        this.buffer = buffer;
        this.readMode = readMode;
    }



    public int sInt(Integer value) {
        if (readMode) {
            value = buffer.readInt();
        } else {
            buffer.writeInt(value);
        }
        return value;
    }


    public String sString(String value) {
        if (readMode) {
//            buffer.readBytes()
//            buffer.read
        }
        buffer.writeCharSequence(value, Charset.forName("UTF-8"));
        buffer.writeBytes(value.getBytes());
        return value;
    }


    public static CharsetDecoder getCharsetDecoder() throws Exception {
        Charset charset = Charset.forName("UTF-8");
        return charset.newDecoder();
    }
}
