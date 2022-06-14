package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.TmemLogVO;

public class TmemLogDAOImpl implements TmemLogDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<TmemLogVO> selectTeamMemberLogList(Criteria cri) throws SQLException {

		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<TmemLogVO> tmemLogList = session.selectList("TmemLog-Mapper.selectTeamMemberLogList", cri, rowBounds);	
			
		return tmemLogList;
	}
	
	@Override
	public List<TmemLogVO> selectTeamMemberLogsByTno(int teamNo) throws SQLException {
		
		List<TmemLogVO> tmemLogListByTno = session.selectList("TmemLog-Mapper.selectTeamMemberLogsByTno", teamNo);	
			
		return tmemLogListByTno;
	}

	@Override
	public TmemLogVO selectTeamMemberLogByLno(int tmemLogNo) throws SQLException {
		TmemLogVO tmemLogListByLno = session.selectOne("TmemLog-Mapper.selectTeamMemberLogsByLno", tmemLogNo);
		return tmemLogListByLno;
	}

	@Override
	public void insertTeamMemberLog(TmemLogVO tmemLog) throws SQLException {
		session.update("TmemLog-Mapper.insertTeamMemberLog", tmemLog);
	}
	
	@Override
	public void deleteTeamMemberLog(TmemLogVO tmemLog) throws SQLException {
		session.update("TmemLog-Mapper.deleteTeamMemberLog", tmemLog);
		
	}

	@Override
	public int getSeqNextValue() throws SQLException {
		int tmemLogNo = session.selectOne("TmemLog-Mapper.selectTmemLogSeqNext");
		return tmemLogNo;
	}



}
