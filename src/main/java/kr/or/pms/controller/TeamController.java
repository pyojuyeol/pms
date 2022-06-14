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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.TeamModifyCommand;
import kr.or.pms.command.TeamRegistCommand;
import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.TeamVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.ComCodeService;
import kr.or.pms.service.ProjectService;
import kr.or.pms.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private TeamService teamService;
	
	@Autowired
	private ComCodeService comCodeService;
	
	@Autowired
	private ProjectService projectService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Criteria cri, Model model) throws Exception {
		Map<String, Object> dataMap = teamService.getTeamList(cri);
		
		// 부서 코드
		List<ComCodeVO> positionList = comCodeService.getComCodeListByCodeGrp("C0002");
		model.addAttribute("positionList", positionList);
		
		model.addAttribute("dataMap", dataMap);
	}
	
	@RequestMapping(value = "/registForm", method = RequestMethod.GET)
	public void registForm() throws Exception {}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(TeamRegistCommand registCMD, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/team/list.do";
		if("1".equals( registCMD.getOpen() )) {
			url = "redirect:/team/registForm.open?open=1";
		}
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String register = loginUser.getUserId();
		
		// 팀 + 팀원 DB 저장
		TeamVO team = registCMD.toTeamVO();
		int teamNo = teamService.getSeqNextValue();
		team.setTeamNo(teamNo);
		team.setTeamNm((String)request.getAttribute("XSSteamNm"));
		teamService.regist(team, register);
		
		// Output
		rttr.addFlashAttribute("from", "regist");
		
		return url;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(int teamNo, String from, ModelAndView mnv) throws Exception {
		String url = "team/detail";
		
		TeamVO team = null;
		team = teamService.getTeam(teamNo);
		
		List<ProjectVO> teamPrjList = projectService.getTeamProjectListByTno(teamNo);
		List<ProjectVO> teamProceedingPrjList = projectService.getTeamProceedingProjectListByTno(teamNo);
		
		List<ProjectVO> teamCompletePrjList = projectService.getTeamCompleteProjectListByTno(teamNo);
		
		// 부서 코드
		List<ComCodeVO> positionList = comCodeService.getComCodeListByCodeGrp("C0002");
		mnv.addObject("positionList", positionList);
		
		mnv.addObject("teamPrjList", teamPrjList);
		mnv.addObject("teamComPrjList", teamCompletePrjList);
		mnv.addObject("team", team);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	public ModelAndView modifyForm(int teamNo, ModelAndView mnv, String open) throws Exception {
		String url = "team/modifyForm";
		
		
		TeamVO team = teamService.getTeam(teamNo);
		mnv.addObject("open",open);
		mnv.addObject("team", team);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, TeamModifyCommand modifyCMD, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/team/detail.do";
		if("1".equals( modifyCMD.getOpen() )) {
			url = "redirect:/team/detail.open";
		}
		
		// DB 저장
		TeamVO team = modifyCMD.toTeamVO();
		team.setTeamNm((String)request.getAttribute("XSSteamNm"));
		teamService.modify(team);
		
		// Output
		rttr.addAttribute("from", "modify");
		rttr.addAttribute("teamNo", team.getTeamNo());
		rttr.addFlashAttribute("from", "modify");
		return url;
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(int teamNo, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/team/list.do";
		
		// DB 삭제
		teamService.remove(teamNo);
		
		rttr.addAttribute("teamNo", teamNo);
		rttr.addFlashAttribute("from", "remove");
		
		return url;
	}
	
	
	
}
