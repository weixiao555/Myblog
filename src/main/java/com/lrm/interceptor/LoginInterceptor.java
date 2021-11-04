package com.lrm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @title: LoginInterceptor
 * @projectName: blog
 * @description: null
 * @author: zhang·chuan
 * @date: 2021/10/15 16:09
 */
// 定义拦截的规则
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
	                         HttpServletResponse response,
	                         Object handler) throws Exception {
		if (request.getSession().getAttribute("user") == null){
			response.sendRedirect("/admin");
			return false;
		}

			return true;
	}
}
