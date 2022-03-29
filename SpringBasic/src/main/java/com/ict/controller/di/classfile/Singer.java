package com.ict.controller.di.classfile;

// import org.springframework.stereotype.Component;

// @Component
// Singer를 상속한 발라드가수와 팝가수 생성
public class Singer {

	// 가수는 무대가 있건 없건 노래를 할 수 있기때문에
	// 다른 어떠한 요소 없이 오직 노래기능만 넣어준다.
	public void sing() {
		System.out.println("가수가 노래를 한다.");
	}
}
