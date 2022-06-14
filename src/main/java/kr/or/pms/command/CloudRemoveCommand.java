package kr.or.pms.command;

public class CloudRemoveCommand {
	private String type;
	private int dirNo;
	private int cldFileNo;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDirNo() {
		return dirNo;
	}
	public void setDirNo(int dirNo) {
		this.dirNo = dirNo;
	}
	public int getCldFileNo() {
		return cldFileNo;
	}
	public void setCldFileNo(int cldFileNo) {
		this.cldFileNo = cldFileNo;
	}
}
