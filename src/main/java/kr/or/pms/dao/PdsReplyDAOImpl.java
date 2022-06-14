package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.ReplyVO;

public class PdsReplyDAOImpl implements PdsReplyDAO {

	SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<ReplyVO> selectReplyListForPaging(int pdsNo, Criteria cri) throws SQLException {
		
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<ReplyVO> pdsReplyList = session.selectList("PdsReply-Mapper.selectReplyList",pdsNo,rowBounds);
		return pdsReplyList;
	}

	@Override
	public int selectPdsRnoByPdsNo(int pdsNo) throws SQLException {
		int pdsRno = session.selectOne("PdsReply-Mapper.selectPdsRnoByPdsNo",pdsNo);
		return pdsRno;
	}

	@Override
	public int insertPdsReply(ReplyVO reply) throws SQLException {
		int cnt = session.insert("PdsReply-Mapper.insertReply",reply);
		return cnt;
	}

	@Override
	public int selectReplyNo() throws SQLException {
		int rno = session.selectOne("PdsReply-Mapper.selectReplySeqNextValue");
		return rno;
	}


	@Override
	public int updateReply(ReplyVO reply) throws SQLException {
		int cnt = session.update("PdsReply-Mapper.updateReply",reply);
		return cnt;
	}

	@Override
	public int deleteReply(int replySeq) throws SQLException {
		int cnt = session.update("PdsReply-Mapper.deleteReply",replySeq);
		return cnt;
	}

	@Override
	public int deleteAllReply(int replyNo) throws SQLException {
		int cnt = session.update("PdsReply-Mapper.deleteAllReply",replyNo);
		return cnt;
	}

	@Override
	public int countReply(int replyNo) throws SQLException {
		int totalReplyCnt = session.selectOne("PdsReply-Mapper.countReply",replyNo);
		return totalReplyCnt;
	}
	

}
