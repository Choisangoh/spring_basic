package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@RequestMapping(value="/getData", method=RequestMethod.POST)
						// /getData?data1=데이터1&data2=데이터2 에 해당하는 요소를 받아온다.
	public String getDate(String data1, int data2, Model model) {		
		System.out.println("data1에 든 값 : " + data1);
		System.out.println("data12에 든 값 : " + data2);
		System.out.println("data1가 정수임을 증명 " + (data2+100));
		model.addAttribute("data1", data1);
		model.addAttribute("data2", data2);
		return "getResult";
		
	}
	
	// 외부에서 전송하는 데이터를 /getMoney 주소로 받아오기
	// 이 주소는 int won이라는 형식으로 금액을 받아서 
	// 환율에 따른 환전 금액을 콘솔에 찍기. 환전화페는 임의로 정해서
	// 결과페이지는 exchange.jsp
	// 메서드명은 임의로 만들기
	@RequestMapping(value="/getMoney", method=RequestMethod.POST) // post방식으로만 받도록 처리
	// 1. 포워딩 시 바인딩을 하고 싶다면 Model model 선언
	public String Money(int won, Model model) {
		System.out.println(won + "달러 환전금액은 " + won*1220 + " 입니다.");
		double result = (won * 1220);
		// 2. model.addAttribute("보낼 이름", 보낼 자료);
		// 넘어간 데이터는 .jsp파일에서 el을 이용해 출력
		// ex => model.addAttribute("test", 자료)로 바인딩한 경우 .jsp에서 출력 가능 
		model.addAttribute("result", result);
		model.addAttribute("won", won);
		return "exchange";
	}
	
	// form 페이지와 결과페이지를 분리해야 한다.
	// 다만 목적지 주소가 .jsp기준이 아닌, @RequestMapping상의 주소기준으로 간다.
	// 주소 moneyForm으로 연결되도록 아래에 어노테이션 + 메서드를 구성
	// moneyForm.jsp로 연결
	// moneyForm.jsp에는 목적지를 #으로 하고
	// name=won인 폼을 추가로 만들기 
	
	// 1. @RequestMapping에 어떤 주소로 접속해야 하는지 작성
	@RequestMapping(value="/moneyForm")
	// 2. public String 메서드() 만들기
	public String moneyForm() {
		// 3. return구문 뒤에 연결함 .jsp파일의 이름 적기(확장자는 X)
		return "moneyForm";
	}
	
	@GetMapping(value="/dataForm")
	public String dataForm() {
		return "dataForm";
	}
	
	
	// 스프링 5버전부터 허용
	// @요청메서드Mapping은 해당 메서드만 허용하는 이노테이션
	// PostMapping, GetMapping
	@PostMapping(value="/onlyGet")
	public String onlyGet() {
		return "onlyGet";
	}
	
	// 성적 입력 폼 접근 로직
	@GetMapping(value="/score")
	public String scoreForm() {
		return "scoreForm";
	}
	
	@PostMapping(value="/score")
	public String score() {
		return "scoreResult";
	}
	
}
