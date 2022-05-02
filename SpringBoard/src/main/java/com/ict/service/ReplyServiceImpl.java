package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.ReplyVO;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	// 서비스가 매퍼를 호출하므로 매퍼를 위에 선언해야 한다.
	@Autowired
	private ReplyMapper mapper;
	
	// 댓글쓰기 시 board_tbl쪽에도 관여해야 하므로
	// board테이블을 수정하는 mapper를 추가 선언
	@Autowired
	private BoardMapper boardmapper;

	@Override
	public List<ReplyVO> listReply(long bno) {
		return mapper.getList(bno);
	}

	@Override
	public void addReply(ReplyVO vo) {
		mapper.create(vo);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		mapper.update(vo);	
	}

	@Transactional
	@Override
	public void removeReply(Long rno) {
		mapper.delete(rno);
		Long bno = mapper.getBno(rno);
		boardmapper.updateReplyCount(bno, -1);
	}	
}
