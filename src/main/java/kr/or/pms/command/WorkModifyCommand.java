package kr.or.pms.command;

import kr.or.pms.dto.WorkVO;

public class WorkModifyCommand extends WorkRegistCommand {

	private String workNo;	// 일감 번호
	
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	
	public WorkVO toWorkVO() throws Exception {
		WorkVO work = super.toWorkVO();
		work.setWorkNo(Integer.parseInt(this.workNo));

		return work;
	}
}
