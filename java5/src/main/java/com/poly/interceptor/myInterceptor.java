package com.poly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.poly.controller.sessionSV;
import com.poly.model.account;


@Component
public class myInterceptor implements HandlerInterceptor {
	
	@Autowired
	sessionSV session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		boolean isAdmin = request.getRequestURI().contains("/admin/");
		try {
			account acc =  session.getAttribute("acc");
			
			if(acc == null) {
				
				request.setAttribute("error","Hãy Login Trước !");
				response.sendRedirect("/login");
				return false;
			}
			if(isAdmin) {
				if(!acc.isAdmin()) {
					response.sendRedirect("/login");
				}
			}
			return true;	
		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect("/login");
			return false;	
		}
		 
		}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
}
