package com.springboot.bootdemo.base.webSocket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/** mina编码 */
public class MyPrefixedStringEncoder extends ProtocolEncoderAdapter {

    private Charset charset;

    public MyPrefixedStringEncoder(Charset charset){
        this.charset = charset;
    }
    @Override
    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        String value = message.toString();

        //定义缓冲区容量
        IoBuffer ioBuffer = IoBuffer.allocate(value.length()).setAutoExpand(true);
        // 写入缓冲区
        ioBuffer.putPrefixedString(value,4,charset.newEncoder());

        ioBuffer.flip();//转换为读模式
        protocolEncoderOutput.write(ioBuffer);
    }
}
