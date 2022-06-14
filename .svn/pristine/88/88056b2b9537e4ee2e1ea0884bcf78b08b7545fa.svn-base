package kr.or.pms.command;

import java.util.Date;

import kr.or.pms.dto.IssueVO;

public class IssueRegistCommand {
	
    private String issueNo;               //NUMBER
    private String gradeCode;          //VARCHAR2(20 BYTE)
	private String prjNo;                 //NUMBER
	private String issueRegister;      //VARCHAR2(100 BYTE)
	private String content;            //VARCHAR2(2000 BYTE)
	private String sttCode;            //VARCHAR2(5 BYTE)
	private String replyNo;               //NUMBER
	private String title;              //VARCHAR2(500 BYTE)
	private String bookmarkNo ="";            // NUMBER
	private Date regDt;                // DATE
	private String prjNm;
	private String workNo;
	private String open;
	public IssueVO toIssueVO() {
		IssueVO issue = new IssueVO();
//		issue.setIssueNo(Integer.parseInt(this.issueNo));
		issue.setGradeCode(this.gradeCode);
//		issue.setPrjNo(Integer.parseInt(this.prjNo));
		issue.setIssueRegister(this.issueRegister);
		issue.setContent(this.content);
		issue.setSttCode(this.sttCode);
//		issue.setReplyNo(Integer.parseInt(this.replyNo));
		issue.setTitle(this.title);
		issue.setWorkNo(Integer.parseInt(this.workNo));
		if(!this.bookmarkNo.equals("")) {			
			issue.setBookmarkNo(Integer.parseInt(this.bookmarkNo));
		} else {
			issue.setBookmarkNo(0);
		}
		
		issue.setRegDt(this.regDt);
		
		return issue;	
	}
	
	
	public String getWorkNo() {
		return workNo;
	}


	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}


	public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(String prjNo) {
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
	public String getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(String replyNo) {
		this.replyNo = replyNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBookmarkNo() {
		return bookmarkNo;
	}
	public void setBookmarkNo(String bookmarkNo) {
		this.bookmarkNo = bookmarkNo;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getPrjNm() {
		return prjNm;
	}
	public void setPrjNm(String prjNm) {
		this.prjNm = prjNm;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	
	

}
