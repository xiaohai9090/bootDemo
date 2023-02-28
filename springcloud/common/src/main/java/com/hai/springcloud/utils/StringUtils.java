package com.hai.springcloud.utils;

import cn.hutool.core.lang.UUID;

/**
 * @author xiaohai
 * @date 2023/2/27 18:00
 */
public class StringUtils {

    /**
     * fastUUID 去"-"
     * @return
     */
    public static String fastSimpleUUID() {
        return UUID.fastUUID().toString(true);
    }
}
