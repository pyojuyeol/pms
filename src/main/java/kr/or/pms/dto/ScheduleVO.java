package kr.or.pms.dto;

import java.util.Date;

public class ScheduleVO {
	
	private int schedNo;
	private String title;
	private String content;
	private String register;
	private String userNm;
	private Date regDt;
	private Date bgnDt;
	private Date endDt;
	private String ipcrCode;
	private String alldayNyCode;
	private String url;
	private String keyCode;
	private int prjNo;
	private String prjNm;
	private String selectDt="0";
	public int getSchedNo() {
		return schedNo;
	}
	public void setSchedNo(int schedNo) {
		this.schedNo = schedNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getBgnDt() {
		return bgnDt;
	}
	public void setBgnDt(Date bgnDt) {
		this.bgnDt = bgnDt;
	}
	public Date getEndDt() {
		return endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}
	public String getIpcrCode() {
		return ipcrCode;
	}
	public void setIpcrCode(String ipcrCode) {
		this.ipcrCode = ipcrCode;
	}
	public String getAlldayNyCode() {
		return alldayNyCode;
	}
	public void setAlldayNyCode(String alldayNyCode) {
		this.alldayNyCode = alldayNyCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	public int getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(int prjNo) {
		this.prjNo = prjNo;
	}
	public String getPrjNm() {
		return prjNm;
	}
	public void setPrjNm(String prjNm) {
		this.prjNm = prjNm;
	}
	public String getSelectDt() {
		return selectDt;
	}
	public void setSelectDt(String selectDt) {
		this.selectDt = selectDt;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	
	
	
	
}
