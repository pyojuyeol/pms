package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dao.CloudDAO;
import kr.or.pms.dao.ComCodeDAO;
import kr.or.pms.dao.UserDAO;
import kr.or.pms.dto.CloudDirectoryVO;
import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.RecoverPwdVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.exception.IdNotFoundException;
import kr.or.pms.exception.InvalidPasswordException;

public class UserServiceImpl implements UserService {

	
	private UserDAO userDAO;
	private CloudDAO cloudDAO;
	private ComCodeDAO comCodeDAO;
	
	public ComCodeDAO getComCodeDAO() {
		return comCodeDAO;
	}

	public void setComCodeDAO(ComCodeDAO comCodeDAO) {
		this.comCodeDAO = comCodeDAO;
	}

	public CloudDAO getCloudDAO() {
		return cloudDAO;
	}

	public void setCloudDAO(CloudDAO cloudDAO) {
		this.cloudDAO = cloudDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public UserVO getUserForLogin(String id) throws Exception {
		UserVO user = userDAO.selectUserForLogin(id);
		return user;
	}
	@Override
	public UserVO getUser(String id) throws Exception {
		UserVO user = userDAO.selectUserById(id);
//		setNmFromCode(user);
		return user;
	}
	
	@Override
	public String getUserNm(String id) throws Exception {
		String userNm = userDAO.selectUserNmById(id);
		return userNm;
	}
	

	@Override
	public List<UserVO> getUserList() throws Exception {
		List<UserVO> userList = userDAO.selectUserList();
		setNmFromCode(userList);
		
		return userList;
	}
	
	@Override
	public List<UserVO> getUserListActive() throws Exception {
		List<UserVO> userList = userDAO.selectUserListActive();
		setNmFromCode(userList);
		
		return userList;
	}


	@Override
	public Map<String, Object> getSearchUserList(Criteria cri) throws Exception {

		Criteria searchCri = (Criteria)cri;
		Map<String, Object> dataMap = new HashMap<String, Object>();

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(userDAO.selectUserListCount(searchCri));

		List<UserVO> userList = userDAO.selectUserList(searchCri);
		setNmFromCode(userList);

		dataMap.put("userList", userList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	@Override
	public void regist(UserVO user) throws Exception {
		CloudDirectoryVO cloudDirectory = new CloudDirectoryVO();
		user.setUserId(userDAO.getNextId());
		userDAO.insertUser(user);

		cloudDirectory.setDirNo(cloudDAO.selectCloudDirectorySeqNextValue());
		cloudDirectory.setDirPath(user.getUserId());
		cloudDirectory.setDirNm("내 클라우드");
		cloudDirectory.setCldKeyCode("2");
		cloudDirectory.setUpDir(0);
		cloudDirectory.setUserId(user.getUserId());
		
		cloudDAO.insertUserCloudDirectory(cloudDirectory);
	}
	@Override
	public void registList(List<UserVO> users) throws Exception {
	
		setCodeFromNm(users);
		String userId = userDAO.getNextId();
		int userIdNum = Integer.parseInt(userId);
		for (UserVO userVO : users) {
			userVO.setUserId(userIdNum + "");
			userIdNum++;
		}
		userDAO.insertUserList(users);
		
		for (UserVO userVO : users) {
			CloudDirectoryVO cloudDirectory = new CloudDirectoryVO();
			
			userId = userVO.getUserId();
			cloudDirectory.setDirNo(cloudDAO.selectCloudDirectorySeqNextValue());
			cloudDirectory.setDirPath(userId);
			cloudDirectory.setDirNm("내 클라우드");
			cloudDirectory.setCldKeyCode("2");
			cloudDirectory.setUpDir(0);
			cloudDirectory.setUserId(userId);
			
			cloudDAO.insertUserCloudDirectory(cloudDirectory);
		}
	}

	@Override
	public void modify(UserVO user) throws Exception {
		userDAO.updateUser(user);
	}
	
	@Override
	public void modifyFromMyPage(UserVO user) throws Exception {
		userDAO.updateUserFromMyPage(user);
	}
	
	@Override
	public void remove(String id) throws Exception {

		userDAO.deleteUser(id);

	}	

	@Override
	public void enabled(String id,int state) throws Exception {

		userDAO.enabledUser(id,state);
	}

	


	@Override
	public void login(String id, String pwd) throws IdNotFoundException, InvalidPasswordException, SQLException {
		UserVO user = userDAO.selectUserById(id);
		if (user == null)
			throw new IdNotFoundException();
		if (!pwd.equals(user.getPwd()))
			throw new InvalidPasswordException();
	}


	@Override
	public List<UserVO> getUserList(Criteria cri) throws Exception {
		List<UserVO> userList = userDAO.selectUserList(cri);
		return userList;
	}

	@Override
	public String getUserIdByEmail(String email) throws Exception {
		String userId = userDAO.selectUserIdByEmail(email);
		return userId;
	}

	@Override
	public void registRecoverKey(RecoverPwdVO rpVO) throws Exception {
		userDAO.insertRecoverKey(rpVO);
	}

	
	@Override
	public String recoverPwd(RecoverPwdVO rpVO) throws Exception {
		String userId = userDAO.selectUserIdByRecoverKey(rpVO.getKey());
		rpVO.setUserId(userId);
		
		userDAO.updatePwd(rpVO);
		userDAO.deleteRecoverKey(userId);
		return userId;
	}
	
	@Override
	public void modifyPwd(RecoverPwdVO rpVO) throws Exception {
		userDAO.updatePwd(rpVO);
	}

	@Override
	public String getUserEmailById(String userId) throws Exception {
		String email = userDAO.selectEmailById(userId);
		return email;
	}
	

	
	
	
	
	//유저랑 관련된 공통코드를 DB에서 가져옴
	public Map<String,Map<String,ComCodeVO>> getUserComCodeMap() throws Exception{
		// 공통코드를 담을 맵
		Map<String,Map<String,ComCodeVO>> comCodeMap = new HashMap<>();
		Map<String,ComCodeVO> genderMap = new HashMap<>();
		Map<String,ComCodeVO> positionMap = new HashMap<>();
		Map<String,ComCodeVO> deptMap = new HashMap<>();
		Map<String,ComCodeVO> sttMap = new HashMap<>();

		//DB에서 공통코드 가져오기
		List<ComCodeVO> comCodeList = comCodeDAO.selectComCodeListofUser();

		//공통코드 분류해서 맵에 담기(key:code,value:ComcodeVO)
		for (ComCodeVO comCodeVO : comCodeList) {
			switch(comCodeVO.getCodeGrp()) {
				case "C0001": genderMap.put(comCodeVO.getCode(),comCodeVO);
							  genderMap.put(comCodeVO.getNm(),comCodeVO);break;
				case "C0002": positionMap.put(comCodeVO.getCode(),comCodeVO);
						      positionMap.put(comCodeVO.getNm(),comCodeVO); break;
				case "C0003": deptMap.put(comCodeVO.getCode(),comCodeVO);
							  deptMap.put(comCodeVO.getNm(),comCodeVO); break;
				case "C0004": sttMap.put(comCodeVO.getCode(),comCodeVO);
							  sttMap.put(comCodeVO.getNm(),comCodeVO); break;
			}
		}
		
		comCodeMap.put("gender",genderMap);
		comCodeMap.put("position",positionMap);
		comCodeMap.put("dept",deptMap);
		comCodeMap.put("stt",sttMap);
		
		return comCodeMap;
	}
	
	// userVO List안에 gender,position,dept,stt의 공통코드값을 입력값을 이용해서 채움
	public void setCodeFromNm(List<UserVO> userList) throws Exception {
		//사원공통코드 가져오기
		Map<String,Map<String,ComCodeVO>> userComCodeMap = getUserComCodeMap();
		
        for (UserVO userVO : userList) {
        	userVO.setGenderCodeFromComCode( userComCodeMap.get("gender") );
        	userVO.setPositionCodeFromComCode( userComCodeMap.get("position") );
        	userVO.setDeptCodeFromComCode( userComCodeMap.get("dept") );
        	userVO.setUserSttCodeFromComCode( userComCodeMap.get("stt") );
        }
	}
	
	// userVO안에 gender,position,dept,stt의 code값을 공통코드를 이용해서 채움
	public void setCodeFromNm(UserVO userVO) throws Exception {
		//사원공통코드 가져오기
		Map<String,Map<String,ComCodeVO>> userComCodeMap = getUserComCodeMap();
		
			userVO.setGenderCodeFromComCode( userComCodeMap.get("gender") );
			userVO.setPositionCodeFromComCode( userComCodeMap.get("position") );
			userVO.setDeptCodeFromComCode( userComCodeMap.get("dept") );
			userVO.setUserSttCodeFromComCode( userComCodeMap.get("stt") );
	}
	
	// userVO List안에 gender,position,dept,stt값을 공통코드를 이용해서 채움
	public void setNmFromCode(List<UserVO> userList) throws Exception {
		//사원공통코드 가져오기
		Map<String,Map<String,ComCodeVO>> userComCodeMap = getUserComCodeMap();

		for (UserVO userVO : userList) {
			userVO.setGenderFromComCode( userComCodeMap.get("gender") );
			userVO.setPositionFromComCode( userComCodeMap.get("position") );
			userVO.setDeptFromComCode( userComCodeMap.get("dept") );
			userVO.setUserSttFromComCode( userComCodeMap.get("stt") );
		}
	}

	// userVO안에 gender,position,dept,stt값을 공통코드를 이용해서 채움
	public void setNmFromCode(UserVO userVO) throws Exception {
		//사원공통코드 가져오기
		Map<String,Map<String,ComCodeVO>> userComCodeMap = getUserComCodeMap();
		
			userVO.setGenderFromComCode( userComCodeMap.get("gender") );
			userVO.setPositionFromComCode( userComCodeMap.get("position") );
			userVO.setDeptFromComCode( userComCodeMap.get("dept") );
			userVO.setUserSttFromComCode( userComCodeMap.get("stt") );
	}
	
}

