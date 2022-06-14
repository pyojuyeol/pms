package kr.or.pms.command;

import kr.or.pms.dto.TeamVO;

public class TeamModifyCommand extends TeamRegistCommand {

	private String teamNo;		// 팀번호
	
	private String open;
	
	public String getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(String teamNo) {
		this.teamNo = teamNo;
	}
	
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public TeamVO toTeamVO() {
		TeamVO team = super.toTeamVO();
		team.setTeamNo(Integer.parseInt(this.teamNo));
		
		return team;
	}
	
}
