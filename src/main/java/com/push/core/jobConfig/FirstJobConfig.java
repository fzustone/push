package com.push.core.jobConfig;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.push.core.MyElasticJobListener;
import com.push.job.FirstJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author chenly1
 * @create 2019-12-13 17:17
 */
@Configuration
public class FirstJobConfig {
	@Resource
	private ZookeeperRegistryCenter regCenter;


	@Value("${firstJob.jobName}")
	private String jobName;
	@Value("${firstJob.cron}")
	private String cron;
	@Value("${firstJob.shardingTotalCount}")
	private int shardingTotalCount;
	@Value("${firstJob.shardingItemParameters}")
	private String shardingItemParameters;

	@Autowired
	public FirstJob firstJob;

	@Bean(initMethod = "init")
	public JobScheduler firstJobScheduler(final FirstJob firstJob) {
		MyElasticJobListener elasticJobListener = new MyElasticJobListener();
		return new SpringJobScheduler(firstJob, regCenter, getLiteJobConfiguration(), elasticJobListener);
	}

	public LiteJobConfiguration getLiteJobConfiguration() {
		return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(buildJobCoreConfiguration(), FirstJob.class.getCanonicalName()))
				.overwrite(true)
				.build();
	}

	private JobCoreConfiguration buildJobCoreConfiguration() {
		return JobCoreConfiguration.newBuilder(jobName, cron, shardingTotalCount)
				.shardingItemParameters(shardingItemParameters)
				.build();
	}

}
