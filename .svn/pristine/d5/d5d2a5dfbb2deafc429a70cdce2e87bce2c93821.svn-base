package kr.or.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.dto.PrjUserVO;

public class PrjUserDAOImpl implements PrjUserDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<PrjUserVO> selectPrjUsersByPno(int prjNo) throws Exception {
		List<PrjUserVO> prjUserList = session.selectList("PrjUser-Mapper.selectPrjUsersByPno", prjNo); 
		return prjUserList;
	}
	
	@Override
	public List<PrjUserVO> selectDisabledPrjUsersByPno(int prjNo) throws Exception {
		List<PrjUserVO> prjUserList = session.selectList("PrjUser-Mapper.selectDisabledPrjUsersByPno", prjNo); 
		return prjUserList;
	}

	@Override
	public PrjUserVO selectTeamMemberByUno(PrjUserVO prjUser) throws Exception {
		PrjUserVO user = session.selectOne("PrjUser-Mapper.selectPrjUserByUno", prjUser);
		return user;
	}

	@Override
	public void insertPrjUser(PrjUserVO prjUser) throws Exception {
		session.update("PrjUser-Mapper.insertPrjUser", prjUser);
	}

	@Override
	public void updatePrjUser(PrjUserVO prjUser) throws Exception {
		session.update("PrjUser-Mapper.updatePrjUser", prjUser);
	}
	
	@Override
	public void updatePrjUserRole(PrjUserVO prjUser) throws Exception {
		session.update("PrjUser-Mapper.updatePrjUserRole", prjUser);
	}
	
	@Override
	public void deletePrjUser(PrjUserVO prjUser) throws Exception {
		session.update("PrjUser-Mapper.deletePrjUser", prjUser);
	}

	@Override
	public void deleteAllPrjUser(int prjNo) throws Exception {
		session.update("PrjUser-Mapper.deleteAllPrjUser", prjNo);
	}

	@Override
	public void enabled(PrjUserVO prjUser) throws Exception {
		session.update("PrjUser-Mapper.enabled", prjUser);
	}

}
