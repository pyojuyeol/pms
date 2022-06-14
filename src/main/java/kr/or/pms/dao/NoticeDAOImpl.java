package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.NoticeCriteria;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.NoticeVO;
import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ReplyVO;

public class NoticeDAOImpl implements NoticeDAO {
	
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<NoticeVO> selectNoticeCriteria(Criteria cri) throws SQLException {
		List<NoticeVO> noticeList = null;
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		if(cri.getPrjNo()==0) {
			noticeList = session.selectList("Notice-Mapper.selectSearchNoticeList",cri,rowBounds);	
		} else {
			noticeList = session.selectList("Notice-Mapper.selectProjectSearchNoticeList",cri,rowBounds);	
		}
			
		return noticeList;
	}

	@Override
	public int selectNoticeCriteriaTotalCount(Criteria cri) throws SQLException {
		int count;
		if(cri.getPrjNo()==0) {
			count = session.selectOne("Notice-Mapper.selectSearchNoticeListCount",cri);
		} else {
			count = session.selectOne("Notice-Mapper.selectProjectSearchNoticeListCount",cri);
		}
		return count;
	}
	
	@Override
	public List<NoticeVO> selectNoticeCriteriaNews(NoticeCriteria cri) throws SQLException {
		
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		List<NoticeVO> noticeList = session.selectList("Notice-Mapper.selectSearchNoticeListNews",cri,rowBounds);	
			
		return noticeList;
	}
	
	@Override
	public int selectNoticeCriteriaTotalCountNews(NoticeCriteria cri) throws SQLException {
		
		int count = session.selectOne("Notice-Mapper.selectSearchNoticeListCountNews",cri);
		return count;
	}
	
	

	@Override
	public List<NoticeVO> selectNoticeCriteriaNtc(NoticeCriteria cri) throws SQLException {
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		List<NoticeVO> noticeList = session.selectList("Notice-Mapper.selectSearchNoticeListNtc",cri,rowBounds);	
			
		return noticeList;
	}

	@Override
	public int selectNoticeCriteriaTotalCountNtc(NoticeCriteria cri) throws SQLException {
		int count = session.selectOne("Notice-Mapper.selectSearchNoticeListCountNtc",cri);
		return count;
	}

	@Override
	public List<NoticeVO> selectNoticeCriteriaEtc(NoticeCriteria cri) throws SQLException {
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		List<NoticeVO> noticeList = session.selectList("Notice-Mapper.selectSearchNoticeListEtc",cri,rowBounds);	
			
		return noticeList;
	}

	@Override
	public int selectNoticeCriteriaTotalCountEtc(NoticeCriteria cri) throws SQLException {
		int count = session.selectOne("Notice-Mapper.selectSearchNoticeListCountEtc",cri);
		return count;
	}


	@Override
	public NoticeVO selectNoticeByNtcNo(int ntcNo) throws SQLException {
		NoticeVO notice = session.selectOne("Notice-Mapper.selectNoticeByNtcNo",ntcNo);
		return notice;
	}

	@Override
	public NoticeVO selectNoticeByImage(String imageFile) throws SQLException {
		NoticeVO notice = session.selectOne("Notice-Mapper.selectNoticeByImage",imageFile);
		return notice;
	}

	@Override
	public void insertNotice(NoticeVO notice) throws SQLException {
		if(notice.getPrjNo() == 0) {
			session.update("Notice-Mapper.insertNotice",notice);
		} else {
			session.update("Notice-Mapper.insertProjectNotice",notice);
		}
	}

	@Override
	public void updateNotice(NoticeVO notice) throws SQLException {
		session.update("Notice-Mapper.updateNotice",notice);
	}

	@Override
	public void deleteNotice(int ntcNo) throws SQLException {
		session.update("Notice-Mapper.deleteNotice",ntcNo);
	}

	@Override
	public void increaseViewCnt(int ntcNo) throws SQLException {
		session.update("Notice-Mapper.increaseViewCnt",ntcNo);
	}
	@Override
	public int selectNoticeSeqNext() throws SQLException {
		int ntcNo = session.selectOne("Notice-Mapper.selectNoticeSeqNext");
		return ntcNo;
	}

	@Override
	public int getSeqNextValue() throws SQLException {
		int attachNo = session.selectOne("Notice-Mapper.selectAttachNoSeqNext");
		return attachNo;
	}

	@Override
	public List<AttachVO> selectAttachesByAttachNo(int attachNo) throws SQLException {
		List<AttachVO> attachList = session.selectList("Notice-Mapper.selectAttachesByAttachNo",attachNo);
		return attachList;
	}

	@Override
	public AttachVO selectAttachByAttachSeq(int attachSeq) throws SQLException {
		AttachVO attach = session.selectOne("Notice-Mapper.selectAttachByAttachSeq",attachSeq);
		return attach;
	}

	@Override
	public void insertAttach(AttachVO attach) throws SQLException {
		session.update("Notice-Mapper.insertAttach",attach);
	}

	@Override
	public void deleteAttach(int attachSeq) throws SQLException {
		session.update("Notice-Mapper.deleteAttach",attachSeq);
	}

	@Override
	public void deleteAllAttach(int attachNo) throws SQLException {
		session.update("Notice-Mapper.deleteAllAttach",attachNo);
	}

	@Override
	public void insertReply(ReplyVO reply) throws SQLException {
		session.update("Notice-Mapper.insertReply",reply);
	}

	@Override
	public void updateReply(ReplyVO reply) throws SQLException {
		session.update("Notice-Mapper.updateReply",reply);
	}

	@Override
	public void deleteReply(int replySeq) throws SQLException {
		session.update("Notice-Mapper.deleteReply",replySeq);
	}

	@Override
	public void deleteAllReply(int replyNo) throws SQLException {
		session.update("Notice-Mapper.deleteAllReply",replyNo);
	}

	@Override
	public int selectReplySeqNextValue() throws SQLException {
		int replyNo = (Integer)session.selectOne("Notice-Mapper.selectReplySeqNextValue");
		return replyNo;
	}

	@Override
	public List<ReplyVO> selectReplyListPage(int replyNo, Criteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<ReplyVO> replyList = session.selectList("Notice-Mapper.selectReplyList", replyNo, rowBounds);
		return replyList;
	}

	@Override
	public int countReply(int replyNo) throws SQLException {
		int count = session.selectOne("Notice-Mapper.countReply",replyNo);
		return count;
	}

	@Override
	public void insertReplyNoToNotice(NoticeVO notice) throws SQLException {
		session.update("Notice-Mapper.insertReplyNoToNotice",notice);
	}

	@Override
	public List<PrjUserVO> selectAllUser(int prjNo) throws SQLException {
		List<PrjUserVO> userList = session.selectList("Notice-Mapper.selectAllUser",prjNo);
		return userList;
	}
	
	@Override
	public List<String> selectAllUsers() throws SQLException {
		List<String> userList = session.selectList("Notice-Mapper.selectAllUsers");
		return userList;
	}
	
	@Override
	public int selectNewNoticeCount(int prjNo) throws Exception {
		int newCnt = session.selectOne("Notice-Mapper.selectNewNoticeCount", prjNo);
		return newCnt;
	}

	

	

	
	

	

}
