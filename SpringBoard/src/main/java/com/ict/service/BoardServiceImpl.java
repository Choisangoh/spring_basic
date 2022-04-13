package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.mapper.BoardMapper;

// BoardService 인터페이스 구현
@Service // 빈 컨테이너 등록(root-context.xml에서 컴포넌트 스캔까지 완료해야 등록됨.)
public class BoardServiceImpl implements BoardService{
	
	@Autowired	
	private BoardMapper boardMapper;

	// 리턴자료형이 없는 insert, delete, update 구문은 사용자 행동 기준으로 메서드르 나눈다
	// 리턴자료형이 있는 select 구문은 하나의 메서드가 하나의 쿼리문을 담당
	@Override
	public List<BoardVO> getList(Criteria cri) {
		return boardMapper.getList(cri);
	}
	
	@Override
	public int countPageNum() {
		return boardMapper.countPageNum();
	}

	@Override
	public BoardVO select(long bno) {
		return boardMapper.select(bno);
	}

	@Override
	public void insert(BoardVO vo) {
		boardMapper.insert(vo);		
	}

	@Override
	public void delete(long bno) {
		boardMapper.delete(bno);		
	}

	@Override
	public void update(BoardVO vo) {
		boardMapper.update(vo);		
	}
}
