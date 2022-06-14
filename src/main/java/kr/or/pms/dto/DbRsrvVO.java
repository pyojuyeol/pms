package kr.or.pms.dto;

import java.util.Date;

public class DbRsrvVO {
	
	private int dbNo;
	private int dbcnNo;
	private int dbRsrvNo;
	
	private String dbNm;
	private String dbcnUser;
	private String dbcnUrl;
	private String dbType;
	
	private Date regDt;
	private Date updDt;
	private String modifier;
	private String writer;
	private int stt;
	private String failureReason;
	
	public int getDbNo() {
		return dbNo;
	}
	public void setDbNo(int dbNo) {
		this.dbNo = dbNo;
	}
	public int getDbcnNo() {
		return dbcnNo;
	}
	public void setDbcnNo(int dbcnNo) {
		this.dbcnNo = dbcnNo;
	}
	public int getDbRsrvNo() {
		return dbRsrvNo;
	}
	public void setDbRsrvNo(int dbRsrvNo) {
		this.dbRsrvNo = dbRsrvNo;
	}
	public String getDbNm() {
		return dbNm;
	}
	public void setDbNm(String dbNm) {
		this.dbNm = dbNm;
	}
	public String getDbcnUser() {
		return dbcnUser;
	}
	public void setDbcnUser(String dbcnUser) {
		this.dbcnUser = dbcnUser;
	}
	public String getDbcnUrl() {
		return dbcnUrl;
	}
	public void setDbcnUrl(String dbcnUrl) {
		this.dbcnUrl = dbcnUrl;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public String getFailureReason() {
		return failureReason;
	}
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	
	
}
