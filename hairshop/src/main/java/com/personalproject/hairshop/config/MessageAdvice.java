package com.personalproject.hairshop.config;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//@Component
//@Aspect
public class MessageAdvice {
	
	
/*	private static final Logger logger = LoggerFactory.getLogger(MessageAdvice.class);
	
	@Around("(within(@org.springframework.stereotype.Controller *)"
        + "|| within(@org.springframework.web.bind.annotation.RestController *))"
        + "&& execution(public * *(..))")
	public void startLog(JoinPoint jp) {
		logger.info("매개변수 : " + Arrays.toString(jp.getArgs()));
	}*/
}
