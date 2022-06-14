package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSessionException;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.RecoverPwdVO;
import kr.or.pms.dto.UserVO;

public interface UserDAO {
	// 회원리스트 조회
	List<UserVO> selectUserList() throws Exception;
	List<UserVO> selectUserListActive() throws Exception;

	List<UserVO> selectUserList(Criteria cri) throws Exception;

	// 일반 리스트 전체 개수
	int selectUserListCount() throws Exception;
	int selectUserListActiveCount() throws Exception;

	int selectUserListCount(Criteria cri) throws Exception;

	// 회원정보 조회
	UserVO selectUserById(String id) throws SQLException;
	UserVO selectUserByPicture(String picture) throws SQLException;
	UserVO selectUserForLogin(String id);

	// 회원 추가
	void insertUser(UserVO user) throws SQLException;
	void insertUserList(List<UserVO> users) throws SQLException;

	// 회원 수정
	void updateUser(UserVO user) throws SQLException;
	
	// 회원 수정
	void updateUserFromMyPage(UserVO user) throws SQLException;

	// 회원정보 삭제
	void deleteUser(String id) throws SQLException;

	// 회원 활성화
	void enabledUser(String id, int enabled) throws SQLException;
	
	// 이름 가져오기
	String selectUserNmById(String id) throws SQLException;

	//아이디 찾기
	String selectUserIdByEmail(String email) throws SQLException;
	// 이메일 찾기
	String selectEmailById(String userId) throws SQLException;

	void insertRecoverKey(RecoverPwdVO rpVO) throws SQLException;

	String selectUserIdByRecoverKey(String key) throws SQLException;

	void updatePwd(RecoverPwdVO rpVO) throws SQLException;

	void deleteRecoverKey(String userId) throws SQLException;

	String getNextId() throws SQLException;
}
