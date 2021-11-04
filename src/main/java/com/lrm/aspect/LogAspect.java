package com.lrm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @title: LogAspect
 * @projectName: blog
 * @description: null
 * @author: zhang·chuan
 * @date: 2021/10/13 16:51
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
	//拦截这个包下面所有的类的所有的方法
	@Pointcut("execution(* com.lrm.controller.*.*(..))")
	public void log(){}


	//方法之前执行
	@Before("log()")
	public void doBefore(JoinPoint joinPoint){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request =attributes.getRequest();
		String url =request.getRequestURL().toString();
		String ip =request.getRemoteAddr();
		String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		RquestLog rquestLog = new RquestLog(url,ip,classMethod,args);
		log.info("Request : {}",rquestLog);
	}

	@After("log()")
	//方法之后执行
	public void doAfter(){
		// log.info("-------doAfter---------");
	}

	//方法return之后执行
	@AfterReturning(returning = "result",pointcut = "log()")
	public void doAterReturn(Object result){
		log.info("Result : {}",result);
	}

	private class RquestLog{
		private String url;
		private String ip;
		private String classMthod;
		private Object[] args;

		public RquestLog(String url, String ip, String classMthod, Object[] args) {
			this.url = url; // 访问的哪一个地址
			this.ip = ip; //访问者的ip地址
			this.classMthod = classMthod; //访问的方法
			this.args = args; //访问的其他参数
		}

		@Override
		public String toString() {
			return "RquestLog{" +
					"url='" + url + '\'' +
					", ip='" + ip + '\'' +
					", classMthod='" + classMthod + '\'' +
					", args=" + Arrays.toString(args) +
					'}';
		}
	}
}
