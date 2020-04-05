package com.davina.qa;

import com.davina.util.IdWorker;
import com.davina.util.JwtUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName QaApplication
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/8 18:05
 * @Version 1.0
 */
@EnableConfigurationProperties
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.davina.qa.dao")
public class QaApplication {
    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class,args);
    }

    @Bean
    public IdWorker getIdWorker(){
        return new IdWorker();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
