package com.push.core;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 作业事件数据库监听，暂时不需要用数据库
 * @author chenly
 */

public class JobEventConfig {

	@Resource
	private DataSource dataSource;

	@Bean
	public JobEventConfiguration jobEventConfiguration() {
		return new JobEventRdbConfiguration(dataSource);
	}
}
