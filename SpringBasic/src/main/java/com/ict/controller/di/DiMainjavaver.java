package com.ict.controller.di;

import com.ict.controller.di.classfile.Book;
import com.ict.controller.di.classfile.Library;
import com.ict.controller.di.classfile.Singer;
import com.ict.controller.di.classfile.Stage;

public class DiMainjavaver {

	public static void main(String[] args) {
		/*
		// 가수, 무대를 생성한 다음
		Singer singer = new Singer();
		Stage stage = new Stage(singer);
		
		// 무대의 공연 메서드 호출
		stage.perform();
		
		// 가수가 그냥 노래하는것도 가능한지 테스트
		singer.sing();
		*/
		
		Book book = new Book();
		// Library library = new Library(book); 생성자 주입이 가능할때는 생성하면서 book을 Library에 넣으면 됨
		Library library = new Library(); // 아무동작도 하지않는 Library의 생성자
		library.setBook(book); // 맴버변수 book 채우기
		library.browse();
		
		
	}
}
