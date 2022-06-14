package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.IssueCriteria;
import kr.or.pms.command.IssuePageMaker;
import kr.or.pms.dao.IssueDAO;
import kr.or.pms.dto.IssueVO;
import kr.or.pms.dto.ReplyVO;

public class IssueServiceImpl implements IssueService {

	private IssueDAO issueDAO;
	
	public void setIssueDAO(IssueDAO issueDAO) {
		this.issueDAO = issueDAO;
	}

	@Override
	public Map<String, Object> getIssueList(IssueCriteria cri) throws Exception {
		List<IssueVO> issueList = issueDAO.selectIssueList(cri);
		
		Map<String,Object> dataMap = new HashMap<String, Object>();
		
		IssuePageMaker pageMaker = new IssuePageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(issueDAO.countIssues(cri));
		
		dataMap.put("issueList", issueList);
		dataMap.put("pageMaker",pageMaker);
		
		return dataMap;
	}
	
	
	@Override
	public List<IssueVO> getDashBoardIssue (String userId) throws SQLException{
		List<IssueVO> dbIssueList = issueDAO.selectDashBoardIssue(userId);
		return dbIssueList;
	}
	
	@Override
	public IssueVO getprjName(int prjNo) throws SQLException {
		IssueVO prjNm = issueDAO.selectprjName(prjNo);
		return prjNm;
	}
	
	
	@Override
	public int countUnresolvedIssue (int prjNo) throws Exception{
		int cnt = issueDAO.countUnresolvedIssue(prjNo);
		return cnt;
	}
	
	@Override
	public int modifyIssueSttToClose(List<IssueVO> issueList, int prjNo) throws SQLException {
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("prjNo",prjNo);
		
		int cnt = 0;
		for (IssueVO issue : issueList) {
			pMap.put("issueNo", issue.getIssueNo());
			issueDAO.updateIssueSttToClose(pMap);
			cnt += 1;
		}
		return cnt;
	}

	
	@Override
	public int modifyIssueSttToOpen(List<IssueVO> issueList, int prjNo) throws SQLException {
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("prjNo",prjNo);
		
		int cnt = 0;
		for (IssueVO issue : issueList) {
			pMap.put("issueNo", issue.getIssueNo());
			issueDAO.updateIssueSttToOpen(pMap);
			cnt += 1;
		}
		
		return cnt;
	}

	@Override
	public List<IssueVO> getAllPrjBookMarkTitleByPrjNo(int prjNo) throws Exception {
		
		List<IssueVO> bmkList = issueDAO.selectAllPrjBookMarkTitleByPrjNo(prjNo);
		
		return bmkList;
	}
	

	@Override
	public String getPrjBookMarkTitleByBmkNo(Map<String, Integer> bmkMap) throws SQLException {
		String bmkTitle = issueDAO.getPrjBookMarkTitleByBmkNo(bmkMap);
		return bmkTitle;
	}


	@Override
	public List<IssueVO> getProjectMember(int prjNo) throws Exception {
		List<IssueVO> prjMemList = issueDAO.selectProjectMember(prjNo);
		return prjMemList;
	}

	@Override
	public int getIssueNoSeq() throws SQLException {
		int issueNo = issueDAO.selectIssueNoSeq();
		return issueNo;
	}
	
	@Override
	public int registIssue(IssueVO issue) throws Exception {
		int cnt = issueDAO.insertIssue(issue);
		return cnt;
	}

	@Override
	public int registRefMember(Map<String,Object>refMap) throws Exception {
		int cnt = issueDAO.insertRefMember(refMap);
		return cnt;
	}
	
	@Override
	public List<IssueVO> getRefMemberList (int issueNo) throws Exception{
		List<IssueVO> refMemList = issueDAO.selectRefMemberList(issueNo);
		return refMemList;
	}
	
	public List<String>getRefMembers(int issueNo) throws SQLException{
		List<String> refMemId = issueDAO.selectRefMembers(issueNo);
		return refMemId;
	}

	@Override
	public IssueVO getissue(int issueNo, int prjNo) throws Exception {
		Map<String, Integer>getIssueMap = new HashMap<String, Integer>();
		
		getIssueMap.put("issueNo", issueNo);
		getIssueMap.put("prjNo", prjNo);
		
		IssueVO issue = issueDAO.selectIssue(getIssueMap);
		return issue;
	}

	@Override
	public void modifyIssueTitle(Map<String,Object>title) throws SQLException {
		issueDAO.updateIssueTitle(title);
		issueDAO.updateIssueGradeCode(title);
		
//		System.out.println("Service Cnt ========"+title.get("sttCode"));
//		System.out.println("Service Cnt ========"+title.get("title"));

	}

	@Override
	public int modifyIssueContent(Map<String,Object>content) throws SQLException {
		int cnt = issueDAO.updateIssueContent(content);
		return cnt;
	}

	
	// 일감 목록 가져오기
	public List<IssueVO>getWorkInfoByPrjNo(int prjNo) throws SQLException{
		List<IssueVO> workList = issueDAO.selectWorkInfoByPrjNo(prjNo);
		return workList;
	}
	
	
	/* Issue Reply ▼ ==================================================================*/
	
	@Override
	public List<ReplyVO> getReplyListByIssueNo(int issueNo) throws SQLException {
		List<ReplyVO> issueReplyList = issueDAO.selectReplyListByIssueNo(issueNo);
		return issueReplyList;
	}

	@Override
	public int registIssueReply(ReplyVO reply) throws SQLException {
		int cnt = issueDAO.insertIssueReply(reply);
		return cnt;
	}

	@Override
	public int modifyIssueReply(ReplyVO reply) throws SQLException {
		int cnt = issueDAO.updateIssueReply(reply);
		return cnt;
	}

	@Override
	public int removeIssueReply(int replySeq) throws SQLException {
		int cnt = issueDAO.deleteIssueReply(replySeq);
		return cnt;
	}

	@Override
	public List<IssueVO> getProjectHomeIssueByPno(int prjNo) throws Exception {
		List<IssueVO> issueList = issueDAO.selectProjectHomeIssueByPno(prjNo);
		return issueList;
	}

}
