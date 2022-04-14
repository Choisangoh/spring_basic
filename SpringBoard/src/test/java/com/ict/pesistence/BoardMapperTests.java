package com.ict.pesistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.domain.BoardVO;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Autowired
	private BoardMapper boardMapper;
	
	/*
	@Test
	public void testGetList() {
		List<BoardVO> result = boardMapper.getList();
		System.out.println("저장된 게시물 정보 : " + boardMapper.getList());
	}
	
	// insert를 실행할 테스트 코드 작성
	//@Test
	public void testInsert() {
		// vo를 입력받는 insert 메서드 특성상
		// title, content, writer가 채워진 vo먼저 생성
		BoardVO vo = new BoardVO();
		System.out.println("채워넣기 전 : " + vo);
		
		// 입력할 글에대한 제목, 글쓴이, 본문을 vo에 넣어줌
		vo.setTitle("새 제목");
		vo.setContent("새 내용");
		vo.setWriter("새 글쓴이");
		
		System.out.println("채워넣기 후 : " + vo);
		
		boardMapper.insert(vo);
	}
	
	//@Test
	public void testSelect() {
		boardMapper.select(5);	
	}
	
	//@Test
	public void testDelete() {
		boardMapper.delete(22);
	}

	//@Test
	public void tesetUpdate() {
		BoardVO vo = new BoardVO();
		System.out.println("전달데이터 입력 안된 vo : " + vo);
		
		vo.setTitle("수정 제목");
		vo.setContent("수정 내용");
		vo.setBno(4);
		
		System.out.println("전달데이터 입력 된 vo : " + vo);
		
		boardMapper.update(vo);
	}
	*/
	@Test
	public void SearchList() {
		SearchCriteria cri = new SearchCriteria();
		cri.setKeyword("테스트");
		cri.setSearchType("t");
		boardMapper.getList(cri);
	}
}
