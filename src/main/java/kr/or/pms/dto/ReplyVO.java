package kr.or.pms.dto;

import java.util.Date;

public class ReplyVO {

	private int replyNo;
	private int replySeq;
	private int upReplyNo;
	private int upReplySeq;
	private String content;
	private String url;
	private String register;
	private Date regDt;
	private Date delDt;
	private Date updDt;
	private String sttCode;
	private String userNm;
	
	
	
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getReplySeq() {
		return replySeq;
	}
	public void setReplySeq(int replySeq) {
		this.replySeq = replySeq;
	}
	public int getUpReplyNo() {
		return upReplyNo;
	}
	public void setUpReplyNo(int upReplyNo) {
		this.upReplyNo = upReplyNo;
	}
	public int getUpReplySeq() {
		return upReplySeq;
	}
	public void setUpReplySeq(int upReplySeq) {
		this.upReplySeq = upReplySeq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getDelDt() {
		return delDt;
	}
	public void setDelDt(Date delDt) {
		this.delDt = delDt;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public String getSttCode() {
		return sttCode;
	}
	public void setSttCode(String sttCode) {
		this.sttCode = sttCode;
	}
	
	

}
