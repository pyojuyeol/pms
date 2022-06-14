package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.NoticeCriteria;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.NoticeVO;
import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ReplyVO;
import kr.or.pms.dto.UserVO;

public interface NoticeService {
	
	// 리스트조회
	Map<String, Object> getList(Criteria cri) throws SQLException;
	Map<String, Object> getListNews(NoticeCriteria cri) throws SQLException;
	Map<String, Object> getListNtc(NoticeCriteria cri) throws SQLException;
	Map<String, Object> getListEtc(NoticeCriteria cri) throws SQLException;

	// 글조회
	NoticeVO getNotice(int ntcNo) throws SQLException;

	// 글작성
	void regist(NoticeVO notice, UserVO loginUser) throws SQLException;

	// 글수정
	void modify(NoticeVO notice) throws SQLException;

	// 글삭제
	void remove(int ntcNo) throws SQLException;

	// 글읽기(조회수증가)
	NoticeVO read(int ntcNo) throws SQLException;
	
	// ntcNo 가져오기
	int selectNoticeSeqNext() throws SQLException;
	
	// logSeq 가져오기
	int selectLogSeqNext() throws SQLException;
	
	//첨부파일 조회
	List<AttachVO> getAttachByAttachNo(int attachNo)throws SQLException;
	AttachVO getAttachByAttachSeq(int attachSeq, String ip)throws SQLException;
	
	//파일정보 삭제
	void removeAttachByAttachSeq(int attachSeq)throws SQLException;
	
	void deleteAllAttach( int attachNo) throws SQLException;
	
	int selectReplySeqNextValue() throws SQLException;
	// 리스트 보기
	Map<String, Object> getReplyList(int replyNo, Criteria cri) throws SQLException;
	
	// 리스트 개수
	int getReplyListCount(int replyNo) throws SQLException;
	
	// 등록
	void registReply(ReplyVO reply) throws SQLException;
	
	// 수정
	void modifyReply(ReplyVO reply) throws SQLException;
	
	// 삭제
	void removeReply(int replySeq) throws SQLException;
	void removeAllReply(int replyNo) throws SQLException;
	
	void insertReplyNoToNotice(NoticeVO notice) throws SQLException;
	
	List<PrjUserVO> selectAllUser(int prjNo) throws SQLException;
	
	
	// 프로젝트 Home 신규 공지글 카운트 조회
	int getNewNoticeCount(int prjNo) throws Exception;
}
