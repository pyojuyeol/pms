package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.ReplyVO;

public interface ReplyDAO {

	// 일감 댓글 리스트 조회
	List<ReplyVO> selectWorkReplyListPage(int workNo, Criteria cri) throws SQLException;
	
	int replyListTotalCountByWno(int workNo) throws SQLException;
	
	// 댓글 등록
	void insertReply(ReplyVO reply) throws SQLException;
	
	// 댓글 수정
	void updateReply(ReplyVO reply) throws SQLException;
	
	// 댓글 삭제
	void deleteReply(int replySeq) throws SQLException;

	// 댓글 전체 삭제 
	void deleteAllReply(int replyNo) throws SQLException;
	
	
	// replyNo 시퀀스
	int selectReplySeqNext() throws SQLException;
}