package com.push.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenly1
 * @create 2019-12-13 16:48
 */
@Configuration
@ComponentScan("com.push")
public class AppConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setOrder(Ordered.HIGHEST_PRECEDENCE);
		configurer.setIgnoreResourceNotFound(true);

		final PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

		List<Resource> resources = new ArrayList<>();
		resources.add(resourceResolver.getResource("classpath:/jdbc.properties"));
		resources.add(resourceResolver.getResource("classpath:/job.properties"));
		resources.add(resourceResolver.getResource("classpath:/reg.properties"));
		configurer.setLocations(resources.toArray(new Resource[0]));
		return configurer;
	}
}
