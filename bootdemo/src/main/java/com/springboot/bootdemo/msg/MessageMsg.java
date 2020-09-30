package com.springboot.bootdemo.msg;

public class MessageMsg {

    private int msgThreadId = 10010;
    private int playerIndex;
    private String message;

    public int getMsgThreadId() {
        return msgThreadId;
    }

    public void setMsgThreadId(int msgThreadId) {
        this.msgThreadId = msgThreadId;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
