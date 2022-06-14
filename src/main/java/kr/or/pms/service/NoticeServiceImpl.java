package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.NoticeCriteria;
import kr.or.pms.command.NoticePageMaker;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dao.LogDAO;
import kr.or.pms.dao.NoticeDAO;
import kr.or.pms.dao.PushDAO;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.FileLogVO;
import kr.or.pms.dto.NoticeVO;
import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.PushVO;
import kr.or.pms.dto.ReplyVO;
import kr.or.pms.dto.UserVO;

public class NoticeServiceImpl implements NoticeService {
	
	private NoticeDAO noticeDAO;
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	private PushDAO pushDAO;
	public void setPushDAO(PushDAO pushDAO) {
		this.pushDAO = pushDAO;
	}
	private LogDAO logDAO;
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
	
	

	@Override
	public Map<String, Object> getList(Criteria cri) throws SQLException {
		List<NoticeVO> noticeList = noticeDAO.selectNoticeCriteria(cri);
		
		if (noticeList != null)
			for (NoticeVO notice : noticeList)
				addAttachList(notice);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(noticeDAO.selectNoticeCriteriaTotalCount(cri));

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("noticeList", noticeList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	
	@Override
	public Map<String, Object> getListNews(NoticeCriteria cri) throws SQLException {
		
		List<NoticeVO> newsList = noticeDAO.selectNoticeCriteriaNews(cri);
		
		if (newsList != null)
			for (NoticeVO notice : newsList)
				addAttachList(notice);
		
		NoticePageMaker pageMaker = new NoticePageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(noticeDAO.selectNoticeCriteriaTotalCountNews(cri));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("newsList", newsList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	@Override
	public Map<String, Object> getListNtc(NoticeCriteria cri) throws SQLException {
		
		List<NoticeVO> ntcList = noticeDAO.selectNoticeCriteriaNtc(cri);
		
		if (ntcList != null)
			for (NoticeVO notice : ntcList)
				addAttachList(notice);
		
		NoticePageMaker pageMaker = new NoticePageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(noticeDAO.selectNoticeCriteriaTotalCountNtc(cri));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("ntcList", ntcList);
		dataMap.put("ntcpageMaker", pageMaker);
		
		return dataMap;
	}
	
	@Override
	public Map<String, Object> getListEtc(NoticeCriteria cri) throws SQLException {
		
		List<NoticeVO> etcList = noticeDAO.selectNoticeCriteriaEtc(cri);
		
		if (etcList != null)
			for (NoticeVO notice : etcList)
				addAttachList(notice);
		
		NoticePageMaker pageMaker = new NoticePageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(noticeDAO.selectNoticeCriteriaTotalCountEtc(cri));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("etcList", etcList);
		dataMap.put("etcpageMaker", pageMaker);
		
		return dataMap;
	}
	
	@Override
	public NoticeVO getNotice(int ntcNo) throws SQLException {

		NoticeVO notice = noticeDAO.selectNoticeByNtcNo(ntcNo);
		addAttachList(notice);
		
		return notice;
		
	}

	@Override
	public void regist(NoticeVO notice, UserVO loginUser) throws SQLException {
		
//		int ntcNo = noticeDAO.getSeqNextValue();
		
		if(notice.getAttachList() != null) {
			int attachNo = noticeDAO.getSeqNextValue();
			for(AttachVO attach : notice.getAttachList()) {
				attach.setRegister(notice.getRegister());
				attach.setAttachNo(attachNo);
				noticeDAO.insertAttach(attach);
				
			}
			notice.setAttachNo(attachNo);
		}
		
		int ntcNo = noticeDAO.selectNoticeSeqNext();
		
		notice.setNtcNo(ntcNo);
		noticeDAO.insertNotice(notice);
		
			
		List<String> userList = noticeDAO.selectAllUsers();
		
		PushVO push = new PushVO();
		for(String user : userList) {
			push.setReceiver(user);
			push.setFromWhere("공지사항");
			push.setPrjNo(Integer.toString(notice.getPrjNo()));
			push.setUrl("/notice/detail.do?ntcNo="+ntcNo);
			push.setMessage(loginUser.getUserNm()+" 님이 "+notice.getTitle()+" 공지사항을 등록하였습니다.");
			pushDAO.insert(push);
		}
			

	}

	@Override
	public void modify(NoticeVO notice) throws SQLException {
		
		if(notice.getAttachNo() ==0 && notice.getAttachList() != null) {
			int attachNo = noticeDAO.getSeqNextValue();
			notice.setAttachNo(attachNo);
			for(AttachVO attach : notice.getAttachList()) {
				attach.setRegister(notice.getRegister());
				attach.setAttachNo(attachNo);
				noticeDAO.insertAttach(attach);
			}
		} else if(notice.getAttachNo() != 0 && notice.getAttachList() != null) {
			for(AttachVO attach : notice.getAttachList()) {
				attach.setRegister(notice.getRegister());
				attach.setAttachNo(notice.getAttachNo());
				noticeDAO.insertAttach(attach);
			}
		}
		
		
		noticeDAO.updateNotice(notice);
		
	}

	@Override
	public void remove(int ntcNo) throws SQLException {

		NoticeVO notice = noticeDAO.selectNoticeByNtcNo(ntcNo);
		noticeDAO.deleteAllAttach(notice.getAttachNo());
		noticeDAO.deleteNotice(ntcNo);
	}

	@Override
	public NoticeVO read(int ntcNo) throws SQLException {

		NoticeVO notice = noticeDAO.selectNoticeByNtcNo(ntcNo);
		noticeDAO.increaseViewCnt(ntcNo);
		
		addAttachList(notice);
		
		return notice;
				
	}
	
	@Override
	public int selectNoticeSeqNext() throws SQLException {
		int ntcNo = noticeDAO.selectNoticeSeqNext();
		return ntcNo;
	}
	
	@Override
	public int selectLogSeqNext() throws SQLException {
		int logSeq = logDAO.selectFileLogSeqNext();
		return logSeq;
	}
	
	@Override
	public List<AttachVO> getAttachByAttachNo(int attachNo) throws SQLException {

		List<AttachVO> attachList = noticeDAO.selectAttachesByAttachNo(attachNo);
		
		return attachList;
	}

	@Override
	public AttachVO getAttachByAttachSeq(int attachSeq, String ip) throws SQLException {

		AttachVO attach = noticeDAO.selectAttachByAttachSeq(attachSeq);
		
		FileLogVO fileLog = new FileLogVO();
		int fileDownLogNo = logDAO.selectFileLogSeqNext();
		
		fileLog.setIp(ip);
		fileLog.setFileDownLogNo(fileDownLogNo);
		fileLog.setUserId(attach.getRegister());
		fileLog.setFileKey("notice");
		fileLog.setFileNo(attach.getAttachNo());
		fileLog.setFileNm(attach.getStroredNm());
		
		logDAO.insertFileDownloadLog(fileLog);
		
		return attach;
	}

	@Override
	public void removeAttachByAttachSeq(int attachSeq) throws SQLException {

		noticeDAO.deleteAttach(attachSeq);

	}
	
	@Override
	public void deleteAllAttach(int attachNo) throws SQLException {

		noticeDAO.deleteAllAttach(attachNo);
		
	}
	
	private void addAttachList(NoticeVO notice) throws SQLException {

		if (notice == null)
			return;

		int attachNo = notice.getAttachNo();
		List<AttachVO> attachList = noticeDAO.selectAttachesByAttachNo(attachNo);

		notice.setAttachList(attachList);
	}

	@Override
	public Map<String, Object> getReplyList(int replyNo, Criteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		List<ReplyVO> replyList = noticeDAO.selectReplyListPage(replyNo, cri);

//		if(replyList!=null)for(ReplyVO reply : replyList) {
//			MemberVO member = memberDAOBean.selectMemberById(reply.getReplyer());
//			reply.setPicture(member.getPicture());
//		}
		
		int count = noticeDAO.countReply(replyNo);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);

		dataMap.put("replyList", replyList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	@Override
	public int getReplyListCount(int replyNo) throws SQLException {
		int count = noticeDAO.countReply(replyNo);
		return count;
	}
	
	@Override
	public int selectReplySeqNextValue() throws SQLException {
		int replyNo = noticeDAO.selectReplySeqNextValue();
		return replyNo;
	}
	@Override
	public void registReply(ReplyVO reply) throws SQLException {
		
		noticeDAO.insertReply(reply);
	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
		noticeDAO.updateReply(reply);
	}

	@Override
	public void removeReply(int replySeq) throws SQLException {
		noticeDAO.deleteReply(replySeq);
	}

	@Override
	public void removeAllReply(int replyNo) throws SQLException {
		noticeDAO.deleteAllReply(replyNo);
	}

	@Override
	public void insertReplyNoToNotice(NoticeVO notice) throws SQLException {
		noticeDAO.insertReplyNoToNotice(notice);
	}

	@Override
	public List<PrjUserVO> selectAllUser(int prjNo) throws SQLException {
		List<PrjUserVO> userList = noticeDAO.selectAllUser(prjNo);
		return userList;
	}

	@Override
	public int getNewNoticeCount(int prjNo) throws Exception {
		int newCnt = noticeDAO.selectNewNoticeCount(prjNo);
		return newCnt;
	}


	


}
