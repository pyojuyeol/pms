package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.RecoverPwdVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.exception.IdNotFoundException;
import kr.or.pms.exception.InvalidPasswordException;

public interface UserService {
	
	public List<UserVO> getUserList()throws Exception;
	public List<UserVO> getUserListActive()throws Exception;
	public List<UserVO> getUserList(Criteria cri)throws Exception;
	Map<String, Object> getSearchUserList(Criteria cri) throws Exception;
	
	UserVO getUserForLogin(String login_id) throws Exception;
	UserVO getUser(String id) throws Exception;
	String getUserNm(String id) throws Exception;

	// 회원찾기
	String getUserIdByEmail(String email)throws Exception;
	// 이메일 찾기
	String getUserEmailById(String userId) throws Exception;
	
	// 회원등록
	void regist(UserVO user) throws Exception;
	void registList(List<UserVO> users) throws Exception;
	
	// 비밀번호 설정코드 등록
	void registRecoverKey(RecoverPwdVO rpVO) throws Exception;
	
	// 회원수정
	void modify(UserVO user) throws Exception;
	
	// 회원수정(마이페이지)
	void modifyFromMyPage(UserVO user) throws Exception;
	
	// 회원삭제
	void remove(String id) throws Exception;
	
	// 회원 상태변경
	void enabled(String id, int enabled)throws Exception;
	
	void login(String id, String pwd) throws IdNotFoundException, InvalidPasswordException, SQLException;
	
	String recoverPwd(RecoverPwdVO rpVO)throws Exception;
	
	// 비밀번호 변경
	void modifyPwd(RecoverPwdVO rpVO) throws Exception; 
}
