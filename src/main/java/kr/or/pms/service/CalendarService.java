package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.dto.ScheduleVO;

public interface CalendarService {
	
	Map<String, Object> selectCalendar(String userId) throws SQLException;
	List<String> selectPrjUserByPrjNo(int prjNo) throws SQLException;
	
	List<ScheduleVO> selectRangeDt() throws SQLException;
	List<ScheduleVO> selectScheduleByDt(ScheduleVO schedule) throws SQLException;
	List<ScheduleVO> selectPersonal(String userId) throws SQLException;
	List<ScheduleVO> selectOrderPersonal(ScheduleVO schedule) throws SQLException;
	List<ScheduleVO> selectCompany() throws SQLException;
	List<ScheduleVO> selectProject(int prjNo) throws SQLException;
	ScheduleVO selectScheduleBySchedNo(int schedNo) throws SQLException;
	
	int getSchedNo() throws SQLException;
	
	void insertSchedule(ScheduleVO schedule) throws SQLException;
	void modifySchedule(ScheduleVO schedule) throws SQLException;
	void deleteSchedule(int schedNo) throws SQLException;
	
}
