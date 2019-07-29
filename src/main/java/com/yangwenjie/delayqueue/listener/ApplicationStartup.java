package com.yangwenjie.delayqueue.listener;

import com.yangwenjie.delayqueue.core.DelayBucketHandler;
import com.yangwenjie.delayqueue.core.DelayQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yang WenJie
 * @date 2018/1/27 下午10:15
 */
@Slf4j
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ExecutorService executorService = Executors.newFixedThreadPool((int)DelayQueue.DELAY_BUCKET_NUM);
        log.info("init...");
        for (int i = 0; i < DelayQueue.DELAY_BUCKET_NUM; i++) {
            executorService.execute(new DelayBucketHandler(DelayQueue.DELAY_BUCKET_KEY_PREFIX+i));
        }
    }
}
