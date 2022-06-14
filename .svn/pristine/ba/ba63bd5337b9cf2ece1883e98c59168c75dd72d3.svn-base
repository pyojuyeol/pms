package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dao.PdsReplyDAO;
import kr.or.pms.dto.ReplyVO;

public class PdsReplyServiceImpl implements PdsReplyService {

	PdsReplyDAO pdsReplyDAO;
	
	
	public void setPdsReplyDAO(PdsReplyDAO dao) {
		this.pdsReplyDAO = dao;
	}

	@Override
	public Map<String,Object> selectPdsReplyList(int pdsNo, Criteria cri) throws Exception {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<ReplyVO> pdsReplyList = pdsReplyDAO.selectReplyListForPaging(pdsNo, cri);
		
		int count = pdsReplyDAO.countReply(pdsNo);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);

		dataMap.put("replyList", pdsReplyList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}


	@Override
	public int addPdsReply(ReplyVO reply, int pdsNo) throws SQLException {
		
		int replyNoInPds = pdsReplyDAO.selectPdsRnoByPdsNo(pdsNo); //replyNoInPds
		
		reply.setReplyNo(replyNoInPds);
		
		System.out.println(reply.getReplyNo());
		System.out.println(reply.getContent());
		System.out.println(reply.getRegister());
		
		int result=0;

		result = pdsReplyDAO.insertPdsReply(reply);
		
		return result;
	}

	@Override
	public int getReplyListCount(int replyNo) throws SQLException {
		int cnt = pdsReplyDAO.countReply(replyNo);
		return cnt;
	}
	
	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
		pdsReplyDAO.updateReply(reply);
	}
	
	@Override
	public void removeReply(int replySeq) throws SQLException {
		pdsReplyDAO.deleteReply(replySeq);
	}
	
	@Override
	public void removeAllReply(int replyNo) throws SQLException {
		pdsReplyDAO.deleteAllReply(replyNo);
	}

}
