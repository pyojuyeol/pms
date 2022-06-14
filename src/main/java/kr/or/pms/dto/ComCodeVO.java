package kr.or.pms.dto;

public class ComCodeVO {
	private String codeGrp;	//공통코드그룹
	private String code;	//공통코드
	private String nm;		//코드 이름
	private String desc;	//코드 설명
	private String upCodeGrp;	//상위 공통코드그룹
	private String upCode;		//상위 공통코드
	private String useNy;	//사용여부
	
	public String getUseNy() {
		return useNy;
	}
	public void setUseNy(String useNy) {
		this.useNy = useNy;
	}
	public String getCodeGrp() {
		return codeGrp;
	}
	public void setCodeGrp(String codeGrp) {
		this.codeGrp = codeGrp;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUpCodeGrp() {
		return upCodeGrp;
	}
	public void setUpCodeGrp(String upCodeGrp) {
		this.upCodeGrp = upCodeGrp;
	}
	public String getUpCode() {
		return upCode;
	}
	public void setUpCode(String upCode) {
		this.upCode = upCode;
	}
	
}
