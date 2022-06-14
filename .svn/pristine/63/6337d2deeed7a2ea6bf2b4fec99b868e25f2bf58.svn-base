package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.dao.CalendarDAO;
import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.ScheduleVO;

public class CalendarServiceImpl implements CalendarService {
	
	private CalendarDAO calendarDAO;
	public void setCalendarDAO(CalendarDAO calendarDAO) {
		this.calendarDAO = calendarDAO;
	}

	@Override
	public Map<String, Object> selectCalendar(String userId) throws SQLException {
		
		List<PrjUserVO> prjUserList = calendarDAO.selectPrjUserByUserId(userId);
		
		String[] colors = {"#eec44b","#35548e","#cc8db2","#feeea9","#b6332d","#e26b48"};
		int i = 0;
		if(prjUserList != null) {
			for(PrjUserVO vo : prjUserList) {
				vo.setColor(colors[i]);
				int prjNo = vo.getPrjNo();
				ProjectVO prjVO = calendarDAO.selectPrjNmByPrjNo(prjNo);
				vo.setPrjNm(prjVO.getPrjNm());
				vo.setBgnDt(prjVO.getBgnDt());
				vo.setEndDt(prjVO.getEndDt());;
				i++;
			}
		}
		
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("prjUserList",prjUserList);
		
		return dataMap;
	}

	@Override
	public List<String> selectPrjUserByPrjNo(int prjNo) throws SQLException {
		List<String> userList = calendarDAO.selectPrjUserByPrjNo(prjNo);
		return userList;
	}
	
	@Override
	public List<ScheduleVO> selectScheduleByDt(ScheduleVO schedule) throws SQLException {
		
		List<ScheduleVO> scheduleList = calendarDAO.selectCompanyByDt(schedule.getSelectDt());
		scheduleList.addAll(calendarDAO.selectPersonalByDt(schedule));
		scheduleList.addAll(calendarDAO.selectOrderPersonalByDt(schedule));
		
		List<PrjUserVO> prjUserList = calendarDAO.selectPrjUserByUserId(schedule.getRegister());
		for(PrjUserVO vo : prjUserList) {
			schedule.setPrjNo(vo.getPrjNo());
			scheduleList.addAll(calendarDAO.selectProjectByDt(schedule));
		}
		
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectRangeDt() throws SQLException {
		List<ScheduleVO> scheduleList = calendarDAO.selectRangeDt();
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectPersonal(String userId) throws SQLException {
		List<ScheduleVO> scheduleList = calendarDAO.selectPersonal(userId);
		return scheduleList;
	}

	@Override
	public List<ScheduleVO> selectOrderPersonal(ScheduleVO schedule) throws SQLException {
		List<ScheduleVO> scheduleList = calendarDAO.selectOrderPersonal(schedule);
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectCompany() throws SQLException {
		List<ScheduleVO> scheduleList = calendarDAO.selectCompany();
		return scheduleList;
	}
	
	@Override
	public List<ScheduleVO> selectProject(int prjNo) throws SQLException {
		List<ScheduleVO> scheduleList = calendarDAO.selectProject(prjNo);
		return scheduleList;
	}
	
	@Override
	public ScheduleVO selectScheduleBySchedNo(int schedNo) throws SQLException {
		ScheduleVO vo = calendarDAO.selectScheduleBySchedNo(schedNo);
		return vo;
	}

	@Override
	public int getSchedNo() throws SQLException {
		int schedNo = calendarDAO.getSchedNo();
		return schedNo;
	}
	
	@Override
	public void insertSchedule(ScheduleVO schedule) throws SQLException {
		calendarDAO.insertSchedule(schedule);
	}

	@Override
	public void modifySchedule(ScheduleVO schedule) throws SQLException {
		calendarDAO.modifySchedule(schedule);
	}

	@Override
	public void deleteSchedule(int schedNo) throws SQLException {
		calendarDAO.deleteSchedule(schedNo);
	}

	

	


	

}
