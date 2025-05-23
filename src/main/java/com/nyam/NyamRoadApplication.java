package com.nyam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nyam.model.dao")
public class NyamRoadApplication {

	public static void main(String[] args) {
		SpringApplication.run(NyamRoadApplication.class, args);
	}
}
