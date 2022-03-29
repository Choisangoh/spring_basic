package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 어노테이션에 네종류 있는데(@Component, @Repository, @Service, @Controller)
@Controller
public class BasicController {
		
	// RequestMapping의 value는 localhost:8181/이란 주소로 접속 시 해당 로직이 실행될지 결정
	// 아무것도 안 적으면 기본적으로 get방식을 허용
	@RequestMapping(value="/goA")
	// 아래에 해당 주소로 접속시 실행하고 싶은 메서드 작성
	public String goA() {
		System.out.println("goA접속 감지");
		// return "goA";라고 적으면 view폴더 내부의 goA.jsp파일을 보여준다.
		return "goA";
	}
	
	@RequestMapping(value="/goB")
	public String goB() {
		return "b";
	}
	
	@RequestMapping(value="/choi")
	public String sang() {
		return "oh";
	}
	
	// 외부에서 전송하는 데이터는 메서드 선언부에 선언된 변수로 받는다.
	// 이름만 일치하면 알아서 받아온다.
	// 자료형을 신경쓸 필요 없다.
	@RequestMapping(value="/getData")
						// ?data1=데이터1&data2=데이터2 에 해당하는 요소를 받아온다.
	public String getDate(String data1, int data2) {
		System.out.println("data1에 든 값 : " + data1);
		System.out.println("data12에 든 값 : " + data2);
		System.out.println("data1가 정수임을 증명 " + (data2+100));
		return "getResult";
		
	}
	
	// 외부에서 전송하는 데이터를 /getMoney 주소로 받아오기
	// 이 주소는 int won이라는 형식으로 금액을 받아서 
	// 환율에 따른 환전 금액을 콘솔에 찍기. 환전화페는 임의로 정해서
	// 결과페이지는 exchange.jsp
	// 메서드명은 임의로 만들기
	@RequestMapping(value="/getMoney")
	public String Money(int won) {
		System.out.println(won + "달러 환전금액은 " + won*1220 + "원 입니다.");
		return "exchange";
	}
}
