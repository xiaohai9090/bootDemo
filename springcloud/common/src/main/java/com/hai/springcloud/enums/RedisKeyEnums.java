package com.hai.springcloud.enums;

/**
 * @author xiaohai
 * @date 2023/2/28 16:13
 */
public enum RedisKeyEnums {

    PLAYER_CACHE("player_cache:"),

    ;

    private String key;

    RedisKeyEnums(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
