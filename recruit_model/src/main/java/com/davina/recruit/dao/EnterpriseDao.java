package com.davina.recruit.dao;

import com.davina.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ClassName EnterpriseDao
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/4 20:53
 * @Version 1.0
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String> , JpaSpecificationExecutor<Enterprise> {

   /**
   *  查询热门企业
   * @author chenyingxin
   * @date 2020/3/11 18:03
   * @param ishot 热门标记：1-热门 0-不热门
   * @return java.util.List<com.davina.recruit.pojo.Enterprise>
   **/
    List<Enterprise> findByIshot(String ishot);
}
