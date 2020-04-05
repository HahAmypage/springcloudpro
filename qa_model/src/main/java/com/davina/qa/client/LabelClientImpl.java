package com.davina.qa.client;

import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import com.davina.qa.client.LabelClient;
import org.springframework.stereotype.Component;

/**
 * @ClassName LabelClientImpl
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/4/5 10:40
 * @Version 1.0
 */

public class LabelClientImpl implements LabelClient {
    /**
     * 根据id查询标签
     *
     * @param id 标签id
     * @return com.davina.entity.Result
     * @author chenyingxin
     * @date 2020/3/11 20:07
     **/
    @Override
    public Result findById(String id) {
        return new Result(false, StatusCode.ERROR,"熔断器启动了");
    }
}
