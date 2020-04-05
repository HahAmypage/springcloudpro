package com.davina.friend.controller;

import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.davina.friend.service.FriendService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName FriendController
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/4/4 15:08
 * @Version 1.0
 */
@Api(value = "交友模块",description = "交友模块")
@CrossOrigin
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation("添加好友")
    @PutMapping(value = "/like/{friendId}/{type}")
    public Result addFriend(@PathVariable String friendId, @PathVariable String type){
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null){
            return new Result(false, StatusCode.ACCESSERROR,"无权访问");
        }

        // if it is like
        if ("1".equals(type)){
            if (friendService.addFriend(claims.getId(),friendId) == 0){
                return new Result(false,StatusCode.ERROR,"已经添加此好友");
            }
        }else {
            // if it don't like
            friendService.addNoFriend(claims.getId(),friendId);
        }
        return new Result(true,StatusCode.OK,"操作成功");
    }

    @ApiOperation("删除好友")
    @DeleteMapping(value = "/{friendId}")
    public Result remove(@PathVariable String friendId){
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null){
            return new Result(false,StatusCode.ACCESSERROR,"无权访问");
        }
        friendService.deleteFriend(claims.getId(),friendId);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
