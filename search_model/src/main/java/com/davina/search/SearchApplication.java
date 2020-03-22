package com.davina.search;

import com.davina.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SearchApplication
 * @Description 搜索微服务
 * @Author Davina Chan
 * @Date 2020/3/22 9:46
 * @Version 1.0
 */
@EnableSwagger2
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }

    @Bean
    public IdWorker getId(){
        return new IdWorker(1,1);
    }
}
