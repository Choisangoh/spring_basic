package com.ict.mapper;

import java.util.List;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;

public interface BoardMapper {
	
	// 버튼추가를 위해 pageNum대신 Criteria를 활용
	public List<BoardVO> getList(Criteria cri);
	
	public void insert(BoardVO vo);
	
	// select구문은 글번호를 입력받아서 해당 글 "하나"에 대한 정보만 얻어올 예정이므로
	// 리턴자료형은 글 하나를 담당할 수 있는 BoardVO로 해야함.
	public BoardVO select(long bno); // bno말고 한글로도 가능 
	
	// DELETE는 리턴자료를 void로 적는다
	// 하나의 글만 삭제할 예정이므로, 삭제할 글 번호를 같이 입력
	public void delete(long bno);
	
	public void update(BoardVO vo);
	
	// 전체 글 개수를 얻어오는 getCountBoard 선언
	// 파라미터는 필요없음
	// 글개수 => 정수 값을 조회하기 떄문에 int 리턴
	public int countPageNum();
	
}
