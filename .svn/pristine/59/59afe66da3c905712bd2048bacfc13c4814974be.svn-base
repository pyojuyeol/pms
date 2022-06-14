package kr.or.pms.command;

import java.util.List;

import kr.or.pms.dto.TeamVO;

public class TeamRegistCommand {

	private String teamNm;		// 팀명
	
	private List<String> addMembers;  // 팀멤버
	
	private String open;
	
	public String getTeamNm() {
		return teamNm;
	}
	public void setTeamNm(String teamNm) {
		this.teamNm = teamNm;
	}
	public List<String> getAddMembers() {
		return addMembers;
	}
	public void setAddMembers(List<String> addMembers) {
		this.addMembers = addMembers;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	
	public TeamVO toTeamVO() {
		TeamVO team = new TeamVO();
		team.setTeamNm(this.teamNm);
		team.setAddMembers(this.addMembers);
		
		return team;
	}
	
	
}