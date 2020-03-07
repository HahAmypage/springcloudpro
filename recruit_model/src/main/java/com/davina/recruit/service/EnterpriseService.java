package com.davina.recruit.service;

import com.davina.recruit.dao.EnterpriseDao;
import com.davina.recruit.pojo.Enterprise;
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
 * @ClassName EnterpriseService
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/4 22:03
 * @Version 1.0
 */
@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseDao enterpriseDao;

    /**
    * @author chenyingxin
    * @Description  查询所有
    * @Date 2020/3/4 22:04
    * @Param []
    * @return java.util.List<com.davina.recruit.pojo.Enterprise>
    **/
    public List<Enterprise> findAll(){
        return enterpriseDao.findAll();
    }

    /**
    * @author chenyingxin
    * @Description 根据id查询
    * @Date 2020/3/5 17:21
    * @Param [id]
    * @return com.davina.recruit.pojo.Enterprise
    **/
    public Enterprise findById(String id){
        return enterpriseDao.findById(id).get();
    }

    /**
    * @author chenyingxin
    * @Description 添加
    * @Date 2020/3/5 17:22
    * @Param [enterprise]
    * @return void
    **/
    public void add(Enterprise enterprise){
        enterpriseDao.save(enterprise);
    }

    /**
    * @author chenyingxin
    * @Description 修改
    * @Date 2020/3/5 17:22
    * @Param [enterprise]
    * @return void
    **/
    public void update(Enterprise enterprise){
        enterpriseDao.save(enterprise);
    }

    /**
    * @author chenyingxin
    * @Description 根据id查询
    * @Date 2020/3/5 17:23
    * @Param [id]
    * @return void
    **/
    public void deleteById(String id){
        enterpriseDao.deleteById(id);
    }

    /**
    * @author chenyingxin
    * @Description 多条件查询
    * @Date 2020/3/5 17:53
    * @Param [searchMap]
    * @return java.util.List<com.davina.recruit.pojo.Enterprise>
    **/
    public List<Enterprise> search(Map searchMap){
        Specification<Enterprise> specification = createSpecification(searchMap);
        return enterpriseDao.findAll(specification);
    }

    /**
    * @author chenyingxin
    * @Description 分页多条件查询
    * @Date 2020/3/5 17:56
    * @Param [searchMap, pageNo, pageSize]
    * @return org.springframework.data.domain.Page<com.davina.recruit.pojo.Enterprise>
    **/
    public Page<Enterprise> findByPage(Map searchMap, int pageNo, int pageSize){
        Specification<Enterprise> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(pageNo - 1,pageSize);
        return enterpriseDao.findAll(specification,pageRequest);
    }

    /**
    * @author chenyingxin
    * @Description 创建多条件
    * @Date 2020/3/5 17:29
    * @Param [searchMap]
    * @return org.springframework.data.jpa.domain.Specification<com.davina.recruit.pojo.Enterprise>
    **/
    public Specification<Enterprise> createSpecification(Map searchMap){
        return new Specification<Enterprise>() {
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))){
                    predicateList.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+searchMap.get("name")+"%"));
                }
                if (searchMap.get("summary") != null && !"".equals(searchMap.get("summary"))){
                    predicateList.add(criteriaBuilder.like(root.get("summary").as(String.class),"%"+searchMap.get("summary")+"%"));
                }

                if (searchMap.get("address") != null && !"".equals(searchMap.get("address"))){
                    predicateList.add(criteriaBuilder.like(root.get("address").as(String.class),"%"+searchMap.get("address")+"%"));
                }

                if (searchMap.get("labels") != null && !"".equals(searchMap.get("labels"))){
                    predicateList.add(criteriaBuilder.equal(root.get("labels").as(String.class),searchMap.get("labels")));
                }

                if (searchMap.get("coordinate") != null && !"".equals(searchMap.get("coordinate"))){
                    predicateList.add(criteriaBuilder.like(root.get("coordinate").as(String.class),"%"+searchMap.get("coordinate")+"%"));
                }

                if (searchMap.get("ishot") != null && !"".equals(searchMap.get("ishot"))){
                    predicateList.add(criteriaBuilder.equal(root.get("ishot").as(String.class),searchMap.get("ishot")));
                }

                if (searchMap.get("jobcount") != null && !"".equals(searchMap.get("jobcount"))){
                    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("jobcount").as(Integer.class),Integer.valueOf(searchMap.get("jobcount").toString())));
                }

                Predicate[] predicates = new Predicate[predicateList.size()];

                return criteriaBuilder.and(predicateList.toArray(predicates));
            }
        };
    }
}
