package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dao.ReplyDAO;
import kr.or.pms.dto.ReplyVO;

public class ReplyServiceImpl implements ReplyService {

	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	@Override
	public Map<String, Object> getWorkReplyList(int workNo, Criteria cri) throws SQLException {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<ReplyVO> workReplyList = replyDAO.selectWorkReplyListPage(workNo, cri);
		
		int count = replyDAO.replyListTotalCountByWno(workNo);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);

		dataMap.put("workReplyList", workReplyList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public int getWorkReplyListCount(int workNo) throws SQLException {
		int cnt = replyDAO.replyListTotalCountByWno(workNo);
		return cnt;
	}

	@Override
	public void registReply(ReplyVO reply) throws SQLException {
		
		System.out.println(reply.getReplyNo());
		System.out.println(reply.getContent());
		System.out.println(reply.getRegister());
		
		replyDAO.insertReply(reply);
	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
		replyDAO.updateReply(reply);
	}

	@Override
	public void removeReply(int replySeq) throws SQLException {
		replyDAO.deleteReply(replySeq);
	}

	@Override
	public void removeAllReply(int replyNo) throws SQLException {
		replyDAO.deleteAllReply(replyNo);
	}

	@Override
	public int selectReplySeqNext() throws SQLException {
		int reply_seq = replyDAO.selectReplySeqNext();
		return reply_seq;
	}

}
