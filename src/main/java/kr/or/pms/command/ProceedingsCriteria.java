package kr.or.pms.command;


public class ProceedingsCriteria {
	
	private int page=1;
	private int perPageNum=10;
	private String searchType="";
	private String keyword="";
	private String category="";
	private String sttCode="";
	private String meetDe="0";
	
	
	private int startRowNum=0;
	private int prjNo=0;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		
		if(page < 1) {
			this.page = 1;
		} else {
			this.page = page;
		}
		setStartRowNum();
		
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		
		if(perPageNum< 1 ) {
			this.perPageNum = 10;
		} else {
			this.perPageNum = perPageNum;
		}
		setStartRowNum();
		
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getStartRowNum() {
		return startRowNum;
	}
	private void setStartRowNum() {
		this.startRowNum = (this.page-1)*perPageNum;
	}
	public int getStartRow() {
		return (this.page-1)*perPageNum;
	}
	
	public int getEndRow() {
		return getStartRow() + perPageNum;
	}
	public int getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(int prjNo) {
		this.prjNo = prjNo;
	}
	public String getSttCode() {
		return sttCode;
	}
	public void setSttCode(String sttCode) {
		this.sttCode = sttCode;
	}
	public String getMeetDe() {
		return meetDe;
	}
	public void setMeetDe(String meetDe) {
		this.meetDe = meetDe;
	}
	public String getBgnDt() {
		String[] ranges;
		String bgn = null;
		if(!meetDe.equals("0")) {
			
			ranges = this.meetDe.split(" ~ ");
			bgn = ranges[0];
		}
		return bgn;
	}
	public String getEndDt() {
		String[] ranges;
		String end = null;
		if(!meetDe.equals("0")) {
			
			ranges = this.meetDe.split(" ~ ");
			end = ranges[1];
		}
		return end;
	}
		
		
		
	
	
	
	
}
