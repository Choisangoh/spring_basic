package com.ict.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	@Before("execution(* com.ict.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		System.out.println("str1 : " +  str1);
		System.out.println("str2 : " +  str2);
	}
	
	@AfterThrowing(pointcut = "execution(* com.ict.service.SampleService*.*(..))", throwing ="exception")
	public void logException(Exception exception) {
		System.out.println("Exception...!!!");
		System.out.println("exception");
	}
	
	@Around("execution(* com.ict.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		
		System.out.println("Target : " + pjp.getTarget());
		System.out.println("Param : " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		///////////// 이전까지 핵심로직은 실행 안 됨 /////////////
		try {
			result = pjp.proceed(); // 핵심로직 실행
			///////// 핵심로직 실행 후 실행할 코드들 ////////
		}catch(Throwable e) {
			// 예외 발생 시 실행
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis(); // 메서드 실행이 모두 끝난 직후 시간 저장
	
		System.out.println("TIME : "  + (end-start)); // 소요시간 계산해 로그에 찍기
		return result;
	}
	
}
