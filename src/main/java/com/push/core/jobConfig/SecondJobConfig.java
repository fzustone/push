package com.push.core.jobConfig;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.push.core.MyElasticJobListener;
import com.push.job.SecondJob;
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
public class SecondJobConfig {
	@Resource
	private ZookeeperRegistryCenter regCenter;

	@Value("${secondJob.jobName}")
	private String jobName;
	@Value("${secondJob.cron}")
	private String cron;
	@Value("${secondJob.shardingTotalCount}")
	private int shardingTotalCount;

	@Autowired
	public SecondJob secondJob;

	@Bean(initMethod = "init")
	public JobScheduler secondJobScheduler(final SecondJob secondJob) {
		MyElasticJobListener myElasticJobListener = new MyElasticJobListener();
		return new SpringJobScheduler(secondJob, regCenter, getLiteJobConfiguration(), myElasticJobListener);
	}

	private LiteJobConfiguration getLiteJobConfiguration() {
		//定义Lite作业根配置
		return LiteJobConfiguration.newBuilder(getSimpleJobConfiguration())
				.overwrite(true)
				.build();
	}

	private SimpleJobConfiguration getSimpleJobConfiguration() {
		return new SimpleJobConfiguration(
				JobCoreConfiguration.newBuilder(jobName, cron, shardingTotalCount)
						.build(), SecondJob.class.getCanonicalName());
	}

}
