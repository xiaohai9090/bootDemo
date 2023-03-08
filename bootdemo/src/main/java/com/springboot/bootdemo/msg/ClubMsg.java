package com.springboot.bootdemo.msg;

import com.springboot.bootdemo.domain.ObjectSerializer;
import com.springboot.bootdemo.base.common.MsgCmdConstant;

/**
 * @author xiaohai
 * @date 2023/3/6 20:15
 */
public class ClubMsg extends NetMsgBase{

    private int clubId;

    private String clubName;

    public ClubMsg() {
        cmd = MsgCmdConstant.CLUB_MSG;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
