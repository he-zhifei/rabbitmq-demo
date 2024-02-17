package com.zhifei.reliabledelivery.service;

import com.alibaba.fastjson.JSON;
import com.zhifei.reliabledelivery.constant.MessageStatus;
import com.zhifei.reliabledelivery.dao.BrokerMessageLogMapper;
import com.zhifei.reliabledelivery.entity.BaseMessage;
import com.zhifei.reliabledelivery.entity.BrokerMessageLog;
import com.zhifei.reliabledelivery.entity.MessageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Service
public class BrokerMessageLogServiceImpl implements BrokerMessageLogService {

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Override
    public void updateStatus(String id, String status, Date updateTime) {
        brokerMessageLogMapper.updateStatus(id, status, updateTime);
    }

    @Override
    public <T> void addMessageContainer(MessageContainer<T> container) {
        BrokerMessageLog log = new BrokerMessageLog();
        log.setId(container.getMessageId());
        log.setMessage(JSON.toJSONString(container));
        log.setStatus(MessageStatus.SENDING);
        Date now = new Date();
        log.setNextRetryTime(new Date(now.getTime() + 60 * 1000));
        log.setCreateTime(now);
        log.setUpdateTime(now);
        brokerMessageLogMapper.add(log);
    }

    @Override
    public void addMessageLog(BaseMessage message) {
        BrokerMessageLog log = new BrokerMessageLog();
        log.setId(message.getMessageId());
        log.setMessage(JSON.toJSONString(message));
        log.setStatus(MessageStatus.SENDING);
        Date now = new Date();
        log.setNextRetryTime(new Date(now.getTime() + 60 * 1000));
        log.setCreateTime(now);
        log.setUpdateTime(now);
        brokerMessageLogMapper.add(log);
    }
}
