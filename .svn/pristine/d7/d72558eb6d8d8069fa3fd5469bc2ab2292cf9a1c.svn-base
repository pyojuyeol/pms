package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.IssueCriteria;
import kr.or.pms.command.IssuePageMaker;
import kr.or.pms.dao.BookMarkDAO;
import kr.or.pms.dto.BookMarkVO;
import kr.or.pms.dto.IssueVO;

public class BookMarkServiceImpl implements BookMarkService {

	BookMarkDAO bookMarkDAO;

	public void setBookMarkDAO(BookMarkDAO bookMarkDAO) {
		this.bookMarkDAO = bookMarkDAO;
	}

	@Override
	public Map<String, Object> getAllBMKList(IssueCriteria cri) throws SQLException {
		List<BookMarkVO> bmkList = bookMarkDAO.selectAllBMKList(cri);
		
		Map<String,Object> dataMap = new HashMap<String, Object>();
		
		IssuePageMaker pageMaker = new IssuePageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bookMarkDAO.countTotalBMK(cri));
		
		dataMap.put("bmkList", bmkList);
		dataMap.put("pageMaker",pageMaker);
		
		return dataMap;
	}

	@Override
	public int countTotalIssue(IssueVO issue) throws SQLException{
		int cnt = bookMarkDAO.countTotalIssue(issue);
		return cnt;
	}

	@Override
	public int countTotalClosedIssue(IssueVO issue) throws SQLException{
		int cnt = bookMarkDAO.countTotalClosedIssue(issue);
		return cnt;
	}
	
	@Override
	public int countTotalOpenedIssue(IssueVO issue) throws SQLException{
		int cnt = bookMarkDAO.countTotalClosedIssue(issue);
		return cnt;
	}
	
	@Override
	public int registBMK(BookMarkVO bmk) throws SQLException {
		int cnt = bookMarkDAO.insertBMK(bmk);
		return cnt;
	}

	@Override
	public int modifyBMK(BookMarkVO bmk) throws SQLException {
		int cnt = bookMarkDAO.updateBMK(bmk);
		return cnt;
	}

	@Override
	public int removeBMK(int bmkNo) throws SQLException {
		int cnt = bookMarkDAO.deleteBMK(bmkNo);
		return cnt;
	}

	@Override
	public int openBMK(int bmkNo) throws SQLException {
		int cnt = bookMarkDAO.openBMK(bmkNo);
		return cnt;
	}

	@Override
	public int closeBMK(int bmkNo) throws SQLException {
		int cnt = bookMarkDAO.closeBMK(bmkNo);
		return cnt;
	}

	@Override
	public int addIssueIntoBMK(Map<String, Object> bmk) throws SQLException {
		int cnt = bookMarkDAO.addIssueIntoBMK(bmk);
		return cnt;
	}

	@Override
	public int excludeIssueIntoBMK(int issueNo) throws SQLException {
		int cnt = bookMarkDAO.excludeIssueIntoBMK(issueNo);
		return cnt;
	}

	@Override
	public BookMarkVO getBMKDetailBmkNo(int bmkNo) throws SQLException {
		BookMarkVO bmk = bookMarkDAO.selectBMKDetailBmkNo(bmkNo);
		return bmk;
	}

	@Override
	public List<IssueVO> getIssueListByBmkNo(Map<String, Integer> issueParam) throws SQLException {
		List<IssueVO> issueList = bookMarkDAO.selectIssueListByBmkNo(issueParam);
		return issueList;
	}

	@Override
	public int registIssueIntoBMK(IssueVO issue) throws SQLException {
		int cnt = bookMarkDAO.insertIssueIntoBMK(issue);
		return cnt;
	}

	
}
