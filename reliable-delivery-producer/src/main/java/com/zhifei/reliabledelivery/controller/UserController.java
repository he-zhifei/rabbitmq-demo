package com.zhifei.reliabledelivery.controller;

import com.zhifei.reliabledelivery.entity.MessageContainer;
import com.zhifei.reliabledelivery.entity.User;
import com.zhifei.reliabledelivery.service.BrokerMessageLogService;
import com.zhifei.reliabledelivery.service.UserService;
import com.zhifei.reliabledelivery.util.MessageIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BrokerMessageLogService brokerMessageLogService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public void add() {
        User user = new User();
        user.setId(1L);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setName("张三");
        MessageContainer<User> container = MessageContainer.<User>builder()
                .messageId(MessageIdGenerator.generateMessageId("user"))
                .body(user).build();
        userService.add(container);
    }

}
