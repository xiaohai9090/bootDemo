package com.springboot.bootdemo.base.common;

import com.springboot.bootdemo.msg.NetMsgBase;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaohai
 * @date 2023/3/7 18:03
 */
public class ReflectASMObjectManager {

    private static Map<Integer, Class> MSG_CLASS_MAP = new HashMap<>();

    /**
     * 注册所有消息构造器访问对象
     */
    public static void registerAllMsgConstructorAccess(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends NetMsgBase>> classes = reflections.getSubTypesOf(NetMsgBase.class);

        for (Class clazz : classes) {
//            ConstructorAccess<NetMsgBase> constructorAccess = getConstructorAccess(clazz);
            try {
                NetMsgBase instance = (NetMsgBase)clazz.getConstructor().newInstance();
                MSG_CLASS_MAP.put(instance.cmd, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static Class getMsgClass(int msgCmd) {
        Class clazz = MSG_CLASS_MAP.get(msgCmd);
        if (clazz == null) {
            return null;
        }
        return clazz;
    }

}
