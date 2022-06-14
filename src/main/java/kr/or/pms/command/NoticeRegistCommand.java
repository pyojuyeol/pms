package kr.or.pms.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.pms.dto.NoticeVO;

public class NoticeRegistCommand {
	
	private String title;
	private String content;
	private String register;
	private int attachNo;
	private int replyNo;
	private List<MultipartFile> uploadFile;
	
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
	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(List<MultipartFile> uploadFile) {
		this.uploadFile = uploadFile;
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
	public NoticeVO toNoticeVO() {
		NoticeVO notice = new NoticeVO();
		notice.setContent(this.content);
		notice.setTitle(this.title);
		notice.setRegister(this.register);
		notice.setAttachNo(this.attachNo);
		notice.setReplyNo(this.replyNo);
		
		return notice;
	}
	
}
