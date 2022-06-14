package kr.or.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.pms.command.WorkCriteria;
import kr.or.pms.command.WorkModifyCommand;
import kr.or.pms.command.WorkRegistCommand;
import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.dto.WorkVO;
import kr.or.pms.service.ComCodeService;
import kr.or.pms.service.ProjectService;
import kr.or.pms.service.WorkLogService;
import kr.or.pms.service.WorkService;

@Controller
@RequestMapping("/project/work")
public class WorkController {

	@Autowired
	private ProjectService prjService;
	
	@Autowired
	private WorkService workService;
	
	@Autowired 
	private WorkLogService workLogService;
	
	@Autowired
	private ComCodeService comCodeService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(HttpServletRequest request, WorkCriteria cri, Model model) throws Exception {
		HttpSession session = request.getSession();
		int prjNo = (int) session.getAttribute("currentPrjNo");
		String open = (String) request.getParameter("open"); //새창 여부
		
		Map<String, Object> dataMap = workService.getWorkListCriteriaByPno(cri, prjNo);
		
		ProjectVO prj = prjService.getProject(prjNo);
		
		// 일감 상태 공통 코드
		List<ComCodeVO> workSttList = comCodeService.getComCodeListByCodeGrp("WORKSTT");
		// 일감 우선순위 공통 코드
		List<ComCodeVO> ipcrList = comCodeService.getComCodeListByCodeGrp("IPCR");
		model.addAttribute("workSttList", workSttList);
		model.addAttribute("ipcrList", ipcrList);
		
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("prj", prj);
		model.addAttribute("open", open);
		
	}
	
	@RequestMapping(value = "/registForm", method = RequestMethod.GET)
	public void registForm(HttpServletRequest request, Model model, String subUpWork) throws Exception {
		HttpSession session = request.getSession();
		int prjNo = (int) session.getAttribute("currentPrjNo");
		String open = (String) request.getParameter("open");
		
		ProjectVO prj = prjService.getProject(prjNo);
		List<WorkVO> workList = workService.getWorkListByPno(prjNo);
		
		// 공통코드
		List<ComCodeVO> workSttList = comCodeService.getComCodeListByCodeGrp("WORKSTT");
		List<ComCodeVO> ipcrList = comCodeService.getComCodeListByCodeGrp("IPCR");
		model.addAttribute("workSttList", workSttList);
		model.addAttribute("ipcrList", ipcrList);
		
		if (subUpWork != null) model.addAttribute("subUpWork", subUpWork);
		model.addAttribute("prj", prj);
		model.addAttribute("workList", workList);
		model.addAttribute("open", open);
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(HttpServletRequest request, WorkRegistCommand registCMD, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/project/work/list.do";
		String open = registCMD.getOpen();
		String from = registCMD.getFrom();
		
		if (open != null && open.equals("1") ) {
			url = "redirect:/project/work/list.open?open=1";
		}
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		int prjNo = (int) session.getAttribute("currentPrjNo");
		
		// 일감 DB 저장
		WorkVO work = registCMD.toWorkVO();
		work.setPrjNo(prjNo);
		work.setWorkUpdater(loginUser.getUserId());
		work.setWorkRegister(loginUser.getUserId());
		work.setWorkTitle((String)request.getAttribute("XSSworkTitle"));
		
		workService.regist(work);
		
		if (from != null && from.equals("subWork")) {
			url = "redirect:/project/work/detail.do?workNo=" + registCMD.getUpWork();
			rttr.addFlashAttribute("from", "subWorkRegist");
		} else {
			rttr.addFlashAttribute("from", "regist");
		}
		
		return url;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest request, Model model, int workNo, String from) throws Exception {
		
		String url = "/project/work/detail";
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String open = request.getParameter("open");
		
		ProjectVO prj = null;
		int prjNo = 0;
		if (from != null && from != "") {
			if (from.equals("dashboard") || from.equals("myWorks") || from.equals("push")) {
				// 홈을 통해 오지 않고 메인에서 바로 넘어오는 경우 세션 등록.
				int currentPrjNo = workService.getPnoByWno(workNo);
				session.setAttribute("currentPrjNo", currentPrjNo);
				prj = prjService.getProject(currentPrjNo);
			}
		} else {
			prjNo = (int) session.getAttribute("currentPrjNo");
			prj = prjService.getProject(prjNo);
		}
		
		WorkVO work = workService.getWork(workNo);
		
		// 공통코드
		List<ComCodeVO> workSttList = comCodeService.getComCodeListByCodeGrp("WORKSTT");
		List<ComCodeVO> ipcrList = comCodeService.getComCodeListByCodeGrp("IPCR");
		model.addAttribute("workSttList", workSttList);
		model.addAttribute("ipcrList", ipcrList);
		
		
		if (from != null && from.equals("subWork")) {
			model.addAttribute("from", from);
		}
		model.addAttribute("loginUser", loginUser);
		model.addAttribute("prj", prj);
		model.addAttribute("work", work);
		model.addAttribute("open", open);
		
		if (from != null) {
			if (from.equals("push") && work.getSttCode().equals("d")) {
				url = "/security/fail";
				model.addAttribute("message", "삭제된 일감 입니다.");
			}
		}
		
		return url;
	}

	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	public void modifyForm(HttpServletRequest request, Model model, int workNo) throws Exception {
		HttpSession session = request.getSession();
		int prjNo = (int) session.getAttribute("currentPrjNo");
		String open = request.getParameter("open"); //새창 여부
		
		ProjectVO prj = prjService.getProject(prjNo);
		List<WorkVO> workList = workService.getWorkListByPno(prjNo);
		
		WorkVO work = workService.getWork(workNo);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(work.getWorkComment());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		
		// 공통코드
		List<ComCodeVO> workSttList = comCodeService.getComCodeListByCodeGrp("WORKSTT");
		List<ComCodeVO> ipcrList = comCodeService.getComCodeListByCodeGrp("IPCR");
		model.addAttribute("workSttList", workSttList);
		model.addAttribute("ipcrList", ipcrList);
		
		model.addAttribute("prj", prj);
		model.addAttribute("workList", workList);
		model.addAttribute("work", work);
		model.addAttribute("open", open);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(WorkModifyCommand modifyCMD, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/project/work/detail.do";
		String open = modifyCMD.getOpen();
		if(open != null && open.equals("1") ) {
			url = "redirect:/project/work/list.open?open=1";
		}
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		
		
		WorkVO newWork = modifyCMD.toWorkVO();
		newWork.setWorkUpdater(loginUser.getUserId());

		// 변경 사항 체크 및 로그 저장 위한 이전 자료 담기
		WorkVO oldWork = workService.getWork(newWork.getWorkNo());
		
		newWork.setWorkTitle((String)request.getAttribute("XSSworkTitle"));
		
		// DB 저장
		workService.modify(newWork);

		// 로그 저장
		workLogService.regist(newWork, oldWork);
		
		rttr.addAttribute("workNo", newWork.getWorkNo());
		rttr.addFlashAttribute("from", "modify");
		
		return url;
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(HttpServletRequest request, int workNo, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/project/work/list.do";
		String open = request.getParameter("open");
		if(open != null && open.equals("1") ) {
			url = "redirect:/project/work/list.open?open=1";
		}
		
		// DB 삭제
		workService.remove(workNo);
		rttr.addFlashAttribute("from", "remove");
		
		return url;
	}

}
