package kr.or.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.exception.InsufficientConditionException;

public class ComCodeDAOImpl implements ComCodeDAO {

	private SqlSession session;	
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<ComCodeVO> selectComCodeListofUser() throws Exception {
		return session.selectList("ComCode-Mapper.selectComCodeListOfUser");
	}
	
	@Override
	public List<ComCodeVO> selectComCodeListByCodeGrp(String codeGrp) throws Exception {
		return session.selectList("ComCode-Mapper.selectComCodeListByCodeGrp", codeGrp);
	}
	
	@Override
	public ComCodeVO selectComCodeByCode(ComCodeVO comCodeVO) throws Exception {
		return session.selectOne("ComCode-Mapper.selectComCodeByCode", comCodeVO);
	}

	@Override
	public String selectComCodeNmByCode(ComCodeVO comCodeVO) throws Exception {
		return session.selectOne("ComCode-Mapper.selectComCodeNmByCode", comCodeVO);
	}
	
	@Override
	public void insertDept(ComCodeVO comCodeVO) throws Exception {
		session.selectOne("ComCode-Mapper.insertDept", comCodeVO);
	}
	
	@Override
	public String selectDeptCodeNext() throws Exception {
		return session.selectOne("ComCode-Mapper.selectDeptCodeNext");
	}

	@Override
	public void deleteDept(String deptCode) throws InsufficientConditionException, Exception {
		int userCount =  session.selectOne("User-Mapper.countUserInDept", deptCode); 
		if(userCount > 0) {
			throw new InsufficientConditionException("소속된 사원이 있는 부서는 삭제할 수 없습니다.");
		}
		session.update("ComCode-Mapper.deleteDept", deptCode);
	}

	@Override
	public void updateDept(ComCodeVO comCodeVO) throws Exception {
		session.update("ComCode-Mapper.updateDept", comCodeVO);
	}
	
	@Override
	public int checkDept(String deptName ) throws Exception {
		return session.selectOne("ComCode-Mapper.checkDeptCode", deptName);
	}
}
