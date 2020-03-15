package com.davina.article.service;

import com.davina.article.dao.ArticleMapper;
import com.davina.article.pojo.Article;
import com.davina.article.pojo.ArticleExample;
import com.davina.entity.PageResult;
import com.davina.util.IdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/13 18:17
 * @Version 1.0
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private IdWorker idWorker;

    /**
    *  查询所有
    * @author chenyingxin
    * @date 2020/3/13 20:07
    * @return: java.util.List<com.davina.article.pojo.Article>
    **/
    public List<Article> findAll(Map<String,String> searchMap){
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        if (searchMap.get("columnid") != null && !"".equals(searchMap.get("columnid").trim())){
            criteria.andColumnidEqualTo(searchMap.get("columnid"));
        }

        if (searchMap.get("channelid") != null && !"".equals(searchMap.get("channelid").trim())){
            criteria.andColumnidEqualTo(searchMap.get("channelid"));
        }

        if (searchMap.get("title") != null && !"".equals(searchMap.get("title").trim())){
            criteria.andTitleLike("%"+searchMap.get("title")+"%");
        }

        if (searchMap.get("content") != null && !"".equals(searchMap.get("content").trim())){
            criteria.andContentLike("%"+searchMap.get("content")+"%");
        }

        if (searchMap.get("thumbup") != null && !"".equals(searchMap.get("thumbup").trim())){
            criteria.andThumbupGreaterThan(Integer.valueOf(searchMap.get("thumbup")));
        }

        return articleMapper.selectByExample(articleExample);
    }

    /**
    *  根据id查询
    * @author chenyingxin
    * @date 2020/3/13 20:05
    * @param id:
    * @return: com.davina.article.pojo.Article
    **/
    public Article findById(String id){
        return articleMapper.selectByPrimaryKey(id);
    }

    /**
    *  添加文章
    * @author chenyingxin
    * @date 2020/3/13 20:18
    * @param article:
    * @return: void
    **/
    public void insert(Article article){
        article.setId(idWorker.nextId()+"");
        articleMapper.insert(article);
    }

    /**
    *  更新文章
    * @author chenyingxin
    * @date 2020/3/13 20:32
    * @param article:
    * @return: void
    **/
    public void update(Article article){
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();

        criteria.andIdEqualTo(article.getId());

        articleMapper.updateByExampleSelective(article,articleExample);
    }

    /**
    *  根据id删除文章
    * @author chenyingxin
    * @date 2020/3/13 20:33
    * @param id:
    * @return: void
    **/
    public void delete(String id){
        articleMapper.deleteByPrimaryKey(id);
    }
}
