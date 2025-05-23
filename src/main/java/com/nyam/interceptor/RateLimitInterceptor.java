package com.nyam.interceptor;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor{
	private final Bucket bucket;
	
	public RateLimitInterceptor() {
		// 특정 시간 마다 특정 개수의 Token으로 Bucket을 채운다.
		Refill refill = Refill.intervally(3000, Duration.ofMinutes(1));
		
		// 특정 시간 마다의 Token 개수 제한을 건다.
		Bandwidth limit = Bandwidth.classic(3000, refill);
		
		this.bucket = Bucket.builder()
				.addLimit(limit)
				.build();
		
	}
	
	// Bucket내 token의 개수가 남아 있다면 true,
	// Bucket내 token이 없다면 Too many requests.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		if(bucket.tryConsume(1)) {
			return true;
		}else {
			response.setStatus(429);
			response.getWriter().write("Rate Limit Exceeded");
			
			return false;
		}
		
		
	}
	
	
}
