package kr.or.pms.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.pms.command.ScheduleModifyCommand;
import kr.or.pms.command.ScheduleRegistCommand;
import kr.or.pms.dto.HistoryVO;
import kr.or.pms.dto.IssueVO;
import kr.or.pms.dto.PushVO;
import kr.or.pms.dto.ScheduleVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.CalendarService;
import kr.or.pms.service.HistoryService;
import kr.or.pms.service.IssueService;
import kr.or.pms.service.PushService;

@Controller
@RequestMapping("/project/calendar")
public class ProjectCalendarController {
	
	@Resource(name="calendarService")
	private CalendarService service;
	
	@Resource(name="historyService")
	private HistoryService historyService;
	
	@Resource(name="pushService")
	private PushService pushService;
	
	@Resource(name="issueService")
	private IssueService issueService;
	
	@RequestMapping("/list")
	public ModelAndView main(HttpSession session, ModelAndView mnv) throws Exception {
		
		String url = "project/calendar/list";
		UserVO user = (UserVO) session.getAttribute("loginUser");
		int prjNo = (int) session.getAttribute("currentPrjNo");
		
		IssueVO issue = issueService.getprjName(prjNo);
		mnv.addObject("prjNm", issue.getPrjNm());
		
		Map<String, Object> dataMap = service.selectCalendar(user.getUserId());
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping(value = "/check", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String check(@RequestBody Map<String, String> mapJson,HttpServletRequest request) throws Exception {
		
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		UserVO loginUser = (UserVO) request.getSession().getAttribute("loginUser");
		String userId = loginUser.getUserId();
		int prjNo = (int)request.getSession().getAttribute("currentPrjNo");
		
		Calendar cal = Calendar.getInstance(); 
		
		/*
		 *  title          : 'Click for Google',
            start          : new Date(y, m, 28),
            end            : new Date(y, m, 29),
            url            : 'modify.jsp',
            backgroundColor: '#3c8dbc', //Primary (light-blue)
            borderColor    : '#3c8dbc' //Primary (light-blue)
		 * */
		if(mapJson.containsValue("personal")) {
			List<ScheduleVO> scheduleList = service.selectPersonal(userId);
			for(ScheduleVO vo : scheduleList) {
				Map<String, String> data = new HashMap<String, String>();
				cal.setTime(vo.getEndDt());
				cal.add(Calendar.DATE, 1); //1일 더하기
				
				if(vo.getContent().contains("<p>")) {
					String content = vo.getContent().split("<p>")[1];
					String content1 = content.split("</p>")[0];
					data.put("description",content1);
				} else {
					data.put("description",vo.getContent());
				}
				data.put("id",vo.getRegister());
				data.put("title",vo.getTitle());
				data.put("start",simpleDateFormat.format(vo.getBgnDt()));
				data.put("end",simpleDateFormat.format(cal.getTime()));
				data.put("url",request.getContextPath()+"/project/calendar/modifyName.open?schedNo="+vo.getSchedNo());
				data.put("backgroundColor","#8fc7cb");
				data.put("borderColor","#8fc7cb");
				
				dataList.add(data);
			}
		}
		if(mapJson.containsValue("orderPersonal")) {
			ScheduleVO schedule = new ScheduleVO();
			schedule.setRegister(userId);
			schedule.setPrjNo(prjNo);
			List<ScheduleVO> scheduleList = service.selectOrderPersonal(schedule);
			for(ScheduleVO vo : scheduleList) {
				Map<String, String> data = new HashMap<String, String>();
				cal.setTime(vo.getEndDt());
				cal.add(Calendar.DATE, 1); //1일 더하기
				
				
				if(vo.getContent().contains("<p>")) {
					String content = vo.getContent().split("<p>")[1];
					String content1 = content.split("</p>")[0];
					data.put("description",content1);
				} else {
					data.put("description",vo.getContent());
				}
				data.put("id",vo.getRegister());
				data.put("title",vo.getTitle());
				data.put("start",simpleDateFormat.format(vo.getBgnDt()));
				data.put("end",simpleDateFormat.format(cal.getTime()));
//				data.put("url",request.getContextPath()+"/myPage/calendar/modifyName.open?schedNo="+vo.getSchedNo());
				data.put("backgroundColor","#e26b48");
				data.put("borderColor","#e26b48");
				
				dataList.add(data);
			}
		}
		if(mapJson.containsValue("company")) {
			List<ScheduleVO> scheduleList = service.selectCompany();
			for(ScheduleVO vo : scheduleList) {
				Map<String, String> data = new HashMap<String, String>();
				cal.setTime(vo.getEndDt());
				cal.add(Calendar.DATE, 1); //1일 더하기
				
				
				if(vo.getContent().contains("<p>")) {
					String content = vo.getContent().split("<p>")[1];
					String content1 = content.split("</p>")[0];
					data.put("description",content1);
				} else {
					data.put("description",vo.getContent());
				}
				data.put("id",vo.getRegister());
				data.put("title",vo.getTitle());
				data.put("start",simpleDateFormat.format(vo.getBgnDt()));
				data.put("end",simpleDateFormat.format(cal.getTime()));
				data.put("url",request.getContextPath()+"/project/calendar/modifyName.open?schedNo="+vo.getSchedNo());
				data.put("backgroundColor","#52915e");
				data.put("borderColor","#52915e");
				
				dataList.add(data);
			}
		}
		if(mapJson.containsValue("project")) {
			List<ScheduleVO> scheduleList = service.selectProject(prjNo);
			for(ScheduleVO vo : scheduleList) {
				Map<String, String> data = new HashMap<String, String>();
				cal.setTime(vo.getEndDt());
				cal.add(Calendar.DATE, 1); //1일 더하기
				
				
				if(vo.getContent().contains("<p>")) {
					String content = vo.getContent().split("<p>")[1];
					String content1 = content.split("</p>")[0];
					data.put("description",content1);
				} else {
					data.put("description",vo.getContent());
				}
				data.put("id",vo.getRegister());
				data.put("title",vo.getTitle());
				data.put("start",simpleDateFormat.format(vo.getBgnDt()));
				data.put("end",simpleDateFormat.format(vo.getEndDt()));
				data.put("url",request.getContextPath()+"/project/calendar/modifyName.open?schedNo="+vo.getSchedNo());
				data.put("backgroundColor","#eec44b");
				data.put("borderColor","#eec44b");
				
				dataList.add(data);
			}
		}
		
		String json = new ObjectMapper().writeValueAsString(dataList);
		return json;
		
	}
	
	@RequestMapping("/registForm")
	public String registForm() throws Exception {
		
		String url = "project/calendar/regist";
		return url;
		
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String regist(ScheduleRegistCommand regCommand, HttpServletRequest request, RedirectAttributes rttr,HttpSession session) throws Exception {
		
		String url = "redirect:/project/calendar/registForm.open";
		
		ScheduleVO schedule = regCommand.toScheduleVO();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		int prjNo = (int)session.getAttribute("currentPrjNo");
		
		int schedNo = service.getSchedNo();
		schedule.setSchedNo(schedNo);
		schedule.setPrjNo(prjNo);
		service.insertSchedule(schedule);
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1"))) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(prjNo);
			history.setFromWhere("schedule");
			history.setUrl("/project/calendar/detail.do?schedNo="+schedule.getSchedNo());
			history.setTitle(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 등록하였습니다.");
			history.setContent(schedule.getContent());
			historyService.regist(history);
		}
		
		// 알림 등록
		List<String> userList = service.selectPrjUserByPrjNo(prjNo);
		PushVO push = new PushVO();
		if(schedule.getKeyCode().equals("3")) {
			for(String user : userList) {
				push.setReceiver(user);
				push.setFromWhere("일정");
				push.setPrjNo(Integer.toString(prjNo));
				push.setUrl("/project/calendar/detail.do?schedNo="+schedule.getSchedNo()+"&prjNo="+prjNo);
				push.setMessage(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 등록하였습니다.");
				pushService.regist(push);
			}
		}
		rttr.addFlashAttribute("from", "regist");
		return url;
		
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int schedNo, String from, ModelAndView mnv) throws Exception {
		
		String url = "project/calendar/detail";
		
		ScheduleVO schedule = service.selectScheduleBySchedNo(schedNo);
		
		if(schedule == null) {
			mnv.addObject("message","삭제된 페이지 입니다.");
			url = "security/fail";
		} else {
			mnv.addObject("schedule", schedule);
		}
		
		mnv.setViewName(url);
		return mnv;
		
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(ModelAndView mnv, int schedNo) throws Exception {
		
		String url = "project/calendar/modify";
		
		ScheduleVO schedule = service.selectScheduleBySchedNo(schedNo);
		
		mnv.addObject("schedule", schedule);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/modifyName")
	public ModelAndView modifyName(ModelAndView mnv, int schedNo,HttpSession session) throws Exception {
		
		String url = "project/calendar/modifyName";
		
//		UserVO user = (UserVO) session.getAttribute("loginUser");
//		Map<String, Object> dataMap = service.selectCalendar(user.getUserId());
		
		
		
		ScheduleVO schedule = service.selectScheduleBySchedNo(schedNo);
//		
//		mnv.addObject("dataMap", dataMap);
		mnv.addObject("schedule", schedule);
		mnv.addObject("schedNo", schedNo);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/modifyContentName")
	public ModelAndView modifyContent(ModelAndView mnv, int schedNo,HttpSession session) throws Exception {
		
		String url = "project/calendar/modifyContentName";
		
//		UserVO user = (UserVO) session.getAttribute("loginUser");
//		Map<String, Object> dataMap = service.selectCalendar(user.getUserId());
		
		
		ScheduleVO schedule = service.selectScheduleBySchedNo(schedNo);
//		
//		mnv.addObject("dataMap", dataMap);
		mnv.addObject("schedule", schedule);
		mnv.addObject("schedNo", schedNo);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/modify")
	public String modifyPOST(ScheduleModifyCommand modifyCommand, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		String url = "redirect:/project/calendar/detail.do";
		UserVO loginUser = (UserVO) request.getSession().getAttribute("loginUser");
		ScheduleVO schedule = modifyCommand.toScheduleVO();
		
		int prjNo = (int)request.getSession().getAttribute("currentPrjNo");
		
		service.modifySchedule(schedule);
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1"))) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(prjNo);
			history.setFromWhere("schedule");
			history.setUrl("/project/calendar/detail.do?schedNo="+schedule.getSchedNo());
			history.setTitle(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 수정하였습니다.");
			history.setContent(schedule.getContent());
			historyService.regist(history);
		}
		
		// 알림 등록
		List<String> userList = service.selectPrjUserByPrjNo(prjNo);
		
		PushVO push = new PushVO();
		if(schedule.getKeyCode().equals("3")) {
			for(String user : userList) {
				push.setReceiver(user);
				push.setFromWhere("일정");
				push.setPrjNo(Integer.toString(prjNo));
				push.setUrl("/project/calendar/detail.do?schedNo="+schedule.getSchedNo()+"&prjNo="+prjNo);
				push.setMessage(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 수정하였습니다.");
				pushService.regist(push);
			}
		}
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("schedNo", schedule.getSchedNo());
		
		return url;
		
	}
	
	@RequestMapping("/modifyTitle")
	public String modifyTitle(String schedNo,String title, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		String url = "project/calendar/modifySuccess";
		
		UserVO loginUser = (UserVO) request.getSession().getAttribute("loginUser");
		ScheduleVO schedule = service.selectScheduleBySchedNo(Integer.parseInt(schedNo));
		schedule.setTitle(title);
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1")) && schedule.getPrjNo() !=0) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(schedule.getPrjNo());
			history.setFromWhere("schedule");
			history.setUrl("/project/calendar/detail.do?schedNo="+schedule.getSchedNo());
			history.setTitle(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 수정하였습니다.");
			history.setContent(schedule.getContent());
			historyService.regist(history);
		}
		
		service.modifySchedule(schedule);
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("schedNo", schedule.getSchedNo());
		
		return url;
		
	}
	
	@RequestMapping("/modifyContent")
	public String modifyContent(String schedNo,String content,String rangeDt, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		String url = "project/calendar/modifySuccess";
		
		UserVO loginUser = (UserVO) request.getSession().getAttribute("loginUser");
		ScheduleVO schedule = service.selectScheduleBySchedNo(Integer.parseInt(schedNo));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String[] ranges = rangeDt.split(" ~ ");
		String bgnDtStr = ranges[0];
		String endDtStr = ranges[1];
		Date bgnDate = formatter.parse(bgnDtStr);
		Date endDate = formatter.parse(endDtStr);
		schedule.setBgnDt(bgnDate);
		schedule.setEndDt(endDate);
		schedule.setContent(content);
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1")) && schedule.getPrjNo() !=0) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(schedule.getPrjNo());
			history.setFromWhere("schedule");
			history.setUrl("/project/calendar/detail.do?schedNo="+schedule.getSchedNo());
			history.setTitle(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 수정하였습니다.");
			history.setContent(schedule.getContent());
			historyService.regist(history);
		}
		
		service.modifySchedule(schedule);
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("schedNo", schedule.getSchedNo());
		
		return url;
		
	}
	
	@RequestMapping("/dragModify")
	@ResponseBody
	public String dragModifyPOST(@RequestBody Map<String, String> jsonMap, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		int prjNo = (int)request.getSession().getAttribute("currentPrjNo");
		UserVO loginUser = (UserVO) request.getSession().getAttribute("loginUser");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date bgnDate = simpleDateFormat.parse(jsonMap.get("start"));
		Date endDate = simpleDateFormat.parse(jsonMap.get("end"));
		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(endDate);
		cal.add(Calendar.DATE, -1); //1일 빼기
		
		String url = jsonMap.get("url");
		
		int schedNo = Integer.parseInt(url.split("=")[1]); 
		
		ScheduleVO schedule = service.selectScheduleBySchedNo(schedNo);
		schedule.setBgnDt(bgnDate);
		schedule.setEndDt(cal.getTime());
		
		service.modifySchedule(schedule);
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1"))) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(prjNo);
			history.setFromWhere("schedule");
			history.setUrl("/project/calendar/detail.do?schedNo="+schedule.getSchedNo());
			history.setTitle(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 수정하였습니다.");
			history.setContent(schedule.getContent());
			historyService.regist(history);
		}
		
		// 알림 등록
		List<String> userList = service.selectPrjUserByPrjNo(prjNo);
		
		PushVO push = new PushVO();
		if(schedule.getKeyCode().equals("3")) {
			for(String user : userList) {
				push.setReceiver(user);
				push.setFromWhere("일정");
				push.setPrjNo(Integer.toString(prjNo));
				push.setUrl("/project/calendar/detail.do?schedNo="+schedule.getSchedNo()+"&prjNo="+prjNo);
				push.setMessage(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 수정하였습니다.");
				pushService.regist(push);
			}
		}
		String json = new ObjectMapper().writeValueAsString(jsonMap);
		return json;
		
	}
	
	@RequestMapping("/remove")
	public ModelAndView remove(int schedNo, RedirectAttributes rttr, HttpSession session, ModelAndView mnv) throws Exception {
		
		int prjNo = (int)session.getAttribute("currentPrjNo");
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String url = "redirect:/project/calendar/list.do";
		
		ScheduleVO schedule = service.selectScheduleBySchedNo(schedNo);
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1"))) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(prjNo);
			history.setFromWhere("schedule");
			history.setUrl("/project/calendar/list.do?schedNo="+schedule.getSchedNo());
			history.setTitle(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 삭제하였습니다.");
			history.setContent(schedule.getContent());
			historyService.regist(history);
		}
		
		// 알림 등록
		List<String> userList = service.selectPrjUserByPrjNo(prjNo);
		
		PushVO push = new PushVO();
		if(schedule.getKeyCode().equals("3")) {
			for(String user : userList) {
				push.setReceiver(user);
				push.setFromWhere("일정");
				push.setPrjNo(Integer.toString(prjNo));
				push.setUrl("/project/calendar/detail.do?schedNo="+schedule.getSchedNo()+"&prjNo="+prjNo);
				push.setMessage(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 삭제하였습니다.");
				pushService.regist(push);
			}
		}
		
		service.deleteSchedule(schedNo);
		
		rttr.addFlashAttribute("from", "remove");
		rttr.addAttribute("schedNo", schedNo);
		
//		mnv.addObject("schedNo", schedNo);
		mnv.setViewName(url);
		
		return mnv; 
		
	}
}
