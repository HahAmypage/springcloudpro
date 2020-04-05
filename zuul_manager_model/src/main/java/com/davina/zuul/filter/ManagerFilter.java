package com.davina.zuul.filter;

import com.davina.util.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ManagerFilter
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/4/5 11:54
 * @Version 1.0
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
    *  过滤器类型
    * @author chenyingxin
    * @date 2020/4/5 11:56
    * @return: java.lang.String
    **/
    @Override
    public String filterType() {
        // 前置过滤器
        return "pre";
    }

    /**
    *  优先级，数字越大，优先级越低
    * @author chenyingxin
    * @date 2020/4/5 11:56
    * @return: int
    **/
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
    * 过滤器开关，true 表示开启
    * @author chenyingxin
    * @date 2020/4/5 11:56
    * @return: boolean
    **/
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul过滤器");

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        String url = request.getRequestURL().toString();
        if (url.indexOf("/admin/login") > 0 || url.lastIndexOf("/v2/api-docs") > 0 || url.indexOf("/user/login") > 0){
            // 如果是登陆页，放行
            System.out.println("登陆页面"+url);
            return null;
        }

        //获取头信息
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")){
            String token = authorization.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null){
                if ("admin".equals(claims.get("roles"))){
                    requestContext.addZuulRequestHeader("Authorization",authorization);
                    System.out.println("token 验证通过，添加了头信息:"+authorization);
                    return null;
                }
            }
        }
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(401);
        requestContext.setResponseBody("无权访问");
        requestContext.getResponse().setContentType("text/html;charset=UTF-8");
        return null;
    }
}
