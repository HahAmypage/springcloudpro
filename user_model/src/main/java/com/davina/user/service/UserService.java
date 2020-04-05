package com.davina.user.service;

import com.davina.user.dao.UserMapper;
import com.davina.user.pojo.User;
import com.davina.user.pojo.UserExample;
import com.davina.util.IdWorker;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/23 22:23
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    /**
    *  查询用户列表
    * @author chenyingxin
    * @date 2020/3/23 22:35
    * @param user:
    * @return: java.util.List<com.davina.user.pojo.User>
    **/
    public List<User> findAll(User user){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        if (user.getNickname() != null && !"".equals(user.getNickname().trim())){
            criteria.andNicknameLike("%"+user.getNickname().trim()+"%");
        }
        return userMapper.selectByExample(userExample);
    }

    /**
    *  根据id查询用户
    * @author chenyingxin
    * @date 2020/3/23 22:36
    * @param id: 用户id
    * @return: com.davina.user.pojo.User
    **/
    public User findById(String id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
    *  添加用户
    * @author chenyingxin
    * @date 2020/3/23 22:37
    * @param user:
    * @return: void
    **/
    @Transactional
    public void add(User user,String code){

        //提取系统正确的验证码
        String sysCode = (String) redisTemplate.opsForValue().get("smscode_" + user.getMobile());
        if (sysCode == null){
            throw new RuntimeException("请点击获取验证码");
        }

        if (!sysCode.equalsIgnoreCase(code)){
            throw new RuntimeException("输入的验证错误");
        }

        user.setPassword(cryptPasswordEncoder.encode(user.getPassword())); //加密后赋值
        user.setId(idWorker.nextId()+"");
        user.setFollowcount(0);//关注数
        user.setFanscount(0);//粉丝数
        user.setOnline(0L);//在线时长
        user.setRegdate(new Date());//注册日期
        user.setUpdatedate(new Date());//更新日期
        user.setLastdate(new Date());//最后登陆日期
        userMapper.insert(user);
    }

    /**
    *  修改用户
    * @author chenyingxin
    * @date 2020/3/23 22:37
    * @param user:
    * @return: void
    **/
    @Transactional
    public void update(User user){
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
    *  删除用户
    * @author chenyingxin
    * @date 2020/3/23 22:39
    * @param id: 用户id
    * @return: void
    **/
    @Transactional
    public void delete(String id){
        userMapper.deleteByPrimaryKey(id);
    }

    /**
    *  发送短信
    * @author chenyingxin
    * @date 2020/3/24 20:52
    * @param mobile: 手机号码
    * @return: void
    **/
    public void sendSMS(String mobile) {
        // 生成随机验证码
        String code = RandomStringUtils.randomNumeric(6);
        System.out.println("验证码："+code);
        //存入 redis（5 分钟有效）
        redisTemplate.opsForValue().set("smscode_"+mobile,code,15, TimeUnit.MINUTES);
        Map<String,String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("code",code);
        //存入 mq 队列
        rabbitMessagingTemplate.convertAndSend("sms",map);
    }

    /**
    *  登录
    * @author chenyingxin
    * @date 2020/3/27 21:05
    * @param map: 登录名
    * @return: com.davina.user.pojo.User
    **/
    public User login(Map<String,String> map) {
        User user = userMapper.login(map);
        if (user != null && cryptPasswordEncoder.matches(map.get("password"),user.getPassword())){
            return user;
        }
        return null;
    }

    /**
    *  更新粉丝数
    * @author chenyingxin
    * @date 2020/4/4 17:29
    * @param userid:
    * @param x:
    * @return: void
    **/
    @Transactional
    public void incFanscount(String userid, int x) {
        User user = userMapper.selectByPrimaryKey(userid);
        user.setFanscount(user.getFanscount()+x);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
    *  更新关注数
    * @author chenyingxin
    * @date 2020/4/4 17:33
    * @param userid:
    * @param x:
    * @return: void
    **/
    @Transactional
    public void incFollowcount(String userid, int x) {
        User user = userMapper.selectByPrimaryKey(userid);
        user.setFollowcount(user.getFollowcount()+x);
        userMapper.updateByPrimaryKeySelective(user);
    }
}
