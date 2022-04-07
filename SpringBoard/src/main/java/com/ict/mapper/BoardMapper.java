package com.ict.mapper;

import java.util.List;

import com.ict.domain.BoardVO;

public interface BoardMapper {
	
	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을
	// 어노테이션을 이용해 작성 가능
	// @Select("SELECT * FROM board_tbl WHERE bno < 4")
	public List<BoardVO> getList();
	
	public void insert(BoardVO vo);
	
	// select구문은 글번호를 입력받아서 해당 글 "하나"에 대한 정보만 얻어올 예정이므로
	// 리턴자료형은 글 하나를 담당할 수 있는 BoardVO로 해야함.
	public BoardVO select(long bno); // bno말고 한글로도 가능 
	
	// DELETE는 리턴자료를 void로 적는다
	// 하나의 글만 삭제할 예정이므로, 삭제할 글 번호를 같이 입력
	public void delete(long bno);
	
	public void update(BoardVO vo);
	
	
}
