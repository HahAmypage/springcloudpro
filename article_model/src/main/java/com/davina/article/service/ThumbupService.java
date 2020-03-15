package com.davina.article.service;

import com.davina.article.dao.UserThumbupMapper;
import com.davina.article.pojo.UserThumbup;
import com.davina.article.pojo.UserThumbupExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ThumbupService
 * @Description 点赞/取消点
 * @Author Davina Chan
 * @Date 2020/3/15 11:57
 * @Version 1.0
 */
@Service
public class ThumbupService {

    @Autowired
    private UserThumbupMapper userThumbupMapper;

    /**
     *  根据userid和articleid查询是否已经点赞
     * @author chenyingxin
     * @date 2020/3/15 11:49
     * @param userId: 用户id
     * @param articleId: 文章id
     * @return: com.davina.article.pojo.UserThumbup
     **/
    public List<UserThumbup> selectByUserIdAndArticleId(String userId, String articleId){
        UserThumbupExample userThumbupExample = new UserThumbupExample();
        UserThumbupExample.Criteria criteria = userThumbupExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andArticleIdEqualTo(articleId);
        return userThumbupMapper.selectByExample(userThumbupExample);
    }

    /**
     *  添加点赞记录
     * @author chenyingxin
     * @date 2020/3/15 11:55
     * @param userId: 用户id
     * @param articleId: 文章id
     * @return: void
     **/
    public void insert(String userId,String articleId){
        UserThumbup userThumbup = new UserThumbup(userId, articleId);
        userThumbupMapper.insert(userThumbup);
    }

    /**
    *  删除点赞记录
    * @author chenyingxin
    * @date 2020/3/15 12:00
    * @param userId: 用户id
    * @param articleId: 文章id
    * @return: void
    **/
    public void delete(String userId,String articleId){
        UserThumbupExample userThumbupExample = new UserThumbupExample();
        UserThumbupExample.Criteria criteria = userThumbupExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andArticleIdEqualTo(articleId);
        userThumbupMapper.deleteByExample(userThumbupExample);
    }
}
