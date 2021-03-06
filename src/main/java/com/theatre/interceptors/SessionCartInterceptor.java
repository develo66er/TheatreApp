package com.theatre.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.theatre.models.CartDTO;


public class SessionCartInterceptor  extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("cart") == null)
			session.setAttribute("cart", new CartDTO());
		return super.preHandle(request, response, handler);
	}
}
