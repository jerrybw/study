package com.skch.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.skch.bean.UserQuestion;

public class CommonInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		String parameter = request.getParameter("jsonstr");
//		Gson gson = new Gson();
//		UserQuestion fromJson = gson.fromJson(parameter,UserQuestion.class);
//		String userId = fromJson.getUserId();
//		if(userId == null || userId.equals("")){
//			response.setContentType("application/json; charset=utf-8");
//			request.getRequestDispatcher("/login.htm").forward(request, response);
//			return false;
//		}else {
		
//		System.out.println("拦截");
		return super.preHandle(request, response, handler);
//		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}
