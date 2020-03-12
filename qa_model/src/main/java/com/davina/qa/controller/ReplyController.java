package com.davina.qa.controller;

import com.davina.qa.service.ReplyService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ReplyController
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/8 18:27
 * @Version 1.0
 */
@Api(value = "回答模块",description = "回答模块")
@CrossOrigin
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    private final Logger logger = LoggerFactory.getLogger(RestController.class);
}
