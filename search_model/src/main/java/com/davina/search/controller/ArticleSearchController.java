package com.davina.search.controller;

import com.davina.search.pojo.Article;
import com.davina.entity.PageResult;
import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.davina.search.service.ArticleSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ArticleSearchController
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/22 10:04
 * @Version 1.0
 */
@Api(value = "检索文章",description = "检索文章模块")
@CrossOrigin
@RestController
@RequestMapping(value = "/searcharticle")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    @ApiOperation(value = "分页检索文章")
    @GetMapping(value = "/{keyword}/{pageNo}/{pageSize}")
    public Result findByTitleOrContentLike(@PathVariable("keyword") String keyword,@PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize){
        Page<Article> articlePage = articleSearchService.findByTitleOrContentLike(keyword, pageNo, pageSize);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Article>(articlePage.getTotalElements(),articlePage.getContent()));
    }
}
