package com.davina.entity;

/**
 * @ClassName StatusCode
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/3 20:31
 * @Version 1.0
 */
public class StatusCode {

    /**
     * 成功
     */
    public static final int OK = 20000;
    /**
     * 失败
     */
    public static final int ERROR = 20001;

    /**
     * 权限不足
     */
    public static final int ACCESSERROR = 20002;

    /**
     * 登录失败
     */
    public static final int LOGINERROR = 20003;
}
