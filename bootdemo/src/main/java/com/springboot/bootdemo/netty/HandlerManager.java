package com.springboot.bootdemo.netty;

import com.springboot.bootdemo.base.common.DefaultMsgHandler;
import com.springboot.bootdemo.handler.MessageHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaohai
 * @date 2023/3/8 21:12
 */
public class HandlerManager {

    private static final Logger LOG = Logger.getLogger(HandlerManager.class);


    private static Map<Integer, MessageHandler> defaultHandler = new HashMap<>();

    public static void initDefaultHandler() {
        try {

            // 反射加载handler包下的带有DefaultMsgHandler注解的类
            Reflections reflections = new Reflections("com.springboot.bootdemo.handler");
            Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(DefaultMsgHandler.class);
            for (Class clazz : typesAnnotatedWith) {
                DefaultMsgHandler defaultMsgHandler = (DefaultMsgHandler) clazz.getAnnotation(DefaultMsgHandler.class);
                int cmd = defaultMsgHandler.cmd();
                if (defaultHandler.containsKey(cmd)) {
                    LOG.error("重复的协议ID cmd = " + cmd);
                    break;
                }
                MessageHandler instance = (MessageHandler) clazz.newInstance();
                defaultHandler.put(cmd, instance);
            }
        } catch (Exception e) {
            LOG.error("初始化handler失败", e);
        }
    }


    public static MessageHandler getDefaultHandler(int cmd) {
        return defaultHandler.get(cmd);
    }
}
