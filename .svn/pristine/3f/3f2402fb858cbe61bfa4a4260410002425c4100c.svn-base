package kr.or.pms.command;
import com.josephoconnell.html.HTMLInputFilter;

import kr.or.pms.dto.ReplyVO;

public class ReplyRegistCommand {
	
	private int workNo;
	private String register;
	private String content;
	
	public int getWorkNo() {
		return workNo;
	}
	public void setWorkNo(int workNo) {
		this.workNo = workNo;
	}
	public String getContent() {
		return content;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public ReplyVO toReplyVO() {
		ReplyVO reply = new ReplyVO();
		
		reply.setReplyNo(this.workNo);
		reply.setRegister(this.register);
		reply.setContent(HTMLInputFilter.htmlSpecialChars(this.content));
		
		return reply;
	}
}
