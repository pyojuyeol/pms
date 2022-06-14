package kr.or.pms.command;

import kr.or.pms.dto.CloudDirectoryVO;
import kr.or.pms.dto.CloudVO;

public class CloudModifyCommand {
	private int cldFileNo;
	private String oldFileName;
	private String newFileName;
	private String path;
	private String type;
	private String extension;
	private String userId;
	private int dirNo;
	
	public int getCldFileNo() {
		return cldFileNo;
	}
	public void setCldFileNo(int cldFileNo) {
		this.cldFileNo = cldFileNo;
	}
	public String getOldFileName() {
		return oldFileName;
	}
	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	public String getNewFileName() {
		return newFileName;
	}
	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getDirNo() {
		return dirNo;
	}
	public void setDirNo(int dirNo) {
		this.dirNo = dirNo;
	}
	
	public CloudVO toCloudVO() {
		CloudVO cloud = new CloudVO();
		
		cloud.setCldFileNo(cldFileNo);
		cloud.setStoredNm(path.substring(path.lastIndexOf("/") + 1));
		cloud.setFileNm(newFileName);
		cloud.setPath(path);
		cloud.setExt(extension);
		cloud.setUpdater(userId);
		
		return cloud;
	}
	
	public CloudDirectoryVO toCloudDirectoryVO() {
		CloudDirectoryVO cloudDirectory = new CloudDirectoryVO();
		cloudDirectory.setDirNo(dirNo);
		cloudDirectory.setDirNm(newFileName);
		cloudDirectory.setUpdater(userId);
		cloudDirectory.setDirPath(path);
		
		return cloudDirectory;
	}
}
