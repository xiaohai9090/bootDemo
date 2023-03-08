package com.springboot.bootdemo.msg;

import com.springboot.bootdemo.domain.ObjectSerializer;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author xiaohai
 * @date 2023/3/5 15:49
 */
public class NetMsgBase implements Serializable {

    public int cmd = 0;

}
