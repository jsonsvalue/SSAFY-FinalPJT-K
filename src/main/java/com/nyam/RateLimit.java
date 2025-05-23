package com.nyam;

import java.time.Duration;

import org.springframework.stereotype.Component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

@Component
public class RateLimit {
	public static void main(String[] args) {
		
//		// 특정 시간 마다 특정 개수의 Token으로 Bucket을 채운다.
//		Refill refill = Refill.intervally(10, Duration.ofMinutes(1));
//		
//		// 특정 시간 마다의 Token 개수 제한을 건다.
//		Bandwidth limit = Bandwidth.classic(10, refill);
//		
//		Bucket bucket = Bucket.builder()
//				.addLimit(limit)
//				.build();
//		
//		for (int i = 0; i < 11; i++) {
//	        if (!bucket.tryConsume(1)) {
//	            throw new RuntimeException("Rate limit exceeded");
//	        }
//	    }
//		
//		System.out.println("Within Rate Limit");
			
	}
	
	public RateLimit() {
		// 특정 시간 마다 특정 개수의 Token으로 Bucket을 채운다.
		Refill refill = Refill.intervally(10, Duration.ofMinutes(1));
		
		// 특정 시간 마다의 Token 개수 제한을 건다.
		Bandwidth limit = Bandwidth.classic(10, refill);
		
		Bucket bucket = Bucket.builder()
				.addLimit(limit)
				.build();
				
	}
	
	
		
		
	
	
		
	
}
