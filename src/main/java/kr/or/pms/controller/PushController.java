package kr.or.pms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.PushVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.ProjectService;
import kr.or.pms.service.PushService;

@Controller
@RequestMapping("/push")
public class PushController {
	
	@Resource(name="pushService")
	private PushService service;
	
	@Autowired
	private ProjectService projectService;
	
	
	/* regist 예시 */
	/*
	 * 알림 등록 해주세욥
	 * 
	 * 팀 - 내가 팀에 등록될 때, 우리 팀에 팀원 추가시. 
	 * 프로젝트 - 내가 프로젝트에 참여될 때, 내가 참여중인 프로젝트에 맴버 추가, 삭제시 
	 * 이슈 - 나를 참조한 이슈가 등록, 수정시. 나를 참조한 이슈에 댓글이 등록/수정시 
	 * 일감 - 내가 담당자인 일감이 등록/수정/삭제시. 내가 담당자인 일감에 댓글이 등록,수정,삭제시 
	 * 일정 - 내가 참여중인 프로젝트에 일정이 등록, 수정, 삭제될때
	 * 업무보고 - 나에게 업무보고 시 회의록 - 내가 승인해야 할 회의록 등록시 
	 * 공지사항 - 내가 참여중인 프로젝트 공지사항 등록시
	 */
	@RequestMapping(value = "/regist")
	public String regist(PushVO push) throws Exception {
		
		String url = "redirect:list.do";
		
		push.setFromWhere("TEAM");//안내문 title로 나오는 내용
		push.setReceiver("admin");//알림 받을 id
		push.setPrjNo("1");//알림 받을 id
		push.setMessage("운영자님이 admin님을 이슈에 참조하였습니다.");
		push.setUrl("/team/list.do");
		service.regist(push);
		
		return url;
		
	}
	
	
	@RequestMapping("/list")
	public ModelAndView getList(Criteria cri, ModelAndView mnv, HttpSession session) throws Exception {
		
		UserVO user = (UserVO)session.getAttribute("loginUser");

		String url = "push/list";
		String userId = user.getUserId();
		
		Map<String, Object> dataMap = service.getList(cri, userId);
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/getMenuPushList")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getMenuPushList(ModelAndView mnv, HttpSession session) throws Exception {
		
		UserVO user = (UserVO)session.getAttribute("loginUser");
		String userId = user.getUserId();

		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> dataMap = service.getMenuPushList(userId);
		
		entity = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		
		
		return entity;
		
	}
	
	
	@RequestMapping(value = "/modifyStatus", produces = "text/plain;charset=utf-8")
	public ResponseEntity<String>  modifyStatus(@RequestParam(defaultValue="") String pushNo, @RequestParam(defaultValue="") String prjNo, HttpSession session) throws Exception {
		
		ResponseEntity<String> entity = null;
		service.modifyReadCode(Integer.parseInt(pushNo));
		
		if(!prjNo.equals("")) {			
			int prjNoInt = Integer.parseInt(prjNo);
			ProjectVO project = projectService.getProject(prjNoInt);
			
			if(project!=null) {			
				session.setAttribute("currentPrjNo", prjNo);
				entity = new ResponseEntity<String>("success currentPrjNo: "+session.getAttribute("currentPrjNo"), HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		}
					
		
		return entity;
		
	}
	
	@RequestMapping(value = "/modifyReadCode", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> modifyReadCode(@RequestBody List<Integer> targetPushNoList) throws Exception {
		
		ResponseEntity<String> entity = null;
		try {
			
			service.modifyReadCode(targetPushNoList);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
		
	}
	
	/*
	 * @RequestMapping(value = "/modifyReadCode", produces =
	 * "text/plain;charset=utf-8")
	 * 
	 * @ResponseBody public ResponseEntity<String> modifyReadCode(@RequestBody
	 * List<Integer> targetPushNoList) throws Exception {
	 * 
	 * ResponseEntity<String> entity = null;
	 * 
	 * int result = service.modifyReadCode(targetPushNoList);
	 * 
	 * if (result > 0) { entity = new ResponseEntity<String>("success",
	 * HttpStatus.OK); } else { entity = new ResponseEntity<String>("",
	 * HttpStatus.OK); }
	 * 
	 * return entity;
	 * 
	 * }
	 */
	
	@RequestMapping(value = "/remove", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> remove(@RequestBody List<Integer> targetPushNoList) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		int result = service.remove(targetPushNoList);

		if (result > 0) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>("", HttpStatus.OK);
		}
		
		return entity;
		
	}
}
