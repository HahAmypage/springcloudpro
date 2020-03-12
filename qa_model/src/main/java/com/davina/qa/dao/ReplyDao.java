package com.davina.qa.dao;

import com.davina.qa.pojo.Reply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName ReplyDao
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/8 18:21
 * @Version 1.0
 */
@Repository
@Mapper
public interface ReplyDao {

    /**
    * 根据问题查询对应的回复
    * @author chenyingxin
    * @date 2020/3/11 20:36
    * @param problemId: 问题id
    * @return: java.util.List<com.davina.qa.pojo.Reply>
    **/
    @Select("SELECT * FROM tb_reply WHERE problemId = #{problemId}")
    List<Reply> findByProblemid(String problemId);

    /**
    * 根据id删除回复（在删除问题的时候用）
    * @author chenyingxin
    * @date 2020/3/11 20:37
    * @param problemId: 问题id
    * @return: int
    **/
    @Delete("DELETE FROM tb_reply WHERE problemId = #{problemId}")
    int deleteByProblemid(String problemId);

    /**
    * 新增回复
    * @author chenyingxin
    * @date 2020/3/11 20:37
    * @param reply: 回复对象
    * @return: void
    **/
    @Insert("INSERT INTO tb_reply(id,problemId,content,createtime,updatetime,userid,nickname) VALUES(#{id},#{problemId},#{content},#{createtime},#{updatetime},#{userid},#{nickname})")
    void save(Reply reply);

    /**
    * 根据id删除回复
    * @author chenyingxin
    * @date 2020/3/11 20:37
    * @param id: 回复id
    * @return: void
    **/
    @Delete("DELETE FROM tb_reply WHERE id = #{id}")
    void deleteById(String id);

    /**
    * 更新回复
    * @author chenyingxin
    * @date 2020/3/11 20:37
    * @param reply: 回复对象
    * @return: void
    **/
    void update(Reply reply);
}
