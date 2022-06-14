package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.PageMaker;
import kr.or.pms.command.ReportCriteria;
import kr.or.pms.dao.ReportDAO;
import kr.or.pms.dto.ReportVO;

public class ReportServiceImpl implements ReportService {

	private ReportDAO reportDAO;

	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	@Override
	public Map<String,Object> getMyDailyReportList(ReportCriteria cri) throws SQLException {
		List<ReportVO> dailyRptList = reportDAO.selectMyDailyReportList(cri);
		
		Map<String,Object> dailyRptDataMap = new HashMap<String, Object>();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reportDAO.countMyDailyReportList(cri));
		
		dailyRptDataMap.put("dailyRptList",dailyRptList);
		dailyRptDataMap.put("dailyPageMaker",pageMaker);
		
		return dailyRptDataMap;
	}

	@Override
	public Map<String,Object> getMyMonthlyReportList(ReportCriteria cri) throws SQLException {
		List<ReportVO> monthlyRptList = reportDAO.selectMyMonthlyReportList(cri);
		
		Map<String,Object> monthlyRptDataMap = new HashMap<String, Object>();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reportDAO.countMyMonthlyReportList(cri));
		
		monthlyRptDataMap.put("monthlyRptList", monthlyRptList);
		monthlyRptDataMap.put("monthlyRptPageMaker", pageMaker);
		
		return monthlyRptDataMap;
	}

	@Override
	public Map<String,Object> getMyTempReportList(ReportCriteria cri) throws SQLException {
		List<ReportVO> tempRptList = reportDAO.selectMyTempReportList(cri);
		
		Map<String,Object> tempDataMap = new HashMap<String, Object>();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reportDAO.countMyTempReportList(cri));
		
		tempDataMap.put("tempRptList", tempRptList);
		tempDataMap.put("temppageMaker", pageMaker);
		
		return tempDataMap;
	}

	@Override
	public Map<String,Object> getReceivedDailyReportList(ReportCriteria cri) throws SQLException {
		List<ReportVO> receivedDailyReportList = reportDAO.selectReceivedDailyReportList(cri);
		
		Map<String,Object> receivedDailyDataMap = new HashMap<String, Object>();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reportDAO.countReceivedDailyReport(cri));
		
		receivedDailyDataMap.put("receivedDailyReportList", receivedDailyReportList);
		receivedDailyDataMap.put("receivedDailyReportPageMaker", pageMaker);
		
		return receivedDailyDataMap;
	}

	@Override
	public Map<String, Object> getReceivedMonthReportList(ReportCriteria cri) throws SQLException {
		List<ReportVO> receivedMonthReportList = reportDAO.selectReceivedMonthReportList(cri);
		
		Map<String,Object> receivedMonthDataMap = new HashMap<String, Object>();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reportDAO.countReceivedMonthReport(cri));
		
		receivedMonthDataMap.put("receivedMonthReportList", receivedMonthReportList);
		receivedMonthDataMap.put("receivedMonthPageMaker", pageMaker);
		
		return receivedMonthDataMap;
	}
	

	@Override
	public String loginUserIsPlOrPm(ReportVO report) throws SQLException {
		String plpm = reportDAO.loginUserIsPlOrPm(report);
		return plpm;
	}

	@Override
	public List<ReportVO> getProjectPlList(int prjNo) throws SQLException {
		List<ReportVO> plList = reportDAO.selectProjectPlList(prjNo);
		return plList;
	}
	
	@Override
	public String getPMid(int prjNo) throws SQLException{
		String pmId = reportDAO.selectPMid(prjNo);
		return pmId;
	}

	@Override
	public ReportVO getReportByRepNo(ReportVO report) throws SQLException {
		ReportVO rpt = reportDAO.selectReportByRepNo(report);
		return rpt;
	}

	@Override
	public int selectRepNoSeq() throws SQLException{
		int repNo = reportDAO.selectRepNoSeq();
		return repNo;
	}
	
	@Override
	public int registDailyReport(ReportVO report) throws SQLException {
		int cnt = reportDAO.insertDailyReport(report);
		return cnt;
	}

	@Override
	public int registMonthlyReport(ReportVO report) throws SQLException {
		int cnt= reportDAO.insertMonthlyReport(report);
		return cnt;
	}

	@Override
	public int registDailyTempReport(ReportVO report) throws SQLException {
		int cnt= reportDAO.insertDailyTempReport(report);
		return cnt;
	}

	@Override
	public int registMonthlyTempReport(ReportVO report) throws SQLException {
		int cnt= reportDAO.insertMonthlyTempReport(report);
		return cnt;
	}
	
	@Override
	public String getMyTeamName(int prjNo) throws SQLException {
		String teamNm = reportDAO.selectMyTeamName(prjNo);
		return teamNm;
	}

	@Override
	public int removeTempReport(int repNo) throws SQLException {
		int cnt = reportDAO.deleteTempReport(repNo);
		return cnt;
	}

	@Override
	public int registTempReportToSendReport(ReportVO report) throws SQLException {
		int cnt = reportDAO.registTempReportToSendReport(report);
		return cnt;
	}

	@Override
	public int modifyTempReport(ReportVO report) throws SQLException {
		int cnt= reportDAO.updateTempReport(report);
		return cnt;
	}

	@Override
	public int modifyUnreadToRead(int repNo) throws SQLException {
		int cnt = reportDAO.updateUnreadToRead(repNo);
		return cnt;
	}

	

}
