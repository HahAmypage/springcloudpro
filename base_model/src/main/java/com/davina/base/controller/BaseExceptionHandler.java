package com.davina.base.controller;

import com.davina.entity.Result;
import com.davina.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName BaseExceptionHandler
 * @Description 公共异常处理类
 * @Author Davina Chan
 * @Date 2020/3/4 18:04
 * @Version 1.0
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
