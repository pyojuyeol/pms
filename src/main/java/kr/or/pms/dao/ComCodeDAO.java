package kr.or.pms.dao;

import java.util.List;

import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.exception.InsufficientConditionException;

public interface ComCodeDAO {
	
	//공통코드 리스트 가져오기
	public List<ComCodeVO> selectComCodeListofUser () throws Exception;
	public List<ComCodeVO> selectComCodeListByCodeGrp (String comCodeGrp) throws Exception;

	//공통코드 가져오기
	public ComCodeVO selectComCodeByCode(ComCodeVO comCodeVO) throws Exception;
	
	//공통코드 이름 가져오기
	public String selectComCodeNmByCode(ComCodeVO comCodeVO) throws Exception;
	
	//부서 추가하기
	void insertDept(ComCodeVO comCodeVO) throws Exception;
	
	//부서 다음번호 받아오기
	String selectDeptCodeNext() throws Exception;
	
	//부서 삭제하기
	void deleteDept(String deptCode) throws InsufficientConditionException, Exception;
	
	//부서 이름 바꾸기
	void updateDept(ComCodeVO comCodeVO) throws Exception;
	
	//부서 중복체크
	int checkDept(String deptName ) throws Exception;
}
