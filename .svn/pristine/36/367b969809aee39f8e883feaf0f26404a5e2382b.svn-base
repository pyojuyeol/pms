package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.ReplyVO;

public interface PdsReplyDAO {

	List<ReplyVO> selectReplyListForPaging(int pdsNo, Criteria cri) throws SQLException;
	
	int selectPdsRnoByPdsNo(int pdsNo) throws SQLException;
	int selectReplyNo()throws SQLException;
	int insertPdsReply(ReplyVO reply) throws SQLException;
	int updateReply(ReplyVO reply) throws SQLException;
	int deleteReply(int replySeq) throws SQLException;
	int deleteAllReply(int replyNo) throws SQLException;
	
	int countReply(int replyNo) throws SQLException;
}
