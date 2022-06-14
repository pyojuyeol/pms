package kr.or.pms.command;

public class NoticeCriteria {
	
	private int page = 1;
	private int newsPage = 1;
	private int ntcPage = 1;
	private int etcPage = 1;
	private int perPageNum = 10;
	private int ntcPerPageNum = 10;
	private int etcPerPageNum = 10;
	private String searchType = "";
	private String keyword = "";
	private int prjNo = 0;
	private int startRowNum = 0;
	private int ntcStartRowNum = 0;
	private int etcStartRowNum = 0;
	private String pageType;
	
	
	public int getPrjNo() {
		return prjNo;
	}
	public void setPrjNo(int prjNo) {
		this.prjNo = prjNo;
	}
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
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
	
	public int getNewsPage() {
		return newsPage;
	}
	public void setNewsPage(int newsPage) {
		this.newsPage = newsPage;
	}
	
	
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
	
	
	
	
	
	
	public int getNtcPage() {
		return ntcPage;
	}
	public void setNtcPage(int ntcPage) {
		if (ntcPage < 1) {
			this.ntcPage = 1;
		} else {
			this.ntcPage = ntcPage;
		}
		setNtcStartRowNum();
	}
	public int getNtcPerPageNum() {
		return ntcPerPageNum;
	}
	public void setNtcPerPageNum(int ntcPerPageNum) {
		if (ntcPerPageNum < 1 ) {
			this.ntcPerPageNum = 10;
		} else {
			this.ntcPerPageNum = ntcPerPageNum;
		}
		setNtcStartRowNum();
	}
	public int getNtcStartRowNum() {
		return ntcStartRowNum;
	}
	public void setNtcStartRowNum(int ntcStartRowNum) {
		this.ntcStartRowNum = ntcStartRowNum;
	}
	private void setNtcStartRowNum() {
		this.ntcStartRowNum = (this.ntcPage - 1) * ntcPerPageNum;
	}
	public int getNtcStartRow() {
		return (this.ntcPage - 1) * ntcPerPageNum;
	}
	public int getNtcEndRow() {
		return getNtcStartRow() + ntcPerPageNum;
	}
	
	
	
	public int getEtcPage() {
		return etcPage;
	}
	public void setEtcPage(int etcPage) {
		if (etcPage < 1) {
			this.etcPage = 1;
		} else {
			this.etcPage = etcPage;
		}
		setEtcStartRowNum();
	}
	public int getEtcPerPageNum() {
		return etcPerPageNum;
	}
	public void setEtcPerPageNum(int etcPerPageNum) {
		if (etcPerPageNum < 1 ) {
			this.etcPerPageNum = 10;
		} else {
			this.etcPerPageNum = etcPerPageNum;
		}
		setEtcStartRowNum();
	}
	public int getEtcStartRowNum() {
		return etcStartRowNum;
	}
	public void setEtcStartRowNum(int etcStartRowNum) {
		this.etcStartRowNum = etcStartRowNum;
	}
	private void setEtcStartRowNum() {
		this.etcStartRowNum = (this.etcPage - 1) * etcPerPageNum;
	}
	public int getEtcStartRow() {
		return (this.etcPage - 1) * etcPerPageNum;
	}
	public int getEtcEndRow() {
		return getEtcStartRow() + etcPerPageNum;
	}
	
	
}
