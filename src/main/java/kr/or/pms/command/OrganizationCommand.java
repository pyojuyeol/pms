package kr.or.pms.command;

import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.TeamVO;
import kr.or.pms.dto.TmemVO;
import kr.or.pms.dto.UserVO;

public class OrganizationCommand {
	private String id;
	private String userId = "";
	private String pId;
	private String name;
	private String userName;
	private boolean open = true;
	private boolean isParent = true;
	private boolean checked = false;

	private String user = "0";
	
	private String gender;
	private String position;
	private String userStt;
	private String dept;

	
	public OrganizationCommand(ComCodeVO dept) {
		id = "d"+dept.getCode();
		pId = "d"+dept.getUpCode();
		if(pId == null) {
			pId = "0";
		}
		name = dept.getNm();
	}
	public OrganizationCommand(ComCodeVO dept,int deptKey) {
		id = "d"+dept.getCode();
		pId = "d"+dept.getUpCode();
		if(pId == null) {
			pId = "0";
		}
		name = dept.getNm()+"#"+dept.getCode();
	}

	public OrganizationCommand(UserVO userVO) {
		id = userVO.getUserId();
		userId = userVO.getUserId();
		pId = "d"+userVO.getDeptCode();
		name = userVO.getUserNm()+":"+id;
		userName = userVO.getUserNm();
		
		gender = userVO.getGender();
		position = userVO.getPosition();
		userStt = userVO.getUserStt();
		dept = userVO.getDept();
		
		isParent = false;
		user = "1";
	}
	
	public OrganizationCommand(TeamVO team) {
		id = "t" + team.getTeamNo();
		pId = "0";
		name = team.getTeamNm()+"#"+team.getTeamNo();
	}

	public OrganizationCommand(TmemVO tmemVO, int num) {
		id = "tm" + num;
		userId = tmemVO.getUserId();
		pId = "t" + tmemVO.getTeamNo();
		name = tmemVO.getUserNm()+":"+tmemVO.getUserId();
		userName = tmemVO.getUserNm();
		
		isParent = false;
		user = "1";
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean getChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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
		this.dept = dept;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

}
