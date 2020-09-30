package com.springboot.bootdemo.base.webSocket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/** 实现mina的解码，编码协议 */
public class MyPrefixedStringCodecFactory implements ProtocolCodecFactory {

    private final MyPrefixedStringEncoder encoder;

    private final MyPrefixedStringDecoder decoder;

    public MyPrefixedStringCodecFactory(Charset charset){
        encoder = new MyPrefixedStringEncoder(charset);
        decoder = new MyPrefixedStringDecoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return null;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return null;
    }

    public MyPrefixedStringDecoder getDecoder() {
        return decoder;
    }

    public MyPrefixedStringEncoder getEncoder() {
        return encoder;
    }

}
