package com.davina.recruit.service;

import com.davina.recruit.dao.EnterpriseDao;
import com.davina.recruit.pojo.Enterprise;
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

    @Autowired
    private IdWorker idWorker;

    private static final String NAME = "name";

    private static final String SUMMARY = "summary";

    private static final String ADDRESS = "address";

    private static final String LABELS = "labels";

    private static final String COORDINATE = "coordinate";

    private static final String ISHOT = "ishot";

    private static final String JOBCOUNT = "jobcount";

    /**
    * 查询所有企业
    * @author chenyingxin
    * @date 2020/3/11 21:04
    * @return: java.util.List<com.davina.recruit.pojo.Enterprise>
    **/
    public List<Enterprise> findAll(){
        return enterpriseDao.findAll();
    }

    /**
    * 根据id查询
    * @author chenyingxin
    * @date 2020/3/11 21:05
    * @param id: 企业id
    * @return: com.davina.recruit.pojo.Enterprise
    **/
    public Enterprise findById(String id){
        return enterpriseDao.findById(id).get();
    }

    /**
    * 添加
    * @author chenyingxin
    * @date 2020/3/11 21:06
    * @param enterprise: 企业对象
    * @return: void
    **/
    public void add(Enterprise enterprise){
        enterprise.setId(idWorker.nextId()+"");
        enterpriseDao.save(enterprise);
    }

    /**
    * 修改
    * @author chenyingxin
    * @date 2020/3/11 21:06
    * @param enterprise: 企业对象
    * @return: void
    **/
    public void update(Enterprise enterprise){
        enterpriseDao.save(enterprise);
    }

    /**
    * 根据id查询
    * @author chenyingxin
    * @date 2020/3/11 21:07
    * @param id: 企业id
    * @return: void
    **/
    public void deleteById(String id){
        enterpriseDao.deleteById(id);
    }

    /**
    * 多条件查询
    * @author chenyingxin
    * @date 2020/3/11 21:07
    * @param searchMap: 查询条件
    * @return: java.util.List<com.davina.recruit.pojo.Enterprise>
    **/
    public List<Enterprise> search(Map searchMap){
        Specification<Enterprise> specification = createSpecification(searchMap);
        return enterpriseDao.findAll(specification);
    }

    /**
    * 分页多条件查询
    * @author chenyingxin
    * @date 2020/3/11 21:07
    * @param searchMap: 查询条件
    * @param pageNo: 页码
    * @param pageSize: 页大小
    * @return: org.springframework.data.domain.Page<com.davina.recruit.pojo.Enterprise>
    **/
    public Page<Enterprise> findByPage(Map searchMap, int pageNo, int pageSize){
        Specification<Enterprise> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(pageNo - 1,pageSize);
        return enterpriseDao.findAll(specification,pageRequest);
    }

    /**
    * 查询热门企业
    * @author chenyingxin
    * @date 2020/3/11 21:09
    * @return: java.util.List<com.davina.recruit.pojo.Enterprise>
    **/
    public List<Enterprise> findIshot(){
        return enterpriseDao.findByIshot("1");
    }

    /**
    * 创建多条件
    * @author chenyingxin
    * @date 2020/3/11 21:09
    * @param searchMap: 查询条件
    * @return: org.springframework.data.jpa.domain.Specification<com.davina.recruit.pojo.Enterprise>
    **/
    public Specification<Enterprise> createSpecification(Map searchMap){
        return new Specification<Enterprise>() {
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get(NAME) != null && !"".equals(searchMap.get(NAME))){
                    predicateList.add(criteriaBuilder.like(root.get(NAME).as(String.class),"%"+searchMap.get(NAME)+"%"));
                }
                if (searchMap.get(SUMMARY) != null && !"".equals(searchMap.get(SUMMARY))){
                    predicateList.add(criteriaBuilder.like(root.get(SUMMARY).as(String.class),"%"+searchMap.get(SUMMARY)+"%"));
                }

                if (searchMap.get(ADDRESS) != null && !"".equals(searchMap.get(ADDRESS))){
                    predicateList.add(criteriaBuilder.like(root.get(ADDRESS).as(String.class),"%"+searchMap.get(ADDRESS)+"%"));
                }

                if (searchMap.get(LABELS) != null && !"".equals(searchMap.get(LABELS))){
                    predicateList.add(criteriaBuilder.equal(root.get(LABELS).as(String.class),searchMap.get(LABELS)));
                }

                if (searchMap.get(COORDINATE) != null && !"".equals(searchMap.get(COORDINATE))){
                    predicateList.add(criteriaBuilder.like(root.get(COORDINATE).as(String.class),"%"+searchMap.get(COORDINATE)+"%"));
                }

                if (searchMap.get(ISHOT) != null && !"".equals(searchMap.get(ISHOT))){
                    predicateList.add(criteriaBuilder.equal(root.get(ISHOT).as(String.class),searchMap.get(ISHOT)));
                }

                if (searchMap.get(JOBCOUNT) != null && !"".equals(searchMap.get(JOBCOUNT))){
                    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get(JOBCOUNT).as(Integer.class),Integer.valueOf(searchMap.get(JOBCOUNT).toString())));
                }

                Predicate[] predicates = new Predicate[predicateList.size()];

                return criteriaBuilder.and(predicateList.toArray(predicates));
            }
        };
    }
}
