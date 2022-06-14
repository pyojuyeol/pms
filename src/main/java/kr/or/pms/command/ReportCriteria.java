package kr.or.pms.command;

public class ReportCriteria extends Criteria {

	private String submitDt;         // 등록날짜
	private String repRegister;
	private String approver;
	
	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getRepRegister() {
		return repRegister;
	}

	public void setRepRegister(String repRegister) {
		this.repRegister = repRegister;
	}

	public String getSubmitDt() {
		return submitDt;
	}

	public void setSubmitDt(String submitDt) {
		this.submitDt = submitDt;
	}
	
	
}
