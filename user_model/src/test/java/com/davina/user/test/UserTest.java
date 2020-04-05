package com.davina.user.test;

import com.davina.user.UserApplication;
import com.davina.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserTest
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/27 21:31
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testLogin(){
        Map<String,String> map = new HashMap<>();
        map.put("loginname","哈哈");
        map.put("password","11");
        userService.login(map);
    }
}
