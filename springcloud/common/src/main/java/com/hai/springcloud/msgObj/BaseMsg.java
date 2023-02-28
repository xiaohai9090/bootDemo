package com.hai.springcloud.msgObj;

import com.hai.springcloud.domain.BaseObject;

/**
 * @author xiaohai
 * @date 2023/2/27 21:04
 */
public class BaseMsg extends BaseObject{

    public int msgCMD = 0;// 操作指令

    public int getMsgCMD() {
        return msgCMD;
    }

    public void setMsgCMD(int msgCMD) {
        this.msgCMD = msgCMD;
    }
}
