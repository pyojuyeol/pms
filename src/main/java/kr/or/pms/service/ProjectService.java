package kr.or.pms.service;

import java.util.List;
import java.util.Map;

import kr.or.pms.command.ProjectCriteria;
import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ProjectVO;

public interface ProjectService {

	// 프로젝트 리스트 조회
	Map<String, Object> getProjectList(ProjectCriteria cri) throws Exception;
	
	// 참여중인 프로젝트 리스트 조회
	List<ProjectVO> getJoinProjectList(String userId) throws Exception;
	
	// 팀 프로젝트 리스트 조회
	List<ProjectVO> getTeamProjectList(String userId) throws Exception;
	// 팀 프로젝트 리스트 조회
	List<ProjectVO> getTeamProjectListByTno(int teamNo) throws Exception;
	
	// 팀이 진행중인 프로젝트 목록 조회
	List<ProjectVO> getTeamProceedingProjectListByTno(int teamNo) throws Exception;
	// 팀이 완료한 프로젝트 목록 조회
	List<ProjectVO> getTeamCompleteProjectListByTno(int teamNo) throws Exception;
	
	// Record 프로젝트 목록 조회
	Map<String, Object> getRecordList(ProjectCriteria cri) throws Exception;
	
	// 프로젝트 상세조회
	public ProjectVO getProject(int prjNo) throws Exception;
	
	// 프로젝트 등록
	void regist(ProjectVO project, String userId) throws Exception;
	
	// 프로젝트 수정
	void modify(ProjectVO project) throws Exception; 
	
	// 프로젝트 삭제
	void remove(int prjNo) throws Exception;
	
	//
	List<PrjUserVO> getProjectUserList(int prjNo) throws Exception;
	
	// 시퀀스
	int getSeqNextValue() throws Exception;
}
