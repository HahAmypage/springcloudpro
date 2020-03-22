package com.davina.search.service;

import com.davina.search.pojo.Article;
import com.davina.search.dao.ArticleSearchRepository;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @ClassName ArticleSearchService
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/22 11:58
 * @Version 1.0
 */
@Service
public class ArticleSearchService {

    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    /**
    *  文章检索
    * @author chenyingxin
    * @date 2020/3/22 12:01
    * @param keyword: 搜索关键字
    * @param pageNo: 页码
    * @param pageSize: 页大小
    * @return: org.springframework.data.domain.Page<com.davina.article.pojo.Article>
    **/
    public Page<Article> findByTitleOrContentLike(String keyword,Integer pageNo,Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageNo - 1,pageSize);
        return articleSearchRepository.findByTitleOrContentLike(keyword,keyword,pageRequest);
    }
}
