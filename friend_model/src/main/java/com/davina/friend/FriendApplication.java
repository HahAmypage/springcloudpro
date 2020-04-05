package com.davina.friend;

import com.davina.util.JwtUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName FriendApplication
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/4/4 12:33
 * @Version 1.0
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.davina.friend.dao")
@EnableCaching
public class FriendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class,args);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
