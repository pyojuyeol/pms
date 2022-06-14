package kr.or.pms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.WorkCriteria;
import kr.or.pms.dto.WorkVO;

public class WorkDAOImpl implements WorkDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<WorkVO> selectWorkListCriteriaByPno(WorkCriteria cri, int prjNo) throws Exception {
		cri.setPrjNo(prjNo);
		
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<WorkVO> workList = session.selectList("Work-Mapper.selectSearchWorkListByPno", cri, rowBounds);
		
		return workList;
	}
	
	@Override
	public List<WorkVO> selectMyWorkListById(WorkCriteria cri, String userId) throws Exception {
		cri.setUserId(userId);
		
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<WorkVO> myWorkList = session.selectList("Work-Mapper.selectMyWorkListById", cri, rowBounds);
		return myWorkList;
	}
	@Override
	public int selectMyWorkListTotalCountById(WorkCriteria cri, String userId) throws Exception {
		int totalCount = session.selectOne("Work-Mapper.selectMyWorkListCountById", cri);
		return totalCount;
	}
	
	
	
	@Override
	public List<WorkVO> selectWorkListByPno(int prjNo) throws Exception {
		List<WorkVO> workList = session.selectList("Work-Mapper.selectWorkListByPno", prjNo);
		return workList;
	}
	
	@Override
	public List<WorkVO> selectWorkListHomeById(String userId) throws Exception {
		List<WorkVO> workList = session.selectList("Work-Mapper.selectWorkListHomeById", userId);
		return workList;
	}
	
	@Override
	public List<WorkVO> selectWorkListProjectHomeById(Map<String, String> map) throws Exception {
		List<WorkVO> workList = session.selectList("Work-Mapper.selectWorkListProjectHomeById", map);
		return workList;
	}
	
	@Override
	public List<WorkVO> selectSubWorkListByUno(int upWork) throws Exception {
		List<WorkVO> subWorkList = session.selectList("Work-Mapper.selectSubWorkListByUno", upWork);
		return subWorkList;
	}
	
	@Override
	public List<WorkVO> selectSubWorksByUno(int upWork) throws Exception {
		List<WorkVO> subWorkList = session.selectList("Work-Mapper.selectSubWorksByUno", upWork);
		return subWorkList;
	}
	
	@Override
	public List<WorkVO> selectWorkListFromGantt(WorkVO workVO) throws Exception {
		List<WorkVO> workList = session.selectList("Work-Mapper.selectWorkListFromGantt", workVO);
		return workList;
	}
	
	@Override
	public int selectWorkListTotalCountCriteriaByPno(WorkCriteria cri, int prjNo) throws Exception {
		cri.setPrjNo(prjNo);
		
		int totalCount = session.selectOne("Work-Mapper.selectSearchWorkListCountByPno", cri);
		return totalCount;
	}
	@Override
	public int selectWorkListTotalCountByPno(int prjNo) throws Exception {
		int totalCount = session.selectOne("Work-Mapper.selectWorkListCountByPno", prjNo);
		return totalCount;
	}
	
	@Override
	public WorkVO selectWorkByWno(int workNo) throws Exception {
		WorkVO work = session.selectOne("Work-Mapper.selectWorkByWno", workNo);
		return work;
	}

	@Override
	public void insertWork(WorkVO work) throws Exception {
		session.update("Work-Mapper.insertWork", work);
	}

	@Override
	public void updateWork(WorkVO work) throws Exception {
		session.update("Work-Mapper.updateWork", work);
	}

	@Override
	public void endWork(WorkVO work) throws Exception {
		session.update("Work-Mapper.endWork", work);
	}

	@Override
	public void deleteWork(int workNo) throws Exception {
		session.update("Work-Mapper.deleteWork", workNo);
	}

	@Override
	public int getSeqNextValue() throws Exception {
		int workNo = session.selectOne("Work-Mapper.selectWorkSeqNext");
		return workNo;
	}

	@Override
	public int selectPnoByWno(int workNo) throws Exception {
		int prjNo = session.selectOne("Work-Mapper.selectPnoByWno", workNo);
		return prjNo;
	}

	@Override
	public int selectRequestWorkCountByPno(int prjNo) throws Exception {
		int cnt = session.selectOne("Work-Mapper.selectRequestWorkCount", prjNo);
		return cnt;
	}

	@Override
	public int selectProceedingWorkCountByPno(int prjNo) throws Exception {
		int cnt = session.selectOne("Work-Mapper.selectProceedingWorkCount", prjNo);
		return cnt;
	}

	@Override
	public int selectFeedbackWorkCountByPno(int prjNo) throws Exception {
		int cnt = session.selectOne("Work-Mapper.selectFeedbackWorkCount", prjNo);
		return cnt;
	}

	@Override
	public int selectCompleteWorkCountByPno(int prjNo) throws Exception {
		int cnt = session.selectOne("Work-Mapper.selectCompleteWorkCount", prjNo);
		return cnt;
	}

	@Override
	public int selectHoldWorkCountByPno(int prjNo) throws Exception {
		int cnt = session.selectOne("Work-Mapper.selectHoldWorkCount", prjNo);
		return cnt;
	}

	@Override
	public String selectUserNmById(String userId) throws Exception {
		String userNm = session.selectOne("Work-Mapper.selectUserNmById", userId);
		return userNm;
	}





}
