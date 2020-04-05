package com.davina.user.controller;

import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.davina.user.pojo.Admin;
import com.davina.user.pojo.User;
import com.davina.user.service.AdminService;
import com.davina.user.service.UserService;
import com.davina.util.JwtUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/23 22:39
 * @Version 1.0
 */
@Api(value = "管理员模块",description = "管理员模块")
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @ApiOperation("多条件分页查询")
    @PostMapping(value = "/{pageNo}/{pageSize}")
    public Result findAll(@RequestBody User user,@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<User> userPageInfo = new PageInfo<>(adminService.findAll(user));
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<>(userPageInfo.getTotal(),userPageInfo.getList()));
    }

    @ApiOperation("根据id查询用户")
    @GetMapping(value = "/{id}")
    public Result findOneById(@PathVariable("id")String id){
        return new Result(true,StatusCode.OK,"查询成功",adminService.findById(id));
    }

    @ApiOperation("添加用户")
    @PostMapping(value = "/{code}")
    public Result add(@RequestBody User user,@PathVariable("code")String code){
        try {
            adminService.add(user,code);
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
            adminService.update(user);
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
            adminService.delete(id);
            return new Result(true,StatusCode.OK,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }

    @ApiOperation("发送短信")
    @PostMapping(value = "/sendsms/{mobile}")
    public Result sendSMS(@PathVariable("mobile") String mobile){
        adminService.sendSMS(mobile);
        return new Result(true,StatusCode.OK,"发送短信成功");
    }

    @ApiOperation("登录")
    @PostMapping("/login/{loginname}")
    public Result login(@RequestBody Map<String,String> map){
        Admin admin = adminService.login(map);
        if (admin != null){
            // 生成token
            String token = jwtUtil.createJWT(admin.getId(),admin.getLoginname(),"admin");
            Map map1 = new HashMap();
            map1.put("token",token);
            map1.put("name",admin.getLoginname());
            return new Result(true,StatusCode.OK,"登录成功",map1);
        }
        return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
    }

    @ApiOperation("更新粉丝数")
    @PostMapping(value = "/incfans/{userid}/{x}")
    public void incFanscount(@PathVariable("userid") String userid,@PathVariable("x") int x){
        adminService.incFanscount(userid,x);
    }

    @ApiOperation("更新关注数")
    @PostMapping(value = "/incfollow/{userid}/{x}")
    public void incFollowcount(@PathVariable("userid") String userid,@PathVariable("x") int x){
        adminService.incFollowcount(userid,x);
    }
}
