package com.yangwenjie.delayqueue.core;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 扫描延迟任务桶中的任务，放到准备队列中
 * 
 * @author Yang WenJie
 * @date 2018/1/27 下午8:03
 */
@Slf4j
public class DelayBucketHandler implements Runnable {

    private String delayBucketKey;

    public DelayBucketHandler(String delayBucketKey){
        this.delayBucketKey = delayBucketKey;
    }

    public String getDelayBucketKey() {
        return delayBucketKey;
    }

    public void setDelayBucketKey(String delayBucketKey) {
        this.delayBucketKey = delayBucketKey;
    }

    @Override
    public void run() {
        while (true) {
            try {
//                log.info(Thread.currentThread().getName()+"轮训...");
                ScoredSortedItem item = DelayBucket.getFromBucket(this.delayBucketKey);
                // 没有任务
                if (item == null) {
                    sleep();
                    continue;
                }
                // 延迟时间没到
                if (item.getDelayTime() > System.currentTimeMillis()) {
                    sleep();
                    continue;
                }

                DelayQueueJob delayQueueJod = DelayQueueJobPool.getDelayQueueJod(item.getDelayQueueJodId());
                // 延迟任务元数据不存在
                if (delayQueueJod == null) {
                    DelayBucket.deleteFormBucket(this.delayBucketKey, item);
                    continue;
                }

                // 再次确认延时时间是否到了
                if (delayQueueJod.getDelayTime() > System.currentTimeMillis()) {
                    // 删除旧的
                    DelayBucket.deleteFormBucket(this.delayBucketKey, item);
                    // 重新计算延迟时间
                    DelayBucket.addToBucket(this.delayBucketKey,
                                            new ScoredSortedItem(delayQueueJod.getId(), delayQueueJod.getDelayTime()));
                } else {
                    ReadyQueue.pushToReadyQueue(delayQueueJod.getTopic(), delayQueueJod.getId());
                    DelayBucket.deleteFormBucket(this.delayBucketKey, item);
                }

            } catch (Exception e) {
                log.error("扫描delaybucket出错：", e);
            }
        }
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }
}
