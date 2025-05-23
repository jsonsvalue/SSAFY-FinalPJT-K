package com.nyam.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		// CORS preflight 요청 조차 막아버리면 CORS 문제가 생길 수 있기 때문에,
		// Interceptor에서 CORS preflight은 지나가게 해줘야 한다.
		// 그렇지 않으면 CORS를 허용하지 않아서, CORS 에러가 난다.
		
		if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			return true;
		}
		
		// Session이 존재할 때만 가져오고, 없다면 null을 반환한다.
		// 그러니까 Session이 없다고 새로 생성하지 않는다.
		HttpSession session = request.getSession(false);
		
		if(session== null || session.getAttribute("loginUser")== null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
			return false;
		}
		
		return true;
	}
	
	
	
}
