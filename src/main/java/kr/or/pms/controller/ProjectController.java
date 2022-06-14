package kr.or.pms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.pms.command.ProjectCriteria;
import kr.or.pms.command.ProjectModifyCommand;
import kr.or.pms.command.ProjectRegistCommand;
import kr.or.pms.command.WorkCriteria;
import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.IssueVO;
import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.TeamVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.dto.WorkVO;
import kr.or.pms.mail.MailMessageCommand;
import kr.or.pms.mail.MimeAttachNotifier;
import kr.or.pms.service.ComCodeService;
import kr.or.pms.service.HtmlTempService;
import kr.or.pms.service.IssueService;
import kr.or.pms.service.NoticeService;
import kr.or.pms.service.PrjUserService;
import kr.or.pms.service.ProjectService;
import kr.or.pms.service.TeamService;
import kr.or.pms.service.UserService;
import kr.or.pms.service.WorkService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService prjService;

	@Autowired
	private TeamService teamService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private HtmlTempService htmlTempService;
	
	@Autowired
	private MimeAttachNotifier notifier;
	
	@Autowired
	private PrjUserService prjUserService;

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private ComCodeService comCodeService;
	
	@Resource(name="issueService")
	private IssueService issueService;
	
	
	@RequestMapping(value = "/project/home", method = RequestMethod.GET)
	public void home(HttpServletRequest request, WorkCriteria cri, String prjNo, Model model) throws Exception {
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		
		ProjectVO prj = null;
		int workTotalCount = 0;
		int unresolvedIssueCnt = 0;
		int newNoticeCount = 0;
		List<WorkVO> myWorkList = null;
		List<Integer> sttCntList = null;
		List<IssueVO> issueList = null;
		
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		if (prjNo == null) {
			int prjInt = (int) session.getAttribute("currentPrjNo");
			prj = prjService.getProject(prjInt);
			workTotalCount = workService.getWorkListTotalCountByPno(cri, prjInt);
			map.put("prjNo", prjInt+"");
			myWorkList = workService.getWorkListProjectHomeById(map);
			unresolvedIssueCnt = issueService.countUnresolvedIssue(prjInt);
			newNoticeCount = noticeService.getNewNoticeCount(prjInt);
			sttCntList = workService.getSttCodeWorkCountListByPno(prjInt);
			issueList = issueService.getProjectHomeIssueByPno(prjInt);
		} else {
			// currentPrjNo 세션 업데이트
			int currentPrjNo = Integer.parseInt(prjNo);
			session.setAttribute("currentPrjNo", currentPrjNo);
			int prjInt = (int) session.getAttribute("currentPrjNo");
			prj = prjService.getProject(prjInt);
			workTotalCount = workService.getWorkListTotalCountByPno(cri, prjInt);
			map.put("prjNo", prjInt+"");
			myWorkList = workService.getWorkListProjectHomeById(map);
			unresolvedIssueCnt = issueService.countUnresolvedIssue(prjInt);
			newNoticeCount = noticeService.getNewNoticeCount(prjInt);
			sttCntList = workService.getSttCodeWorkCountListByPno(prjInt);
			issueList = issueService.getProjectHomeIssueByPno(prjInt);
		}
		
		model.addAttribute("prj", prj);
		model.addAttribute("myWorkList", myWorkList);
		model.addAttribute("workTotalCount", workTotalCount);
		model.addAttribute("unresolvedIssueCnt", unresolvedIssueCnt);
		model.addAttribute("newNoticeCount", newNoticeCount);
		model.addAttribute("sttCntList", sttCntList);
		model.addAttribute("issueList", issueList);
	}
	
	@RequestMapping(value = "/prjSetting/list", method = RequestMethod.GET)
	public void list(ProjectCriteria cri, Model model) throws Exception {
		Map<String, Object> dataMap = prjService.getProjectList(cri);
		
		List<ComCodeVO> prjRoleList = comCodeService.getComCodeListByCodeGrp("PRJROLE");
		model.addAttribute("prjRoleList", prjRoleList);
		
		model.addAttribute("dataMap", dataMap);
	}
	
	@RequestMapping(value = "/prjSetting/registForm", method = RequestMethod.GET)
	public void registForm(HttpServletRequest request, Model model) throws Exception {
		
//		HttpSession session = request.getSession();
//		int prjInt = (int) session.getAttribute("currentPrjNo");
		
		// 팀 정보
		List<TeamVO> teamList = teamService.getTeamList();
				
		model.addAttribute("teamList", teamList);
		
	}
	
	@RequestMapping(value = "/prjSetting/regist", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> regist(@RequestBody ProjectRegistCommand registCMD, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		
		ResponseEntity<String> entity = null;

		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String register = loginUser.getUserId();
		
		// 프로젝트 DB 저장
		int prjNo = prjService.getSeqNextValue();
		ProjectVO prj = registCMD.toProjectVO();
		prj.setPrjNo(prjNo);
//		prj.setPrjNm((String)request.getAttribute("XSSprjNm"));
		prjService.regist(prj, register);
		
		// 이메일 발송
		prjInvitationMail(prj);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		// Output
		rttr.addFlashAttribute("from", "regist");
		
		return entity;
	}

	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void detail() throws Exception {}
	
	
	@RequestMapping(value = "/prjSetting/modifyForm", method = RequestMethod.GET)
	public ModelAndView modifyForm(int prjNo, ModelAndView mnv) throws Exception {
		String url = "prjSetting/modifyForm";
		
		// 프로젝트 정보
		ProjectVO prj = prjService.getProject(prjNo);
		
		// 팀 정보
		List<TeamVO> teamList = teamService.getTeamList();
				
		List<ComCodeVO> prjRoleList = comCodeService.getComCodeListByCodeGrp("PRJROLE");
		mnv.addObject("prjRoleList", prjRoleList);
		
		mnv.addObject("teamList", teamList);
		mnv.addObject("prj", prj);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/prjSetting/modify", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> modify(@RequestBody ProjectModifyCommand modifyCMD, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		// DB 저장
		ProjectVO prj = modifyCMD.toProjectVO();
//		prj.setPrjNm((String)request.getAttribute("XSSprjNm"));
		
		prjService.modify(prj);
		
		
		// 이메일 발송
//		prjInvitationMail(prj);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		// Output
		rttr.addFlashAttribute("from", "modify");
		
		return entity;
	}
	
	@RequestMapping(value = "/prjSetting/remove", method = RequestMethod.GET)
	public String remove(int prjNo, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/prjSetting/list.do";
		
		// DB 삭제
		prjService.remove(prjNo);
		
		rttr.addFlashAttribute("from", "remove");
		
		return url;
	}
	
	// 프로젝트 초대 이메일 발송 메서드
	public void prjInvitationMail(ProjectVO prj) throws Exception {
		MailMessageCommand mailReq = new MailMessageCommand();
		
		List<PrjUserVO> addPrjUserList = prj.getAddPrjUsers();
		
		for (PrjUserVO addPrjUser : addPrjUserList) {
			String userId = addPrjUser.getUserId();
			String email = userService.getUserEmailById(userId);
			
			List<String> paramList = new ArrayList<String>();
			paramList.add(prj.getPrjNm());
			paramList.add("http://localhost/pms/prjSetting/invitation?prjNo=" + prj.getPrjNo() + "&userId=" + userId);

			//TEMP 가져오기 헤더푸터의 tempNm, 컨테이너 tempNm, 파라미터 리스트 순
			String htmlStr = htmlTempService.getHtmlStr("basic", "joinProject", paramList);
			
			mailReq.setReceiver(email);
			mailReq.setTitle("프로젝트 초대 확인");
			mailReq.setContent(htmlStr);
			notifier.sendMail(mailReq);
		}
	}
	
	// 프로젝트 알림 또는 이메일 확인 버튼 클릭 시 연결.
	@RequestMapping(value = "/prjSetting/invitation")
	public String JoinCheck(HttpServletRequest request, int prjNo, RedirectAttributes rttr, String userId) throws Exception {
		String url = "redirect:/project/home.do";
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		PrjUserVO prjUser = new PrjUserVO();
		if (userId != null && userId != "") {
			prjUser.setUserId(userId);
			prjUser.setPrjNo(prjNo);
		} else {
			prjUser.setUserId(loginUser.getUserId());
			prjUser.setPrjNo(prjNo);
		}
		
		prjUserService.enabled(prjUser);
		
		rttr.addAttribute("prjNo", prjNo);
		rttr.addFlashAttribute("from", "invitation");
		
		return url;
	}
}
