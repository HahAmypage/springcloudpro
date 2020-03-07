package com.davina.base.dao;

import com.davina.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName LabelDao
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/3 20:05
 * @Version 1.0
 */
public interface LabelDao extends JpaRepository<Label,String> , JpaSpecificationExecutor<Label> {
}
