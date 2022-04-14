package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ict.domain.BoardVO;
import com.ict.domain.PageMaker;
import com.ict.domain.SearchCriteria;
import com.ict.service.BoardService;

// 컨트롤러가 컨트롤러 기능을 할 수 있도록 처리
@Controller
public class BoardController {
	
	// 컨트롤러는 Service만 호출하도록 구조를 바꾼다
	// Service를 BoardController 내부에서 쓸 수 있도록 선언/주입
	@Autowired
	private BoardService service;
	
	// 전체 글 목록을 볼 수 있는 페이지인 boardList.jsp로 연결되는 
	// /boardList주소를 get방식으로 선언
	// 메서드 내부에서는 boardMapper의 .getList를 호출해 그 결과를 바인딩
	@GetMapping("/boardList")
	// @RequestParam(name="사용할 변수명", defaultValue="지정하고싶은 기본값") 
	// @GetMapping({"/boardList/{pageNum}", "/boardList"})
	// @PathVariable의 경우 defaultValue를 직접 줄 수 없으나, required=false를 이용해 필수입력을 안받게 처리한 후
	// 컨트롤러 내부에서 디폴트값을 입력할 수 있다.
	// 기본형 자료는 null을 저장할 수 없기 떄문에 wrapper class를 이용해 Long을 선언
	//public String boardList(@PathVariable(required=false) Long pageNum, Model model) { 		
	//if(pageNum == null) {
	//pageNum = 1L; // Long형은 숫자 뒤에 L을 붙여야 대입된다.
	//}
	public String boardList(SearchCriteria cri, Model model) {
		// model.addAttibute("바인딩 이름", 바인딩 자료);
		List<BoardVO> boardList = service.getList(cri);	
		model.addAttribute("boardList", boardList);
		
		// 버튼 처리를 위해 추가로 페이지메이커 생성 및 세팅
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri); // cri 입력
		int countPage = service.countPageNum(cri);// 실제 DB내 글 개수 받아오기
		pageMaker.setTotalBoard(countPage); // calcData()호출도 되면서 순식간에 prev, next, startPage, endPage 세팅
		model.addAttribute("pageMaker", pageMaker);		
		return "boardList";
	}

	// 글 하나만 조회할 수 있는 디테일 페이지인 boardDetail.jsp로 연결되는
	// /boardDetail주소를 get방식으로 선언
	// 주소 뒤에 ?bno=번호 형식으로 화면에는 적힌 번호 글만 조회가능
	// @PathVariable적용 방식으로 바꾸기
	@GetMapping("/boardDetail/{bno}")
	public String boardDetail(@PathVariable long bno, Model model) {
		BoardVO board = service.select(bno);
		model.addAttribute("board", board);
		return "boardDetail";
	}
	
	// insert페이지를 위한 폼으로 연결되는 컨트롤러 먼저 만들기
	// get방식으로 /boardInsert 주소 접속 시 form페이지로 연결
	@GetMapping("/boardInsert")
	public String boardInsertForm() {
		return "boardInsertForm";
	}
	
	// /boradInsert인데 post방식을 처리하는 메서드 만들기
	// BoardVO를 입력받도록 해주면 실제로는 BoardVO의 맴버변수명으로 들어오는 자료를 입력받음
	// 입력받은 BoardVO를 토대로 mapper쪽의 insert메서드를 실행하고
	// 리다이렉트는 return "redirect:/목적지주소" 형식으로 리턴구문 작성
	// boardList로 돌려보내기
	@PostMapping("/boardInsert")
	public String boardInsert(BoardVO vo) {
	    service.insert(vo);
		return "redirect:/boardList";		
	}
	
	// 글삭제 로직은 post방식
	// /boardDelete 주소로 처리하고
	// bno를 받아서 해당 글 삭제
	// 글 삭제 버튼은 detail페이지 하단에 form으로 만들어서 bno를 hidden으로 전달하는 
	// submit버튼 생성하기
	@PostMapping("/boardDelete")
	public String boardDelete(long bno) {
		service.delete(bno);
		return "redirect:/boardList";
	}
	
	// /boardUpdateForm를 post방식으로 접속하는 form 연결 메서드 만들기
	// update로직은 이미 데이터가 입력되어 있어야 됨
	// 따라서 내가 수정하고자 하는 글의 정보를 VO로 받아온 다음
	// 폼 페이지에 포워딩해서 기입해야함.
	@PostMapping("/boardUpdateForm")
	public String boardUpdateForm(long bno, Model model) {		
		BoardVO board = service.select(bno);
		model.addAttribute("board", board);
		return "boardUpdateForm";
	}
	
	// /boardUpdate를 post방식으로 접속하는 메서드 만들기
	// update(BoardVO)를 실행해서, 폼에서 날려준 데이터를 토대로
	// 해당 글의 내용이 수정되도록 만들기
	// 수정 후에는 수정요청이 들어온 글 번호의 디테일 페이지로 리다이렉트 시키기
	@PostMapping("/boardUpdate")
	public String boardUpdate(BoardVO vo) {
	service.update(vo);
	return "redirect:/boardDetail/" + vo.getBno();
	}
	
}
