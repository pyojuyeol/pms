package kr.or.pms.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.or.pms.dto.WorkVO;

public class WorkRegistCommand {

	private String prjNo;			// 프로젝트 번호
	private String upWork;			// 상위 일감
	private String workTitle;		// 제목
	private String workComment;		// 설명
	private String worker;			// 담당자
	private String sttCode;			// 상태 코드
	private String ipcrCode;		// 중요도 코드
	private String workRate;		// 진행도
	private String rangeDt;     	// 일감 기한
	private String workUrl;			// URL
	
	private String regDt;			// 등록 일시
	private String updDt;			// 수정 일시
	
	private String open;			// 새창=1
	private String from;			// 상세 보기에서 하위일감 추가를 눌렀는지.
	
	public String getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(String prjNo) {
		this.prjNo = prjNo;
	}
	public String getUpWork() {
		return upWork;
	}
	public void setUpWork(String upWork) {
		this.upWork = upWork;
	}
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	public String getWorkComment() {
		return workComment;
	}
	public void setWorkComment(String workComment) {
		this.workComment = workComment;
	}
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
	public String getSttCode() {
		return sttCode;
	}
	public void setSttCode(String sttCode) {
		this.sttCode = sttCode;
	}
	public String getIpcrCode() {
		return ipcrCode;
	}
	public void setIpcrCode(String ipcrCode) {
		this.ipcrCode = ipcrCode;
	}
	public String getWorkRate() {
		return workRate;
	}
	public void setWorkRate(String workRate) {
		this.workRate = workRate;
	}
	public String getRangeDt() {
		return rangeDt;
	}
	public void setRangeDt(String rangeDt) {
		this.rangeDt = rangeDt;
	}
	public String getWorkUrl() {
		return workUrl;
	}
	public void setWorkUrl(String workUrl) {
		this.workUrl = workUrl;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public WorkVO toWorkVO() throws Exception {
		WorkVO work = new WorkVO();
		
		if (this.upWork != null && this.upWork != "") {
			work.setUpWork(Integer.parseInt(this.upWork));
		}
		
		work.setWorkTitle(this.workTitle);
		work.setWorkComment(this.workComment);
		work.setWorker(this.worker);
		work.setSttCode(this.sttCode);
		work.setIpcrCode(this.ipcrCode);
		work.setWorkUrl(this.workUrl);
		
		if (this.workRate != null) {
			work.setWorkRate(Integer.parseInt(this.workRate));
		}
		
		// 일자 세팅
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] ranges = this.rangeDt.split(" ~ ");
		String bgnDtStr = ranges[0] + " 00:00:00";
		String endDtStr = ranges[1] + " 23:59:59";;
		
		Date bgnDate = formatter.parse(bgnDtStr);
		Date endDate = formatter.parse(endDtStr);
		
		work.setBgnDt(bgnDate);
		work.setEndDt(endDate);
		
		
		return work;
	}
	
	
}
