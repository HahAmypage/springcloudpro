package com.davina.article.service;

import com.davina.article.dao.ArticleMapper;
import com.davina.article.pojo.Article;
import com.davina.article.pojo.ArticleExample;
import com.davina.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisTemplate redisTemplate;

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
    * @param id: 文章id
    * @return: com.davina.article.pojo.Article
    **/
    public Article findById(String id){
        // 先找缓存
        Article article = (Article) redisTemplate.opsForValue().get("article_"+id);
        if (article == null){
            // 缓存没有查数据库，并存入缓存
            article = articleMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set("article_"+id,article,1, TimeUnit.DAYS);
        }
        return article;
    }

    /**
    *  添加文章
    * @author chenyingxin
    * @date 2020/3/13 20:18
    * @param article: 文章对象
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
    * @param article: 文章对象
    * @return: void
    **/
    public void update(Article article){
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();

        criteria.andIdEqualTo(article.getId());

        redisTemplate.delete("article_"+article.getId());
        articleMapper.updateByExampleSelective(article,articleExample);
    }

    /**
    *  根据id删除文章
    * @author chenyingxin
    * @date 2020/3/13 20:33
    * @param id: 文章id
    * @return: void
    **/
    public void delete(String id){
        redisTemplate.delete("article_"+id);
        articleMapper.deleteByPrimaryKey(id);
    }

    /**
    *  审核文章
    * @author chenyingxin
    * @date 2020/3/15 11:14
    * @param id: 文章id
    * @return: void
    **/
    public void examine(String id) {
        articleMapper.examine(id);
    }

    /**
    *  点赞/取消点赞（更新文章点赞数）
    * @author chenyingxin
    * @date 2020/3/15 11:19
    * @return: void
    **/
    public void thumbup(String id,Integer thumbup){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("thumbup",thumbup);
        articleMapper.thumbup(map);
    }

}
