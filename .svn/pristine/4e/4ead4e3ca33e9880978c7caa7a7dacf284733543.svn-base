package kr.or.pms.service;

import java.util.List;

import kr.or.pms.dao.ComCodeDAO;
import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.exception.InsufficientConditionException;

public class ComCodeServiceImpl implements ComCodeService{
	
	private ComCodeDAO comCodeDAO;
	public void setComCodeDAO(ComCodeDAO comCodeDAO) {
		this.comCodeDAO = comCodeDAO;
	}
	
	@Override
	public List<ComCodeVO> getComCodeListOfUser() throws Exception {
		return comCodeDAO.selectComCodeListofUser();
	}
	
	@Override
	public List<ComCodeVO> getComCodeListByCodeGrp(String comCodeGrp) throws Exception {
		return comCodeDAO.selectComCodeListByCodeGrp(comCodeGrp);
	}

	@Override
	public ComCodeVO getComCodeByCode(ComCodeVO comCodeVO) throws Exception {
		return comCodeDAO.selectComCodeByCode(comCodeVO);
	}

	@Override
	public String getComCodeNmByCode(ComCodeVO comCodeVO) throws Exception {
		return comCodeDAO.selectComCodeNmByCode(comCodeVO);
	}

	@Override
	public void registDept(String deptNm) throws Exception {
		ComCodeVO dept = new ComCodeVO();
		dept.setNm(deptNm);
		dept.setCode(comCodeDAO.selectDeptCodeNext());
		comCodeDAO.insertDept(dept);
	}

	@Override
	public void removeDept(String deptCode) throws InsufficientConditionException, Exception {
		comCodeDAO.deleteDept(deptCode);
	}

	@Override
	public void modifyDept(ComCodeVO comCodeVO) throws Exception {
		comCodeDAO.updateDept(comCodeVO);
	}

	@Override
	public int checkDept(String deptName) throws Exception {
		return comCodeDAO.checkDept(deptName);
	}
}
