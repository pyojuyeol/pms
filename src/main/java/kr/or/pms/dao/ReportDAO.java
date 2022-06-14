package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.ReportCriteria;
import kr.or.pms.dto.ReportVO;

public interface ReportDAO {
	
	// 내가 작성한 보고서
	List<ReportVO> selectMyDailyReportList (ReportCriteria cri) throws SQLException;
	List<ReportVO> selectMyMonthlyReportList (ReportCriteria cri) throws SQLException;
	List<ReportVO> selectMyTempReportList (ReportCriteria cri) throws SQLException;
	int countMyDailyReportList(ReportCriteria cri) throws SQLException;
	int countMyMonthlyReportList(ReportCriteria cri) throws SQLException;
	int countMyTempReportList(ReportCriteria cri) throws SQLException;

	// 내가 받은 보고서
	List<ReportVO> selectReceivedDailyReportList (ReportCriteria cri) throws SQLException;
	List<ReportVO> selectReceivedMonthReportList (ReportCriteria cri) throws SQLException;
	int countReceivedDailyReport(ReportCriteria cri) throws SQLException;
	int countReceivedMonthReport(ReportCriteria cri) throws SQLException;
	
	// pl, pm 조회
	String loginUserIsPlOrPm(ReportVO report) throws SQLException;
	List<ReportVO> selectProjectPlList(int prjNo) throws SQLException;
	// pm id 조회
	String selectPMid(int prjNo) throws SQLException;
	// 소속 팀 이름 조회
	String selectMyTeamName(int prjNo) throws SQLException;
	
	// 보고서 등록(일일/월간/임시)
	int selectRepNoSeq() throws SQLException;
	
	int insertDailyReport(ReportVO report) throws SQLException;
	int insertMonthlyReport(ReportVO report) throws SQLException;
	int insertDailyTempReport(ReportVO report) throws SQLException;
	int insertMonthlyTempReport(ReportVO report) throws SQLException;
	
	// report detail
	ReportVO selectReportByRepNo (ReportVO report)throws SQLException;
	
	// temp report 등록/수정/삭제
	int deleteTempReport(int repNo) throws SQLException;
	int registTempReportToSendReport(ReportVO report) throws SQLException;
	int updateTempReport(ReportVO report) throws SQLException;
	
	// 보고 읽음처리
	int updateUnreadToRead(int repNo) throws SQLException;
}
