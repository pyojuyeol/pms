package kr.or.pms.dto;

import java.util.Date;

public class CloudDirectoryVO {
	private int dirNo;
	private String dirNm;
	private String dirPath;
	private String cldKeyCode;
	private int upDir;
	private int prjNo;
	private String userId;
	private Date regDt;
	private String register;
	private Date updDt;
	private String updater;
	
	public int getDirNo() {
		return dirNo;
	}
	public void setDirNo(int dirNo) {
		this.dirNo = dirNo;
	}
	public String getDirNm() {
		return dirNm;
	}
	public void setDirNm(String dirNm) {
		this.dirNm = dirNm;
	}
	public String getDirPath() {
		return dirPath;
	}
	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}
	public String getCldKeyCode() {
		return cldKeyCode;
	}
	public void setCldKeyCode(String cldKeyCode) {
		this.cldKeyCode = cldKeyCode;
	}
	public int getUpDir() {
		return upDir;
	}
	public void setUpDir(int upDir) {
		this.upDir = upDir;
	}
	public int getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(int prjNo) {
		this.prjNo = prjNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
}
