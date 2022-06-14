package kr.or.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.dto.WorkLogVO;

public class WorkLogDAOImpl implements WorkLogDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<WorkLogVO> selectWorkLogListByWno(int workNo) throws Exception {
		List<WorkLogVO> workLogList = session.selectList("WorkLog-Mapper.selectWorkLogListByWno", workNo);
		return workLogList;
	}

	@Override
	public void insertWorkLog(WorkLogVO workLog) throws Exception {
		session.update("WorkLog-Mapper.insertWorkLog", workLog);
	}

	@Override
	public String selectUserNmById(String userId) throws Exception {
		String userNm = session.selectOne("Work-Mapper.selectUserNmById", userId);
		return userNm;
	}
}
