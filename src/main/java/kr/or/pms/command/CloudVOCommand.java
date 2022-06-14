package kr.or.pms.command;

import org.springframework.web.multipart.MultipartFile;

import kr.or.pms.dto.CloudDirectoryVO;

public class CloudVOCommand {
	private String cldKeyCode;
	private int upDir;
	private String dirPath;
	private MultipartFile uploadFile;
	private String userId;
	private int prjNo;
	private String filePath;
	private int dirNo;
	private String dirNm;
	
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
	public String getDirPath() {
		return dirPath;
	}
	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(int prjNo) {
		this.prjNo = prjNo;
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
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
	public CloudDirectoryVO toCloudDirectory() {
		CloudDirectoryVO cdv = new CloudDirectoryVO();
		cdv.setCldKeyCode(cldKeyCode);
		cdv.setUpDir(upDir);
		cdv.setUserId(userId);
		cdv.setDirPath(dirPath);
		cdv.setPrjNo(prjNo);
		cdv.setDirNm(dirNm);
		return cdv;
	}
}
