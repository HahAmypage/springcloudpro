package com.davina.qa.service;

import com.davina.qa.dao.ReplyDao;
import com.davina.qa.pojo.Reply;
import com.davina.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ReplyService
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/8 18:24
 * @Version 1.0
 */
@Service
public class ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private IdWorker idWorker;

    /**
    * @author chenyingxin
    * @Description 根据问题id查询回答
    * @Date 2020/3/8 20:04
    * @Param [problemId]
    * @return java.util.List<com.davina.qa.pojo.Reply>
    **/
    public List<Reply> findByProblemId(String problemId){
        return replyDao.findByProblemid(problemId);
    }

    /**
    * @author chenyingxin
    * @Description 新增回答
    * @Date 2020/3/8 20:04
    * @Param [reply]
    * @return void
    **/
    public void add(Reply reply){
        reply.setId(idWorker.nextId()+"");
        replyDao.save(reply);
    }

    /**
    * @author chenyingxin
    * @Description 修改回答
    * @Date 2020/3/8 20:05
    * @Param [reply]
    * @return void
    **/
    public void update(Reply reply){
        replyDao.update(reply);
    }

    /**
    * @author chenyingxin
    * @Description 删除回答
    * @Date 2020/3/8 20:06
    * @Param [id]
    * @return void
    **/
    public void delete(String id){
        replyDao.deleteById(id);
    }
}
