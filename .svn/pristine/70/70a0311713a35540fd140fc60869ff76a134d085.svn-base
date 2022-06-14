package kr.or.pms.command;

import org.springframework.web.multipart.MultipartFile;

import kr.or.pms.dto.UserVO;

public class UserModifyCommand {

	private String userId;
	private String pwd;
	private String genderCode;
	private String email;
	private String hp;
	private String addr;
	private String birth;
	private String userNm;
	private String picSavedNm;
	private String positionCode;
	private String userSttCode;
	private String deptCode;
	private String authGrpCode;
	private String regdate;
	
	private String gender;
	private String position;
	private String userStt;
	private String dept;
	private MultipartFile picture; // 사진파일	
	private String uploadPicture; // 변경된 사진 파일명.
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		if(hp == null || hp.equals("")) {hp = "-";}
		this.hp = hp.replaceAll(",", "-");
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		if(addr == null || addr.equals("")) {addr = "-";}
		this.addr = addr;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		if(birth == null || birth.equals("")) {birth = "-";}
		this.birth = birth;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getPicSavedNm() {
		return picSavedNm;
	}
	public void setPicSavedNm(String picSavedNm) {
		this.picSavedNm = picSavedNm;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	
	public String getUserSttCode() {
		return userSttCode;
	}
	public void setUserSttCode(String userSttCode) {
		this.userSttCode = userSttCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getAuthGrpCode() {
		return authGrpCode;
	}
	public void setAuthGrpCode(String authGrpCode) {
		this.authGrpCode = authGrpCode;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	public String getUserStt() {
		return userStt;
	}
	public void setUserStt(String userStt) {
		this.userStt = userStt;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		if(dept == null || dept.equals("")) {dept = "0";}
		this.dept = dept;
	}
	
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	public String getUploadPicture() {
		return uploadPicture;
	}
	public void setUploadPicture(String uploadPicture) {
		this.uploadPicture = uploadPicture;
	}
	
	public UserVO toUserVO() {
		UserVO userVO = new UserVO();
		
		userVO.setUserId(userId);
		userVO.setPwd(pwd);
		userVO.setGenderCode(genderCode);
		userVO.setEmail(email);
		userVO.setHp(hp);
		userVO.setAddr(addr);
		userVO.setBirth(birth);
		userVO.setUserNm(userNm);
		userVO.setPicSavedNm(picSavedNm);
		userVO.setPositionCode(positionCode);
		userVO.setUserSttCode(userSttCode);
		userVO.setDeptCode(deptCode);
		userVO.setAuthGrpCode(authGrpCode);
		
		userVO.setGender(gender);
		userVO.setPosition(position);
		userVO.setUserStt(userStt);
		userVO.setDept(dept);
		
		return userVO;
	}
	

}

