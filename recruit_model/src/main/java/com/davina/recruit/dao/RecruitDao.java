package com.davina.recruit.dao;

import com.davina.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName RecruitDao
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/4 20:54
 * @Version 1.0
 */
public interface RecruitDao extends JpaRepository<Recruit,String> , JpaSpecificationExecutor<Recruit> {
}
