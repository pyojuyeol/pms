package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.AdminLogCriteria;
import kr.or.pms.command.Criteria;
import kr.or.pms.dto.ConnectLogVO;
import kr.or.pms.dto.DbcnLogVO;
import kr.or.pms.dto.FileLogVO;
import kr.or.pms.dto.SystemSchedulerLogVO;

public interface LogService {
	// 로그인 로그 입력
	public void registLoginLog(ConnectLogVO connectLog) throws Exception;
	
	// 중복 로그인 시 로그아웃 처리 여부 확인
	public ConnectLogVO checkLogout(String loginUserId) throws SQLException;
	
	// 로그아웃 로그 입력
	public void modifyLogoutLog(String loginUserId) throws Exception;
	
	// 회원 아이디로 로그아웃 되지 않은 DB 정보 가져오기
	public ConnectLogVO getLoginLogByUserId(String userId) throws Exception;
	
	/* 관리자 이력 조회 메서드 */
	// 대시보드 내용
//	public Map<String, Object> getAllAdminLogs(AdminLogCriteria adminCri) throws Exception;
	
	// 스케쥴러 로그
	public Map<String, Object> getSystemSchedulerLog(AdminLogCriteria adminCri) throws Exception;
	
	// 파일 다운로드 로그
	public Map<String, Object> getFileLog(AdminLogCriteria adminCri) throws Exception;
	
	// 로그인/로그아운 로그
	public Map<String, Object> getConnectLog(AdminLogCriteria adminCri) throws Exception;
	
	// DB접속 로그
	public Map<String, Object> getDbcnLog(AdminLogCriteria adminCri) throws Exception;
	/* 관리자 이력 조회 메서드 끝 */
	
 
}
