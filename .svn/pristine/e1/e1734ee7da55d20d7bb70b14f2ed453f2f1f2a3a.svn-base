package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.IssueCriteria;
import kr.or.pms.dto.IssueVO;
import kr.or.pms.dto.ReplyVO;

public class IssueDAOImpl implements IssueDAO {

	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<IssueVO> selectIssueList(IssueCriteria cri) throws SQLException {
		
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<IssueVO>issueList = session.selectList("Issue-Mapper.selectIssueList",cri, rowBounds);
		return issueList;
	}
	
	
	@Override
	public List<IssueVO> selectDashBoardIssue (String userId) throws SQLException{
		List<IssueVO> dbIssueList = session.selectList("Issue-Mapper.selectDashBoardIssue", userId);
		
		return dbIssueList;
	}
	
	@Override
	public IssueVO selectprjName(int prjNo) throws SQLException {
		IssueVO prjNm = session.selectOne("Issue-Mapper.selectPrjName", prjNo);
		return prjNm;
	}
	
	@Override
	public int countIssues(IssueCriteria cri) throws SQLException {
		int cnt = session.selectOne("Issue-Mapper.countTotalIssues", cri);
		return cnt;
	}
	
	@Override
	public int countUnresolvedIssue (int prjNo) throws SQLException{
		int cnt = session.selectOne("Issue-Mapper.countUnresolvedIssue",prjNo);
		return cnt;
	}

	@Override
	public void updateIssueSttToClose(Map<String,Object>closeMap) throws SQLException {
		session.update("Issue-Mapper.updateCloseIssue",closeMap);
	}

	@Override
	public void updateIssueSttToOpen(Map<String, Object> openMpap) throws SQLException {
		session.update("Issue-Mapper.updateOpentIssue",openMpap);
		
	}

	@Override
	public List<IssueVO> selectAllPrjBookMarkTitleByPrjNo(int prjNo) throws SQLException {
		List<IssueVO> mbkTitleList = session.selectList("Issue-Mapper.selectAllPrjBookMarkTitleByPrjNo", prjNo);
		return mbkTitleList;
	}

	@Override
	public List<IssueVO> selectProjectMember(int prjNo) throws SQLException {
		List<IssueVO> prjMemList = session.selectList("Issue-Mapper.selectProjectMember",prjNo);
		return prjMemList;
	}
	
	@Override
	public int selectIssueNoSeq() throws SQLException {
		int issueNo = session.selectOne("Issue-Mapper.issueNoSeq");
		return issueNo;
	}

	@Override
	public int insertIssue(IssueVO issue) throws SQLException {
		int cnt = session.update("Issue-Mapper.insertIssue",issue);
		return cnt;
	}

	@Override
	public int insertRefMember(Map<String,Object>refMap) throws SQLException {
		int cnt = session.update("Issue-Mapper.insertReferencedMemberByProjectIssue",refMap);
		return cnt;
	}
	
	public List<IssueVO> selectRefMemberList (int issueNo) throws SQLException{
		List<IssueVO> refMemList = session.selectList("Issue-Mapper.selectRefMemberList",issueNo);
		return refMemList;
	}

	public List<String>selectRefMembers(int issueNo) throws SQLException{
		List<String> refMemId = session.selectList("Issue-Mapper.selectRefMembers");
		return refMemId;
	}
	
	@Override
	public String getPrjBookMarkTitleByBmkNo(Map<String, Integer> bmkMap) throws SQLException {
		String bmkTitle = session.selectOne("Issue-Mapper.selectPrjBookMarkTitleByBmkNo", bmkMap);
		return bmkTitle;
	}

	@Override
	public IssueVO selectIssue(Map<String,Integer>issue) throws SQLException {
		IssueVO isu = session.selectOne("Issue-Mapper.selectIssueByIssueeNo", issue);
		return isu;
	}

	@Override
	public int updateIssueTitle(Map<String, Object> title) throws SQLException {
		int cnt = session.update("Issue-Mapper.updateIssueTitle", title);
		
		return cnt;
	}

	
	@Override
	public int updateIssueGradeCode(Map<String, Object> gradeCode) throws SQLException {
		int cnt = session.update("Issue-Mapper.updateIssueGradeCode", gradeCode);
		return cnt;
	}

	@Override
	public int updateIssueContent(Map<String, Object> content) throws SQLException {
		int cnt = session.update("Issue-Mapper.updateIssueContent", content);
		return cnt;
	}
	
	@Override
	public List<IssueVO> selectIssueListByWorkNo(int workNo) throws Exception {
		List<IssueVO> issueList = session.selectList("Issue-Mapper.selectIssueListByWorkNo", workNo);
		return issueList;
	}

	@Override
	public List<IssueVO> selectWorkInfoByPrjNo(int prjNo) throws SQLException {
		List<IssueVO>workList = session.selectList("Issue-Mapper.selectWorkInfoByPrjNo",prjNo);
		return workList;
	}
	
	/* Issue Reply ▼ ==================================================================*/
	
	@Override
	public List<ReplyVO> selectReplyListByIssueNo(int issueNo) throws SQLException {
		List<ReplyVO> issueReplyList = session.selectList("Issue-Mapper.selectReplyListByIssueNo",issueNo);
		return issueReplyList;
	}

	@Override
	public int insertIssueReply(ReplyVO reply) throws SQLException {
		int cnt = session.update("Issue-Mapper.insertIssueReply",reply);
		return cnt;
	}

	@Override
	public int updateIssueReply(ReplyVO reply) throws SQLException {
		int cnt = session.update("Issue-Mapper.updateIssueReply",reply);
		return cnt;
	}

	@Override
	public int deleteIssueReply(int replySeq) throws SQLException {
		int cnt = session.update("Issue-Mapper.deleteIssueReply",replySeq);
		return cnt;
	}

	@Override
	public int countReply(int issueNo) throws SQLException{
		int cnt = session.selectOne("Issue-Mapper.countIssueReply", issueNo);
		return cnt;
	}

	@Override
	public List<IssueVO> selectProjectHomeIssueByPno(int prjNo) throws Exception {
		List<IssueVO> issueList = session.selectList("Issue-Mapper.selectProjectHomeIssueByPno", prjNo);
		return issueList;
	}


}
