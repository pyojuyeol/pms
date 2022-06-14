package kr.or.pms.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.ReplyVO;

public interface ReplyService {

	// 일감 댓글 리스트 조회
	Map<String, Object> getWorkReplyList(int workNo, Criteria cri) throws SQLException;
	
	int getWorkReplyListCount(int workNo) throws SQLException;
	
	// 댓글 등록
	void registReply(ReplyVO reply) throws SQLException;
	
	// 댓글 수정
	void modifyReply(ReplyVO reply) throws SQLException;
	
	// 댓글 삭제
	void removeReply(int replySeq) throws SQLException;

	// 댓글 전체 삭제 
	void removeAllReply(int replyNo) throws SQLException;
	
	// replyNo 시퀀스
	int selectReplySeqNext() throws SQLException;
}
