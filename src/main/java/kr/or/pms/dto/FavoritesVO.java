package kr.or.pms.dto;

public class FavoritesVO {

	private String userId;	// 유저 아이디 
	private int prjNo;		// 프로젝트 번호
	private String prjNm;	// 프로젝트 이름
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	
}