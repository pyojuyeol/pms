package kr.or.pms.dto;

import java.util.Date;

public class TmemVO {
	
	private int tmemNo;	  	 // 팀원 번호
	private int teamNo;	  	 // 팀 번호
	private String userId;	 // 팀원 ID
	private String userNm;   // 팀원 이름
	private String leader;   // 리더 여부
	private String sttCode;  // 상태코드
	private Date updDt;	 	 // 수정 일자
	
	private String positionCode;  // 부서 코드
	
	public int getTmemNo() {
		return tmemNo;
	}
	public void setTmemNo(int tmemNo) {
		this.tmemNo = tmemNo;
	}
	public int getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public String getSttCode() {
		return sttCode;
	}
	public void setSttCode(String sttCode) {
		this.sttCode = sttCode;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	
	public TmemLogVO toTmemLogVO() {
		TmemLogVO tmemLog = new TmemLogVO();
		tmemLog.setTeamNo(this.teamNo);
		tmemLog.setUserId(this.userId);
		
		return tmemLog;
	}
	
}
