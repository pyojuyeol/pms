package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.AdminLogCriteria;
import kr.or.pms.command.Criteria;
import kr.or.pms.dto.ConnectLogVO;
import kr.or.pms.dto.DbcnLogVO;
import kr.or.pms.dto.FileLogVO;
import kr.or.pms.dto.SystemSchedulerLogVO;

public interface LogDAO {
	
	// 파일 다운로드 로그 입력
	public void insertFileDownloadLog(FileLogVO fileLog) throws SQLException; 
	
	// 파일 다운로드 로그 sequence 값 받아오기
	public int selectFileLogSeqNext() throws SQLException;
	
	// 로그인 로그 입력
	public void insertLoginLog(ConnectLogVO connectLog) throws SQLException;
	
	// 로그아웃 로그 입력
	public void updateLogoutLog(ConnectLogVO connectLog) throws SQLException;
	
	// 로그인 로그 sequence 값 받아오기
	public int selectConnectLogSeqNext() throws SQLException;
	
	public ConnectLogVO selectLoginLogByUserId(String userId) throws SQLException;
	
	/* 관리자 이력 조회 메서드 */
	// 스케쥴러 로그
	public List<SystemSchedulerLogVO> selectSystemSchedulerLog(AdminLogCriteria admincri) throws SQLException;
	
	// 파일 다운로드 로그
	public List<FileLogVO> selectFileLog(AdminLogCriteria admincri) throws SQLException;
	
	// 로그인/로그아운 로그
	public List<ConnectLogVO> selectConnectLog(AdminLogCriteria admincri) throws SQLException;
	
	// DB접속 로그
	public List<DbcnLogVO> selectDbcnLog(AdminLogCriteria admincri) throws SQLException;
	
	void insertDbcnLog(DbcnLogVO dbcnLog) throws SQLException;
	/* 관리자 이력 조회 메서드 끝 */
	
	/* 각 로그 pageMaker */
	public int selectTotalConnectLog(AdminLogCriteria admincri) throws SQLException;
	
	public int selectTotalFileLog(AdminLogCriteria admincri) throws SQLException;
	
	public int selectTotalSystemSchedulerLog(AdminLogCriteria admincri) throws SQLException;
	
	public int selectTotalDbcnLog(AdminLogCriteria admincri) throws SQLException;

}
