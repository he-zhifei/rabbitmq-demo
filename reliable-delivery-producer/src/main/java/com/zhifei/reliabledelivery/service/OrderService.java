package com.zhifei.reliabledelivery.service;

import com.zhifei.reliabledelivery.entity.Order;

public interface OrderService {

    /**
     * 订单添加
     * @param order
     * @return 持久化的订单
     */
    Order add(Order order);
}
