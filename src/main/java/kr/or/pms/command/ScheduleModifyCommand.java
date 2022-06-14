package kr.or.pms.command;

import kr.or.pms.dto.ScheduleVO;

public class ScheduleModifyCommand extends ScheduleRegistCommand{
	
	private int schedNo;
	
	public int getSchedNo() {
		return schedNo;
	}
	public void setSchedNo(int schedNo) {
		this.schedNo = schedNo;
	}

	@Override
	public ScheduleVO toScheduleVO() throws Exception {
		
		ScheduleVO vo = super.toScheduleVO();
		
		vo.setSchedNo(schedNo);
		
		return vo;
	}
	
	
	
}
