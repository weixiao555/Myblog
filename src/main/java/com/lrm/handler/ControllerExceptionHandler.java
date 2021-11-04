package com.lrm.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @title: ControllerExceptionHandler
 * @projectName: blog
 * @description: null
 * @author: zhang·chuan
 * @date: 2021/10/13 16:02
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

	//标识这是可以做异常处理
	@ExceptionHandler()
	public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception{
		//打印日志的信息
		log.error("Rquest URL: {},Exception : {}",request.getRequestURL(),e);

		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
			throw e;
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("url",request.getRequestURL());
		mv.addObject("exception",e);
		mv.setViewName("error/error");
		return mv;
	}
}
