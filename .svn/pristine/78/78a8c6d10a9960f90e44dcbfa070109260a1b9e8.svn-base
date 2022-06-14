package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.ReportCriteria;
import kr.or.pms.dto.ReportVO;

public class ReportDAOImpl implements ReportDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<ReportVO> selectMyDailyReportList(ReportCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<ReportVO> reportList = session.selectList("Report-Mapper.selectMyDailyReportList", cri, rowBounds);
		return reportList;
	}

	@Override
	public int countMyDailyReportList(ReportCriteria cri) throws SQLException {
		int cnt = session.selectOne("Report-Mapper.countMyDailyReportList", cri);
		return cnt;
	}

	@Override
	public List<ReportVO> selectMyMonthlyReportList(ReportCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<ReportVO> reportList = session.selectList("Report-Mapper.selectMyMonthlyReportList", cri, rowBounds);
		return reportList;	}

	@Override
	public int countMyMonthlyReportList(ReportCriteria cri) throws SQLException {
		int cnt = session.selectOne("Report-Mapper.countMyMonthlyReportList", cri);
		return cnt;
	}

	@Override
	public List<ReportVO> selectMyTempReportList(ReportCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<ReportVO> reportList = session.selectList("Report-Mapper.selectMyTempReportList", cri, rowBounds);
		return reportList;	}

	@Override
	public int countMyTempReportList(ReportCriteria cri) throws SQLException {
		int cnt = session.selectOne("Report-Mapper.countMyTempReportList", cri);
		return cnt;
	}

//	***********************************
	
	@Override
	public List<ReportVO> selectReceivedDailyReportList(ReportCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<ReportVO> reportList = session.selectList("Report-Mapper.selectReceivedDailyReportList", cri, rowBounds);
		return reportList;	
	}

	@Override
	public List<ReportVO> selectReceivedMonthReportList(ReportCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<ReportVO> reportList = session.selectList("Report-Mapper.selectReceivedMonthReportList", cri, rowBounds);
		return reportList;	
	}
	
	@Override
	public int countReceivedDailyReport(ReportCriteria cri) throws SQLException {
		int cnt = session.selectOne("Report-Mapper.countReceivedDailyReport", cri);
		return cnt;
	}

	@Override
	public int countReceivedMonthReport(ReportCriteria cri) throws SQLException {
		int cnt = session.selectOne("Report-Mapper.countReceivedMonthReport", cri);
		return cnt;
	}

//	***********************************
	
	@Override
	public String loginUserIsPlOrPm(ReportVO report) throws SQLException {
		String plpm = session.selectOne("Report-Mapper.loginUserIsPlOrPm", report);
		return plpm;
	}

	@Override
	public List<ReportVO> selectProjectPlList(int prjNo) throws SQLException {
		List<ReportVO> plList = session.selectList("Report-Mapper.selectProjectPlList",prjNo);
		return plList;
	}

	@Override
	public String selectMyTeamName(int prjNo) throws SQLException {
		String teamNm = session.selectOne("Report-Mapper.selectMyTeamName", prjNo);
		return teamNm;
	}

	@Override
	public String selectPMid(int prjNo) throws SQLException {
		String pmId = session.selectOne("Report-Mapper.selectPMid", prjNo);
		return pmId;
	}
	@Override
	public int selectRepNoSeq() throws SQLException{
		int repNo = session.selectOne("Report-Mapper.repNoSeq");
		return repNo;
	}
	
	@Override
	public int insertDailyReport(ReportVO report) throws SQLException {
		int cnt = session.insert("Report-Mapper.insertDailyReport",report);
		return cnt;
	}

	@Override
	public int insertMonthlyReport(ReportVO report) throws SQLException {
		int cnt = session.insert("Report-Mapper.insertMonthlyReport",report);
		return cnt;
	}

	@Override
	public int insertDailyTempReport(ReportVO report) throws SQLException {
		int cnt = session.insert("Report-Mapper.insertDailyTempReport", report);
		return cnt;
	}

	@Override
	public int insertMonthlyTempReport(ReportVO report) throws SQLException {
		int cnt = session.insert("Report-Mapper.insertMonthlyTempReport",report);
		return cnt;
	}
	
	@Override
	public ReportVO selectReportByRepNo (ReportVO report) throws SQLException {
		ReportVO rpt = session.selectOne("Report-Mapper.selectReportByRepNo", report);
		return rpt;
	}

	@Override
	public int deleteTempReport(int repNo) throws SQLException {
		int cnt = session.update("Report-Mapper.deleteTempReport", repNo);
		return cnt;
	}

	@Override
	public int registTempReportToSendReport(ReportVO report) throws SQLException {
		int cnt = session.update("Report-Mapper.registTempReportToSendReport", report);
		return cnt;
	}

	@Override
	public int updateTempReport(ReportVO report) throws SQLException {
		int cnt = session.update("Report-Mapper.updateTempReport", report);
		return cnt;
	}

	@Override
	public int updateUnreadToRead(int repNo) throws SQLException {
		int cnt= session.update("Report-Mapper.updateUnreadToRead", repNo);
		return cnt;
	}

	
	
}
