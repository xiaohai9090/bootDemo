package com.hai.springcloud.Constant;

/**
 * @author xiaohai
 * @date 2023/3/1 12:28
 *
 * 消息队列路由键/主题
 */
public class MqConstants {

    /** 交换机 */
    public static final String DIRECT_EXANGE = "direct_exange:";
    public static final String TOPIC_EXANGE = "topic_exange";


    /** 路由 */
    public static final String DIRECT_ROUTING = "direct_routing:player";
    public static final String TOPIC_ROUTING = "topic_routing:player";


    /** 路由键 */
    public static final String ROUTING_KEY = "routing_key:player";
    public static final String DIRECT_ROUTING_KEY = "routing_key:direct_player";


    /** 主题 */
    public static final String TOPIC_ = "topic:name";
}
