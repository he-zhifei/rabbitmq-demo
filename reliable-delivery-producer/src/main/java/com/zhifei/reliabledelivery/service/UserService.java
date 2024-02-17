package com.zhifei.reliabledelivery.service;

import com.zhifei.reliabledelivery.entity.MessageContainer;
import com.zhifei.reliabledelivery.entity.User;

public interface UserService {

    /**
     * 订单添加
     * @return 持久化的订单
     */
    <T> User add(MessageContainer<T> container);
}
