package kr.or.pms.command;

public class DbcnCriteria extends Criteria{
	
	private String searchDbNm="";
	private String type = "dbcn";

	

	public String getSearchDbNm() {
		return searchDbNm;
	}

	public void setSearchDbNm(String searchDbNm) {
		this.searchDbNm = searchDbNm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
