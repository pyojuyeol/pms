package kr.or.pms.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ProjectVO;

public class ProjectModifyCommand {

	private String prjNo;	    // 프로젝트 번호
	private String prjNm;    	// 프로젝트 명
	private String prjComment;	// 프로젝트 설명
	private String enabled;		// 공개여부
	private String sttCode;		// 상태코드
	private String teamNo;      // 팀 번호
	private String rangeDt;     // 프로젝트 기한
	
	private Map<String, String> addMemberMap;   // 프로젝트 유저
	
	
	public String getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(String prjNo) {
		this.prjNo = prjNo;
	}
	public String getPrjNm() {
		return prjNm;
	}
	public void setPrjNm(String prjNm) {
		this.prjNm = prjNm;
	}
	public String getPrjComment() {
		return prjComment;
	}
	public void setPrjComment(String prjComment) {
		this.prjComment = prjComment;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getSttCode() {
		return sttCode;
	}
	public void setSttCode(String sttCode) {
		this.sttCode = sttCode;
	}
	public String getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(String teamNo) {
		this.teamNo = teamNo;
	}
	public String getRangeDt() {
		return rangeDt;
	}
	public void setRangeDt(String rangeDt) {
		this.rangeDt = rangeDt;
	}
	public Map<String, String> getAddMemberMap() {
		return addMemberMap;
	}
	public void setAddMemberMap(Map<String, String> addMemberMap) {
		this.addMemberMap = addMemberMap;
	}
	
	public ProjectVO toProjectVO() throws Exception {
		ProjectVO prj = new ProjectVO();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String[] ranges = this.rangeDt.split(" ~ ");
		String bgnDtStr = ranges[0];
		String endDtStr = ranges[1];
		Date bgnDate = formatter.parse(bgnDtStr);
		Date endDate = formatter.parse(endDtStr);
		
		prj.setPrjNo(Integer.parseInt(this.prjNo));
		prj.setPrjNm(this.prjNm);
		prj.setPrjComment(this.prjComment);
		prj.setTeamNo(Integer.parseInt(this.teamNo));
		prj.setEnabled(this.enabled);
		prj.setSttCode(this.sttCode);
		prj.setBgnDt(bgnDate);
		prj.setEndDt(endDate);
		
		addUser(prj);
        
		return prj;
	}
	
	public ProjectVO addUser(ProjectVO prj) throws Exception {
		
		List<PrjUserVO> prjUserList = new ArrayList<>();
		
        Iterator<String> prjUserIt = addMemberMap.keySet().iterator();
        while (prjUserIt.hasNext()) {
            String userId = prjUserIt.next();
            String prjRole = addMemberMap.get(userId);
            
            PrjUserVO prjUser = new PrjUserVO();
            prjUser.setUserId(userId);
            prjUser.setPrjRole(prjRole);;
            
            prjUserList.add(prjUser);
        }		
        prj.setAddPrjUsers(prjUserList);
        
        return prj;
	}
	
}