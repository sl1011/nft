package com.shitouren.core.config.interceptor;

import com.shitouren.core.config.redis.CloudRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CloudHandlerInterceptor extends HandlerInterceptorAdapter {


    @Autowired
	private CloudRedisTemplate cloudRedisTemplate;

	// 在控制器执行前调用
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return CloudSecurityVerify.signVerify(cloudRedisTemplate, request,response, handler);
	}

	// 在后端控制器执行后调用
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	// 整个请求执行完成后调用
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	
}
