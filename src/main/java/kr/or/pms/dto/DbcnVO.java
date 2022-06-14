package kr.or.pms.dto;

import java.util.Date;

public class DbcnVO {
	
	private int dbcnNo;
	private String dbcnNm;
	private String dbcnUrl;
	private String dbcnUser;
	private String dbcnPwd;
	private String dbType;
	private String path;
	private String fileNm;
	private int maxIdle = 2;
	private int maxTotal = 2;
	private String sttCode;
	private Date regDt;
	private Date updDt;
	private int delKey;
	private Date delDt;
	
	private int dbNo;
	private String dbNm;
	private String rsrvDbNm;
	private String dbDriver;
	
	
	public String getRsrvDbNm() {
		return rsrvDbNm;
	}
	public void setRsrvDbNm(String rsrvDbNm) {
		this.rsrvDbNm = rsrvDbNm;
	}
	public int getDbcnNo() {
		return dbcnNo;
	}
	public void setDbcnNo(int dbcnNo) {
		this.dbcnNo = dbcnNo;
	}
	public String getDbcnNm() {
		return dbcnNm;
	}
	public void setDbcnNm(String dbcnNm) {
		this.dbcnNm = dbcnNm;
	}
	public String getDbcnUrl() {
		return dbcnUrl;
	}
	public void setDbcnUrl(String dbcnUrl) {
		this.dbcnUrl = dbcnUrl;
	}
	public String getDbcnUser() {
		return dbcnUser;
	}
	public void setDbcnUser(String dbcnUser) {
		this.dbcnUser = dbcnUser;
	}
	public String getDbcnPwd() {
		return dbcnPwd;
	}
	public void setDbcnPwd(String dbcnPwd) {
		this.dbcnPwd = dbcnPwd;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public int getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
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
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public int getDelKey() {
		return delKey;
	}
	public void setDelKey(int delKey) {
		this.delKey = delKey;
	}
	public Date getDelDt() {
		return delDt;
	}
	public void setDelDt(Date delDt) {
		this.delDt = delDt;
	}
	public int getDbNo() {
		return dbNo;
	}
	public void setDbNo(int dbNo) {
		this.dbNo = dbNo;
	}
	public String getDbNm() {
		return dbNm;
	}
	public void setDbNm(String dbNm) {
		this.dbNm = dbNm;
	}
	public String getDbDriver() {
		return dbDriver;
	}
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}
	
}
