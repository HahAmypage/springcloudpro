package com.davina.friend.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/3 20:55
 * @Version 1.0
 */
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {

    /** 
    * @author chenyingxin
    * @Description    
            创建API应用
            apiInfo() 增加API相关信息
            通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
            本例采用指定扫描的包路径来定义指定要建立API的目录。
    * @Date 2020/3/3 21:02
    * @Param []
    * @return springfox.documentation.spring.web.plugins.Docket
    **/
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.davina.friend.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /** 
    * @author chenyingxin
    * @Description
            创建该API的基本信息（这些基本信息会展现在文档页面中）
            访问地址：http://ip:port/swagger-ui.html
    * @Date 2020/3/3 21:03
    * @Param []
    * @return springfox.documentation.service.ApiInfo
    **/        
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("交友服务")
                .description("交友服务接口")
                .contact(new Contact("davina","","ddddd@qq.com"))
                // 版本
                .version("1.0")
                .build();
    }

}
