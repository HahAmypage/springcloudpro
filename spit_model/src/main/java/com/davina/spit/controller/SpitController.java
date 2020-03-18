package com.davina.spit.controller;

import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.davina.spit.pojo.Spit;
import com.davina.spit.service.SpitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SpitController
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/17 17:58
 * @Version 1.0
 */
@Api(value = "吐槽模块",description = "吐槽模块")
@CrossOrigin
@RestController
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    private final Logger logger = LoggerFactory.getLogger(SpitController.class);

    @ApiOperation(value = "查询所有")
    @GetMapping
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findAll());
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
    }

    @ApiOperation(value = "添加吐槽")
    @PostMapping
    public Result insert(@RequestBody Spit spit){
        try {
            spitService.save(spit);
            return new Result(true,StatusCode.OK,"添加成功");
        }catch (Exception e){
            logger.error("插入失败"+e.getMessage());
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    @ApiOperation(value = "更新吐槽")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Spit spit,@PathVariable("id")String id){
        spit.setId(id);
        try {
            spitService.update(spit);
            return new Result(true,StatusCode.OK,"更新成功");
        }catch (Exception e){
            logger.error("更新失败"+e.getMessage());
            return new Result(false,StatusCode.ERROR,"更新失败");
        }
    }

    @ApiOperation(value = "删除吐槽")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable("id") String id){
        try {
            spitService.delete(id);
            return new Result(true,StatusCode.OK,"删除车成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }

    @ApiOperation(value = "根据父id分页查询吐槽")
    @GetMapping(value = "comment/{parentId}/{pageNo}/{pageSize}")
    public Result findByParentId(@PathVariable("parentId") String parentId,Integer pageNo,Integer pageSize){
        Page<Spit> spitPage = spitService.findByParentId(parentId, pageNo, pageSize);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(spitPage.getTotalElements(),spitPage.getContent()));
    }

    @ApiOperation(value = "点赞")
    @PutMapping(value = "/thumbup/{id}")
    public Result thumbup(@PathVariable("id") String id){
        try {
            //判断用户是否点过赞
            String userid = "2023";// 后边我们会修改为当前登陆的用户
            if (redisTemplate.opsForValue().get("thumbup_"+userid+"_"+id) != null){
                return new Result(false,StatusCode.ERROR,"你已经点过赞了");
            }
            spitService.updateThumbup(id);
            redisTemplate.opsForValue().set("thumbup_"+userid+"_"+id,"1");
            return new Result(true,StatusCode.OK,"点赞成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"点赞失败");
        }
    }
}
