package com.davina.article.controller;

import com.davina.article.pojo.Article;
import com.davina.article.service.ArticleService;
import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.github.pagehelper.Page;
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
 * @ClassName ArticleController
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/13 18:23
 * @Version 1.0
 */
@Api(value = "文章模块",description = "文章模块")
@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    /**
    *  查询所有
    * @author chenyingxin
    * @date 2020/3/13 20:08
    * @return: com.davina.entity.Result
    **/
    @ApiOperation(value = "查询多条件查询")
    @PostMapping(value = "/search/{pageNo}/{pageSize}")
    public Result findAll(@RequestBody Map<String,String> searchMap,@PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize){
        PageHelper.startPage(pageNo - 1,pageSize);
        Page<Article> articlePage = (Page<Article>) articleService.findAll(searchMap);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(articlePage.getTotal(),articlePage.getResult()));
    }

    @ApiOperation(value = "根据id查文章")
    @GetMapping(value = "/{id}")
    public Result findById(String id){
        return new Result(true, StatusCode.OK,"查询成功",articleService.findById(id));
    }


    @ApiOperation(value = "添加文章")
    @PostMapping
    public Result add(@RequestBody Article article){
        try {
            articleService.insert(article);
            return new Result(true,StatusCode.OK,"添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    @ApiOperation(value = "更新文章")
    @PutMapping
    public Result update(@RequestBody Article article){
        try {
            articleService.update(article);
            return new Result(true,StatusCode.OK,"更新成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(true,StatusCode.ERROR,"更新失败");
        }
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable("id") String id){
        try {
            articleService.delete(id);
            return new Result(true,StatusCode.OK,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }
}
