package com.push;

import com.push.core.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author chenly1
 * @create 2019-12-13 17:49
 */
public class Main {
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(AppConfig.class);
	}
}
