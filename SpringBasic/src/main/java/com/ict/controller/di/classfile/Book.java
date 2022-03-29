package com.ict.controller.di.classfile;

import org.springframework.stereotype.Component;

// 빈 컨테이너에 등록하기
@Component
public class Book {

	public void read() {
		System.out.println("책을 읽습니다.");
	}
}
