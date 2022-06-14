package kr.or.pms.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.ProjectCriteria;
import kr.or.pms.dto.ProjectVO;

public class ProjectDAOImpl implements ProjectDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<ProjectVO> selectProjectCriteria(ProjectCriteria cri) throws Exception {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
	
		List<ProjectVO> projectList = session.selectList("Project-Mapper.selectSearchProjectList", cri, rowBounds); 
		
		return projectList;
	}

	@Override
	public List<ProjectVO> selectRecordListCriteria(ProjectCriteria cri) throws Exception {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
	
		List<ProjectVO> RecordList = session.selectList("Project-Mapper.selectSearchRecordList", cri, rowBounds); 
		return RecordList;
	}
	
	@Override
	public List<ProjectVO> selectJoinProjectById(String userId) throws Exception {
		List<ProjectVO> joinPrjList = session.selectList("Project-Mapper.selectJoinProjectListById", userId);
		return joinPrjList;
	}
	
	@Override
	public List<ProjectVO> selectTeamProjectListById(String userId) throws Exception {
		List<ProjectVO> teamPrjList = session.selectList("Project-Mapper.selectTeamProjectListById", userId);
		return teamPrjList;
	}

	@Override
	public List<ProjectVO> selectTeamProjectListByTno(int teamNo) throws Exception {
		List<ProjectVO> teamPrjList = session.selectList("Project-Mapper.selectTeamProjectListByTno", teamNo);
		return teamPrjList;
	}
	
	
	@Override
	public List<ProjectVO> selectTeamProceedingProjectListByTno(int teamNo) throws Exception {
		List<ProjectVO> teamPrjList = session.selectList("Project-Mapper.selectTeamProceedingProjectListByTno", teamNo);
		return teamPrjList;
	}

	@Override
	public List<ProjectVO> selectTeamCompleteProjectListByTno(int teamNo) throws Exception {
		List<ProjectVO> teamPrjList = session.selectList("Project-Mapper.selectTeamCompleteProjectListByTno", teamNo);
		return teamPrjList;
	}
	
	@Override
	public int selectProjectListTotalCount(ProjectCriteria cri) throws Exception {
		int totalCount = session.selectOne("Project-Mapper.selectSearchProjectListCount", cri);
		return totalCount;
	}

	@Override
	public ProjectVO selectProjectByPno(int prjNo) throws Exception {
		ProjectVO project = session.selectOne("Project-Mapper.selectProjectByPno", prjNo);
		return project;
	}

	@Override
	public void insertProject(ProjectVO project) throws Exception {
		session.update("Project-Mapper.insertProject", project);
	}

	@Override
	public void updateProject(ProjectVO project) throws Exception {
		session.update("Project-Mapper.updateProject", project);
	}

	@Override
	public void deleteProject(int prjNo) throws Exception {
		session.update("Project-Mapper.deleteProject", prjNo);
	}

	@Override
	public int getSeqNextValue() throws Exception {
		int prjNo = session.selectOne("Project-Mapper.selectProjectSeqNext");
		return prjNo;
	}

	@Override
	public int selectProceedingPrjListCount(int teamNo) throws Exception {
		int pCnt = session.selectOne("Project-Mapper.selectProceedingPrjListCount", teamNo);
		return pCnt;
	}

	@Override
	public int selectCompletePrjListCount(int teamNo) throws Exception {
		int cCnt = session.selectOne("Project-Mapper.selectCompletePrjListCount", teamNo);
		return cCnt;
	}

	@Override
	public int selectHoldPrjListCount(int teamNo) throws Exception {
		int hCnt = session.selectOne("Project-Mapper.selectHoldPrjListCount", teamNo);
		return hCnt;
	}


}
