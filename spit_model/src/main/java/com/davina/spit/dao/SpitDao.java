package com.davina.spit.dao;

import com.davina.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @ClassName SpitDao
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/17 17:36
 * @Version 1.0
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    Page<Spit> findByParentid(String parentId, Pageable pageable);
}
