package kr.or.pms.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.or.pms.dto.ScheduleVO;

public class ScheduleRegistCommand {
	
	private int schedNo;
	private String title;
	private String content;
	private String register;
	private String regDt;
	private String bgnDt;
	private String endDt;
	private String rangeDt;
	private String ipcrCode;
	private String alldayNyCode;
	private String url;
	private String keyCode;
	private int prjNo;
	public int getSchedNo() {
		return schedNo;
	}
	public void setSchedNo(int schedNo) {
		this.schedNo = schedNo;
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
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getBgnDt() {
		return bgnDt;
	}
	public void setBgnDt(String bgnDt) {
		this.bgnDt = bgnDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getRangeDt() {
		return rangeDt;
	}
	public void setRangeDt(String rangeDt) {
		this.rangeDt = rangeDt;
	}
	public String getIpcrCode() {
		return ipcrCode;
	}
	public void setIpcrCode(String ipcrCode) {
		this.ipcrCode = ipcrCode;
	}
	public String getAlldayNyCode() {
		return alldayNyCode;
	}
	public void setAlldayNyCode(String alldayNyCode) {
		this.alldayNyCode = alldayNyCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	public int getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(int prjNo) {
		this.prjNo = prjNo;
	}
	
	public ScheduleVO toScheduleVO() throws Exception {
		
		ScheduleVO vo = new ScheduleVO();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String[] ranges = this.rangeDt.split(" ~ ");
		String bgnDtStr = ranges[0];
		String endDtStr = ranges[1];
		
		Date bgnDate = formatter.parse(bgnDtStr);
		Date endDate = formatter.parse(endDtStr);
		
		vo.setBgnDt(bgnDate);
		vo.setEndDt(endDate);
		vo.setTitle(this.title);
		vo.setRegister(this.register);
		vo.setContent(this.content);
		vo.setKeyCode(this.keyCode);
		vo.setIpcrCode(this.ipcrCode);
		
		if(this.ipcrCode.equals("2")) {
			vo.setPrjNo(this.prjNo);
		}
		
		return vo;
		
	}
	
	
	
	
}
