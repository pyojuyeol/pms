package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.TeamVO;

public class TeamDAOImpl implements TeamDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<TeamVO> selectTeamCriteria(Criteria cri) throws SQLException {
		
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<TeamVO> teamList = session.selectList("Team-Mapper.selectSearchTeamList", cri, rowBounds);
		
		return teamList;
	}
	
	@Override
	public List<TeamVO> selectTeamList() throws Exception {
		List<TeamVO> teamList = session.selectList("Team-Mapper.selectSearchTeamList");
		return teamList;
	}
	
	@Override
	public int selectTeamListTotalCount(Criteria cri) throws SQLException {
		int totalCount = session.selectOne("Team-Mapper.selectSearchTeamListCount", cri);
		return totalCount;
	}
	
	@Override
	public TeamVO selectTeamByTno(int teamNo) throws SQLException {
		TeamVO team = session.selectOne("Team-Mapper.selectTeamByTno", teamNo);
		return team;
	}
	
	@Override
	public void insertTeam(TeamVO team) throws SQLException {
		session.update("Team-Mapper.insertTeam", team);
	}
	
	@Override
	public void updateTeam(TeamVO team) throws SQLException {
		session.update("Team-Mapper.updateTeam", team);
	}
	
	@Override
	public void deleteTeam(int teamNo) throws SQLException {
		session.update("Team-Mapper.deleteTeam", teamNo);
	}
	
	@Override
	public int getSeqNextValue() throws SQLException {
		int teamNo = session.selectOne("Team-Mapper.selectTeamSeqNext");
		return teamNo;
	}

}
