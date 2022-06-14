package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.AdminLogCriteria;
import kr.or.pms.dto.ConnectLogVO;
import kr.or.pms.dto.DbcnLogVO;
import kr.or.pms.dto.FileLogVO;
import kr.or.pms.dto.SystemSchedulerLogVO;

public class LogDAOImpl implements LogDAO{

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	
	@Override
	public void insertFileDownloadLog(FileLogVO fileLog) throws SQLException {
		session.update("Log-Mapper.insertFileDownloadLog", fileLog);
	}

	@Override
	public int selectFileLogSeqNext() throws SQLException {
		int fileLogSeqNextValue = session.selectOne("Log-Mapper.selectFileLogSeqNext");
		
		return fileLogSeqNextValue;
	}

	@Override
	public void insertLoginLog(ConnectLogVO connectLog) throws SQLException {
		session.update("Log-Mapper.insertLoginLog", connectLog);
	}

	@Override
	public void updateLogoutLog(ConnectLogVO connectLog) throws SQLException {
		session.update("Log-Mapper.updateLogoutLog", connectLog);
	}

	@Override
	public int selectConnectLogSeqNext() throws SQLException {
		int connectLogSeqNextValue = session.selectOne("Log-Mapper.selectConnectLogSeqNext");
		
		return connectLogSeqNextValue;
	}

	@Override
	public ConnectLogVO selectLoginLogByUserId(String userId) throws SQLException {
		ConnectLogVO connectLog = session.selectOne("Log-Mapper.selectLoginLogByUserId", userId);
		
		return connectLog;
	}

	@Override
	public List<SystemSchedulerLogVO> selectSystemSchedulerLog(AdminLogCriteria admincri) throws SQLException {
		List<SystemSchedulerLogVO> sysSchedLogList = session.selectList("Log-Mapper.selectSystemSchedulerLog", admincri);
		return sysSchedLogList;
	}

	@Override
	public List<FileLogVO> selectFileLog(AdminLogCriteria admincri) throws SQLException {
		List<FileLogVO> fileLogList = session.selectList("Log-Mapper.selectFileLog", admincri);
		return fileLogList;
	}

	@Override
	public List<ConnectLogVO> selectConnectLog(AdminLogCriteria admincri) throws SQLException {
		List<ConnectLogVO> connectLogList = session.selectList("Log-Mapper.selectConnectLog", admincri);
		return connectLogList;
	}

	@Override
	public List<DbcnLogVO> selectDbcnLog(AdminLogCriteria admincri) throws SQLException {
		List<DbcnLogVO> dbcnLogList = session.selectList("Log-Mapper.selectDbcnLog", admincri);
		return dbcnLogList;
	}

	@Override
	public int selectTotalConnectLog(AdminLogCriteria admincri) throws SQLException {
		int cnt = session.selectOne("Log-Mapper.selectTotalConnectLog", admincri);
		return cnt;
	}

	@Override
	public int selectTotalFileLog(AdminLogCriteria admincri) throws SQLException {
		int cnt = session.selectOne("Log-Mapper.selectTotalFileLog", admincri);
		return cnt;
	}

	@Override
	public int selectTotalSystemSchedulerLog(AdminLogCriteria admincri) throws SQLException {
		int cnt = session.selectOne("Log-Mapper.selectTotalSystemSchedulerLog", admincri);
		return cnt;
	}

	@Override
	public int selectTotalDbcnLog(AdminLogCriteria admincri) throws SQLException {
		int cnt = session.selectOne("Log-Mapper.selectTotalDbcnLog", admincri);
		return cnt;
	}
	
	@Override
	public void insertDbcnLog(DbcnLogVO dbcnLog) throws SQLException {
		session.update("Log-Mapper.insertDbcnLog", dbcnLog);
	}
	
}
