package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.ReportCriteria;
import kr.or.pms.dto.ReportVO;

public interface ReportService {

//	Map<String, Object>reportList(ReportCriteria cri) throws SQLException;
	
	Map<String, Object> getMyDailyReportList (ReportCriteria cri) throws SQLException;
	Map<String, Object> getMyMonthlyReportList (ReportCriteria cri) throws SQLException;
	Map<String, Object> getMyTempReportList (ReportCriteria cri) throws SQLException;

	Map<String, Object> getReceivedDailyReportList (ReportCriteria cri) throws SQLException;
	Map<String, Object> getReceivedMonthReportList (ReportCriteria cri) throws SQLException;
	
	// pl, pm 조회
	String loginUserIsPlOrPm(ReportVO report) throws SQLException;
	// pl 리스트 조회
	List<ReportVO> getProjectPlList(int prjNo) throws SQLException;
	// 소속 팀명 조회
	String getMyTeamName(int prjNo) throws SQLException;
	// pm id 가져오기
	String getPMid(int prjNo) throws SQLException;
	
	// 업무보고 등록
	int selectRepNoSeq() throws SQLException;
	
	int registDailyReport(ReportVO report) throws SQLException;
	int registMonthlyReport(ReportVO report) throws SQLException;
	int registDailyTempReport(ReportVO report) throws SQLException;
	int registMonthlyTempReport(ReportVO report) throws SQLException;
	
	// report detail
	ReportVO getReportByRepNo (ReportVO report)throws SQLException;
	
	// temp report 등록/수정/삭제
	int removeTempReport(int repNo) throws SQLException;
	int registTempReportToSendReport(ReportVO report) throws SQLException;
	int modifyTempReport(ReportVO report) throws SQLException;
	
	// 보고서 읽음처리
	int modifyUnreadToRead(int repNo) throws SQLException;
}
