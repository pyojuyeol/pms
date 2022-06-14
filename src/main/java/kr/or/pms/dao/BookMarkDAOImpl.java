package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.IssueCriteria;
import kr.or.pms.dto.BookMarkVO;
import kr.or.pms.dto.IssueVO;

public class BookMarkDAOImpl implements BookMarkDAO {

	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<BookMarkVO> selectAllBMKList(IssueCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<BookMarkVO> bmkList = session.selectList("BookMark-Mapper.selectAllBMKList",cri,rowBounds);
		return bmkList;
	}
	
	@Override
	public int countTotalBMK(IssueCriteria cri) throws SQLException{
		int cnt = session.selectOne("BookMark-Mapper.countTotalBMK",cri);
		return cnt;
	}
	
	@Override
	public int countTotalIssue(IssueVO issue) throws SQLException {
		int cnt = session.selectOne("BookMark-Mapper.countTotalIssue", issue);
		return cnt;
	}

	@Override
	public int countTotalClosedIssue(IssueVO issue) throws SQLException {
		int cnt = session.selectOne("BookMark-Mapper.countTotalClosedIssue", issue);
		return cnt;
	}
	
	@Override
	public int countTotalOpenedIssue(IssueVO issue) throws SQLException {
		int cnt = session.selectOne("BookMark-Mapper.countTotalOpenedIssue", issue);
		return cnt;
	}

	@Override
	public int insertBMK(BookMarkVO bmk) throws SQLException {
		int cnt = session.update("BookMark-Mapper.insertBMK", bmk);
		return cnt;
	}

	@Override
	public int updateBMK(BookMarkVO bmk) throws SQLException {
		int cnt = session.update("BookMark-Mapper.updateBMK", bmk);
		return cnt;
	}

	@Override
	public int deleteBMK(int bmkNo) throws SQLException {
		int cnt = session.update("BookMark-Mapper.deleteBMK", bmkNo);
		return cnt;
	}

	@Override
	public int openBMK(int bmkNo) throws SQLException {
		int cnt = session.update("BookMark-Mapper.openBMK", bmkNo);
		return cnt;
	}

	@Override
	public int closeBMK(int bmkNo) throws SQLException {
		int cnt = session.update("BookMark-Mapper.closeBMK", bmkNo);
		return cnt;
	}

	@Override
	public int addIssueIntoBMK(Map<String, Object> bmk) throws SQLException {
		int cnt = session.update("BookMark-Mapper.addIssueIntoBMK", bmk);
		return cnt;
	}

	@Override
	public int excludeIssueIntoBMK(int issueNo) throws SQLException {
		int cnt = session.update("BookMark-Mapper.excludeIssueFromBMK", issueNo);
		return cnt;
	}

	@Override
	public BookMarkVO selectBMKDetailBmkNo(int bmkNo) throws SQLException {
		BookMarkVO bmk = session.selectOne("BookMark-Mapper.selectBMKDetailBmkNo", bmkNo);
		return bmk;
	}

	@Override
	public List<IssueVO> selectIssueListByBmkNo(Map<String, Integer> issueParam) throws SQLException {
		List<IssueVO> issueList = session.selectList("BookMark-Mapper.selectIssueListByBmkNo", issueParam);
		return issueList;
	}

	@Override
	public int insertIssueIntoBMK(IssueVO issue) throws SQLException {
		int cnt = session.selectOne("BookMark-Mapper.insertIssueIntoBMK", issue);
		return cnt;
	}

	
	
}

