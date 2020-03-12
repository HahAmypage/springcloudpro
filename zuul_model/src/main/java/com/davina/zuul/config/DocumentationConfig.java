package com.davina.zuul.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DocumentationConfig
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/8 11:26
 * @Version 1.0
 */
@Configuration
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    /**
     * Retrieves an instance of the appropriate type. The returned object may or may not be a new
     * instance, depending on the implementation.
     *
     * @return an instance of the appropriate type
     */
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("基础服务", "/base/v2/api-docs", "2.0"));
        resources.add(swaggerResource("招聘服务", "/recruit/v2/api-docs", "2.0"));
        resources.add(swaggerResource("问答服务", "/qa/v2/api-docs", "2.0"));
        resources.add(swaggerResource("文章服务", "/article/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
