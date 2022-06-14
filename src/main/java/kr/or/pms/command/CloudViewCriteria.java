package kr.or.pms.command;

public class CloudViewCriteria {
	private String mainCategory;
	private String subCategory;
	private String cldKeyCode;
	private int prjNo;
	private String userId;
	private int dirNo;
	private int upDir = 0;
	private String keyword;
	private String dirNm;
	
	public String getMainCategory() {
		return mainCategory;
	}
	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getCldKeyCode() {
		return cldKeyCode;
	}
	public void setCldKeyCode(String cldKeyCode) {
		this.cldKeyCode = cldKeyCode;
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
	public int getDirNo() {
		return dirNo;
	}
	public void setDirNo(int dirNo) {
		this.dirNo = dirNo;
	}
	public int getUpDir() {
		return upDir;
	}
	public void setUpDir(int upDir) {
		this.upDir = upDir;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDirNm() {
		return dirNm;
	}
	public void setDirNm(String dirNm) {
		this.dirNm = dirNm;
	}
}
