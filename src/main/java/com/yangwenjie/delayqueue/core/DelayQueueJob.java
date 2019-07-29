package com.yangwenjie.delayqueue.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 延迟任务
 *
 * @author Yang WenJie
 * @date 2018/1/27 上午12:18
 */
@Data
public class DelayQueueJob {

    /**
     * 延迟任务的唯一标识，用于检索任务
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private long   id;

    /**
     * 任务类型（具体业务类型）
     */
    private String topic;

    /**
     * 任务的执行时间
     */
    private long   delayTime;

    /**
     * 任务的执行超时时间
     */
    private long   ttrTime;

    /**
     * 任务具体的消息内容，用于处理具体业务逻辑用
     */
    private String message;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DelayQueueJob{");
        sb.append("id=").append(id);
        sb.append(", topic='").append(topic).append('\'');
        sb.append(", delayTime=").append(delayTime);
        sb.append(", ttrTime=").append(ttrTime);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
