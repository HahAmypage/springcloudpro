package com.davina.qa.service;

import com.davina.qa.dao.PlDao;
import com.davina.qa.dao.ProblemDao;
import com.davina.qa.dao.ReplyDao;
import com.davina.qa.pojo.Problem;
import com.davina.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ProblemService
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/8 18:23
 * @Version 1.0
 */
@Service
public class ProblemService {

    @Autowired
    private ProblemDao problemDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private PlDao plDao;

    private static final String TITLE = "title";

    private static final String CONTENT ="content";
    /**
    * @author chenyingxin
    * @Description 根据labelID查询所有问题
    * @Date 2020/3/8 19:48
    * @Param []
    * @return java.util.List<com.davina.qa.pojo.Problem>
    **/
    public List<Problem> findAllByLabelId(String labelId){
        return problemDao.findAllByLabelId(labelId);
    }

    /**
    * @author chenyingxin
    * @Description 根据id查询问题
    * @Date 2020/3/8 19:49
    * @Param [id]
    * @return com.davina.qa.pojo.Problem
    **/
    public Problem findById(String id){
        return problemDao.findById(id);
    }

    /**
    * @author chenyingxin
    * @Description 添加问题
    * @Date 2020/3/8 19:51
    * @Param [problem]
    * @return void
    **/
    public void add(Problem problem,String labelId){
        problem.setId(idWorker.nextId()+"");
        problemDao.save(problem);
        // 在标签和问题关系表添加记录
        plDao.save(problem.getId(),labelId);
    }

    /**
    * @author chenyingxin
    * @Description 修改问题
    * @Date 2020/3/8 19:52
    * @Param [problem]
    * @return void
    **/
    public void update(Problem problem){
        problemDao.update(problem);
    }

    /**
    * @author chenyingxin
    * @Description 根据id删除问题
    * @Date 2020/3/8 19:52
    * @Param [id]
    * @return void
    **/
    public void delete(String id,String labelId){
        // 先删除关联的回答
        replyDao.deleteByProblemid(id);
        problemDao.deleteById(id);
        // 在从标签和问题关系表中删除相关记录
        plDao.delete(id,labelId);
    }

    /**
    * @author chenyingxin
    * @Description 根据labelId多条件查询
    * @Date 2020/3/8 19:54
    * @Param [searchMap]
    * @return java.util.List<com.davina.qa.pojo.Problem>
    **/
    public List<Problem> searchByLabelid(Map searchMap){
        if (searchMap.get(TITLE) != null && !"".equals(searchMap.get(TITLE))){
            searchMap.put(TITLE,"%"+searchMap.get(TITLE)+"%");
        }
        if (searchMap.get(CONTENT) != null && !"".equals(searchMap.get(CONTENT))){
            searchMap.put(CONTENT,"%"+searchMap.get(CONTENT)+"%");
        }
        return problemDao.searchByLabelid(searchMap);
    }

    /**
    * @author chenyingxin
    * @Description 查询最新回复的问题列表
    * @Date 2020/3/8 20:21
    * @Param [labelId, pageNo, pageSize]
    * @return java.util.List<com.davina.qa.pojo.Problem>
    **/
    public List<Problem> findByNewReply(String labelId,int pageNo,int pageSize){
        return problemDao.findByNewRely(labelId);
    }

    /**
    * @author chenyingxin
    * @Description 热门回复问题
    * @Date 2020/3/8 20:29
    * @Param [labelId, pageNo, pageSize]
    * @return java.util.List<com.davina.qa.pojo.Problem>
    **/
    public List<Problem> findByHotReply(String labelId,int pageNo,int pageSize){
        return problemDao.findByHotReply(labelId);
    }

    /**
    * @author chenyingxin
    * @Description 等待回复问题
    * @Date 2020/3/8 20:31
    * @Param [labelId, pageNo, pageSize]
    * @return java.util.List<com.davina.qa.pojo.Problem>
    **/
    public List<Problem> findByWaitReply(String labelId,int pageNo,int pageSize){
        return problemDao.findByWaitReply(labelId);
    }
    /**
    * @author chenyingxin
    * @Description 拼接条件
    * @Date 2020/3/8 19:54
    * @Param [searchMap]
    * @return org.springframework.data.jpa.domain.Specification<com.davina.qa.pojo.Problem>
    **/
//    private Specification<Problem> createSpecification(Map searchMap) {
//        return new Specification<Problem>() {
//            @Override
//            public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicateList = new ArrayList<>();
//                if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))){
//                    predicateList.add(criteriaBuilder.like(root.get("title").as(String.class),"%"+searchMap.get("title")+"%"));
//                }
//                if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))){
//                    predicateList.add(criteriaBuilder.like(root.get("content").as(String.class),"%"+searchMap.get("content")+"%"));
//                }
//                if (searchMap.get("solve") != null && !"".equals(searchMap.get("solve"))){
//                    predicateList.add(criteriaBuilder.equal(root.get("solve").as(String.class),searchMap.get("solve")));
//                }
//                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
//            }
//        };
//    }
}
