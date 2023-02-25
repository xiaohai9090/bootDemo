package com.hai.springcloud.domain;

import java.util.Date;

public class Player extends BaseObject {

    /**
     * 玩家ID
     */
    private int playerIndex;

    /**
     * 玩家昵称
     */
    private String playerName;

    /**
     * 登陆时间
     */
    private Date loginTime;

    /**
     * 区域ID
     */
    private int areaId;

    /**
     * 余额
     */
    private int money;
    private int teamId;

    /**
     * 密码
     */
    private String password;

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerIndex=" + playerIndex +
                ", playerName='" + playerName + '\'' +
                ", loginTime=" + loginTime +
                ", areaId=" + areaId +
                ", money=" + money +
                ", teamId=" + teamId +
                ", password='" + password + '\'' +
                '}';
    }
}
