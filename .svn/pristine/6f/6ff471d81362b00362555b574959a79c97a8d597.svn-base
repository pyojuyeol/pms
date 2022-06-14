package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.IssueCriteria;
import kr.or.pms.dto.BookMarkVO;
import kr.or.pms.dto.IssueVO;

public interface BookMarkService {

	Map<String,Object> getAllBMKList(IssueCriteria cri) throws SQLException;
	
	int countTotalIssue(IssueVO issue) throws SQLException;
	int countTotalClosedIssue(IssueVO issue) throws SQLException;
	int countTotalOpenedIssue(IssueVO issue) throws SQLException;
	
	int registBMK(BookMarkVO bmk) throws SQLException;
	int modifyBMK(BookMarkVO bmk) throws SQLException;
	int removeBMK(int bmkNo) throws SQLException;
	
	int openBMK(int bmkNo) throws SQLException;
	int closeBMK(int bmkNo) throws SQLException;
	
	// addIssueIntoBMK -> 받아와야 할 파라미터 = bookmarkNo, issueNo
	int addIssueIntoBMK(Map<String,Object>bmk) throws SQLException;
	int excludeIssueIntoBMK(int issueNo) throws SQLException;
	
	// bookmark detail에 있는 issueList 불러오기
	BookMarkVO getBMKDetailBmkNo(int bmkNo) throws SQLException;
	List<IssueVO> getIssueListByBmkNo(Map<String,Integer> issueParam) throws SQLException;
	
	// bookmark 에서 issue 등록하기
	int registIssueIntoBMK(IssueVO issue) throws SQLException;
}
