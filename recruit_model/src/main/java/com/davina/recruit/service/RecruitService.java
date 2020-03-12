package com.davina.recruit.service;

import com.davina.recruit.dao.RecruitDao;
import com.davina.recruit.pojo.Recruit;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RecruitService
 * @Description 招聘信息
 * @Author Davina Chan
 * @Date 2020/3/8 13:16
 * @Version 1.0
 */
@Service
public class RecruitService {

    @Autowired
    private RecruitDao recruitDao;

    @Autowired
    private IdWorker idWorker;

    private static final String JOBNAME = "jobname";

    private static final String SALARY = "salary";

    private static final String CONDITIONS = "conditions";

    private static final String EDUCATION = "education";

    private static final String ADDRESS = "address";

    private static final String LABEL = "label";

    private static final String CREATETIME = "createtime";

    /**
    *  查询所有
    * @author chenyingxin
    * @date 2020/3/11 21:47
    * @return: java.util.List<com.davina.recruit.pojo.Recruit>
    **/
    public List<Recruit> findAll(){
        return recruitDao.findAll();
    }

    /**
    *  根据id查询
    * @author chenyingxin
    * @date 2020/3/11 21:47
    * @param id: 职位id
    * @return: com.davina.recruit.pojo.Recruit
    **/
    public Recruit findById(String id){
        return recruitDao.findById(id).get();
    }

    /**
    *  添加
    * @author chenyingxin
    * @date 2020/3/11 21:47
    * @param recruit: 职位对象
    * @return: void
    **/
    public void add(Recruit recruit){
        recruit.setId(idWorker.nextId()+"");
        recruitDao.save(recruit);
    }

    /**
    *  根据id查询
    * @author chenyingxin
    * @date 2020/3/11 21:46
    * @param recruit: 职位对象
    * @return: void
    **/
    public void update(Recruit recruit){
        Recruit recruit1 = this.findById(recruit.getId());

        recruitDao.save(recruit);
    }

    /**
    *  根据id查询
    * @author chenyingxin
    * @date 2020/3/11 21:46
    * @param id: 职位id
    * @return: void
    **/
    public void deleteById(String id){
        recruitDao.deleteById(id);
    }

    /**
    *  多条件查询
    * @author chenyingxin
    * @date 2020/3/11 21:46
    * @param searchMap: 查询条件
    * @return: java.util.List<com.davina.recruit.pojo.Recruit>
    **/
    public List<Recruit> search(Map searchMap){
        Specification<Recruit> recruitSpecification = createSpecification(searchMap);
        return recruitDao.findAll(recruitSpecification);
    }

    /**
    *  多条件分页查询
    * @author chenyingxin
    * @date 2020/3/11 21:46
    * @param searchMap: 查询条件
    * @param pageNo: 页码
    * @param pageSize: 页大小
    * @return: org.springframework.data.domain.Page<com.davina.recruit.pojo.Recruit>
    **/
    public Page<Recruit> findByPage(Map searchMap,int pageNo,int pageSize){
        Specification<Recruit> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize);
        return recruitDao.findAll(specification,pageRequest);
    }

    /**
    *  查询推荐职位
    * @author chenyingxin
    * @date 2020/3/11 21:44
    * @param state: 1-推荐 0-不推荐
    * @return: java.util.List<com.davina.recruit.pojo.Recruit>
    **/
    public List<Recruit> findRecommend(String state){
        return recruitDao.findTop10ByStateOrderByCreatetime(state);
    }

    /**
    *  查询最新职位
    * @author chenyingxin
    * @date 2020/3/11 21:43
    * @return: java.util.List<com.davina.recruit.pojo.Recruit>
    **/
    public List<Recruit> findNewList(){
        return recruitDao.findTop10ByStateNotOrderByCreatetimeDesc("0");
    }

    /**
    * 创建条件
    * @author chenyingxin
    * @date 2020/3/11 21:36
    * @param searchMap: 前端传入的条件
    * @return: org.springframework.data.jpa.domain.Specification<com.davina.recruit.pojo.Recruit>
    **/
    private Specification<Recruit> createSpecification(Map searchMap) {
        return new Specification<Recruit>() {
            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();

                if(searchMap.get(JOBNAME) != null && !"".equals(searchMap.get(JOBNAME))){
                    predicateList.add(criteriaBuilder.like(root.get(JOBNAME).as(String.class),"%"+searchMap.get(JOBNAME)+"%"));
                }

                if (searchMap.get(SALARY) != null && !"".equals(searchMap.get(SALARY))){
                    // 目前只是添加大于等于条件，后续考虑改为比较一个范围
                    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get(SALARY).as(Integer.class),Integer.valueOf(searchMap.get(SALARY).toString())));
                }

                if (searchMap.get(CONDITIONS) != null && !"".equals(searchMap.get(CONDITIONS))){
                    predicateList.add(criteriaBuilder.like(root.get(CONDITIONS).as(String.class),"%"+searchMap.get(CONDITIONS)+"%"));
                }

                if (searchMap.get(EDUCATION) != null && !"".equals(searchMap.get(EDUCATION))){
                    predicateList.add(criteriaBuilder.equal(root.get(EDUCATION).as(String.class),searchMap.get(EDUCATION)));
                }

                if (searchMap.get(ADDRESS) != null && !"".equals(searchMap.get(ADDRESS))){
                    predicateList.add(criteriaBuilder.like(root.get(ADDRESS).as(String.class),"%"+searchMap.get(ADDRESS)+"%"));
                }

                if (searchMap.get(LABEL) != null && !"".equals(searchMap.get(LABEL))){
                    predicateList.add(criteriaBuilder.equal(root.get(LABEL).as(String.class),searchMap.get(LABEL)));
                }

                if (searchMap.get(CREATETIME) != null && !"".equals(searchMap.get(CREATETIME))){

                    predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get(CREATETIME).as(Timestamp.class),Timestamp.valueOf(searchMap.get(CREATETIME).toString())));

                }

                Predicate[] predicates = new Predicate[predicateList.size()];

                return criteriaBuilder.and(predicateList.toArray(predicates));
            }
        };
    }

}
