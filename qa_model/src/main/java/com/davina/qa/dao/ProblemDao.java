package com.davina.qa.dao;

import com.davina.qa.pojo.Problem;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resources;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ProblemDao
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/8 18:20
 * @Version 1.0
 */
@Repository
@Mapper
public interface ProblemDao{

    /**
    * 根据标签id多条件查询问题列表
    * @author chenyingxin
    * @date 2020/3/11 20:33
    * @param searchMap: 查询条件
    * @return: java.util.List<com.davina.qa.pojo.Problem>
    **/
    List<Problem> searchByLabelid(Map searchMap);

    /**
    * 查询指定标签的所有问题
    * @author chenyingxin
    * @date 2020/3/11 20:33
    * @param labelId: 标签id
    * @return: java.util.List<com.davina.qa.pojo.Problem>
    **/
    @Select("SELECT * FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid = #{labelId})")
    List<Problem> findAllByLabelId(@Param("labelId") String labelId);

    /**
    * 根据id查询指定问题
    * @author chenyingxin
    * @date 2020/3/11 20:34
    * @param id: 问题id
    * @return: com.davina.qa.pojo.Problem
    **/
    @Select("SELECT * FROM tb_problem WHERE id = #{id}")
    Problem findById(@Param("id") String id);

    /**
    * 保存一条问题
    * @author chenyingxin
    * @date 2020/3/11 20:34
    * @param problem: 插入的问题对象
    * @return: void
    **/
    @Insert("INSERT INTO tb_problem(id,title,content,createtime,updatetime,userid,nickname,visits,thumbup,reply,solve,replyname,replytime) VALUES(#{id},#{title},#{content},#{createtime},#{updatetime},#{userid},#{nickname},#{visits},#{thumbup},#{reply},#{solve},#{replyname},#{replytime})")
    void save(Problem problem);

    /**
    * 根据id删除问题
    * @author chenyingxin
    * @date 2020/3/11 20:55
    * @param id: 问题id
    * @return: void
    **/
    @Delete("DELETE FROM tb_problem WHERE id = #{id}")
    void deleteById(String id);

    /**
    * 更新问题
    * @author chenyingxin
    * @date 2020/3/11 20:34
    * @param problem: 更新的问题对象
    * @return: void
    **/
    void update(Problem problem);

    /**
    * 查询最新回复问题列表
    * @author chenyingxin
    * @date 2020/3/11 20:35
    * @param labelId: 标签id
    * @return: java.util.List<com.davina.qa.pojo.Problem>
    **/
    @Select("SELECT * FROM tb_problem WHERE id IN(SELECT problemid FROM tb_pl WHERE labelid = #{labelId}) ORDER BY replytime DESC ")
    List<Problem> findByNewRely(@Param("labelId") String labelId);

    /**
    * 查询热门问题列表
    * @author chenyingxin
    * @date 2020/3/11 20:35
    * @param labelId: 标签id
    * @return: java.util.List<com.davina.qa.pojo.Problem>
    **/
    @Select("SELECT * FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid = #{labelId}) ORDER BY reply DESC ")
    List<Problem> findByHotReply(@Param("labelId") String labelId);

    /**
    * 查询等待回答问题列表
    * @author chenyingxin
    * @date 2020/3/11 20:35
    * @param labelId: 标签id
    * @return: java.util.List<com.davina.qa.pojo.Problem>
    **/
    @Select("SELECT * FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid = #{labelId}) AND reply = 0 ORDER BY createtime DESC ")
    List<Problem> findByWaitReply(@Param("labelId") String labelId);
}
