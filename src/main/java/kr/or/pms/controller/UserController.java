package kr.or.pms.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.RecoverPwdVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.mail.MailMessageCommand;
import kr.or.pms.mail.MimeAttachNotifier;
import kr.or.pms.service.ComCodeService;
import kr.or.pms.service.HtmlTempService;
import kr.or.pms.service.UserService;
import kr.or.pms.util.ExcelUtils;
import kr.or.pms.util.MakeFileName;
import kr.or.pms.util.ServerToServerFileIo;
import kr.or.pms.util.UserSha256;

@Controller
public class UserController {
	
	@Resource(name="comCodeService")
	private ComCodeService comCodeService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="htmlTempService")
	private HtmlTempService htmlTempService;
	
	@Autowired
	private MimeAttachNotifier notifier;
	
	@Resource(name="picturePath")
	private String picturePath;

	@RequestMapping("/member/list")
	public void list(Criteria cri, Model model) throws Exception {
		
		Map<String, Object> dataMap = userService.getSearchUserList(cri);
		addUserComCodeIntoModel(model);
		
		model.addAttribute("dataMap", dataMap);
	}
	
	@RequestMapping(value = "/member/registForm")
	public void registForm(Model model) throws Exception {
		addUserComCodeIntoModel(model);
	}
	
	@RequestMapping("/member/idCheck")
	@ResponseBody
	public ResponseEntity<String> idCheck(String userId) throws Exception {
		ResponseEntity<String> entity = null;

		try {
			UserVO userVO = userService.getUser(userId);

			if (userVO != null) {
				entity = new ResponseEntity<String>("duplicated", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("", HttpStatus.OK);
			}
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}
	
	@RequestMapping(value = "/member/regist", method = RequestMethod.POST)
	public String regist(UserVO userVO, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/member/list.do";
		if(userVO == null) {
			url = "redirect:/member/regist.do";
			rttr.addFlashAttribute("from", "regist");
			return url;
		}
		//?????????????????? ??????
		String pwd= UserSha256.getRamdomPassword(8);
		String secPwd = UserSha256.encrypt(pwd);
		userVO.setPwd(secPwd);
		//?????? ??????
		userService.regist(userVO);
		//???????????? ?????????????????? ????????? ?????????	
		sendMail(userVO);
		//
		rttr.addFlashAttribute("from", "regist");
		
		return url;
	}
	
	@RequestMapping(value = "/member/uploadExcelFile", method = RequestMethod.POST)
    public String registByExcelFile(MultipartHttpServletRequest request, Model model) throws Exception {
        MultipartFile file = null;
        Iterator<String> iterator = request.getFileNames();
        if(iterator.hasNext()) {
            file = request.getFile(iterator.next());
        }
        
        List<UserVO> userList = ExcelUtils.uploadExcelFile(file);
        
        //?????? ???????????? ???????????? ????????? ??????????????? ??????
//        setCodeFromNm(userList);
			
        for (UserVO userVO : userList) {
        	//?????????????????? ??????
        	String pwd= UserSha256.getRamdomPassword(8);
        	String secPwd = UserSha256.encrypt(pwd);
        	userVO.setPwd(secPwd);
        }
        userService.registList(userList);

        for (UserVO userVO : userList) {
        	//???????????? ?????????????????? ????????? ?????????	
        	sendMail(userVO);
		}
		
        model.addAttribute("list", userList);
        return "jsonView";
    }
	
	@RequestMapping(value = "/member/detail")
	public void detail(String id,Model model) throws Exception {
		UserVO userVO = userService.getUser(id);
		model.addAttribute("user", userVO);
		addUserComCodeIntoModel(model);
		
	}
	
	@RequestMapping(value = "/member/modifyForm")
	public void modifyForm(String id, Model model) throws Exception {
		
		UserVO userVO = userService.getUser(id);
		model.addAttribute("user", userVO);
		addUserComCodeIntoModel(model);
		
	}
	
	@RequestMapping(value = "/member/modify", method = RequestMethod.POST)
	public String modify(UserVO user, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/member/detail.do?id="+user.getUserId();
		
		try {
			userService.modify(user);
		
			rttr.addFlashAttribute("from", "modify");
		} catch (SQLException e) {
			throw e;
		}
		
		return url;
	
	}
	
	@RequestMapping(value = "/member/downloadExcelFile", method = RequestMethod.POST)
    public String downloadExcelFile(Model model) throws Exception {
		//?????? ??????????????? ????????????
		List<UserVO> userList = userService.getUserList();
		//????????????????????? ???????????? ??????
//		setNmFromCode(userList);
        
        SXSSFWorkbook workbook = ExcelUtils.excelFileDownloadProcess(userList);
        
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "??????????????????");
        
        return "excelDownloadView";
    }
	
	@RequestMapping(value = "/member/downloadExcelFileForm", method = RequestMethod.POST)
	public String downloadExcelFileForm(Model model) throws Exception {
		
		SXSSFWorkbook workbook = ExcelUtils.excelFileFormDownloadProcess();
		
		model.addAttribute("locale", Locale.KOREA);
		model.addAttribute("workbook", workbook);
		model.addAttribute("workbookName", "????????????_??????");
		
		return "excelDownloadView";
	}


	// ????????????  "genderMap","positionMap","deptMap","sttMap" ???????????? ??? ?????? 
	public void addUserComCodeIntoModel(Model model) throws Exception {
		// ??????????????? ?????? ??????
		List<ComCodeVO> genders = new ArrayList<>();
		List<ComCodeVO> positions = new ArrayList<>();
		List<ComCodeVO> depts = new ArrayList<>();
		List<ComCodeVO> stts = new ArrayList<>();
		
		//DB?????? ???????????? ????????????
		List<ComCodeVO> comCodeList = comCodeService.getComCodeListOfUser();

		//???????????? ???????????? ???????????? ??????
		for (ComCodeVO comCodeVO : comCodeList) {
			switch(comCodeVO.getCodeGrp()) {
				case "C0001": genders.add(comCodeVO); break;
				case "C0002": positions.add(comCodeVO); break;
				case "C0003": depts.add(comCodeVO); break;
				case "C0004": stts.add(comCodeVO); break;
			}
		}
		
		model.addAttribute("genders",genders);
		model.addAttribute("positions",positions);
		model.addAttribute("depts",depts);
		model.addAttribute("stts",stts);
	}
	
	
	//?????????????????? ?????? ??????
	public void sendMail(UserVO userVO) throws Exception {
		//???????????? ?????????????????? ????????? ?????????	
				String userEmail = userVO.getEmail();
				
				String id = userVO.getUserId();
				String name = userVO.getUserNm();
				MailMessageCommand mailReq = new MailMessageCommand();
				String key = UUID.randomUUID().toString();
				
				RecoverPwdVO rpVO = new RecoverPwdVO();
				rpVO.setKey(key);
				rpVO.setUserId(id);
				userService.registRecoverKey(rpVO);
				
				//????????? ???????????? ???????????? ??????
				List<String> paramList = new ArrayList<String>();
				paramList.add(name + " ???");
				paramList.add(id);
				paramList.add("http://localhost/pms/common/recoverPwd.do?key="+ key);
				
				//TEMP ???????????? ??????????????? tempNm, ???????????? tempNm, ???????????? ????????? ???
				String htmlStr = htmlTempService.getHtmlStr("", "joinUser", paramList);
				mailReq.setReceiver(userEmail);
				mailReq.setSender("prospecs0@naver.com");
				mailReq.setTitle("PROSPEC'S ????????? ???????????? ?????? ???????????????.");
				mailReq.setContent(htmlStr);
				notifier.sendMail(mailReq);
				//
	}
	
	//??????????????? ?????????
	@RequestMapping(value = "/getPicture", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<byte[]> getPicture(String id) throws Exception {
		String imgPath = picturePath;
		String picture = userService.getUser(id).getPicSavedNm();
		ResponseEntity<byte[]> entity = null;
		byte[] bytes = null;
		bytes = ServerToServerFileIo.getFile(imgPath, picture);
		entity = new ResponseEntity<byte[]>(bytes, HttpStatus.CREATED);
		
		return entity;
	}
	
	
	private String savePicture(String oldPicture, MultipartFile multi) throws Exception {
		String fileName = null;

		/* ?????????????????? */
		if (!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {

			/* ???????????????????????? */
			String uploadPath = picturePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "\\$\\$");
			File storeFile = new File(uploadPath, fileName);

			storeFile.mkdirs();

			// local HDD ??? ??????.
			multi.transferTo(storeFile);

			if (oldPicture != null && !oldPicture.isEmpty() && !oldPicture.equals("noImage.jpg")) {
				File oldFile = new File(uploadPath, oldPicture);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
		}
		return fileName;
	}

	@RequestMapping(value = "/picture", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> picture(@RequestParam("pictureFile") MultipartFile multi, String oldPicture)
			throws Exception {

		ResponseEntity<String> entity = null;

		String result = "";
		HttpStatus status = null;

		/* ?????????????????? */
		if ((result = savePicture(oldPicture, multi)) == null) {
			result = "????????? ??????????????????.!";
			status = HttpStatus.BAD_REQUEST;
		} else {
			status = HttpStatus.OK;

		}

		entity = new ResponseEntity<String>(result, status);

		return entity;

	}

}
