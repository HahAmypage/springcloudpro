package com.davina.gathering.controller;

import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.davina.gathering.pojo.GatheringWithBLOBs;
import com.davina.gathering.service.GatheringService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName GatheringController
 * @Description 活动模块
 * @Author Davina Chan
 * @Date 2020/3/15 18:08
 * @Version 1.0
 */
@Api(value = "活动模块",description = "活动模块")
@CrossOrigin
@RestController
@RequestMapping("/gathering")
public class GatheringController {

    @Autowired
    private GatheringService gatheringService;

    private final Logger logger = LoggerFactory.getLogger(GatheringController.class);

    @ApiOperation(value = "查询多条件查询")
    @PostMapping(value = "/search/{pageNo}/{pageSize}")
    public Result findAll(@RequestBody GatheringWithBLOBs gatheringWithBLOBs, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        Page<GatheringWithBLOBs> gatheringPage = (Page<GatheringWithBLOBs>) gatheringService.findAll(gatheringWithBLOBs);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<>(gatheringPage.getTotal(),gatheringPage.getResult()));
    }

    @ApiOperation(value = "根据id查活动")
    @GetMapping(value = "/{id}")
    public Result findById(String id){
        return new Result(true, StatusCode.OK,"查询成功",gatheringService.findById(id));
    }


    @ApiOperation(value = "添加活动")
    @PostMapping
    public Result add(@RequestBody GatheringWithBLOBs gatheringWithBLOBs){
        try {
            gatheringService.insert(gatheringWithBLOBs);
            return new Result(true,StatusCode.OK,"添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    @ApiOperation(value = "更新活动")
    @PutMapping
    public Result update(@RequestBody GatheringWithBLOBs gatheringWithBLOBs){
        try {
            gatheringService.update(gatheringWithBLOBs);
            return new Result(true,StatusCode.OK,"更新成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(true,StatusCode.ERROR,"更新失败");
        }
    }

    @ApiOperation(value = "删除活动")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable("id") String id){
        try {
            gatheringService.delete(id);
            return new Result(true,StatusCode.OK,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }
}
