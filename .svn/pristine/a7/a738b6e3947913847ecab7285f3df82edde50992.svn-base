package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.IssueCriteria;
import kr.or.pms.dto.IssueVO;
import kr.or.pms.dto.ReplyVO;

public interface IssueDAO {

	List<IssueVO> selectIssueList (IssueCriteria cri) throws SQLException;
	List<IssueVO> selectDashBoardIssue (String userId) throws SQLException;
//	List<IssueVO> selectIssueList (Criteria cri) throws SQLException;
	
	// 프로젝트 홈 이슈 목록 조회
	List<IssueVO> selectProjectHomeIssueByPno(int prjNo) throws Exception;
	
	IssueVO selectprjName (int prjNo) throws SQLException;
	
	int countIssues(IssueCriteria cri) throws SQLException;
//	int countIssues(Criteria cri) throws SQLException;
	int countUnresolvedIssue (int prjNo) throws SQLException;
	
	String getPrjBookMarkTitleByBmkNo(Map<String, Integer> bmkMap) throws SQLException;
	List<IssueVO> selectAllPrjBookMarkTitleByPrjNo (int prjNo) throws SQLException;

	List<IssueVO> selectProjectMember(int prjNo) throws SQLException;
	
	void updateIssueSttToClose(Map<String,Object>closeMap) throws SQLException;
	void updateIssueSttToOpen(Map<String,Object>openMap) throws SQLException;
	
	public int selectIssueNoSeq() throws SQLException;
	
	int insertIssue (IssueVO issue) throws SQLException;
	int insertRefMember (Map<String,Object>refMap) throws SQLException;
	List<IssueVO> selectRefMemberList (int issueNo) throws SQLException;
	List<String>selectRefMembers(int issueNo) throws SQLException;
	
	IssueVO selectIssue(Map<String,Integer>issue) throws SQLException;
	
	int updateIssueTitle(Map<String, Object>title) throws SQLException;
	int updateIssueGradeCode(Map<String, Object>gradeCode) throws SQLException;
	int updateIssueContent(Map<String,Object>content) throws SQLException;
	
	// 일감의 이슈 리스트 가지고 오기
	List<IssueVO> selectIssueListByWorkNo(int workNo) throws Exception;
	List<IssueVO>selectWorkInfoByPrjNo(int prjNo) throws SQLException;	
	
	/* Issue Reply ====================================================*/
	List<ReplyVO> selectReplyListByIssueNo(int issueNo) throws SQLException;

	int insertIssueReply(ReplyVO reply) throws SQLException;
	int updateIssueReply(ReplyVO reply) throws SQLException;
	int deleteIssueReply(int replySeq) throws SQLException;
	
	int countReply(int issueNo) throws SQLException;
	
}
