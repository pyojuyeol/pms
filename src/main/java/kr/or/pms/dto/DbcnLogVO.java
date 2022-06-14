package kr.or.pms.dto;

import java.util.Date;

public class DbcnLogVO {
	private int cngLogNo;
	private int dbcnNo;
	private String path;
	private String fileNm;
	private String dbNm;
	private String dbcnUrl;
	private String dbcnUser;
	private String dbcnPwd;
	private Date cngDt;
	public int getCngLogNo() {
		return cngLogNo;
	}
	public void setCngLogNo(int cngLogNo) {
		this.cngLogNo = cngLogNo;
	}
	public int getDbcnNo() {
		return dbcnNo;
	}
	public void setDbcnNo(int dbcnNo) {
		this.dbcnNo = dbcnNo;
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
	public String getDbNm() {
		return dbNm;
	}
	public void setDbNm(String dbNm) {
		this.dbNm = dbNm;
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
	public Date getCngDt() {
		return cngDt;
	}
	public void setCngDt(Date cngDt) {
		this.cngDt = cngDt;
	}
	
	
	
}
