package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.ReplyVO;

public class ReplyDAOImpl implements ReplyDAO {

	SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<ReplyVO> selectWorkReplyListPage(int workNo, Criteria cri) throws SQLException {
		
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<ReplyVO> workReplyList = session.selectList("Reply-Mapper.selectWorkReplyList", workNo, rowBounds);
		
		return workReplyList;
	}

	@Override
	public int replyListTotalCountByWno(int workNo) throws SQLException {
		int replyListCnt = session.selectOne("Reply-Mapper.replyListTotalCount", workNo);
		return replyListCnt;
	}

	@Override
	public void insertReply(ReplyVO reply) throws SQLException {
		session.update("Reply-Mapper.insertReply", reply);
	}

	@Override
	public void updateReply(ReplyVO reply) throws SQLException {
		session.update("Reply-Mapper.updateReply", reply);
	}

	@Override
	public void deleteReply(int replySeq) throws SQLException {
		session.update("Reply-Mapper.deleteReply", replySeq);
	}

	@Override
	public void deleteAllReply(int replyNo) throws SQLException {
		session.update("Reply-Mapper.deleteAllReply", replyNo);
	}

	@Override
	public int selectReplySeqNext() throws SQLException {
		int replySeq = session.selectOne("Reply-Mapper.selectReplySeqNext");
		return replySeq;
	}

}
