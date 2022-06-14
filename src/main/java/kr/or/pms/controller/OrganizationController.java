package kr.or.pms.controller;

import java.io.Console;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.pms.command.OrganizationCommand;
import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.TeamVO;
import kr.or.pms.dto.TmemVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.exception.InsufficientConditionException;
import kr.or.pms.service.ComCodeService;
import kr.or.pms.service.ProjectService;
import kr.or.pms.service.TeamService;
import kr.or.pms.service.UserService;
import kr.or.pms.util.MyUtil;


@Controller
public class OrganizationController {
	@Resource(name="comCodeService")
	private ComCodeService comCodeService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="teamService")
	private TeamService teamService;
	
	@Resource(name="projectService")
	private ProjectService projectService;
	
	@RequestMapping("/organization/list" )
	public void list(Model model) throws Exception {
	}
	
	@RequestMapping(value="/organization/list", method = RequestMethod.POST )
	public @ResponseBody ResponseEntity<Map<String,List<OrganizationCommand>>> listDepts(Model model) throws Exception {
		
		//리턴 타입 구성
		ResponseEntity<Map<String,List<OrganizationCommand>>> entity = null;
		//	상태
		HttpStatus status = null;
		//	source 
		Map<String,List<OrganizationCommand>> orgs= new HashMap<>();
		List<OrganizationCommand> orgDept= new ArrayList<>();
		List<OrganizationCommand> orgTeam= new ArrayList<>();
		List<OrganizationCommand> orgPrj= new ArrayList<>();
		orgs.put("orgDept",orgDept);
		orgs.put("orgTeam",orgTeam);
		orgs.put("orgPrj",orgPrj);
		
		// 서비스 호출 ( 부서,사용자 불러오기)
		List<ComCodeVO> depts = null; 
		List<UserVO> deptUserList = null;
		List<TeamVO> teams = null; 
		List<TmemVO> teamMemberList = new ArrayList<TmemVO>();
		try {
			depts = comCodeService.getComCodeListByCodeGrp("C0003");
			deptUserList = userService.getUserListActive();
			teams = teamService.getTeamList();
			//team에서 tmem 꺼내서 추가
			for (TeamVO team : teams) {
				List<TmemVO> tmemList = team.getTeamMemberList();
//				if(tmemList == null || tmemList.size() <= 0 ) {	
//					continue;
//				}
				teamMemberList.addAll(tmemList);
			}
		} catch (Exception e) {
			return new ResponseEntity<Map<String,List<OrganizationCommand>>>(orgs, HttpStatus.BAD_REQUEST);
		}
		// 사원를 조직도에 추가
		for (UserVO userVO : deptUserList) {
//			if("ROLE_ADMIN".equals(userVO.getAuthGrpCode()) ) {
//				continue;
//			}
			//orgCommand로 변환
			OrganizationCommand orgDTO = new OrganizationCommand(userVO);
			orgDept.add(orgDTO);
		}
		// 부서를 조직도에 추가
		for (ComCodeVO dept : depts) {
			//orgCommand로 변환
			OrganizationCommand orgDTO = new OrganizationCommand(dept,1);
			//체크된 자식이 있으면 체크
			orgDept.add(orgDTO);
		}
		// 팀원을 조직도에 추가
		int num = 0;
		for (TmemVO teamMember : teamMemberList) {
			OrganizationCommand orgDTO = new OrganizationCommand(teamMember,num);
			num++;
			orgTeam.add(orgDTO);
		}
		// 팀을 조직도에 추가
		for (TeamVO team : teams) {
			OrganizationCommand orgDTO = new OrganizationCommand(team);
			//체크된 자식이 있으면 체크
			orgTeam.add(orgDTO);
		}
		
		entity = new ResponseEntity<Map<String,List<OrganizationCommand>>>(orgs, HttpStatus.OK);
		
		return entity;
	}

	@RequestMapping(value="/orgExplorer/list", method = RequestMethod.GET )
	public String listExplorer(Model model, String name) throws Exception {
		String url = "/organization/listExplorer";
		model.addAttribute("name",name);
		return url;
	}
	
	@RequestMapping(value="/orgExplorer/list", method = RequestMethod.POST )
	@ResponseBody
	public ResponseEntity<Map<String,List<OrganizationCommand>>> listDepts(Model model, @RequestBody List<String> userCheckedList) throws Exception {
		
		Set<String> userCheckedSet = new HashSet<>(userCheckedList);
		//리턴 타입 구성
		ResponseEntity<Map<String,List<OrganizationCommand>>> entity = null;
		//	상태
		HttpStatus status = null;
		//	source 
		Map<String,List<OrganizationCommand>> orgs= new HashMap<>();
		List<OrganizationCommand> orgDept= new ArrayList<>();
		List<OrganizationCommand> orgTeam= new ArrayList<>();
		List<OrganizationCommand> orgPrj= new ArrayList<>();
		orgs.put("orgDept",orgDept);
		orgs.put("orgTeam",orgTeam);
		orgs.put("orgPrj",orgPrj);

		// 서비스 호출 ( 부서,사용자 불러오기)
		List<ComCodeVO> depts = null; 
		List<UserVO> deptUserList = null;
		List<TeamVO> teams = null; 
		List<TmemVO> teamMemberList = new ArrayList<TmemVO>();
		try {
			depts = comCodeService.getComCodeListByCodeGrp("C0003");
			deptUserList = userService.getUserListActive();
			teams = teamService.getTeamList();
			//team에서 tmem 꺼내서 추가
			for (TeamVO team : teams) {
				List<TmemVO> tmemList = team.getTeamMemberList();
//				if(tmemList == null || tmemList.size() <= 0 ) {	
//					continue;
//				}
				teamMemberList.addAll(tmemList);
			}
		} catch (Exception e) {
			return new ResponseEntity<Map<String,List<OrganizationCommand>>>(orgs, HttpStatus.BAD_REQUEST);
		}
		// 사원를 조직도에 추가
		Set<String> deptCheckedSet = new HashSet<>();
		for (UserVO userVO : deptUserList) {
//			if("ROLE_ADMIN".equals(userVO.getAuthGrpCode()) ) {
//				continue;
//			}
			//orgCommand로 변환
			OrganizationCommand orgDTO = new OrganizationCommand(userVO);
			orgDept.add(orgDTO);
			//체크리스트에 있으면 체크
			if( userCheckedSet.contains(userVO.getUserId()) ) {
				orgDTO.setChecked(true);
				deptCheckedSet.add( orgDTO.getpId() );
			}
		}
		// 부서를 조직도에 추가
		for (ComCodeVO dept : depts) {
			//orgCommand로 변환
			OrganizationCommand orgDTO = new OrganizationCommand(dept);
			//체크된 자식이 있으면 체크
			orgDept.add(orgDTO);
			if( deptCheckedSet.contains( orgDTO.getId()) ) {
				orgDTO.setChecked(true);
			}
		}
		// 팀원을 조직도에 추가
		Set<String> teamCheckedSet = new HashSet<>();
		int num = 0;
		for (TmemVO teamMember : teamMemberList) {
			OrganizationCommand orgDTO = new OrganizationCommand(teamMember,num);
			num++;
			orgTeam.add(orgDTO);
			if( userCheckedSet.contains(teamMember.getUserId()) ) {
				orgDTO.setChecked(true);
				teamCheckedSet.add( orgDTO.getpId() );
			}
		}
		// 팀을 조직도에 추가
		for (TeamVO team : teams) {
			OrganizationCommand orgDTO = new OrganizationCommand(team);
			//체크된 자식이 있으면 체크
			orgTeam.add(orgDTO);
			if( teamCheckedSet.contains( orgDTO.getId()) ) {
				orgDTO.setChecked(true);
			}
		}
		
		entity = new ResponseEntity<Map<String,List<OrganizationCommand>>>(orgs, HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value="/organization/registForm", method = RequestMethod.GET )
	public void registFormDept(Model model) throws Exception {
		
	}
	
	@RequestMapping(value="/organization/regist", method = RequestMethod.POST )
	public String registDept(Model model, String deptNm,RedirectAttributes rttr) throws Exception {
		//파라미터 구성
		String url = "redirect:/organization/registForm.open";

		//서비스 실행
		comCodeService.registDept(deptNm);
		
		rttr.addFlashAttribute("from", "regist");
		
		return url;
	}
	
	//부서명 변경
	@RequestMapping(value="/organization/check", method = RequestMethod.GET )
	@ResponseBody
	public ResponseEntity<String> checkDept(Model model, String name ) throws Exception {
		//파라미터 선언
		String deptName = name;
		//헤더 설정
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "text", Charset.forName("UTF-8")));

		//유효성 검사
		if (deptName == null) {
			return new ResponseEntity<String>("부서명이 입력되지 않았습니다.",header, HttpStatus.BAD_REQUEST);
		}
		if (deptName.equals("")) {
			return new ResponseEntity<String>("사용할수 없는 부서명 입니다.",header, HttpStatus.BAD_REQUEST);
		}
		
		//서비스 호출 - 중복체크
		if( comCodeService.checkDept(deptName) > 0) {
			return new ResponseEntity<String>("해당 부서명은 이미 존재합니다.",header, HttpStatus.BAD_REQUEST);
		}
		

		return new ResponseEntity<String>("사용가능한 부서이름",header ,HttpStatus.OK);
	}
	
	@RequestMapping(value="/organization/modify", method = RequestMethod.GET )
	@ResponseBody
	public ResponseEntity<String> modifyDept(Model model,  String name, String code) throws Exception {
		//헤더 설정
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "text", Charset.forName("UTF-8")));
		//파라미터 구성
		String deptName = name; 
		String deptCode = code; 
		
		//파라미터 검증
		if(deptName == null || deptCode == null) {
			return new ResponseEntity<String>("서버 에러.", header, HttpStatus.BAD_REQUEST );
		}
		if (deptName.equals("")) {
			return new ResponseEntity<String>("사용할수 없는 부서명 입니다.",header, HttpStatus.BAD_REQUEST);
		}
		//서비스 호출 - 중복체크
		if( comCodeService.checkDept(deptName) > 0) {
			return new ResponseEntity<String>("해당 부서명은 이미 존재합니다.",header, HttpStatus.BAD_REQUEST);
		}
			
		//서비스 호출 - 수정 실행
		ComCodeVO deptVO = new ComCodeVO();
		deptVO.setNm(deptName);
		deptVO.setCode(deptCode);
		try {
			comCodeService.modifyDept(deptVO);
		} catch (Exception e) {
			return new ResponseEntity<String>("서버 장애", header, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("정상 처리되었습니다.", header, HttpStatus.OK );
	}
	
	@RequestMapping(value="/organization/remove", method = RequestMethod.GET )
	@ResponseBody
	public ResponseEntity<String> removeDept(Model model, String code) throws Exception {
		//헤더 설정
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "text", Charset.forName("UTF-8")));
		//파라미터 구성
		String deptCode = code; 
		
		//파라미터 검증
		if(deptCode == null || deptCode.equals("")) {
			return new ResponseEntity<String>("서버 에러.", header, HttpStatus.BAD_REQUEST );
		}
			
		//서비스 호출 - 수정 실행
		try {
			comCodeService.removeDept(deptCode);
		} catch (InsufficientConditionException e) {
			return new ResponseEntity<String>(e.getMessage(), header, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<String>("서버장에로 인해 실패했습니다.", header, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("정상 처리되었습니다.", header, HttpStatus.OK );
	}
	
}
