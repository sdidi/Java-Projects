package com.springboot.rediseEx2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class SpringBootRedisAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisAppApplication.class, args);
	}
}
