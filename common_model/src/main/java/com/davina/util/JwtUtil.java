package com.davina.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/29 17:59
 * @Version 1.0
 */
@Component
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    private String key;

    private Long ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    /**
    *  生成 JWT
    * @author chenyingxin
    * @date 2020/3/29 18:01
    * @param id: 
    * @param subject: 
    * @param roles: 
    * @return: java.lang.String
    **/ 
    public String createJWT(String id,String subject,String roles){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id).setSubject(subject).setIssuedAt(now).signWith(SignatureAlgorithm.HS256,key).claim("roles",roles);
        if (ttl > 0){
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
    *  解析JWT
    * @author chenyingxin
    * @date 2020/3/29 18:06
    * @param jwtStr:
    * @return: io.jsonwebtoken.Claims
    **/
    public Claims parseJWT(String jwtStr){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwtStr).getBody();
    }
}
