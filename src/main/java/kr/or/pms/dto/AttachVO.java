package kr.or.pms.dto;

import java.util.Date;

public class AttachVO {

	private int attachNo;
	private int attachSeq;
	private String attachPath;
	private String nm;
	private String stroredNm;
	private String ext;
	private String url;
	private String fileSize;
	private int downCnt;
	private Date regDt;
	private String register;
	private Date updDt;
	private String updater;
	 
	public int getAttachNo() {
		return attachNo;
	}
	public void setAttachNo(int attachNo) {
		this.attachNo = attachNo;
	}
	public int getAttachSeq() {
		return attachSeq;
	}
	public void setAttachSeq(int attachSeq) {
		this.attachSeq = attachSeq;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getStroredNm() {
		return stroredNm;
	}
	public void setStroredNm(String stroredNm) {
		this.stroredNm = stroredNm;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public int getDownCnt() {
		return downCnt;
	}
	public void setDownCnt(int downCnt) {
		this.downCnt = downCnt;
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
