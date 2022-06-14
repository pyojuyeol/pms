package kr.or.pms.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.pms.dto.PdsVO;

public class PdsModifyCommand {
	
	private String pdsNo;
	private String title;
	private String content;
	private String register;
	private int attachNo;
	private int replyNo;
	private List<MultipartFile> uploadFile;
	private String[] deleteFile;
	public String getPdsNo() {
		return pdsNo;
	}
	public void setPdsNo(String pdsNo) {
		this.pdsNo = pdsNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public int getAttachNo() {
		return attachNo;
	}
	public void setAttachNo(int attachNo) {
		this.attachNo = attachNo;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(List<MultipartFile> uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String[] getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(String[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	public PdsVO toPdsVO() {
		PdsVO pds = new PdsVO();
		pds.setPdsNo(Integer.parseInt(this.pdsNo));
		pds.setContent(content);
		pds.setTitle(this.title);
		pds.setRegister(this.register);
		pds.setAttachNo(this.attachNo);
		pds.setReplyNo(this.replyNo);
		
		return pds;
	}
	
}
