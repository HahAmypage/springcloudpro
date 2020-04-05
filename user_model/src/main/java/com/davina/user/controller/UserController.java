package com.davina.user.controller;

import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.davina.user.pojo.User;
import com.davina.user.service.UserService;
import com.davina.util.JwtUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/23 22:39
 * @Version 1.0
 */
@Api(value = "用户模块",description = "用户模块")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation("多条件分页查询")
    @PostMapping(value = "/{pageNo}/{pageSize}")
    public Result findAll(@RequestBody User user,@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<User> userPageInfo = new PageInfo<>(userService.findAll(user));
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<>(userPageInfo.getTotal(),userPageInfo.getList()));
    }

    @ApiOperation("根据id查询用户")
    @GetMapping(value = "/{id}")
    public Result findOneById(@PathVariable("id")String id){
        return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
    }

    @ApiOperation("添加用户")
    @PostMapping(value = "/{code}")
    public Result add(@RequestBody User user,@PathVariable("code")String code){
        try {
            userService.add(user,code);
            return new Result(true,StatusCode.OK,"添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    @ApiOperation("更新用户")
    @PutMapping
    public Result update(@RequestBody User user){
        try {
            userService.update(user);
            return new Result(true,StatusCode.OK,"更改失败");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"更新失败");
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable("id") String id){
        try {
//            // 获取头
//            Claims claims = (Claims) request.getAttribute("admin_claims");
//            if (claims == null){
//                return new Result(false,StatusCode.ACCESSERROR,"无权限访问");
//            }
            userService.delete(id);
            return new Result(true,StatusCode.OK,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }

    @ApiOperation("发送短信")
    @PostMapping(value = "/sendsms/{mobile}")
    public Result sendSMS(@PathVariable("mobile") String mobile){
        userService.sendSMS(mobile);
        return new Result(true,StatusCode.OK,"发送短信成功");
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> map){
        User user = userService.login(map);
        if (user != null){
            // 生成token
            String token = jwtUtil.createJWT(user.getId(),user.getLoginname(),"user");
            Map map1 = new HashMap();
            map1.put("token",token);
            map1.put("name",user.getLoginname());
            return new Result(true,StatusCode.OK,"登录成功",map1);
        }
        return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
    }

    @ApiOperation("更新粉丝数")
    @PostMapping(value = "/incfans/{userid}/{x}")
    public void incFanscount(@PathVariable("userid") String userid,@PathVariable("x") int x){
        userService.incFanscount(userid,x);
    }

    @ApiOperation("更新关注数")
    @PostMapping(value = "/incfollow/{userid}/{x}")
    public void incFollowcount(@PathVariable("userid") String userid,@PathVariable("x") int x){
        userService.incFollowcount(userid,x);
    }
}
