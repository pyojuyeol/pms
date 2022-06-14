package kr.or.pms.command;

public class IssueCriteria {
	
	private int page=1;
	private int perPageNum=10;
	private String searchType="";
	private String keyword="";
	private String sttCode="";
	private String gradeCode="";
	private int startRowNum=0;
	private int prjNo=0;
	
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getSttCode() {
		return sttCode;
	}
	public void setSttCode(String sttCode) {
		this.sttCode = sttCode;
	}
	
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
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
	
	
	
}
