package com.lrm.interceptor;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @title: WebConfig
 * @projectName: blog
 * @description: null
 * @author: zhang·chuan
 * @date: 2021/10/15 16:14
 */
// 定义拦截哪些路径
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				//拦截admin下的所有请求,除了/admin和/admin/login,防止不登陆就进入
				.addPathPatterns("/admin/**")
				.excludePathPatterns("/admin")
				.excludePathPatterns("/admin/login");
		super.addInterceptors(registry);
	}
}
