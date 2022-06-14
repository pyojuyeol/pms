package kr.or.pms.dto;

import java.util.Date;

public class ReportVO {
	
	private int rownum;            
	private int repNo;             // 레포트번호
	private int prjNo;             // 프로젝트번호
	private String repRegister;    // 등록자
	private String approver;       // 업무보고 받은 사람(pl,pm) 
	private String title;          // 제목
	private String repContent;     // 내용1(메인내용)
	private String subContent;	   // 내용2(계획 등...)	
	private String etcContent;	   // 내용3(기타 참고 내용 등...)
	private String sttCode;        // 임시저장 여부(send - 제출 / temp - 임시저장)
	private Date regDt;            // 작성날짜
	private Date submitDt;         // 등록날짜
	private Date apprDt;           // 업무보고 확인날짜
	private String returnDt;       // 업무보고 확인 여보(읽음 - read / 읽지 않음 - unread)
	private String ctgCode;        // 업무보고 양식 기준(일일보고 - day / 월간보고 - month)
	
	private int replyNo;           //NUMBER
	private int attachNo;
	private String prjNm;
	private String userNm;
	private String userId;

	// 팀명 조회
	private String teamNm;
	
	// 직책
	private String prjRole;
	
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getAttachNo() {
		return attachNo;
	}
	public void setAttachNo(int attachNo) {
		this.attachNo = attachNo;
	}
	public String getSubContent() {
		return subContent;
	}
	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}
	public String getEtcContent() {
		return etcContent;
	}
	public void setEtcContent(String etcContent) {
		this.etcContent = etcContent;
	}
	public String getPrjRole() {
		return prjRole;
	}
	public void setPrjRole(String prjRole) {
		this.prjRole = prjRole;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTeamNm() {
		return teamNm;
	}
	public void setTeamNm(String teamNm) {
		this.teamNm = teamNm;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getPrjNm() {
		return prjNm;
	}
	public void setPrjNm(String prjNm) {
		this.prjNm = prjNm;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public int getRepNo() {
		return repNo;
	}
	public void setRepNo(int repNo) {
		this.repNo = repNo;
	}
	public int getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(int prjNo) {
		this.prjNo = prjNo;
	}
	public String getRepRegister() {
		return repRegister;
	}
	public void setRepRegister(String repRegister) {
		this.repRegister = repRegister;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRepContent() {
		return repContent;
	}
	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}
	public String getSttCode() {
		return sttCode;
	}
	public void setSttCode(String sttCode) {
		this.sttCode = sttCode;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getSubmitDt() {
		return submitDt;
	}
	public void setSubmitDt(Date submitDt) {
		this.submitDt = submitDt;
	}
	public Date getApprDt() {
		return apprDt;
	}
	public void setApprDt(Date apprDt) {
		this.apprDt = apprDt;
	}
	public String getReturnDt() {
		return returnDt;
	}
	public void setReturnDt(String returnDt) {
		this.returnDt = returnDt;
	}
	public String getCtgCode() {
		return ctgCode;
	}
	public void setCtgCode(String ctgCode) {
		this.ctgCode = ctgCode;
	}
	
}
