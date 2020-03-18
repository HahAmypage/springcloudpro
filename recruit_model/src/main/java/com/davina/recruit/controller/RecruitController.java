package com.davina.recruit.controller;

import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.davina.recruit.pojo.Recruit;
import com.davina.recruit.service.RecruitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName RecruitController
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/8 14:01
 * @Version 1.0
 */
@Api(value = "招聘模块",description = "招聘模块")
@RestController
@CrossOrigin
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    private final Logger logger = LoggerFactory.getLogger(RecruitController.class);

    @ApiOperation("查询所有招聘信息")
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",recruitService.findAll());

    }

    @ApiOperation("根据id查询招聘信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询成功",recruitService.findById(id));
    }

    @ApiOperation("添加招聘信息")
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Recruit recruit){
        try {
            recruitService.add(recruit);
            return new Result(true,StatusCode.OK,"添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    @ApiOperation("修改招聘信息")
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody Recruit recruit){
        try {
            recruitService.update(recruit);
            return new Result(true,StatusCode.OK,"修改成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.OK,"修改失败");
        }
    }

    @ApiOperation("删除招聘信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id){
        try {
            recruitService.deleteById(id);
            return new Result(true,StatusCode.OK,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }

    @ApiOperation("多条件查询")
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result search(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",recruitService.search(searchMap));
    }

    @ApiOperation("多条件分页查询")
    @RequestMapping(value = "/{pageNo}/{pageSize}",method = RequestMethod.POST)
    public Result findByPage(@RequestBody Map searchMap,@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        Page<Recruit> recruitPage = recruitService.findByPage(searchMap, pageNo, pageSize);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(recruitPage.getTotalElements(),recruitPage.getContent()));
    }

    @ApiOperation("查询推荐职位")
    @RequestMapping(value = "/search/recommend",method = RequestMethod.GET)
    public Result findRecommend(){
        return new Result(true,StatusCode.OK,"查询成功",recruitService.findRecommend("2"));
    }

    @ApiOperation("查询最新职位")
    @RequestMapping(value = "/search/newlist",method = RequestMethod.GET)
    public Result findNewlist(){
        return new Result(true,StatusCode.OK,"查询成功",recruitService.findNewList());
    }
}
