package com.davina.friend.dao;

import com.davina.friend.pojo.FriendExample;
import com.davina.friend.pojo.Friend;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FriendMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend
     *
     * @mbggenerated
     */
    int countByExample(FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend
     *
     * @mbggenerated
     */
    int deleteByExample(FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Friend key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend
     *
     * @mbggenerated
     */
    int insert(Friend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend
     *
     * @mbggenerated
     */
    int insertSelective(Friend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend
     *
     * @mbggenerated
     */
    List<Friend> selectByExample(FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Friend record, @Param("example") FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Friend record, @Param("example") FriendExample example);
}