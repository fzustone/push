package com.push.core;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by chenly
 *
 * 任务总监听器
 */

@Slf4j
public class MyElasticJobListener implements ElasticJobListener{

    private long beginTime = 0;


    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        beginTime = System.currentTimeMillis();

        log.info("===>{} JOB BEGIN TIME: {} <===",shardingContexts.getJobName(), transformTime(beginTime));
    }

    private LocalDateTime transformTime(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time),
                ZoneId.systemDefault());
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        long endTime = System.currentTimeMillis();
        log.info("===>{} JOB END TIME: {},TOTAL CAST: {} <===",shardingContexts.getJobName(), transformTime(endTime), endTime - beginTime);
    }


}
