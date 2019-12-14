package com.push.core;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenly1
 * @create 2019-12-13 16:32
 */
@Configuration
public class DataBaseConfig {
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String driverUrl;
	@Value("${jdbc.username}")
	private String driverUsername;
	@Value("${jdbc.password}")
	private String driverPassword;
	@Value("${jdbc.initialSize}")
	private String initialSize;
	@Value("${jdbc.minIdle}")
	private String minIdle;
	@Value("${jdbc.maxActive}")
	private String maxActive;
	@Value("${jdbc.maxWait}")
	private String maxWait;
	@Value("${jdbc.timeBetweenEvictionRunsMillis}")
	private String timeBetweenEvictionRunsMillis;
	@Value("${jdbc.minEvictableIdleTimeMillis}")
	private String minEvictableIdleTimeMillis;
	@Value("${jdbc.validationQuery}")
	private String validationQuery;
	@Value("${jdbc.testWhileIdle}")
	private String testWhileIdle;
	@Value("${jdbc.testOnBorrow}")
	private String testOnBorrow;
	@Value("${jdbc.poolPreparedStatements}")
	private String poolPreparedStatements;
	@Value("${jdbc.maxPoolPreparedStatementPerConnectionSize}")
	private String maxPoolPreparedStatementPerConnectionSize;

	@Bean(name = "dataSource", destroyMethod = "close")
	public DruidDataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(driverUrl);
		dataSource.setUsername(driverUsername);
		dataSource.setPassword(driverPassword);
		dataSource.setInitialSize(Integer.parseInt(initialSize));
		dataSource.setMinIdle(Integer.parseInt(minIdle));
		dataSource.setMaxActive(Integer.parseInt(maxActive));
		dataSource.setMaxWait(Long.parseLong(maxWait));
		dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(timeBetweenEvictionRunsMillis));
		dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(minEvictableIdleTimeMillis));
		dataSource.setValidationQuery(validationQuery);
		dataSource.setTestWhileIdle(Boolean.parseBoolean(testWhileIdle));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(testOnBorrow));
		dataSource.setPoolPreparedStatements(Boolean.parseBoolean(poolPreparedStatements));
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(maxPoolPreparedStatementPerConnectionSize));
		return dataSource;
	}
}
