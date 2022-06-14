package kr.or.pms.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.ReplyVO;

public interface PdsReplyService {

	int getReplyListCount(int replyNo) throws SQLException;
	
	Map<String,Object> selectPdsReplyList(int pdsNo, Criteria cri) throws Exception;
	
//	int selectPdsRnoByPdsNo(int pdsNo) throws SQLException;
	int addPdsReply(ReplyVO reply, int pdsNo) throws SQLException;
	
	void modifyReply(ReplyVO reply) throws SQLException;
	
	void removeReply(int replySeq) throws SQLException;
	void removeAllReply(int replyNo) throws SQLException;
}
