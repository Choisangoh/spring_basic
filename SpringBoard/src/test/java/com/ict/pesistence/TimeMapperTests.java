package com.ict.pesistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.time.mapper.TimeMapper;
import com.sun.tools.javac.util.Log;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {
	
	// 인터페이스는 호출하려면 구현클래스화 해야함
	// 의존성 주입(@Autowired) 하면 자동으로 마이바티스가 구현해줌
	@Autowired
	private TimeMapper timeMapper;
	
	//@Test
	public void testGetTime() {
		System.out.println("현재 시간 조회중");
		System.out.println(timeMapper.getTime());
	}
	
	@Test
	public void testGetTime2() {
		System.out.println("getTime2 호출!!!");
		System.out.println(timeMapper.getTime2());
	}
}
