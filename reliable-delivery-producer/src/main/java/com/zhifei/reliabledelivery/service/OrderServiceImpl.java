package com.zhifei.reliabledelivery.service;

import com.alibaba.fastjson.JSON;
import com.zhifei.reliabledelivery.constant.MessageStatus;
import com.zhifei.reliabledelivery.dao.BrokerMessageLogMapper;
import com.zhifei.reliabledelivery.dao.OrderMapper;
import com.zhifei.reliabledelivery.entity.BrokerMessageLog;
import com.zhifei.reliabledelivery.entity.MessageContainer;
import com.zhifei.reliabledelivery.entity.Order;
import com.zhifei.reliabledelivery.producer.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private RabbitSender rabbitSender;

    @Override
    public Order add(Order order) {

        //1.订单入库
        orderMapper.add(order);

        //2.订单的消息记录入库
        BrokerMessageLog log = new BrokerMessageLog();
        log.setId(order.getMessageId());
        log.setMessage(JSON.toJSONString(order));
        log.setStatus(MessageStatus.SENDING);
        Date now = new Date();
        log.setNextRetryTime(new Date(now.getTime() + 60 * 1000));
        log.setCreateTime(now);
        log.setUpdateTime(now);
        brokerMessageLogMapper.add(log);

        //3.发送消息到消息队列中
        rabbitSender.sendOrderMessage(order);

        return order;
    }

}
