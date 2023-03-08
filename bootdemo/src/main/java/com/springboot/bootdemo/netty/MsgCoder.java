package com.springboot.bootdemo.netty;

import com.alibaba.fastjson.JSON;
import com.springboot.bootdemo.base.common.NetConstant;
import com.springboot.bootdemo.base.common.ReflectASMObjectManager;
import com.springboot.bootdemo.msg.NetMsgBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author xiaohai
 * @date 2023/3/5 12:52
 */
public class MsgCoder extends ByteToMessageCodec<NetMsgBase> {

    private static final Logger LOG = Logger.getLogger(MsgCoder.class);


    @Override
    public void encode(ChannelHandlerContext channelHandlerContext, NetMsgBase netMsgBase, ByteBuf out) throws Exception {
        out.writeBytes(NetConstant.MSG_HEADER_FLAG.getBytes());  // 魔数8个字节
        out.writeInt(1002);   // 服务器ID  4个字节
        out.writeInt(netMsgBase.cmd);  // 指令的类型 4个字节
//        out.writeByte();   // 请求序号


        // 长度
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(bos);
//        oos.writeObject(netMsgBase);
        String data = JSON.toJSONString(netMsgBase);
        byte[] bytes = data.getBytes();
        out.writeInt(bytes.length);

        // 写入数据
        out.writeBytes(bytes);
        // 序列化
//        ObjectSerializer objectSerializer = new ObjectSerializer(out, false);
//        netMsgBase.serialize(objectSerializer);

    }

    @Override
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        byte[] magicByte = new byte[8];
        in.readBytes(magicByte, 0, 8);    // 读取魔数的8个字节
        String magic = new String(magicByte);  // 字节数组转字符串 魔数

        if (!magic.equals(NetConstant.MSG_HEADER_FLAG)) {
            // 魔数不对
            return;
        }

        int serverId = in.readInt();  // 服务器ID 1002
        int msgCmd = in.readInt();  // 协议号
//        byte version = in.readByte();  // 版本号
//        int serializerType = in.readInt();  // 序列化类型
//        int cmd = in.readInt();  // 指令
//        byte sequenceId = in.readByte();  // 请求序号
//        in.readByte();// 无意义
        int dataSize = in.readInt();// 获取长度
        byte[] data = new byte[dataSize];
        in.readBytes(data, 0, dataSize);

//        NetMsgBase msg = JSON.parseObject(new String(data), NetMsgBase.class);
        Class msgClass = ReflectASMObjectManager.getMsgClass(msgCmd);
        NetMsgBase msg = (NetMsgBase) JSON.parseObject(new String(data), msgClass);
//        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
//        NetMsgBase msg = (NetMsgBase) ois.readObject();
//        ObjectSerializer objectSerializer = new ObjectSerializer(in, true);
//        NetMsgBase msg = new NetMsgBase();
//        msg.serialize(objectSerializer);

        list.add(msg);  // 为了给下一个handler用
    }
}
