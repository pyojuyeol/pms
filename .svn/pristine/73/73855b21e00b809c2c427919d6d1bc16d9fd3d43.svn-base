package kr.or.pms.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.pms.command.ProceedingsCriteria;
import kr.or.pms.dto.IssueVO;
import kr.or.pms.dto.PrcdUserVO;
import kr.or.pms.dto.ProceedingsVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.IssueService;
import kr.or.pms.service.ProceedingsService;
import kr.or.pms.service.UserService;

@Controller
@RequestMapping("/project/proceedings")
public class ProceedingsController {
	
	@Resource(name="proceedingsService")
	private ProceedingsService service;
	
	@Resource(name="issueService")
	private IssueService issueService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("/list")
	public ModelAndView getList(ProceedingsCriteria cri, ModelAndView mnv, HttpSession session) throws Exception {
		
		String url = "project/proceedings/list";
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		UserVO userVO = (UserVO) session.getAttribute("loginUser");
		String userId = userVO.getUserId();
		
		IssueVO issue = issueService.getprjName(prjNo);
		mnv.addObject("prjNm", issue.getPrjNm());
		
		cri.setPrjNo(prjNo);
		Map<String, Object> dataMap = service.getList(cri);
		
		@SuppressWarnings("unchecked")
		List<ProceedingsVO> proceedings = (List<ProceedingsVO>) dataMap.get("procList");
		
		for(ProceedingsVO vo : proceedings) {
			String userNm = userService.getUserNm(vo.getRegister());
			vo.setUserNm(userNm);
		}
		
		dataMap.put("confirmList", service.selectPrcdNoByUserId(userId,prjNo)); 
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
		
	}

	@RequestMapping("/registForm")
	public ModelAndView registForm(ModelAndView mnv, HttpSession session) throws Exception {
		
		String url = "project/proceedings/registForm";
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		IssueVO issue = issueService.getprjName(prjNo);
		mnv.addObject("prjNm", issue.getPrjNm());
		mnv.addObject("prjNo", prjNo);
		
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> regist(ProceedingsVO proceedings) throws Exception {
		ResponseEntity<String> entity = null;
		
		proceedings.setUserNm(userService.getUserNm(proceedings.getRegister()));
		System.out.println("유저아이디"+Arrays.toString(proceedings.getUserId()));
		service.registProceedings(proceedings);
		System.out.println("asdlfkjaslekfjoasvinlaskefoiasjdf" + proceedings.getPdfFile().getSize());
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int prcdNo, String from, ModelAndView mnv, HttpSession session) throws Exception {
		
		String url = "project/proceedings/detail";
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		IssueVO issue = issueService.getprjName(prjNo);
		mnv.addObject("prjNm", issue.getPrjNm());
		
		ProceedingsVO proceedings = service.selectProceedingsByPrcdNo(prcdNo,prjNo);
		System.out.println("널이냐"+proceedings);
		if(proceedings == null) {
			mnv.addObject("message","삭제된 페이지 입니다.");
			url = "security/fail";
		} else {
			String userNm = userService.getUserNm(proceedings.getRegister());
			proceedings.setUserNm(userNm);
			
			List<PrcdUserVO> prcdUser = service.selectUserIdByPrcdNo(prcdNo);
			
			for(PrcdUserVO vo : prcdUser) {
				vo.setUserNm(userService.getUserNm(vo.getUserId()));
			}
			mnv.addObject("proceedings", proceedings);
			mnv.addObject("userList", prcdUser);
		}
		
		
		
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(ModelAndView mnv, int prcdNo, HttpSession session) throws Exception {
		
		String url = "project/proceedings/modify";
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		IssueVO issue = issueService.getprjName(prjNo);
		mnv.addObject("prjNm", issue.getPrjNm());
		
		ProceedingsVO proceedings = service.selectProceedingsByPrcdNo(prcdNo,prjNo);
		
		String userNm = userService.getUserNm(proceedings.getRegister());
		proceedings.setUserNm(userNm);
		
		List<PrcdUserVO> prcdUser = service.selectUserIdByPrcdNo(prcdNo);
		
		for(PrcdUserVO vo : prcdUser) {
			vo.setUserNm(userService.getUserNm(vo.getUserId()));
		}
		
		mnv.addObject("proceedings", proceedings);
		mnv.addObject("userList", prcdUser);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/reWrite")
	public ModelAndView reWrite(ModelAndView mnv, int prcdNo, HttpSession session) throws Exception {
		
		String url = "project/proceedings/reWrite";
		List<String> userId = new ArrayList<String>(); 
		int prjNo = (int) session.getAttribute("currentPrjNo");
		IssueVO issue = issueService.getprjName(prjNo);
		mnv.addObject("prjNm", issue.getPrjNm());
		
		ProceedingsVO proceedings = service.selectProceedingsByPrcdNo(prcdNo,prjNo);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + proceedings.getExcelContent().length);
		
		String userNm = userService.getUserNm(proceedings.getRegister());
		proceedings.setUserNm(userNm);
		
		List<PrcdUserVO> prcdUser = service.selectUserIdByPrcdNo(prcdNo);
		
		
		for(PrcdUserVO vo : prcdUser) {
			vo.setUserNm(userService.getUserNm(vo.getUserId()));
			userId.add(vo.getUserId());
		}
		String[] userIdStr = userId.toArray(new String[userId.size()]);
		proceedings.setUserId(userIdStr);
		
		mnv.addObject("proceedings", proceedings);
		mnv.addObject("userList", prcdUser);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/modify")
	public String modifyPOST(ProceedingsVO proceedings, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		String url = "redirect:/project/proceedings/detail.do";
		
		int prjNo = (int) request.getSession().getAttribute("currentPrjNo");
		
		proceedings.setPrjNo(prjNo);
		proceedings.setReturnMsg(proceedings.getCategory());
		
		
		service.modifyProceedings(proceedings);
		
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("prcdNo", proceedings.getPrcdNo());
		
		return url;
		
	}
	@RequestMapping("/return")
	public String returnPOST(ProceedingsVO proceedings, HttpServletRequest request, RedirectAttributes rttr, HttpSession session) throws Exception {
		
		String url = "redirect:/project/proceedings/detail.do";
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		
		
		proceedings.setPrjNo(prjNo);
		proceedings.setReturnMsg(proceedings.getCategory());
		service.returnProceedings(proceedings);
		
		rttr.addFlashAttribute("from", "return");
		rttr.addAttribute("prcdNo", proceedings.getPrcdNo());
		
		return url;
		
	}
	@RequestMapping("/getPdf")
	@ResponseBody
	public void getPdfFile(int prcdNo, HttpServletResponse response) throws Exception {
		InputStream is = null;
		ServletOutputStream os = null;
		
		String contentType = "application/pdf";
		response.setContentType(contentType);
		
		try {
			byte[] pdfArray = service.getPdfFile(prcdNo);
	        is = new ByteArrayInputStream(pdfArray);
	        
	        os = response.getOutputStream();
	        int binaryRead;
	        
	        while ((binaryRead = is.read()) != -1)    {
	            os.write(binaryRead);
	        }
		} catch (IOException e) {
			throw new IOException();
		} finally {
			os.close();
			is.close();
		}
	}
	
	@RequestMapping("/getExcel")
	public String getExcelFile(int prcdNo, HttpSession session, Model model) throws Exception {
		String url = "downloadFile";
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		
		ProceedingsVO proceedings = service.selectProceedingsByPrcdNo(prcdNo,prjNo);
		
		byte[] excelArray = service.getExcelFile(prcdNo);
		String fileName = proceedings.getTitle() + ".xlsx";
		
		model.addAttribute("bytes", excelArray);
		model.addAttribute("fileName", fileName);
		
		return url;
			
	}
	
}
