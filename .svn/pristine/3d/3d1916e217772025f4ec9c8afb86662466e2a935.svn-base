package kr.or.pms.dao;

import java.util.List;

import kr.or.pms.command.ProjectCriteria;
import kr.or.pms.dto.ProjectVO;

public interface ProjectDAO {
	
	// 프로젝트 목록 조회
	List<ProjectVO> selectProjectCriteria(ProjectCriteria cri) throws Exception;
	
	// 참여중인 프로젝트 목록 조회
	List<ProjectVO> selectJoinProjectById(String userId) throws Exception;
	
	// 팀 프로젝트 목록 조회
	List<ProjectVO> selectTeamProjectListById(String userId) throws Exception;
	// 팀 프로젝트 목록 조회
	List<ProjectVO> selectTeamProjectListByTno(int teamNo) throws Exception;
	
	// 팀이 진행중인 프로젝트 목록 조회
	List<ProjectVO> selectTeamProceedingProjectListByTno(int teamNo) throws Exception;
	// 팀이 완료한 프로젝트 목록 조회
	List<ProjectVO> selectTeamCompleteProjectListByTno(int teamNo) throws Exception;
	
	// Record 프로젝트 목록 조회
	List<ProjectVO> selectRecordListCriteria(ProjectCriteria cri) throws Exception;

	// 리스트 전체 개수
	int selectProjectListTotalCount(ProjectCriteria cri) throws Exception;
	
	// 프로젝트 정보 조회
	ProjectVO selectProjectByPno(int prjNo) throws Exception;
	
	// 프로젝트 추가
	void insertProject(ProjectVO project) throws Exception;
	
	// 프로젝트 수정
	void updateProject(ProjectVO project) throws Exception;
	
	// 프로젝트 삭제
	void deleteProject(int prjNo) throws Exception;
	
	// 시퀀스
	int getSeqNextValue() throws Exception;
	
	
	// 진행중인 프로젝트 개수
	int selectProceedingPrjListCount(int teamNo) throws Exception;
	
	// 완료된 프로젝트 개수
	int selectCompletePrjListCount(int teamNo) throws Exception;
	
	// 보류중인 프로젝트 개수
	int selectHoldPrjListCount(int teamNo) throws Exception;
	
	// 
	
}
