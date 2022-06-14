package kr.or.pms.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.AdminLogCriteria;
import kr.or.pms.command.Criteria;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dao.LogDAO;
import kr.or.pms.dto.ConnectLogVO;
import kr.or.pms.dto.DbcnLogVO;
import kr.or.pms.dto.FileLogVO;
import kr.or.pms.dto.SystemSchedulerLogVO;

public class LogServiceImpl implements LogService {
	
	private LogDAO logDAO;
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

	@Override
	public void registLoginLog(ConnectLogVO connectLog) throws Exception {
		int connectLogSeqNextValue = logDAO.selectConnectLogSeqNext();
		
		connectLog.setCnLogNo(connectLogSeqNextValue);
		
		logDAO.insertLoginLog(connectLog);
	}
	@Override
	public ConnectLogVO checkLogout(String loginUserId) throws SQLException {
		ConnectLogVO connectLog = logDAO.selectLoginLogByUserId(loginUserId);
		
		return connectLog;
	}

	@Override
	public void modifyLogoutLog(String loginUserId) throws Exception {
		ConnectLogVO connectLog = logDAO.selectLoginLogByUserId(loginUserId);
		
		connectLog.setCnKeyCode("logout");
		connectLog.setLogoutDt(new Date());
		connectLog.setUserId(loginUserId);
		
		long loginDt = connectLog.getLoginDt().getTime();
		long logoutDt = connectLog.getLogoutDt().getTime();
		
		long cnTime = logoutDt - loginDt;
		String cnTimeString = "";
		
		if (cnTime / (1000) < 0) {
			cnTimeString = (cnTime / 1000) + "초";
		}
		if (cnTime / (1000) >= 1) {
			cnTimeString = (cnTime / 1000) + "초";
		}
		if (cnTime / (1000 * 60) >= 1) {
			cnTimeString = (cnTime / (1000 * 60)) + "분 " + (cnTime % (1000 * 60) / 1000) + "초";
		}
		if (cnTime / (1000 * 60 * 60) >= 1) {
			cnTimeString = (cnTime / (1000 * 60 * 60)) + "시간 " + (cnTime % (1000 * 60 * 60) / 1000 / 60) + "분 " + (cnTime % (1000 * 60 * 60) / 1000 % 60) + "초";
		}
		if (cnTime / (1000 * 60 * 60 * 24) >= 1) {
			cnTimeString = (cnTime / (1000 * 60 * 60 * 24)) + "일 " + (cnTime % (1000 * 60 * 60 * 24) / 1000 / 60 / 60) + "시간 " + (cnTime % (1000 * 60 * 60 * 24) / 1000 / 60 % 60) + "분 " + (cnTime % (1000 * 60 * 60 * 24) / 1000 % 60 % 60) + "초";
		}
		
		connectLog.setCnTime(cnTimeString);
		
		logDAO.updateLogoutLog(connectLog);
	}

	@Override
	public ConnectLogVO getLoginLogByUserId(String userId) throws Exception {
		ConnectLogVO connectLog= logDAO.selectLoginLogByUserId(userId);
		return connectLog;
	}

	@Override
	public Map<String, Object> getSystemSchedulerLog(AdminLogCriteria adminCri) throws Exception {
		Map<String, Object> logData = new HashMap<String, Object>();
		
		PageMaker systemPageMaker = new PageMaker();
		systemPageMaker.setCri(adminCri);
		systemPageMaker.setTotalCount(logDAO.selectTotalSystemSchedulerLog(adminCri));
		
		List<SystemSchedulerLogVO> sysSchedLogList = logDAO.selectSystemSchedulerLog(adminCri);
		
		logData.put("systemLogList", sysSchedLogList);
		logData.put("pageMaker", systemPageMaker);
		
		return logData;
	}

	@Override
	public Map<String, Object> getFileLog(AdminLogCriteria adminCri) throws Exception {
		Map<String, Object> logData = new HashMap<String, Object>();
		
		List<FileLogVO> fileLogList = logDAO.selectFileLog(adminCri);
		
		PageMaker filePageMaker = new PageMaker();
		filePageMaker.setCri(adminCri);
		filePageMaker.setTotalCount(logDAO.selectTotalFileLog(adminCri));
		
		logData.put("fileLogList", fileLogList);
		logData.put("pageMaker", filePageMaker);
		
		return logData;
	}

	@Override
	public Map<String, Object> getConnectLog(AdminLogCriteria adminCri) throws Exception {
		Map<String, Object> logData = new HashMap<String, Object>();
		
		List<ConnectLogVO> connectLogList = logDAO.selectConnectLog(adminCri);
		
		PageMaker connectPageMaker = new PageMaker();
		connectPageMaker.setCri(adminCri);
		connectPageMaker.setTotalCount(logDAO.selectTotalConnectLog(adminCri));
		
		logData.put("connectLogList", connectLogList);
		logData.put("pageMaker", connectPageMaker);
		
		return logData;
	}

	@Override
	public Map<String, Object> getDbcnLog(AdminLogCriteria adminCri) throws Exception {
		Map<String, Object> logData = new HashMap<String, Object>();
		
		List<DbcnLogVO> dbcnLogList = logDAO.selectDbcnLog(adminCri);
		
		PageMaker dbcnPageMaker = new PageMaker();
		dbcnPageMaker.setCri(adminCri);
		dbcnPageMaker.setTotalCount(logDAO.selectTotalDbcnLog(adminCri));
		
		logData.put("dbcnLogList", dbcnLogList);
		logData.put("pageMaker", dbcnPageMaker);
		
		return logData;
	}
}
