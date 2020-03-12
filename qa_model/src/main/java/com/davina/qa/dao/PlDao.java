package com.davina.qa.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName PlDao
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/8 20:44
 * @Version 1.0
 */
@Repository
@Mapper
public interface PlDao  {

    /**
    * 根据问题id和标签id删除问题
    * @author chenyingxin
    * @date 2020/3/11 20:32
    * @param problemid: 问题id
    * @param labelId: 标签id
    * @return: void
    **/
    @Delete("DELETE FROM tb_pl WHERE problemid = #{problemid} AND labelId = #{labelId}")
    void delete(@Param("problemid") String problemid, @Param("labelId") String labelId);

    /**
    * 插入问题和标签的关系
    * @author chenyingxin
    * @date 2020/3/11 20:51
    * @param problemid:
    * @param labelId:
    * @return: void
    **/
    @Insert("INSERT INTO tb_pl(problemid,labelId) VALUES(#{problemid},#{labelId})")
    void save(@Param("problemid") String problemid,@Param("labelId") String labelId);
}
