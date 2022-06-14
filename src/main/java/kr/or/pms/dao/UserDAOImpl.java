package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.RecoverPwdVO;
import kr.or.pms.dto.UserVO;

public class UserDAOImpl implements UserDAO {
	
	private SqlSession session;	
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<UserVO> selectUserListActive() throws Exception {
		List<UserVO> userList  
		= session.selectList("User-Mapper.selectUserListActive");
		
		return userList;
	}
	
	@Override
	public List<UserVO> selectUserList() throws Exception {
		List<UserVO> userList  
			= session.selectList("User-Mapper.selectUserList");
			
		return userList;
	}

	@Override
	public List<UserVO> selectUserList(Criteria cri) throws Exception {
		
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<UserVO> userList  
		= session.selectList("User-Mapper.selectSearchUserList",cri,rowBounds);
		
		return userList;
	}
	
	@Override
	public int selectUserListCount() throws Exception {
		int totalCount = session.selectOne("User-Mapper.selectUserListCount");
		
		return totalCount;
	}
	
	@Override
	public int selectUserListActiveCount() throws Exception {
		int totalCount = session.selectOne("User-Mapper.selectUserListActiveCount");
		
		return totalCount;
	}
	
	@Override
	public int selectUserListCount(Criteria cri) throws Exception {
		int totalCount = session.selectOne("User-Mapper.selectSearchUserListCount",cri);
		
		return totalCount;
	}


	@Override
	public UserVO selectUserById(String id) throws SQLException {
		UserVO user=session.selectOne("User-Mapper.selectUserById",id);			
		return user;
	}
	@Override
	public String selectUserNmById(String id) throws SQLException {
		String userNm=session.selectOne("User-Mapper.selectUserNmById",id);			
		return userNm;
	}

	@Override
	public void insertUser(UserVO user) throws SQLException {
		session.update("User-Mapper.insertUser",user);
		
	}
	
	@Override
	public void insertUserList(List<UserVO> users) throws SQLException {
		session.update("User-Mapper.insertUserList",users);
		
	}
	
	@Override
	public void updateUser(UserVO user) throws SQLException {
		session.update("User-Mapper.updateUser",user);

	}
	
	@Override
	public void updateUserFromMyPage(UserVO user) throws SQLException {
		session.update("User-Mapper.updateUserFromMyPage",user);
		
	}

	@Override
	public void deleteUser(String id) throws SQLException {
		session.update("User-Mapper.deleteUser",id);		
	}

	@Override
	public void enabledUser(String id, int enabled) throws SQLException {
		
		Map<String, Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("id", id);
		dataMap.put("enabled",enabled);
		
		session.update("User-Mapper.enabledUser",dataMap);
		
	}

	@Override
	public UserVO selectUserByPicture(String picture) throws SQLException {
		UserVO user = null;
		user = session.selectOne("User-Mapper.selectUserByPicture", picture);
		
		return user;
	}

	
	
	/*로그인시 필요한 유저 정보*/
	@Override
	public UserVO selectUserForLogin(String id) {
		UserVO user = session.selectOne("User-Mapper.selectUserForLogin", id);
		return user;
	}

	/* 비밀번호 찾기 및 회원번호 찾기 */
	@Override
	public String selectUserIdByEmail(String email) throws SQLException {
		String userId = session.selectOne("User-Mapper.selectUserIdByEmail", email);
		return userId;
	}

	@Override
	public void insertRecoverKey(RecoverPwdVO rpVO) throws SQLException {
		session.update("User-Mapper.insertRecoverKey", rpVO);
	}

	@Override
	public String selectUserIdByRecoverKey(String key) throws SQLException {
		String userId = session.selectOne("User-Mapper.selectUserIdByRecoverKey", key);
		return userId;
	}

	@Override
	public void updatePwd(RecoverPwdVO rpVO) throws SQLException {
		session.update("User-Mapper.updatePwd", rpVO);
	}

	@Override
	public void deleteRecoverKey(String userId) throws SQLException {
		session.update("User-Mapper.deleteRecoverKey", userId);
	}

	@Override
	public String selectEmailById(String userId) throws SQLException {
		String email = session.selectOne("User-Mapper.selectEmailById", userId);
		return email;
	}

	@Override
	public String getNextId() throws SQLException {
		String id = session.selectOne("User-Mapper.selectUserIdNext");
		return id;
	}


}
