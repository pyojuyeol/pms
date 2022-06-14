package kr.or.pms.dto;

import java.util.Date;
import java.util.List;

public class WorkVO {
	
	private int prjNo;		// 프로젝트 번호
	private String prjNm;		// 프로젝트 명
	private int workNo;		// 일감 번호
	private int upWork;		// 상위 일감
	private String workTitle;	// 제목
	private String workComment;	// 설명
	private String worker;		// 담당자
	private String sttCode;		// 상태 코드
	private int workRate;	// 진행도
	private String ipcrCode;	// 중요도 코드
	private Date bgnDt;		// 시작 일시
	private Date endDt;		// 종료 일시
	private String workUrl;		// URL
	
	private List<WorkVO> subWorkList;	// 하위 일감 리스트
	
	// 메타데이터
	private String workRegister;	// 등록자
	private String workUpdater;		// 수정자
	private Date cmplDt;			// 완료 일시
	private Date regDt;			// 등록 일시
	private Date updDt;			// 수정 일시
	
	private String userNm;		// 담당자 이름
	
	// 상세보기 해당 일감 로그 리스트 주입
	private List<WorkLogVO> workLogList;  // 일감 로그

	
	public int getWorkNo() {
		return workNo;
	}
	public void setWorkNo(int workNo) {
		this.workNo = workNo;
	}
	public int getUpWork() {
		return upWork;
	}
	public void setUpWork(int upWork) {
		this.upWork = upWork;
	}
	public int getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(int prjNo) {
		this.prjNo = prjNo;
	}
	public String getPrjNm() {
		return prjNm;
	}
	public void setPrjNm(String prjNm) {
		this.prjNm = prjNm;
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
	public int getWorkRate() {
		return workRate;
	}
	public void setWorkRate(int workRate) {
		this.workRate = workRate;
	}
	public String getIpcrCode() {
		return ipcrCode;
	}
	public void setIpcrCode(String ipcrCode) {
		this.ipcrCode = ipcrCode;
	}
	public Date getBgnDt() {
		return bgnDt;
	}
	public void setBgnDt(Date bgnDt) {
		this.bgnDt = bgnDt;
	}
	public Date getEndDt() {
		return endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}
	public String getWorkUrl() {
		return workUrl;
	}
	public void setWorkUrl(String workUrl) {
		this.workUrl = workUrl;
	}
	public String getWorkRegister() {
		return workRegister;
	}
	public void setWorkRegister(String workRegister) {
		this.workRegister = workRegister;
	}
	public String getWorkUpdater() {
		return workUpdater;
	}
	public void setWorkUpdater(String workUpdater) {
		this.workUpdater = workUpdater;
	}
	public Date getCmplDt() {
		return cmplDt;
	}
	public void setCmplDt(Date cmplDt) {
		this.cmplDt = cmplDt;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public List<WorkLogVO> getWorkLogList() {
		return workLogList;
	}
	public void setWorkLogList(List<WorkLogVO> workLogList) {
		this.workLogList = workLogList;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public List<WorkVO> getSubWorkList() {
		return subWorkList;
	}
	public void setSubWorkList(List<WorkVO> subWorkList) {
		this.subWorkList = subWorkList;
	}
	
}
