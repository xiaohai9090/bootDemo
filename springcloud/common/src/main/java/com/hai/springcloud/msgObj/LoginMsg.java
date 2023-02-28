package com.hai.springcloud.msgObj;

/**
 * @author xiaohai
 * @date 2023/2/27 21:06
 */
public class LoginMsg extends BaseMsg {

    private int playerIndex;

    private String password;

    private String playerName;


    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
