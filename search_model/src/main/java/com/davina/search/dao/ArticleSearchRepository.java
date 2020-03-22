package com.davina.search.dao;

import com.davina.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ClassName ArticleSearchRepository
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/22 10:05
 * @Version 1.0
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article,String> {

    /**
    *  检索文章
    * @author chenyingxin
    * @date 2020/3/22 11:51
    * @param title: 标题
    * @param content: 内容
    * @param pageable: 分页
    * @return: org.springframework.data.domain.Page<com.davina.article.pojo.Article>
    **/
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
