package com.ict.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;
// pom.xml에서 runtime 주석처리
@Aspect
@Log4j
@Component
public class LogAdvice {
	
	@Before("execution(* com.ict.service.SampleService*.*(..))")
					   // 맨앞 *은 접근제한자 전체 허용, SampleService뒤 *은 뒤에 뭐가 오든 허용, 마지막 *은 메서드명 모두 허용
	public void logBefore() {
		System.out.println("===================");
	}
}
