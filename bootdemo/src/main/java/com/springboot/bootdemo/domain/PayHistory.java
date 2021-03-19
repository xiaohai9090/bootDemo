package com.springboot.bootdemo.domain;

import java.util.Date;

/**
 * @author: xiaohai
 * @date: 2021/1/11 17:47
 * @name: PayHistory.class
 */
public class PayHistory {
    private String payHistoryId;
    /**
     * 玩家编号
     */
    private String playerId;
    /**
     * 代充玩家index（这个玩家给当前玩家充值）DAI_CHONG_PLAYER_INDEX
     */
    private int daiChongPlayerIndex = 0;
    /**
     * 数量
     */
    private Integer amount = 0;
    /**
     * 订单号 平台方订单号
     */
    private String orderNo;
    /**
     * 充值时间
     */
    private Date payTime;
    /**
     * 标识充值入口（0 系统赠送  1 移动平台  2 ）
     */
    private Integer flagAccess = 2;
    private int state = 0;
    private int buyItemID = 3001;
    /**
     * 购买类型（1=钻石 2=房卡）
     */
    private int buyItemType = 1;
    /**
     * 交易ID，针对IOS内支付
     */
    private String transaction_id;

    /**
     * 玩家index
     */
    private int playerIndex = 0;




    public String getPayHistoryId() {
        return payHistoryId;
    }

    public void setPayHistoryId(String payHistoryId) {
        this.payHistoryId = payHistoryId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getDaiChongPlayerIndex() {
        return daiChongPlayerIndex;
    }

    public void setDaiChongPlayerIndex(int daiChongPlayerIndex) {
        this.daiChongPlayerIndex = daiChongPlayerIndex;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getFlagAccess() {
        return flagAccess;
    }

    public void setFlagAccess(Integer flagAccess) {
        this.flagAccess = flagAccess;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBuyItemID() {
        return buyItemID;
    }

    public void setBuyItemID(int buyItemID) {
        this.buyItemID = buyItemID;
    }

    public int getBuyItemType() {
        return buyItemType;
    }

    public void setBuyItemType(int buyItemType) {
        this.buyItemType = buyItemType;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
}