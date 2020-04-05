package com.davina.qa.client;

import com.davina.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName LabelClient
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/10 20:38
 * @Version 1.0
 */
@FeignClient(value = "base-model",fallback = LabelClientImpl.class)
public interface LabelClient {

    /**
    *  根据id查询标签
    * @author chenyingxin
    * @date 2020/3/11 20:07
    * @param id 标签id
    * @return com.davina.entity.Result
    **/
    @GetMapping(value = "/label/{id}")
    Result findById(@PathVariable("id") String id);
}
