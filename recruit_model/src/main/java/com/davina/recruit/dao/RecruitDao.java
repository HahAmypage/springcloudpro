package com.davina.recruit.dao;

import com.davina.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ClassName RecruitDao
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/4 20:54
 * @Version 1.0
 */
public interface RecruitDao extends JpaRepository<Recruit,String> , JpaSpecificationExecutor<Recruit> {

    /**
    * 查询top10的推荐职位
    * @author chenyingxin
    * @date 2020/3/11 20:35
    * @param state: 1-在线，0-下线
    * @return: java.util.List<com.davina.recruit.pojo.Recruit>
    **/
    List<Recruit> findTop10ByStateOrderByCreatetime(String state);

    /**
    * 查询top10的最新职位（状态非0的）
    * @author chenyingxin
    * @date 2020/3/11 20:36
    * @param state: 1-在线，0-下线
    * @return: java.util.List<com.davina.recruit.pojo.Recruit>
    **/
    List<Recruit> findTop10ByStateNotOrderByCreatetimeDesc(String state);
}
