package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.ScheduleVO;

public class CalendarDAOImpl implements CalendarDAO {

	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}


	@Override
	public List<PrjUserVO> selectPrjUserByUserId(String userId) throws SQLException {
		
		List<PrjUserVO> prjUserList = session.selectList("Calendar-Mapper.selectPrjUserByUserId",userId);
		
		return prjUserList;
	}

	@Override
	public List<String> selectPrjUserByPrjNo(int prjNo) throws SQLException {
		
		List<String> userList = session.selectList("Calendar-Mapper.selectPrjUserByPrjNo",prjNo);
		
		return userList;
	}
	
	
	@Override
	public List<ScheduleVO> selectRangeDt() throws SQLException {
		List<ScheduleVO> scheduleList = session.selectList("Calendar-Mapper.selectRangeDt");
		
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectPersonal(String userId) throws SQLException {
		List<ScheduleVO> scheduleList = session.selectList("Calendar-Mapper.selectPersonal",userId);
		
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectPersonalByDt(ScheduleVO schedule) throws SQLException {
		List<ScheduleVO> scheduleList = session.selectList("Calendar-Mapper.selectPersonalByDt",schedule);
		
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectOrderPersonal(ScheduleVO schedule) throws SQLException {
		List<ScheduleVO> scheduleList = session.selectList("Calendar-Mapper.selectOrderPersonal",schedule);
		
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectOrderPersonalByDt(ScheduleVO schedule) throws SQLException {
		List<ScheduleVO> scheduleList = session.selectList("Calendar-Mapper.selectOrderPersonalByDt",schedule);
		
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectCompany() throws SQLException {
		List<ScheduleVO> scheduleList = session.selectList("Calendar-Mapper.selectCompany");
		
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectCompanyByDt(String date) throws SQLException {
		List<ScheduleVO> scheduleList = session.selectList("Calendar-Mapper.selectCompanyByDt",date);
		
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectProject(int prjNo) throws SQLException {
		List<ScheduleVO> scheduleList = session.selectList("Calendar-Mapper.selectProject",prjNo);
		
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectProjectByDt(ScheduleVO schedule) throws SQLException {
		List<ScheduleVO> scheduleList = session.selectList("Calendar-Mapper.selectProjectByDt",schedule);
		
		return scheduleList;
	}
	
	@Override
	public ScheduleVO selectScheduleBySchedNo(int schedNo) throws SQLException {
		ScheduleVO vo = session.selectOne("Calendar-Mapper.selectScheduleBySchedNo",schedNo);
		return vo;
	}


	@Override
	public ProjectVO selectPrjNmByPrjNo(int prjNo) throws SQLException {
		
		ProjectVO prjVO = session.selectOne("Calendar-Mapper.selectPrjNmByPrjNo",prjNo);
		
		return prjVO;
	}

	@Override
	public int getSchedNo() throws SQLException {
		int schedNo = session.selectOne("Calendar-Mapper.getSchedNo");
		return schedNo;
	}
	
	@Override
	public void insertSchedule(ScheduleVO schedule) throws SQLException {
		
		if(schedule.getPrjNo() == 0) {
			session.update("Calendar-Mapper.insertSchedule",schedule);
		} else {
			session.update("Calendar-Mapper.insertProjectSchedule",schedule);
		}
		
	}


	@Override
	public void modifySchedule(ScheduleVO schedule) throws SQLException {
		
		if(schedule.getPrjNo() == 0) {
			session.update("Calendar-Mapper.modifySchedule",schedule);
		} else {
			session.update("Calendar-Mapper.modifyProjectSchedule",schedule);
		}
		
	}


	@Override
	public void deleteSchedule(int schedNo) throws SQLException {
		session.update("Calendar-Mapper.deleteSchedule",schedNo);
	}


	

	


	

}
