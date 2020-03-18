package com.davina.recruit.controller;

import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.davina.recruit.pojo.Enterprise;
import com.davina.recruit.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(value = "企业模块",description = "企业模块")
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    private final Logger logger = LoggerFactory.getLogger(EnterpriseController.class);

    @ApiOperation("查询所有企业")
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",enterpriseService.findAll());
    }

    @ApiOperation("根据id查询企业")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findById(id));
    }

    @ApiOperation("添加企业")
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Enterprise enterprise){
        try {
            enterpriseService.add(enterprise);
            return new Result(true, StatusCode.OK,"添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    @ApiOperation("更新企业")
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody Enterprise enterprise){
        try {
            enterpriseService.update(enterprise);
            return new Result(true,StatusCode.OK,"更新成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"更新失败");
        }
    }

    @ApiOperation("删除企业")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id){
        try {
            enterpriseService.deleteById(id);
            return new Result(true,StatusCode.OK,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }

    @ApiOperation("多条件查询")
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.search(searchMap));
    }

    @ApiOperation("多条件分页查询")
    @RequestMapping(value = "/search/{pageNo}/{pageSize}",method = RequestMethod.POST)
    public Result findByPage(@RequestBody Map searchMap,@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        Page<Enterprise> enterprisePage = enterpriseService.findByPage(searchMap, pageNo, pageSize);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(enterprisePage.getTotalElements(),enterprisePage.getContent()));
    }

    @ApiOperation("查询热门企业")
    @RequestMapping(value = "/search/hotlist",method = RequestMethod.GET)
    public Result findIshot(){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findIshot());
    }
}
