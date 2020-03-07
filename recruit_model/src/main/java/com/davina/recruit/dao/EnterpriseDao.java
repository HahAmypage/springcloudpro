package com.davina.recruit.dao;

import com.davina.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName EnterpriseDao
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/4 20:53
 * @Version 1.0
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String> , JpaSpecificationExecutor<Enterprise> {
}
