package com.push.core;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenly1
 * @create 2019-12-13 16:23
 */
@Configuration
public class ZookeeperConfig {

	@Value("${regCenter.serverList}")
	private String serverList;
	@Value("${regCenter.namespace}")
	private String namespace;

	@Bean(initMethod = "init")
	public ZookeeperRegistryCenter regCenter( ) {
		return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
	}

	@Bean
	public ExponentialBackoffRetry retryPolicy( ) {
		return new ExponentialBackoffRetry(1000,3);
	}

	@Bean
	public CuratorFramework curatorFramework(ExponentialBackoffRetry retryPolicy ) {
		return CuratorFrameworkFactory.newClient(serverList,5000,3000,retryPolicy);
	}
}
