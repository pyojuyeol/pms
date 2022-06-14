package kr.or.pms.command;

public class AdminLogCriteria extends Criteria{
	private int page = 1;
	private int perPageNum = 10;
	
	private String keyword = "";
	private int startRowNum = 0;
	
	private String searchType = "";
	private String startDt;
	private String endDt;
	private String logType = "connectLog";
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if (page < 1) {
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
		if (perPageNum < 1 ) {
			this.perPageNum = 10;
		} else {
			this.perPageNum = perPageNum;
		}
		setStartRowNum();
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	private void setStartRowNum() {
		this.startRowNum = (this.page - 1) * perPageNum;
	}
	
	public int getStartRow() {
		return (this.page - 1) * perPageNum;
	}
	
	public int getEndRow() {
		return getStartRow() + perPageNum;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
}
