package com.davina.base.controller;

import com.davina.base.pojo.Label;
import com.davina.base.service.LabelService;
import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(value = "标签模块",description = "标签模块")
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    private static final Logger log = LoggerFactory.getLogger(LabelController.class);

    /**
    *  查询所有
    * @author chenyingxin
    * @date 2020/3/11 21:50
    * @return: com.davina.entity.Result
    **/
    @ApiOperation(value = "查询所有标签")
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    /**
    *  根据 id 查询
    * @author chenyingxin
    * @date 2020/3/11 21:50
    * @param id: 标签id
    * @return: com.davina.entity.Result
    **/
    @ApiOperation(value = "根据 id 查询")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(id));
    }

    /**
    *  添加
    * @author chenyingxin
    * @date 2020/3/11 21:50
    * @param label: 标签对象
    * @return: com.davina.entity.Result
    **/
    @ApiOperation(value = "添加")
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label){
        try {
            labelService.add(label);
            return new Result(true,StatusCode.OK,"添加成功");
        }catch (Exception e){
            log.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    /**
    *  修改
    * @author chenyingxin
    * @date 2020/3/12 17:36
    * @param label:
    * @return: com.davina.entity.Result
    **/
    @ApiOperation(value = "修改")
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody Label label){
        try {
            labelService.update(label);
            return new Result(true,StatusCode.OK,"修改成功");
        }catch (Exception e){
            log.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"修改失败:"+e.getMessage());
        }
    }

    /**
    *  删除
    * @author chenyingxin
    * @date 2020/3/12 17:36
    * @param id:
    * @return: com.davina.entity.Result
    **/
    @ApiOperation(value = "删除")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id){
        try {
            labelService.delete(id);
            return new Result(true,StatusCode.OK,"删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"删除失败:"+e.getMessage());
        }
    }

    /**
    *  多条件查询
    * @author chenyingxin
    * @date 2020/3/12 17:37
    * @param searchMap:
    * @return: com.davina.entity.Result
    **/
    @ApiOperation(value = "多条件查询")
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findSearch(searchMap));
    }

    /**
    *  多条件分页查询
    * @author chenyingxin
    * @date 2020/3/12 17:37
    * @param searchMap:
    * @param pageNo:
    * @param pageSize:
    * @return: com.davina.entity.Result
    **/
    @ApiOperation(value = "多条件分页查询")
    @RequestMapping(value = "/search/{pageNo}/{pageSize}",method = RequestMethod.POST)
    public Result findByPages(@RequestBody Map searchMap, @PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize){
        Page<Label> labelPage = labelService.findByPage(searchMap, pageNo, pageSize);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(labelPage.getTotalElements(),labelPage.getContent()));
    }

}
