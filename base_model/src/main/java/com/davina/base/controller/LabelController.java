package com.davina.base.controller;

import com.davina.base.pojo.Label;
import com.davina.base.service.LabelService;
import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName LabelController
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/3 20:27
 * @Version 1.0
 */
@Api(description = "标签模块")
@RestController
@CrossOrigin // 跨域处理
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
    * @author chenyingxin
    * @Description 查询所有
    * @Date 2020/3/3 20:29
    * @Param []
    * @return java.util.List<Label>
    **/
    @ApiOperation(value = "查询所有标签")
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    /**
    * @author chenyingxin
    * @Description 根据 id 查询
    * @Date 2020/3/3 20:33
    * @Param [id]
    * @return com.davina.entity.Result
    **/
    @ApiOperation(value = "根据 id 查询")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(id));
    }

    /**
    * @author chenyingxin
    * @Description 添加
    * @Date 2020/3/3 20:40
    * @Param [label]
    * @return com.davina.entity.Result
    **/
    @ApiOperation(value = "添加")
    @RequestMapping(method = RequestMethod.POST)
    public Result add(Label label){
        try {
            labelService.add(label);
            return new Result(true,StatusCode.OK,"添加成功");
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    /**
    * @author chenyingxin
    * @Description 修改
    * @Date 2020/3/3 20:41
    * @Param [label]
    * @return com.davina.entity.Result
    **/
    @ApiOperation(value = "修改")
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(Label label){
        try {
            labelService.update(label);
            return new Result(true,StatusCode.OK,"修改成功");
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"修改失败");
        }
    }

    /**
    * @author chenyingxin
    * @Description 删除
    * @Date 2020/3/3 20:42
    * @Param [id]
    * @return com.davina.entity.Result
    **/
    @ApiOperation(value = "删除")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(String id){
        try {
            labelService.delete(id);
            return new Result(true,StatusCode.OK,"删除成功");
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }

    /**
    * @author chenyingxin
    * @Description 多条件查询
    * @Date 2020/3/4 18:22
    * @Param [searchMap]
    * @return com.davina.entity.Result
    **/
    @ApiOperation(value = "多条件查询")
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findSearch(searchMap));
    }

    /**
    * @author chenyingxin
    * @Description 多条件分页查询
    * @Date 2020/3/5 17:19
    * @Param [searchMap, pageNo, pageSize]
    * @return com.davina.entity.Result
    **/
    @ApiOperation(value = "多条件分页查询")
    @RequestMapping(value = "/search/{pageNo}/{pageSize}",method = RequestMethod.POST)
    public Result findByPages(@RequestBody Map searchMap, @PathVariable int pageNo,@PathVariable int pageSize){
        Page<Label> labelPage = labelService.findByPage(searchMap, pageNo, pageSize);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(labelPage.getTotalElements(),labelPage.getContent()));
    }

}
