package com.yangwenjie.delayqueue.listener;

import com.yangwenjie.delayqueue.common.NamedThreadFactory;
import com.yangwenjie.delayqueue.core.DelayBucketHandler;
import com.yangwenjie.delayqueue.core.DelayQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Yang WenJie
 * @date 2018/1/27 下午10:15
 */
@Slf4j
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ExecutorService executorService = new ThreadPoolExecutor((int) DelayQueue.DELAY_BUCKET_NUM,
                                                                 (int) DelayQueue.DELAY_BUCKET_NUM, 0L,
                                                                 TimeUnit.MILLISECONDS,
                                                                 new LinkedBlockingQueue<Runnable>(),
                                                                 new NamedThreadFactory("delay-bucket", true));
        log.info("init...");
        for (int i = 0; i < DelayQueue.DELAY_BUCKET_NUM; i++) {
            executorService.execute(new DelayBucketHandler(DelayQueue.DELAY_BUCKET_KEY_PREFIX + i));
        }
    }
}
