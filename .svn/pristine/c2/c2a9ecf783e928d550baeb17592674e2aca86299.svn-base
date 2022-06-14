package kr.or.pms.dao;

import java.util.List;

import kr.or.pms.dto.PrjUserVO;

public interface PrjUserDAO {
	
	// 프로젝트 멤버 리스트 조회
	public List<PrjUserVO> selectPrjUsersByPno(int prjNo) throws Exception;
	
	// 프로젝트 비활성 멤버 리스트 조회
	public List<PrjUserVO> selectDisabledPrjUsersByPno(int prjNo) throws Exception;
	
	// 프로젝트 멤버 조회
	public PrjUserVO selectTeamMemberByUno(PrjUserVO prjUser) throws Exception;
	
	// 프로젝트 멤버 추가
	public void insertPrjUser(PrjUserVO prjUser) throws Exception;
	
	// 프로젝트 멤버 수정
	public void updatePrjUser(PrjUserVO prjUser) throws Exception;
	
	// 프로젝트 멤버 역할 수정
	public void updatePrjUserRole(PrjUserVO prjUser) throws Exception;

	// 프로젝트 멤버 삭제
	public void deletePrjUser(PrjUserVO prjUser) throws Exception;
	
	// 전체 프로젝트 멤버 삭제
	public void deleteAllPrjUser(int prjNo) throws Exception;
	
	// 이메일 확인 멤버 활성화
	public void enabled(PrjUserVO prjUser) throws Exception;
}
