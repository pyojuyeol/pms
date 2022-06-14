package kr.or.pms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.pms.command.PasswordModifyCommand;
import kr.or.pms.command.UserModifyCommand;
import kr.or.pms.command.WorkCriteria;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.RecoverPwdVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.CalendarService;
import kr.or.pms.service.ComCodeService;
import kr.or.pms.service.UserService;
import kr.or.pms.service.WorkService;
import kr.or.pms.util.MakeFileName;
import kr.or.pms.util.ServerToServerFileIo;
import kr.or.pms.util.UserSha256;

@Controller
public class MyPageController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="comCodeService")
	private ComCodeService comCodeService;
	
	@Resource(name="calendarService")
	private CalendarService calendarService;
	
	@Resource(name="picturePath")
	private String picturePath;

	@Autowired
	private WorkService workService;
	
	//마이페이지 대쉬보드
	@RequestMapping("/myPage/dash")
	public void dash(HttpSession session, Model model, WorkCriteria cri) throws Exception {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		UserVO userVO = userService.getUser(userId);
		
		Map<String, Object> myDataMap = workService.getMyWorkListById(cri, userId);
		
//		List<WorkVO> myWorkList = workService.getMyWorkListById(cri, loginUser.getUserId());
		List<ComCodeVO> workSttList = comCodeService.getComCodeListByCodeGrp("WORKSTT");
		List<ComCodeVO> ipcrList = comCodeService.getComCodeListByCodeGrp("IPCR");
		Map<String, Object> dataMap = calendarService.selectCalendar(userVO.getUserId());
		
		// 공통코드
		setNmFromCode(userVO);
		
		model.addAttribute("user", userVO);
		model.addAttribute("dataMap", dataMap);
		
		model.addAttribute("workSttList", workSttList);
		model.addAttribute("ipcrList", ipcrList);
		
		model.addAttribute("myDataMap", myDataMap);
	}
	 
	//마이페이지 대쉬보드
	@RequestMapping("/myPage/myworkDash")
	public ModelAndView myworkdash(HttpSession session, ModelAndView mnv, WorkCriteria cri) throws Exception {
		String url = "myPage/myworkDash";
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		UserVO userVO = userService.getUser(userId);
		
		Map<String, Object> myDataMap = workService.getMyWorkListById(cri, userId);
		
//		List<WorkVO> myWorkList = workService.getMyWorkListById(cri, loginUser.getUserId());
		List<ComCodeVO> workSttList = comCodeService.getComCodeListByCodeGrp("WORKSTT");
		List<ComCodeVO> ipcrList = comCodeService.getComCodeListByCodeGrp("IPCR");
		
		// 공통코드
		setNmFromCode(userVO);
		
		mnv.addObject("user", userVO);
		mnv.addObject("workSttList", workSttList);
		mnv.addObject("ipcrList", ipcrList);
		mnv.addObject("myDataMap", myDataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/myPage/modify", method = RequestMethod.POST)
	public String modify(HttpSession session, UserModifyCommand userCmd, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/myPage/dash.do";
		
		//command 객체에서 user가져오기
		UserVO userVO = userCmd.toUserVO();
		//세션에서 아이디 가져오기
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		//vo에 아이디 세팅
		userVO.setUserId(userId);
		// 신규 파일 변경 및 기존 파일 삭제
		String oldPicture = userService.getUser(userId).getPicSavedNm();
		if(userCmd.getUploadPicture()!=null && !userCmd.getUploadPicture().isEmpty()) {			
			String fileName = savePicture(oldPicture, userCmd.getPicture(), userId);
			userVO.setPicSavedNm(fileName);
		}else {
			userVO.setPicSavedNm(oldPicture);			
		}
		
		//수정 실행
		try {
			userService.modifyFromMyPage(userVO);
			rttr.addFlashAttribute("from", "modify");
		} catch (Exception e) {
			rttr.addFlashAttribute("from", "modifyFail");
			throw e;
		}
		
		return url;
	}
	
	@RequestMapping(value="/myPage/modifyPw.do", method = RequestMethod.POST)
	public String modifyPwd(HttpSession session, PasswordModifyCommand passCmd, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/myPage/dash.do";
		//세션에서 아이디 가져오기
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		//아이디로 유저정보가져오기
		UserVO userVO = userService.getUserForLogin(userId);
		//입력한 비밀번호 암호화
		String currentPw = UserSha256.encrypt( passCmd.getCurrentPassword() );
		String newPw1 = passCmd.getNewPassword1();
		String newPw2 = passCmd.getNewPassword2();
		String newPw = UserSha256.encrypt( newPw1 );
		//사용자 비밀번호 검증
		if( !userVO.getPwd().equals(currentPw) ) {
			rttr.addFlashAttribute("from", "modifyPwFail");
			rttr.addFlashAttribute("message","사용자 비밀번호가 일치하지 않습니다.");
			return url;
		}
		//비밀번호 일치 검증
		if( !newPw1.equals(newPw2) ) {
			rttr.addFlashAttribute("from", "modifyPwFail");
			rttr.addFlashAttribute("message","새로운 비밀번호가 일치하지 않습니다.");
			return url;
		}
		//비밀번호 저장
		RecoverPwdVO rpVO = new RecoverPwdVO();
		rpVO.setUserId(userId);
		rpVO.setPwd(newPw);
		try {
			userService.modifyPwd(rpVO);
			rttr.addFlashAttribute("from", "modifyPw");
		} catch (Exception e) {
			rttr.addFlashAttribute("from", "modifyPwFail");
			rttr.addFlashAttribute("message","비밀번호 변경에 실패했습니다.");
		}
		
		return url;
	}
	
	private String savePicture(String oldPicture, MultipartFile multi, String userId) throws Exception {
		String fileName = null;

		/* 파일유무확인 */
		if (!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {

			/* 파일저장폴더설정 */
			String uploadPath = picturePath;
			
			List<MultipartFile> files = new ArrayList<MultipartFile>();
			files.add(multi);
			
			// 파일 서버에 파일 저장
			List<AttachVO> attachList = ServerToServerFileIo.getAttachesByUploadFiles(userId, files);
			for(AttachVO attach : attachList) {
				fileName = attach.getStroredNm();
			}
			
			if (oldPicture != null && !oldPicture.isEmpty() && !oldPicture.equals("noImage.png")) {
				ServerToServerFileIo.removeFile(uploadPath, oldPicture);
			}
		}
		return fileName;
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

	//유저랑 관련된 공통코드를 DB에서 가져옴
	public Map<String,Map<String,ComCodeVO>> getUserComCodeMap() throws Exception{
		// 공통코드를 담을 맵
		Map<String,Map<String,ComCodeVO>> comCodeMap = new HashMap<>();
		Map<String,ComCodeVO> genderMap = new HashMap<>();
		Map<String,ComCodeVO> positionMap = new HashMap<>();
		Map<String,ComCodeVO> deptMap = new HashMap<>();
		Map<String,ComCodeVO> sttMap = new HashMap<>();

		//DB에서 공통코드 가져오기
		List<ComCodeVO> comCodeList = comCodeService.getComCodeListOfUser();

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
	
}
