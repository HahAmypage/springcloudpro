package com.davina.spit.service;

import com.davina.entity.PageResult;
import com.davina.spit.dao.SpitDao;
import com.davina.spit.pojo.Spit;
import com.davina.util.IdWorker;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SpitService
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/17 17:40
 * @Version 1.0
 */
@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
    *  查询所有
    * @author chenyingxin
    * @date 2020/3/17 17:57
    * @return: java.util.List<com.davina.spit.pojo.Spit>
    **/
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
    *  根据id查询
    * @author chenyingxin
    * @date 2020/3/17 17:57
    * @param id: 吐槽id
    * @return: com.davina.spit.pojo.Spit
    **/
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    /**
    *  添加吐槽
    * @author chenyingxin
    * @date 2020/3/17 17:57
    * @param spit: 吐槽实体
    * @return: void
    **/
    public void save(Spit spit){
        spit.setId(idWorker.nextId()+"");
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setShare(0);
        spit.setThumbup(0);
        spit.setComment(0);
        spit.setState("1");
        // 如果存在父级，评论+1
        if (spit.getParentid() != null && !"".equals(spit.getParentid())){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    /**
    *  更新吐槽
    * @author chenyingxin
    * @date 2020/3/17 17:57
    * @param spit:
    * @return: void
    **/
    public void update(Spit spit){
        Spit spit2 = this.findById(spit.getId());
        if (spit.getContent() != null && !"".equals(spit.getContent())){
            spit2.setContent(spit.getContent());
        }
        spit2.setPublishtime(new Date());
        spitDao.save(spit2);
    }

    /**
    *  删除吐槽
    * @author chenyingxin
    * @date 2020/3/17 17:58
    * @param id: 吐槽id
    * @return: void
    **/
    public void delete(String id){
        spitDao.deleteById(id);
    }

    /**
    *  根据父id查询
    * @author chenyingxin
    * @date 2020/3/17 18:24
    * @param parentId:
    * @return: java.util.List<com.davina.spit.pojo.Spit>
    **/
    public Page<Spit> findByParentId(String parentId, int pageNo, int pageSize){
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return spitDao.findByParentid(parentId,pageable);
    }

    /**
    *  点赞
    * @author chenyingxin
    * @date 2020/3/17 20:24
    * @param id: 吐槽id
    * @return: void
    **/
    public void updateThumbup(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
}
