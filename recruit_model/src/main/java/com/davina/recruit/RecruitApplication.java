package com.davina.recruit;

import com.davina.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName BaseApplication
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/3 19:48
 * @Version 1.0
 */
@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class RecruitApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class);
    }

    @Bean
    public IdWorker getId(){
        return new IdWorker(1,1);
    }
}
