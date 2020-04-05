package com.davina.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName UserClient
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/4/4 17:35
 * @Version 1.0
 */
@FeignClient("user-model")
public interface UserClient {

    /**
    *  更新粉丝数
    * @author chenyingxin
    * @date 2020/4/4 17:38
    * @param userid:
    * @param x:
    * @return: void
    **/
    @PostMapping(value = "/user/incfans/{userid}/{x}")
    void incFanscount(@PathVariable("userid") String userid,@PathVariable("x") int x);

    /**
    *  更新关注数
    * @author chenyingxin
    * @date 2020/4/4 17:38
    * @param userid:
    * @param x:
    * @return: void
    **/
    @PostMapping(value = "/user/incfollow/{userid}/{x}")
    void incFollowcount(@PathVariable("userid") String userid,@PathVariable("x") int x);
}
