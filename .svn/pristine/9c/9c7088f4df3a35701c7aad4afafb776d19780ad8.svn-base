package kr.or.pms.command;

import java.util.Date;

import kr.or.pms.dto.CloudDirectoryVO;

public class CloudDirectoryCommand {
	private String dirNo;
	private String dirNm;
	private String dirPath;
	private String cldKeyCode;
	private String upDir;
	private String prjNo;
	private String userId;
	private Date regDt;
	private String register;
	private Date updDt;
	private String updater;
	
	public String getDirNo() {
		return dirNo;
	}
	public void setDirNo(String dirNo) {
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
	public String getUpDir() {
		return upDir;
	}
	public void setUpDir(String upDir) {
		this.upDir = upDir;
	}
	public String getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(String prjNo) {
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
	
	public CloudDirectoryVO toCloudDirectoryVO() {
		CloudDirectoryVO cloudDirectoryVO = new CloudDirectoryVO();
		cloudDirectoryVO.setCldKeyCode(cldKeyCode);
		cloudDirectoryVO.setDirNm(dirNm);
		cloudDirectoryVO.setUpDir(Integer.parseInt(upDir));
		cloudDirectoryVO.setDirPath(dirPath);
		if (prjNo != null) {
			cloudDirectoryVO.setPrjNo(Integer.parseInt(prjNo));
		}
		
		return cloudDirectoryVO;
	}
}
