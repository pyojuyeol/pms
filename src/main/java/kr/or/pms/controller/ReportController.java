package kr.or.pms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.pms.command.ReportCriteria;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.PushVO;
import kr.or.pms.dto.ReportVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.ProjectService;
import kr.or.pms.service.PushService;
import kr.or.pms.service.ReportService;
import kr.or.pms.service.UserService;

@Controller
@RequestMapping("/project/report")
public class ReportController {
	
	@Resource(name = "reportService")
	private ReportService reportService;
	
	@Resource(name="projectService")
	private ProjectService projectService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="pushService")
	private PushService service;
	
	@RequestMapping("/list")
	public ModelAndView list(ReportCriteria cri, ModelAndView mnv, HttpSession session) throws Exception { 
		String url = "project/report/list";
		String plpmUrl = "project/report/getList";
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		ProjectVO prj=projectService.getProject(prjNo);
		
		
		cri.setPrjNo(prjNo);
		cri.setRepRegister(userId);

		String prjNm = prj.getPrjNm();
		
//		String approverNm = userService.getUserNm(cri.getApprover());
		ReportVO report = new ReportVO();
		report.setPrjNo(prjNo);
		report.setUserId(userId);
		
		String plpm = reportService.loginUserIsPlOrPm(report);

		if(plpm!=null) {
			// crew, pl 접속 시
			Map<String,Object> dailyReportMap = reportService.getMyDailyReportList(cri);
			Map<String,Object> monthlyReportMap = reportService.getMyMonthlyReportList(cri);
			Map<String,Object> tempReportMap = reportService.getMyTempReportList(cri);
			
			// pm 접속 시
			cri.setApprover(userId);
			report.setApprover(userId);
			
			Map<String, Object> receivedDailyReportMap = reportService.getReceivedDailyReportList(cri);
			Map<String, Object> receivedMonthReportMap = reportService.getReceivedMonthReportList(cri);
			
			mnv.addObject("prjNm",prjNm);
			mnv.addObject("plpm",plpm);
//			mnv.addObject("approverNm",approverNm);
			mnv.addObject("dailyReportMap", dailyReportMap);
			mnv.addObject("monthlyReportMap",monthlyReportMap);
			mnv.addObject("tempReportMap",tempReportMap);
			mnv.setViewName(url);
			
			if(plpm.equals("PM")) {
				mnv.addObject("prjNm",prjNm);
				mnv.addObject("plpm",plpm);
				mnv.addObject("receivedDailyReportMap", receivedDailyReportMap);
				mnv.addObject("receivedMonthReportMap", receivedMonthReportMap);
				mnv.setViewName(plpmUrl);
			}
		}
		
		return mnv;
	}
	
	@RequestMapping("/getList")
	public ModelAndView getList(ReportCriteria cri, ModelAndView mnv, HttpSession session) throws Exception { 
		String url = "project/report/getList";
		ReportVO report = new ReportVO();
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		ProjectVO prj = projectService.getProject(prjNo);
		cri.setPrjNo(prjNo);
		cri.setApprover(userId);
		String prjNm = prj.getPrjNm();

		report.setUserId(userId);
		report.setPrjNo(prjNo);
		String plpm = reportService.loginUserIsPlOrPm(report);
		
		System.err.println("!@#!@#!@#!@#!#!@#!@#!@#plpm"+plpm);
		
		report.setApprover(userId);

		Map<String, Object> receivedDailyReportMap = reportService.getReceivedDailyReportList(cri);
		Map<String, Object> receivedMonthReportMap = reportService.getReceivedMonthReportList(cri);

		mnv.addObject("prjNm", prjNm);
		mnv.addObject("plpm", plpm);
		mnv.addObject("receivedDailyReportMap", receivedDailyReportMap);
		mnv.addObject("receivedMonthReportMap", receivedMonthReportMap);
		mnv.setViewName(url);

		return mnv;
	}
	
	@RequestMapping("/registForm")
	public ModelAndView registForm(HttpSession session, ModelAndView mnv, String type) throws Exception {
		String url = "/project/report/registForm";
		
		int prjNo = (int)session.getAttribute("currentPrjNo");
		ProjectVO prj = projectService.getProject(prjNo);
		
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		String userNm = loginUser.getUserNm();
		String prjNm = prj.getPrjNm(); 
		
		ReportVO report = new ReportVO();
		report.setPrjNo(prjNo);
		report.setUserId(userId);
		
		String plpm = reportService.loginUserIsPlOrPm(report);
		String teamNm = reportService.getMyTeamName(prjNo);
		String pmId = reportService.getPMid(prjNo);
		
		List<ReportVO> plIdList = reportService.getProjectPlList(prjNo);
		
		mnv.addObject("prjNm",prjNm);
		mnv.addObject("userNm",userNm);
		mnv.addObject("plpm",plpm);
		mnv.addObject("teamNm",teamNm);
		mnv.addObject("plIdList",plIdList);
		mnv.addObject("pmId",pmId);
		
		mnv.setViewName(url);
		
		return mnv;
	}
	
	
	@RequestMapping(value="registDaily", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String registDaily(ReportVO report, HttpSession session, RedirectAttributes rttr)throws Exception{
		String url = "redirect:/project/report/list";
		
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		int prjNo = (int)session.getAttribute("currentPrjNo");
		int repNo = report.getRepNo();

		report.setRepRegister(loginUser.getUserId());
		report.setPrjNo(prjNo);
		
		
		String prjNm = projectService.getProject(prjNo).getPrjNm();
		String registerNm = userService.getUserNm(report.getRepRegister());
		PushVO push = new PushVO();		
		
		if(report.getSttCode().equals("temp") && report.getRepNo()<=0) {
			report.setRepNo(reportService.selectRepNoSeq());
			reportService.registDailyTempReport(report);
			rttr.addFlashAttribute("from", "registTempDailyReport");
			System.err.println("DAY 등록+임시저장");
			
		} else if(report.getSttCode().equals("temp") && report.getRepNo()>0) {
			reportService.modifyTempReport(report);
			rttr.addFlashAttribute("from", "modifyTempDailyReport");
			System.err.println("DAY 임시저장+임시저장");
			
		} else if (report.getSttCode().equals("del")) { // 상태코드가 del인 경우 삭제
			reportService.removeTempReport(report.getRepNo());
			rttr.addFlashAttribute("from", "removeDailyTempReport");
			System.err.println("DAY 임시저장+삭제");
			
		} else if(report.getSttCode().equals("send") && report.getRepNo()>0) {
			reportService.registTempReportToSendReport(report);
			
			System.err.println("DAY 임시저장+등록");
			repNo = report.getRepNo();
			
			// push 등록
			push.setFromWhere("업무보고");//안내문 title로 나오는 내용
			push.setReceiver(report.getApprover());//알림 받을 id
			push.setPrjNo(Integer.toString(prjNo));//알림 받을 id
			push.setMessage(prjNm+" 프로젝트의 "+registerNm+"님이 일일 업무보고를 등록했습니다.");
			push.setUrl("/project/report/detail.do?prjNo="+session.getAttribute("currentPrjNo")+"&repNo="+repNo+"&from=push");
			
			service.regist(push);
			
			rttr.addFlashAttribute("from", "registDailyReport");
			
//		} else if(report.getSttCode().equals("") && report.getRepNo()==0){
		} else {
			report.setRepNo(reportService.selectRepNoSeq());
			reportService.registDailyReport(report);
			
			repNo = report.getRepNo();
			
			// push 등록			
			push.setFromWhere("업무보고");//안내문 title로 나오는 내용
			push.setReceiver(report.getApprover());//알림 받을 id
			push.setPrjNo(Integer.toString(prjNo));//알림 받을 id
			push.setMessage(prjNm+" 프로젝트의 "+registerNm+"님이 일일 업무보고를 등록했습니다.");
			push.setUrl("/project/report/detail.do?prjNo="+session.getAttribute("currentPrjNo")+"&repNo="+repNo+"&from=push");
			service.regist(push);
			
			rttr.addFlashAttribute("from", "registDailyReport");
			System.err.println("등록+등록");
		}
		
		return url;
	}
	
	@RequestMapping(value="registMonthly", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String registMonthly(ReportVO report, HttpSession session, RedirectAttributes rttr)throws Exception{
		String url = "redirect:/project/report/list";
		
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		int prjNo = (int)session.getAttribute("currentPrjNo");
		int repNo = report.getRepNo();
		
		report.setRepRegister(loginUser.getUserId());
		report.setPrjNo(prjNo);
		report.setRepNo(repNo);
		
		String prjNm = projectService.getProject(prjNo).getPrjNm();
		String registerNm = userService.getUserNm(report.getRepRegister());
		PushVO push = new PushVO();
		
		if(report.getSttCode().equals("temp") && repNo<=0) {
			report.setRepNo(reportService.selectRepNoSeq());
			reportService.registMonthlyTempReport(report);
			rttr.addFlashAttribute("from", "registTempMonthlyReport");
			System.err.println("등록+임시저장");
			
		} else if(report.getSttCode().equals("temp") && report.getRepNo()>0) {
			reportService.modifyTempReport(report);
			rttr.addFlashAttribute("from", "modifyTempMonthlyReport");
			System.err.println("임시저장+임시저장");
			
		} else if (report.getSttCode().equals("del")) { // 상태코드가 del인 경우 삭제
			reportService.removeTempReport(repNo);
			rttr.addFlashAttribute("from", "removeMonthlyTempReport");
			System.err.println("MONTH 임시저장+삭제");
			
		} else if(report.getSttCode().equals("send") && report.getRepNo()>0) {
			reportService.registTempReportToSendReport(report);
			System.err.println("임시저장+등록");
			
			repNo = report.getRepNo();
			
			// push 등록
			push.setFromWhere("업무보고");//안내문 title로 나오는 내용
			push.setReceiver(report.getApprover());//알림 받을 id
			push.setPrjNo(Integer.toString(prjNo));//알림 받을 id
			push.setMessage(prjNm+" 프로젝트의 "+registerNm+"님이 월 업무보고를 등록했습니다.");
			push.setUrl("/project/report/detailm.do?prjNo="+session.getAttribute("currentPrjNo")+"&repNo="+repNo+"&from=push");
			service.regist(push);
			rttr.addFlashAttribute("from", "registDailyReport");
			
		} else {
			report.setRepNo(reportService.selectRepNoSeq());
			reportService.registMonthlyReport(report);
			
			repNo = report.getRepNo();
			// push 등록
			push.setFromWhere("업무보고");//안내문 title로 나오는 내용
			push.setReceiver(report.getApprover());//알림 받을 id
			push.setPrjNo(Integer.toString(prjNo));//알림 받을 id
			push.setMessage(prjNm+" 프로젝트의 "+registerNm+"님이 월 업무보고를 등록했습니다.");
			push.setUrl("/project/report/detailm.do?prjNo="+session.getAttribute("currentPrjNo")+"&repNo="+repNo+"&from=push");
			service.regist(push);
			
			rttr.addFlashAttribute("from", "registMonthReport");
			System.err.println("등록+등록");
		}
		
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(ModelAndView mnv, int repNo, HttpSession session, String from) throws Exception {
		String url = "project/report/detail";
		
		int prjNo = (int)session.getAttribute("currentPrjNo");
		
		if(from.equals("receive")) {
			reportService.modifyUnreadToRead(repNo);

			System.err.println("!@#!@#!@#!@#!@#!@# 실행");
		}
		
		ProjectVO prj = projectService.getProject(prjNo);
		
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		String userNm = loginUser.getUserNm();
		String prjNm = prj.getPrjNm(); 
		List<ReportVO> plIdList = reportService.getProjectPlList(prjNo);

		ReportVO report = new ReportVO();
		report.setPrjNo(prjNo);
		report.setUserId(userId);
		report.setRepNo(repNo);
		
		String plpm = reportService.loginUserIsPlOrPm(report);
		String teamNm = reportService.getMyTeamName(prjNo);
		report = reportService.getReportByRepNo(report);
		String approverNm = userService.getUserNm(report.getApprover());
		String registerNm = userService.getUserNm(report.getRepRegister());
		
		
		mnv.addObject("report",report);
		mnv.addObject("prjNm",prjNm);
		mnv.addObject("userNm",userNm);
		mnv.addObject("registerNm",registerNm);
		mnv.addObject("plpm",plpm);
		mnv.addObject("teamNm",teamNm);
		mnv.addObject("approverNm",approverNm);
		mnv.addObject("plIdList",plIdList);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/detailm")
	public ModelAndView detailm(ModelAndView mnv, int repNo, HttpSession session, String from) throws Exception {
		String url = "project/report/detailm";
		
		int prjNo = (int)session.getAttribute("currentPrjNo");
		
		if(from.equals("receive")) {
			reportService.modifyUnreadToRead(repNo);
		}
		
		ProjectVO prj = projectService.getProject(prjNo);
		
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		String userNm = loginUser.getUserNm();
		String prjNm = prj.getPrjNm(); 
		List<ReportVO> plIdList = reportService.getProjectPlList(prjNo);

		ReportVO report = new ReportVO();
		report.setPrjNo(prjNo);
		report.setUserId(userId);
		report.setRepNo(repNo);
		
		String plpm = reportService.loginUserIsPlOrPm(report);
		String teamNm = reportService.getMyTeamName(prjNo);
		report = reportService.getReportByRepNo(report);
		String approverNm = userService.getUserNm(report.getApprover());
		String registerNm = userService.getUserNm(report.getRepRegister());
		
		mnv.addObject("report",report);
		mnv.addObject("prjNm",prjNm);
		mnv.addObject("userNm",userNm);
		mnv.addObject("registerNm",registerNm);
		mnv.addObject("plpm",plpm);
		mnv.addObject("teamNm",teamNm);
		mnv.addObject("approverNm",approverNm);
		mnv.addObject("plIdList",plIdList);
		mnv.setViewName(url);
		
		return mnv;
	}
	
}
