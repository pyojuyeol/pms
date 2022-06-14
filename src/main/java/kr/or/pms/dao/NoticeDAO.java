package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.NoticeCriteria;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.NoticeVO;
import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ReplyVO;

public interface NoticeDAO {
	// notice
	List<NoticeVO> selectNoticeCriteria(Criteria cri) throws SQLException;
	int selectNoticeCriteriaTotalCount(Criteria cri) throws SQLException;
	List<NoticeVO> selectNoticeCriteriaNews(NoticeCriteria cri) throws SQLException;
	int selectNoticeCriteriaTotalCountNews(NoticeCriteria cri) throws SQLException;
	List<NoticeVO> selectNoticeCriteriaNtc(NoticeCriteria cri) throws SQLException;
	int selectNoticeCriteriaTotalCountNtc(NoticeCriteria cri) throws SQLException;
	List<NoticeVO> selectNoticeCriteriaEtc(NoticeCriteria cri) throws SQLException;
	int selectNoticeCriteriaTotalCountEtc(NoticeCriteria cri) throws SQLException;
	
	NoticeVO selectNoticeByNtcNo(int ntcNo) throws SQLException;
	NoticeVO selectNoticeByImage(String imageFile) throws SQLException;
	
	void insertNotice( NoticeVO notice) throws SQLException;
	void updateNotice( NoticeVO notice) throws SQLException;
	void deleteNotice( int ntcNo) throws SQLException;

	int selectNoticeSeqNext() throws SQLException;
	void increaseViewCnt(int ntcNo) throws SQLException;
	
	// attach
	int getSeqNextValue() throws SQLException;

	List<AttachVO> selectAttachesByAttachNo( int attachNo)throws SQLException;
	
	AttachVO selectAttachByAttachSeq( int attachSeq)throws SQLException;
	
	void insertAttach( AttachVO attach) throws SQLException;
	
	void deleteAttach( int attachSeq) throws SQLException;
	
	void deleteAllAttach( int attachNo) throws SQLException;
	
	// reply
	void insertReply( ReplyVO reply) throws SQLException;
	void updateReply( ReplyVO reply) throws SQLException;
	void deleteReply( int replySeq) throws SQLException;
	void deleteAllReply( int replyNo) throws SQLException;
	
	int selectReplySeqNextValue() throws SQLException;
	List<ReplyVO> selectReplyListPage( int replyNo, Criteria cri) throws SQLException;
	
	int countReply( int replyNo) throws SQLException;

	void insertReplyNoToNotice( NoticeVO notice) throws SQLException;
	
	// user
	List<PrjUserVO> selectAllUser(int prjNo) throws SQLException;
	List<String> selectAllUsers() throws SQLException;
	
	
	// 프로젝트 Home 신규 공지글 카운트
	int selectNewNoticeCount(int prjNo) throws Exception;
}
