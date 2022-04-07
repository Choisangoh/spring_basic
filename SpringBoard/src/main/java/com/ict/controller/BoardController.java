package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ict.domain.BoardVO;
import com.ict.mapper.BoardMapper;

// 컨트롤러가 컨트롤러 기능을 할 수 있도록 처리
@Controller
public class BoardController {
	
	// 전체 회원을 보려면, 회원목록을 들고오는 메서드를 실행해야 하고
	// 그러면, 그 메서드를 보유하고 있는 클래스를 선언하고 주입해야한다.
	// BoardMapperTests 참고해서 하기
	@Autowired
	private BoardMapper boardMapper;
	
	// 전체 글 목록을 볼 수 있는 페이지인 boardList.jsp로 연결되는 
	// /boardList주소를 get방식으로 선언
	// 메서드 내부에서는 boardMapper의 .getList를 호출해 그 결과를 바인딩
	@GetMapping(value="/boardList")
	public String boardList(Model model) {
		// model.addAttibute("바인딩 이름", 바인딩 자료);
		List<BoardVO> boardList = boardMapper.getList();
		model.addAttribute("boardList", boardList);
		return "boardList";
	}

	// 글 하나만 조회할 수 있는 디테일 페이지인 boardDetail.jsp로 연결되는
	// /boardDetail주소를 get방식으로 선언
	// 주소 뒤에 ?bno=번호 형식으로 화면에는 적힌 번호 글만 조회가능
	// @PathVariable적용 방식으로 바꾸기
	@GetMapping(value="/boardDetail")
	public String boardDetail(@PathVariable long bno, Model model) {
		BoardVO boardDetail = boardMapper.select(bno);
		model.addAttribute("boardDetail", boardDetail);
		return "boardDetail";
	}
}
