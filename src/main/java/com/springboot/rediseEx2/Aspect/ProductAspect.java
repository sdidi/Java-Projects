package com.springboot.rediseEx2.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.rediseEx2.model.Product;


@Aspect
@Component
public class ProductAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductAspect.class);
	
	@Before(value = "execution(* com.springboot.rediseEx2.services.ProductService.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		LOGGER.info("=======Before method:" + joinPoint.getSignature());

		LOGGER.info("=======Creating a product  - " );
	}
}
