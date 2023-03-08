package com.springboot.bootdemo.msg;

import com.springboot.bootdemo.base.common.MsgCmdConstant;
import com.springboot.bootdemo.domain.ObjectSerializer;

/**
 * @author xiaohai
 * @date 2023/3/6 20:11
 */
public class MemberMsg extends NetMsgBase {

    private int playerIndex;

    private String name;

    public MemberMsg() {
        cmd = MsgCmdConstant.MEMBER_MSG;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
