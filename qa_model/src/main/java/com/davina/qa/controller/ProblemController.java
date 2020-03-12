package com.davina.qa.controller;

import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.davina.qa.client.LabelClient;
import com.davina.qa.pojo.Problem;
import com.davina.qa.service.ProblemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ProblemController
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/8 18:25
 * @Version 1.0
 */
@Api(value = "问题模块",description = "问题模块")
@CrossOrigin
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private LabelClient labelClient;

    private final Logger logger = LoggerFactory.getLogger(ProblemController.class);

    @ApiOperation(value = "根据标签查询问题")
    @GetMapping(value = "/{labelId}")
    public Result findAllByLabelId(@PathVariable("labelId") String labelId){
        return new Result(true, StatusCode.OK,"查询成功",problemService.findAllByLabelId(labelId));
    }

    @ApiOperation("根据id查询问题")
    @GetMapping(value = "/findOne/{id}")
    public Result findById(String id){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
    }

    @ApiOperation("添加问题")
    @PostMapping(value = "/{labelId}")
    public Result add(@RequestBody Problem problem,@PathVariable("labelId") String labelId){
        try {
            problemService.add(problem,labelId);
            return new Result(true,StatusCode.OK,"添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    @ApiOperation("修改问题")
    @PutMapping(value = "/{labelId}")
    public Result update(@RequestBody Problem problem,@PathVariable("labelId") String labelId){
        try {
            problemService.update(problem);
            return new Result(true,StatusCode.OK,"修改成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"修改失败");
        }
    }

    @ApiOperation("删除问题")
    @DeleteMapping(value = "/{labelId}/{problemId}")
    public Result delete(@PathVariable("problemId") String problemId,@PathVariable("labelId") String labelId){
        try {
            problemService.delete(problemId,labelId);
            return new Result(true,StatusCode.OK,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }

    @ApiOperation("多条件分页查询")
    @PostMapping(value = "/{pageNo}/{pageSzie}")
    public Result searchByLabelid(@RequestBody Map searchMap,@PathVariable("pageNo") int pageNo,@PathVariable("pageSzie") int pageSize){
        PageHelper.startPage(pageNo - 1,pageSize);
        PageInfo<Problem> problemPageInfo = new PageInfo<>(problemService.searchByLabelid(searchMap, pageNo, pageSize));
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(problemPageInfo.getTotal(),problemPageInfo.getList()));
    }

    @ApiOperation("查询最新回复的问题列表")
    @GetMapping(value = "/newlist/{labelId}/{pageNo}/{pageSize}")
    public Result findByNewReply(String labelId,int pageNo,int pageSize){
        PageHelper.startPage(pageNo - 1,pageSize);
        List<Problem> problemList = problemService.findByNewReply(labelId, pageNo, pageSize);
        PageInfo<Problem> problemPageInfo = new PageInfo<>(problemList);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(problemPageInfo.getTotal(),problemPageInfo.getList()));
    }

    @ApiOperation("查询热门回复的问题列表")
    @GetMapping(value = "/hotlist/{labelId}/{pageNo}/{pageSize}")
    public Result findByHotReply(String labelId,int pageNo,int pageSize){
        PageHelper.startPage(pageNo - 1,pageSize);
        List<Problem> problemList = problemService.findByHotReply(labelId, pageNo, pageSize);
        PageInfo<Problem> pageInfo = new PageInfo<>(problemList);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(pageInfo.getTotal(),pageInfo.getList()));
    }

    @ApiOperation("查询等待回复的问题列表")
    @RequestMapping(value = "/waitlist/{labelId}/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public Result findByWaitReply(String labelId,int pageNo,int pageSize){
        PageHelper.startPage(pageNo - 1,pageSize);
        List<Problem> problemList = problemService.findByWaitReply(labelId, pageNo, pageSize);
        PageInfo<Problem> problemPageInfo = new PageInfo<>(problemList);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(problemPageInfo.getTotal(),problemPageInfo.getList()));
    }

    @ApiOperation("调用基础服务根据id查询标签")
    @GetMapping(value = "/lable/{id}")
    public Result findLabelById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询成功",labelClient.findById(id));
    }
}
