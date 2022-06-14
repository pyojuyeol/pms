package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.dto.TmemVO;

public class TmemDAOImpl implements TmemDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<TmemVO> selectTeamMembersByTno(int teamNo) throws SQLException {
		List<TmemVO> tmemList = session.selectList("Tmem-Mapper.selectTeamMembersByTno", teamNo);
		return tmemList;
	}
	
	@Override
	public List<TmemVO> selectTeamMembersByTnoD(int teamNo) throws SQLException {
		List<TmemVO> tmemList = session.selectList("Tmem-Mapper.selectTeamMembersByTnoD", teamNo);
		return tmemList;
	}

	@Override
	public TmemVO selectTeamMemberById(TmemVO tmem) throws SQLException {
		TmemVO teamMember = session.selectOne("Tmem-Mapper.selectTeamMemberByMno", tmem);
		return teamMember;
	}

	@Override
	public void insertTeamMember(TmemVO tmem) throws SQLException {
		session.update("Tmem-Mapper.insertTeamMember", tmem);
	}
	
	@Override
	public void updateTeamMember(TmemVO tmem) throws SQLException {
		session.update("Tmem-Mapper.updateTeamMember", tmem);
	}

	@Override
	public void deleteTeamMember(TmemVO tmem) throws SQLException {
		session.update("Tmem-Mapper.deleteTeamMember", tmem);
	}

	@Override
	public void deleteAllTeamMember(int teamNo) throws SQLException {
		session.update("Tmem-Mapper.deleteAllTeamMember", teamNo);
	}

	@Override
	public int getSeqNextValue() throws SQLException {
		int tmemNo = session.selectOne("Tmem-Mapper.selectTmemSeqNext");
		return tmemNo;
	}

}
