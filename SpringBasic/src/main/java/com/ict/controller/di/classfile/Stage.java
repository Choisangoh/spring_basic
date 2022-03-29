package com.ict.controller.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Stage {
	
	// 무대는 가수가 있어야 성립하며, 그때그때 다른 가수를 세울 수도 있다.
	// @Autowired 입력시 해당 자료형과 일치하는 부품이 공장 내에 존재하면 자동으로 결합해준다.
	// 변수 위, 생성자 위 중 하나를 고르면 된다.
	// @Autowired는 @Inject로 대체가능하다.
	//@Autowired
	//@Qualifier("balladSinger")
	//@Qualifier("popSinger")
	private Singer singer; // 가수 맴버변수
	
	// 4버전까지 Autowired 사용시 아무 작업도 하지 않는 디폴트 생성자가 추가되어야 한다.
	 public Stage() {		
	 }
	
	public Stage(Singer singer) {
		this.singer = singer; // 무대에 설 가수를 입력해야 생성자가 호출되도록 처리
	}
	
	public void perform() {
		System.out.print("무대에서 ");
		this.singer.sing();
	}	
}
