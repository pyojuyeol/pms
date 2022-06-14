package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.IssueCriteria;
import kr.or.pms.dto.IssueVO;
import kr.or.pms.dto.ReplyVO;

public interface IssueService {

	Map<String,Object> getIssueList(IssueCriteria cri) throws Exception;
//	Map<String,Object> getIssueList(Criteria cri) throws Exception;
	List<IssueVO> getDashBoardIssue (String userId) throws SQLException;
	List<IssueVO> getProjectHomeIssueByPno(int prjNo) throws Exception;
	
	IssueVO getprjName (int prjNo) throws SQLException;
	int countUnresolvedIssue (int prjNo) throws Exception;
	
	int modifyIssueSttToClose(List<IssueVO>issueList, int prjNo) throws SQLException;
	int modifyIssueSttToOpen(List<IssueVO>issueList, int prjNo) throws SQLException;

	List<IssueVO> getAllPrjBookMarkTitleByPrjNo(int prjNo) throws Exception;
	String getPrjBookMarkTitleByBmkNo(Map<String, Integer> bmkMap) throws SQLException;
	List<IssueVO> getProjectMember(int prjNo) throws Exception;
	
	int getIssueNoSeq() throws SQLException;
	int registIssue(IssueVO issue) throws Exception;
	
	int registRefMember(Map<String,Object>refMap) throws Exception;
	List<IssueVO> getRefMemberList (int issueNo) throws Exception;
	List<String> getRefMembers(int issueNo) throws SQLException;
	
	IssueVO getissue (int issueNo, int prjNo) throws Exception;
	
	void modifyIssueTitle(Map<String,Object>title) throws SQLException;
	int modifyIssueContent(Map<String,Object>content) throws SQLException;
	
	// 일감 목록 가져오기
	List<IssueVO>getWorkInfoByPrjNo(int prjNo) throws SQLException;
	
	/*Issue Reply*/
	List<ReplyVO> getReplyListByIssueNo(int issueNo) throws SQLException;

	int registIssueReply(ReplyVO reply) throws SQLException;
	int modifyIssueReply(ReplyVO reply) throws SQLException;
	int removeIssueReply(int replySeq) throws SQLException;
}
