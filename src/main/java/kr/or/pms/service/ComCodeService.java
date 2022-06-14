package kr.or.pms.service;

import java.util.List;

import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.exception.InsufficientConditionException;

public interface ComCodeService {
	
	//공통코드 리스트 가져오기
	public List<ComCodeVO> getComCodeListOfUser() throws Exception;
	
	public List<ComCodeVO> getComCodeListByCodeGrp (String comCodeGrp) throws Exception;

	//공통코드 가져오기
	public ComCodeVO getComCodeByCode(ComCodeVO comCodeVO) throws Exception;
	
	//공통코드 이름 가져오기
	public String getComCodeNmByCode(ComCodeVO comCodeVO) throws Exception;
	
	//부서 추가하기
	public void registDept(String deptNm) throws Exception;
	
	//부서 삭제하기
	public void removeDept(String deptCode) throws InsufficientConditionException, Exception;
	
	//부서 이름 수정하기
	public void modifyDept(ComCodeVO comCodeVO) throws Exception;
	
	//부서 중복체크
	public int checkDept(String deptName) throws Exception;
}
