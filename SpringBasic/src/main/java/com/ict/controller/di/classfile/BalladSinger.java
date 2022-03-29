package com.ict.controller.di.classfile;

import org.springframework.stereotype.Component;

@Component
public class BalladSinger extends Singer{
	
	// Singer를 상속하고 sing()을 오버라이딩해서
	// "발라드 가수가 감성적인 노래를 부른다."
	// 를 콘솔에 찍도록 설정하기
	// 그리고 빈 컨테이너에 등록
	@Override
	public void sing() {
	System.out.println("발라드가수가 감성적인 노래를 부른다.");
	}
}
