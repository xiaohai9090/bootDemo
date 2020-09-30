package com.springboot.bootdemo.base.webSocket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/** 自定义mina解码器 */
public class MyPrefixedStringDecoder extends CumulativeProtocolDecoder {
    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer in, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

        int remaining_size = in.remaining();

        if (remaining_size > 4){ //前4字节是包头,看看这个消息是否合法，合法消息是前面一个4bytes的int描述后面消息的大小
            in.mark();
        }
        return false;
    }
}
