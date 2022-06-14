package kr.or.pms.command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.WorkVO;

public class GanttCommand {
    private int pID= 0;
    private String pName= "프로젝트";
    private String pStart= "";
    private String pEnd= "";
    private String pPlanStart= "";
    private String pPlanEnd= "";
    private String pClass= "gtaskgreen";
    private String pLink= "";
    private int pMile= 0;
    private String pRes= "";
    private int pComp= 0;
    private int pGroup= 0;
    private int pParent= 0;
    private int pOpen= 1;
    private String pDepend= "";
    private String pCaption= "";
    private String pNotes= "";
    private int pCost = 0;
    private String category= "";
    private String sector= "";
    private String pDuration = "";
    private String deadline = "";
    
    
    public GanttCommand() {};
    public GanttCommand(WorkVO workVO, List<ComCodeVO> ipcrList, List<ComCodeVO> workSttList) {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	
    	pID = workVO.getWorkNo();
    	pName = "#" + workVO.getWorkNo() + " " + workVO.getWorkTitle();
    	pStart = dateFormat.format( workVO.getBgnDt() );
    	pEnd= dateFormat.format( workVO.getEndDt() );
    	pPlanStart= dateFormat.format( workVO.getBgnDt() );
    	pPlanEnd= dateFormat.format( workVO.getEndDt() );
    	pComp= workVO.getWorkRate();
//    	pClass= "gtaskgreen";
//    	pGroup= 0;
//        pLink= "";
//        pMile= 0;
        pRes= workVO.getUserNm() + "(" + workVO.getWorker().substring(0,2) + "***)";
        pParent= workVO.getUpWork();
//        pOpen= 1;
//        pDepend= "";
//        pCaption= "";
        for (ComCodeVO ipcr : ipcrList) {
        	if( ipcr.getCode().equals(workVO.getIpcrCode()) ) {
        		pNotes = "중요도-" + ipcr.getNm() +"<br>";
        	}
		}
        for (ComCodeVO workStt : workSttList) {
        	if( workStt.getCode().equals(workVO.getSttCode()) ) {
        		pNotes += "상태-" + workStt.getNm();
        	}
        }
//        pCost = 0;
//        category= "";
//        sector= "";
//        pDuration = "";
//        deadline = "";
    }
    
	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpStart() {
		return pStart;
	}
	public void setpStart(String pStart) {
		this.pStart = pStart;
	}
	public String getpEnd() {
		return pEnd;
	}
	public void setpEnd(String pEnd) {
		this.pEnd = pEnd;
	}
	public String getpPlanStart() {
		return pPlanStart;
	}
	public void setpPlanStart(String pPlanStart) {
		this.pPlanStart = pPlanStart;
	}
	public String getpPlanEnd() {
		return pPlanEnd;
	}
	public void setpPlanEnd(String pPlanEnd) {
		this.pPlanEnd = pPlanEnd;
	}
	public String getpClass() {
		return pClass;
	}
	public void setpClass(String pClass) {
		this.pClass = pClass;
	}
	public String getpLink() {
		return pLink;
	}
	public void setpLink(String pLink) {
		this.pLink = pLink;
	}
	public int getpMile() {
		return pMile;
	}
	public void setpMile(int pMile) {
		this.pMile = pMile;
	}
	public String getpRes() {
		return pRes;
	}
	public void setpRes(String pRes) {
		this.pRes = pRes;
	}
	public int getpComp() {
		return pComp;
	}
	public void setpComp(int pComp) {
		this.pComp = pComp;
	}
	public int getpGroup() {
		return pGroup;
	}
	public void setpGroup(int pGroup) {
		this.pGroup = pGroup;
	}
	public int getpParent() {
		return pParent;
	}
	public void setpParent(int pParent) {
		this.pParent = pParent;
	}
	public int getpOpen() {
		return pOpen;
	}
	public void setpOpen(int pOpen) {
		this.pOpen = pOpen;
	}
	public String getpDepend() {
		return pDepend;
	}
	public void setpDepend(String pDepend) {
		this.pDepend = pDepend;
	}
	public String getpCaption() {
		return pCaption;
	}
	public void setpCaption(String pCaption) {
		this.pCaption = pCaption;
	}
	public String getpNotes() {
		return pNotes;
	}
	public void setpNotes(String pNotes) {
		this.pNotes = pNotes;
	}
	public int getpCost() {
		return pCost;
	}
	public void setpCost(int pCost) {
		this.pCost = pCost;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getpDuration() {
		return pDuration;
	}
	public void setpDuration(String pDuration) {
		this.pDuration = pDuration;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
    
    
}
