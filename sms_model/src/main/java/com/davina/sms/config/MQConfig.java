package com.davina.sms.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MQConfig
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/26 20:30
 * @Version 1.0
 */
@Configuration
public class MQConfig {

    public static final String MIAOSHA_QUEUE = "sms";

    @Bean
    public Queue miaoShaQueue(){
        return new Queue(MIAOSHA_QUEUE,true);
    }

}
