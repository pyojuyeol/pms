package kr.or.pms.dto;

import java.util.Date;
import java.util.List;

public class TeamVO {
	
	private int teamNo;   		// 팀 번호  
	private String teamNm;   	// 팀명
	private String sttCode;     // 상태 코드 
	private Date regDt;   		// 생성 일시
	private Date delDt;   		// 삭제 일시
	
	private List<ProjectVO> prjList; // 프로젝트 리스트
	
	private int proceedingCnt;	// 진행중인 프로젝트 개수
	private int completeCnt;	// 완료된 프로젝트 개수
	private int holdCnt;		// 보류중인 프로젝트 개수
	
	private List<TmemVO> teamMemberList;	// 팀멤버 목록
	private List<TmemLogVO> logList;		// 팀멤버 로그

	private List<String> addMembers; 	// 추가 멤버
	private List<String> removeMembers;	// 삭제 멤버
	
	public int getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}
	public String getTeamNm() {
		return teamNm;
	}
	public void setTeamNm(String teamNm) {
		this.teamNm = teamNm;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getDelDt() {
		return delDt;
	}
	public void setDelDt(Date delDt) {
		this.delDt = delDt;
	}
	public String getSttCode() {
		return sttCode;
	}
	public void setSttCode(String sttCode) {
		this.sttCode = sttCode;
	}
	public List<TmemVO> getTeamMemberList() {
		return teamMemberList;
	}
	public void setTeamMemberList(List<TmemVO> teamMemberList) {
		this.teamMemberList = teamMemberList;
	}
	public List<TmemLogVO> getLogList() {
		return logList;
	}
	public void setLogList(List<TmemLogVO> logList) {
		this.logList = logList;
	}
	public List<String> getAddMembers() {
		return addMembers;
	}
	public void setAddMembers(List<String> addMembers) {
		this.addMembers = addMembers;
	}
	public List<String> getRemoveMembers() {
		return removeMembers;
	}
	public void setRemoveMembers(List<String> removeMembers) {
		this.removeMembers = removeMembers;
	}
	public int getProceedingCnt() {
		return proceedingCnt;
	}
	public void setProceedingCnt(int proceedingCnt) {
		this.proceedingCnt = proceedingCnt;
	}
	public int getCompleteCnt() {
		return completeCnt;
	}
	public void setCompleteCnt(int completeCnt) {
		this.completeCnt = completeCnt;
	}
	public int getHoldCnt() {
		return holdCnt;
	}
	public void setHoldCnt(int holdCnt) {
		this.holdCnt = holdCnt;
	}
	public List<ProjectVO> getPrjList() {
		return prjList;
	}
	public void setPrjList(List<ProjectVO> prjList) {
		this.prjList = prjList;
	}
	
}
