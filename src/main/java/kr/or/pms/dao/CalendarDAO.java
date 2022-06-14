package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.ScheduleVO;

public interface CalendarDAO {
	
	List<PrjUserVO> selectPrjUserByUserId(String userId) throws SQLException;
	List<String> selectPrjUserByPrjNo(int PrjNo) throws SQLException;
	
	List<ScheduleVO> selectRangeDt() throws SQLException;
	List<ScheduleVO> selectPersonal(String userId) throws SQLException;
	List<ScheduleVO> selectPersonalByDt(ScheduleVO schedule) throws SQLException;
	List<ScheduleVO> selectOrderPersonal(ScheduleVO schedule) throws SQLException;
	List<ScheduleVO> selectOrderPersonalByDt(ScheduleVO schedule) throws SQLException;
	List<ScheduleVO> selectCompany() throws SQLException;
	List<ScheduleVO> selectCompanyByDt(String date) throws SQLException;
	List<ScheduleVO> selectProject(int prjNo) throws SQLException;
	List<ScheduleVO> selectProjectByDt(ScheduleVO schedule) throws SQLException;
	
	ScheduleVO selectScheduleBySchedNo(int schedNo) throws SQLException;
	
	ProjectVO selectPrjNmByPrjNo(int prjNo) throws SQLException;
	
	int getSchedNo() throws SQLException;
	
	void insertSchedule(ScheduleVO schedule) throws SQLException;
	void modifySchedule(ScheduleVO schedule) throws SQLException;
	void deleteSchedule(int schedNo) throws SQLException;
	
}
