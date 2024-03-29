package com.zhifei.reliabledelivery.service;

import com.zhifei.reliabledelivery.entity.BaseMessage;
import com.zhifei.reliabledelivery.entity.MessageContainer;

import java.util.Date;

public interface BrokerMessageLogService {

    /**
     * 更新消息状态以及updateTime
     * @param id
     * @param status
     * @param updateTime
     */
    void updateStatus(String id, String status, Date updateTime);

    <T> void addMessageContainer(MessageContainer<T> container);

    void addMessageLog(BaseMessage message);
}
