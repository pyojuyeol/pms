package kr.or.pms.command;

import kr.or.pms.dto.NoticeVO;

public class NoticeModifyCommand extends NoticeRegistCommand{
	
	private String ntcNo;
	private String[] deleteFile;
	
	public String getNtcNo() {
		return ntcNo;
	}
	public void setNtcNo(String ntcNo) {
		this.ntcNo = ntcNo;
	}
	public String[] getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(String[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	@Override
	public NoticeVO toNoticeVO() {
		NoticeVO notice = super.toNoticeVO();
		notice.setNtcNo(Integer.parseInt(this.ntcNo));
		
		return notice;
	}
}
