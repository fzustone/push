package com.push.core.jobConfig;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.push.core.SimpleJobBaseConfig;
import com.push.job.SpringSimpleJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author chenly1
 * @create 2019-12-13 17:17
 */
@Configuration
public class SimpleJobTestConfig extends SimpleJobBaseConfig {
	@Resource
	private CoordinatorRegistryCenter regCenter;
	@Resource
	private JobEventConfiguration jobEventConfiguration;

	@Value("${simpleJob.cron}")
	private String cron;
	@Value("${simpleJob.shardingTotalCount}")
	private int shardingTotalCount;
	@Value("${simpleJob.shardingItemParameters}")
	private String shardingItemParameters;

	@Bean
	public SimpleJob simpleJob() {
		return new SpringSimpleJob();
	}

	@Bean(initMethod = "init")
	public JobScheduler simpleJobScheduler(final SimpleJob simpleJob) {
		return new SpringJobScheduler(simpleJob, regCenter,
				getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters),
				jobEventConfiguration);
	}
}
