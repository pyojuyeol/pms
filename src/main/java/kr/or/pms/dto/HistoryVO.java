package kr.or.pms.dto;

import java.util.Date;

public class HistoryVO {
	
	private int historyNo;
	private String userId;
	private String url;
	private Date regDt;
	private int prjNo;
	private String prjNm;
	private String fromWhere;
	private String title;
	private String content;
	private String regDtStr;
	private String regDtTime;
	
	public int getHistoryNo() {
		return historyNo;
	}
	public void setHistoryNo(int historyNo) {
		this.historyNo = historyNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public int getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(int prjNo) {
		this.prjNo = prjNo;
	}
	public String getFromWhere() {
		return fromWhere;
	}
	public void setFromWhere(String fromWhere) {
		this.fromWhere = fromWhere;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrjNm() {
		return prjNm;
	}
	public void setPrjNm(String prjNm) {
		this.prjNm = prjNm;
	}
	public String getRegDtStr() {
		return regDtStr;
	}
	public void setRegDtStr(String regDtStr) {
		this.regDtStr = regDtStr;
	}
	public String getRegDtTime() {
		return regDtTime;
	}
	public void setRegDtTime(String regDtTime) {
		this.regDtTime = regDtTime;
	}
	
	
	
	
	
}
