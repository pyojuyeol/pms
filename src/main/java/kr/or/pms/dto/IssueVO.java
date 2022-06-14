package kr.or.pms.dto;

import java.util.Date;

public class IssueVO {

    private int issueNo;               //NUMBER
    private String gradeCode;          //VARCHAR2(20 BYTE)
	private int prjNo;                 //NUMBER
	private String issueRegister;      //VARCHAR2(100 BYTE)
	private String content;            //VARCHAR2(2000 BYTE)
	private String sttCode;            //VARCHAR2(5 BYTE)
	private int attachNo;              //NUMBER
	private int replyNo;               //NUMBER
	private String title;              //VARCHAR2(500 BYTE)
	private int bookmarkNo;            // NUMBER
	private Date regDt;                // DATE
	private String prjNm;

	// BookMark 테이블 데이터
	private String bookmarkTitle; // 북마크 타이틀

	// prj_user 테이블 데이터
	private String userId;
	
	// user 테이블 데이터
	private String userNm;
	private String [] refMem;
	
	// work 테이블 데이터
	private int workNo;
	private String workTitle;
	
	public int getWorkNo() {
		return workNo;
	}

	public void setWorkNo(int workNo) {
		this.workNo = workNo;
	}

	public String getWorkTitle() {
		return workTitle;
	}

	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}

	public String getPrjNm() {
		return prjNm;
	}
	
	public void setPrjNm(String prjNm) {
		this.prjNm = prjNm;
	}

	public String[] getRefMem() {
		return refMem;
	}


	public void setRefMem(String[] refMem) {
		this.refMem = refMem;
	}


	public String getUserNm() {
		return userNm;
	}


	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getIssueNo() {
		return issueNo;
	}


	public void setIssueNo(int issueNo) {
		this.issueNo = issueNo;
	}


	public String getGradeCode() {
		return gradeCode;
	}


	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}


	public int getPrjNo() {
		return prjNo;
	}


	public void setPrjNo(int prjNo) {
		this.prjNo = prjNo;
	}


	public String getIssueRegister() {
		return issueRegister;
	}


	public void setIssueRegister(String issueRegister) {
		this.issueRegister = issueRegister;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getSttCode() {
		return sttCode;
	}


	public void setSttCode(String sttCode) {
		this.sttCode = sttCode;
	}


	public int getAttachNo() {
		return attachNo;
	}


	public void setAttachNo(int attachNo) {
		this.attachNo = attachNo;
	}


	public int getReplyNo() {
		return replyNo;
	}


	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getBookmarkNo() {
		return bookmarkNo;
	}


	public void setBookmarkNo(int bookmarkNo) {
		this.bookmarkNo = bookmarkNo;
	}


	public Date getRegDt() {
		return regDt;
	}


	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}


	public String getBookmarkTitle() {
		return bookmarkTitle;
	}


	public void setBookmarkTitle(String bookmarkTitle) {
		this.bookmarkTitle = bookmarkTitle;
	}
	
	
	
}
