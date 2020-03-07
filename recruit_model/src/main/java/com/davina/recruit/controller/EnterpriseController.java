package com.davina.recruit.controller;

import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.davina.recruit.pojo.Enterprise;
import com.davina.recruit.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sun.net.ProgressSource;

import java.util.List;
import java.util.Map;

/**
 * @ClassName EnterpriseController
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/5 17:56
 * @Version 1.0
 */
@Api("企业模块")
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    /**
    * @author chenyingxin
    * @Description 查询所有企业
    * @Date 2020/3/5 18:05
    * @Param []
    * @return com.davina.entity.Result
    **/
    @ApiOperation("查询所有企业")
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",enterpriseService.findAll());
    }

    /**
    * @author chenyingxin
    * @Description 根据id查询企业
    * @Date 2020/3/5 18:05
    * @Param [id]
    * @return com.davina.entity.Result
    **/
    @ApiOperation("根据id查询企业")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findById(id));
    }

    /**
    * @author chenyingxin
    * @Description 添加企业
    * @Date 2020/3/5 18:07
    * @Param [enterprise]
    * @return com.davina.entity.Result
    **/
    @ApiOperation("添加企业")
    @RequestMapping(method = RequestMethod.POST)
    public Result add(Enterprise enterprise){
        try {
            enterpriseService.add(enterprise);
            return new Result(true, StatusCode.OK,"添加成功");
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    /**
    * @author chenyingxin
    * @Description 更新企业
    * @Date 2020/3/5 18:08
    * @Param [enterprise]
    * @return com.davina.entity.Result
    **/
    @ApiOperation("更新企业")
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(Enterprise enterprise){
        try {
            enterpriseService.update(enterprise);
            return new Result(true,StatusCode.OK,"更新成功");
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"更新失败");
        }
    }

    /**
    * @author chenyingxin
    * @Description 删除企业
    * @Date 2020/3/5 18:13
    * @Param [id]
    * @return com.davina.entity.Result
    **/
    @ApiOperation("删除企业")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(String id){
        try {
            enterpriseService.deleteById(id);
            return new Result(true,StatusCode.OK,"删除成功");
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }

    /**
    * @author chenyingxin
    * @Description 多条件查询
    * @Date 2020/3/5 18:13
    * @Param [searchMap]
    * @return com.davina.entity.Result
    **/
    @ApiOperation("多条件查询")
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.search(searchMap));
    }

    /**
    * @author chenyingxin
    * @Description 多条件分页查询
    * @Date 2020/3/5 18:14
    * @Param [searchMap, pageNo, pageSize]
    * @return com.davina.entity.Result
    **/
    @ApiOperation("多条件分页查询")
    @RequestMapping(value = "/search/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public Result findByPage(@RequestBody Map searchMap,int pageNo,int pageSize){
        Page<Enterprise> enterprisePage = enterpriseService.findByPage(searchMap, pageNo, pageSize);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Enterprise>(enterprisePage.getTotalElements(),enterprisePage.getContent()));
    }
}
