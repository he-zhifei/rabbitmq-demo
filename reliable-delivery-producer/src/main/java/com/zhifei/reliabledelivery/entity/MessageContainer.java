package com.zhifei.reliabledelivery.entity;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageContainer<T> implements Serializable {

    private static final long serialVersionUID = 9111357402963030257L;

    /**
     * 消息id，使用固定规则生成，例如：prefix+时间戳+uuid
     */
    @Getter
    @Setter
    private String messageId;

    /**
     * 具体消息
     */
    @Getter
    @Setter
    private T body;

}
