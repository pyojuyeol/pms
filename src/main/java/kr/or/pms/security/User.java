package kr.or.pms.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.or.pms.dto.UserVO;


@SuppressWarnings("serial")
public class User implements UserDetails {

	private UserVO user;	
	public User(UserVO user) {	
		this.user = user;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();	
		roles.add(new SimpleGrantedAuthority(user.getAuthGrpCode()));
		return roles;
	}

	@Override
	public String getPassword() {
		return user.getPwd();
	}

	@Override
	public String getUsername() {
		return user.getUserId();
	}


	@Override
	public boolean isAccountNonExpired() { //기간제 계정의 경우 기간만료 여부  : enabled =4
		return !user.getUserSttCode().equals("4");
	}

	@Override
	public boolean isAccountNonLocked() { //사용 정지 혹은 휴먼계정  : enabled =3
		return !user.getUserSttCode().equals("3");
	}

	@Override
	public boolean isCredentialsNonExpired() {//인증정보 만료 여부 : enabled =2
		return !user.getUserSttCode().equals("2");
	}

	@Override
	public boolean isEnabled() {// 탈퇴 혹은 삭제 : enabled = 0
		return !user.getUserSttCode().equals("0");
	}
	
	public UserVO getUserVO() {
		return this.user;
	}

}





