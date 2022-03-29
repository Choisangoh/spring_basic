package com.ict.controller.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 빈 컨테이너 등록
@Component
public class Library {

	// Library가 Book에 의존하는 상태로 만들기
	// Book의 기능을 Library가 호출하려면, 내부 맴버변수로 Book이 필요하다.
	private Book book;
	
	// 단, 생성자는 void생성자(아무것도 입력받지 않고 아무것도 안하는)로 만들고
	public Library() {
		
	}
	// 맴버변수 Book에 대한 setter만 만들기(public void setBook(Book book)
	// @Autowired는 1. 맴버변수 위, 2. 생성자 위, 3. setter위에 붙일 수 있다.
	@Autowired
	public void setBook(Book book) {
		this.book = book;
	}
	// 맴버변수 Book을 이용해 "도서관에서 책을 읽습니다"라는 문장을 실행하는
	// browse 메서드 생성
	public void browse() {
		System.out.print("도서관에서 ");
		this.book.read();		
	}
}
