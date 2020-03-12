package com.davina.base.service;

import com.davina.base.dao.LabelDao;
import com.davina.base.pojo.Label;
import com.davina.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LabelService
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/3 20:06
 * @Version 1.0
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    private static final String LABELNAME = "labelname";

    private static final String STATE = "state";

    private static final String RECOMMEND = "recommend";

    /**
    * @author chenyingxin
    * @Description  查询所有
    * @Date 2020/3/3 20:24
    * @Param []
    * @return java.util.List<Label>
    **/
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /**
    * @author chenyingxin
    * @Description 根据id查
    * @Date 2020/3/3 20:24
    * @Param [id]
    * @return Label
    **/
    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /**
    * @author chenyingxin
    * @Description  添加
    * @Date 2020/3/3 20:25
    * @Param [label]
    * @return void
    **/
    public void add(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
    * @author chenyingxin
    * @Description 修改
    * @Date 2020/3/3 20:26
    * @Param [label]
    * @return void
    **/
    public void update(Label label){
        labelDao.save(label);
    }

    /**
    * @author chenyingxin
    * @Description  根据 id 删除
    * @Date 2020/3/3 20:27
    * @Param [id]
    * @return void
    **/
    public void delete(String id){
        labelDao.deleteById(id);
    }

    /**
    * @author chenyingxin
    * @Description 多条件查询
    * @Date 2020/3/4 18:11
    * @Param [searchMap]
    * @return java.util.List<Label>
    **/
    public List<Label> findSearch(Map searchMap) {
        Specification<Label> labelSpecification = createSpecification(searchMap);
        return labelDao.findAll(labelSpecification);
    }

    /**
    * @author chenyingxin
    * @Description 多条件分页查询
    * @Date 2020/3/4 18:45
    * @Param [searchMap, pageNo, pageSize]
    * @return com.davina.entity.PageResult<Label>
    **/
    public Page<Label> findByPage(Map searchMap,int pageNo,int pageSize){
        Specification<Label> labelSpecification = createSpecification(searchMap);
        PageRequest labelPageResult = PageRequest.of(pageNo - 1,pageSize);
        return labelDao.findAll(labelSpecification,labelPageResult);
    }

    /**
    * @author chenyingxin
    * @Description 构建查询条件
    * @Date 2020/3/4 18:12
    * @Param [searchMap]
    * @return org.springframework.data.jpa.domain.Specification<Label>
    **/
    private Specification<Label> createSpecification(Map searchMap) {
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get(LABELNAME) != null && !"".equals(searchMap.get(LABELNAME))){
                    predicateList.add(criteriaBuilder.like(root.get(LABELNAME).as(String.class),"%"+searchMap.get(LABELNAME)+"%"));
                }

                if (searchMap.get(STATE) != null && !"".equals(searchMap.get(STATE))){
                    predicateList.add(criteriaBuilder.equal(root.get(STATE).as(String.class),searchMap.get(STATE)));
                }

                if (searchMap.get(RECOMMEND) != null && !"".equals(searchMap.get(RECOMMEND))){
                    predicateList.add(criteriaBuilder.equal(root.get(RECOMMEND).as(String.class),searchMap.get(RECOMMEND)));
                }

                Predicate[] predicates = new Predicate[predicateList.size()];

                return criteriaBuilder.and(predicateList.toArray(predicates));
            }
        };
    }
}
