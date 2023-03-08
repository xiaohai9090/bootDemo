package com.hai.springcloud.config;

import com.hai.springcloud.Constant.MqConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaohai
 * @date 2023/3/1 13:00
 */
@Configuration
public class RabbitMqConfig {

    /****直连交换机*****************************/
    @Bean(MqConstants.DIRECT_ROUTING)
    public Queue directQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // return new Queue("TestDirectQueue",true,true,false);

        return new Queue(MqConstants.DIRECT_ROUTING);
    }

    @Bean(MqConstants.DIRECT_EXANGE)
    DirectExchange directExchange() {
        return new DirectExchange(MqConstants.DIRECT_EXANGE);
    }

    @Bean
    Binding bindDirectExange() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(MqConstants.DIRECT_ROUTING_KEY);
    }



    /****主题交换机*****************************/
    @Bean(MqConstants.TOPIC_ROUTING)
    public Queue playerQueue() {
        return new Queue(MqConstants.TOPIC_ROUTING);
    }


    public Queue secondQueue() {
        return null;
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(MqConstants.TOPIC_EXANGE);
    }

    @Bean
    Binding bindingExchange() {
        return BindingBuilder.bind(playerQueue()).to(exchange()).with(MqConstants.ROUTING_KEY);
    }


}
