package com.davina.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WebFilter
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/4/5 11:19
 * @Version 1.0
 */
@Component
public class WebFilter extends ZuulFilter {
    /**
    *  过滤器类型：
             pre ：可以在请求路由之前被调用
             route ：在路由请求时候被调用
             post ：在 route 和 error 过滤器之后被调用
             error ：处理请求时发生错误时被调用
    * @author chenyingxin
    * @date 2020/4/5 11:43
    * @return: java.lang.String
    **/ 
    @Override
    public String filterType() {
        // 前置过滤器
        return "pre";
    }

    /**
    *  过滤器执行顺序
            数值越大，优先级越低
    * @author chenyingxin
    * @date 2020/4/5 11:43
    * @return: int
    **/
    @Override
    public int filterOrder() {
        // 优先级为 0，数字越大，优先级越低
        return 0;
    }

    /**
    *  是否执行过滤方法run
    * @author chenyingxin
    * @date 2020/4/5 11:45
    * @return: boolean
    **/
    @Override
    public boolean shouldFilter() {
        // 是否执行该过滤器，此处为 true，说明需要过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul 过滤器...");

        //向 header 中添加鉴权令牌
        RequestContext requestContext = RequestContext.getCurrentContext();

        //获取 header
        HttpServletRequest request = requestContext.getRequest();
        String authorization = request.getHeader("Authorization");
        if (authorization != null){
            requestContext.addZuulRequestHeader("Authorization",authorization);
        }

        // 返回null代表放行
        return null;
    }
}
