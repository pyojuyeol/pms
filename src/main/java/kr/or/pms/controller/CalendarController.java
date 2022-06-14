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
import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ScheduleVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.CalendarService;
import kr.or.pms.service.HistoryService;

@Controller
@RequestMapping("/myPage")
public class CalendarController {
	
	@Resource(name="calendarService")
	private CalendarService service;
	
	@Resource(name="historyService")
	private HistoryService historyService;
	
	@RequestMapping("/main")
	public ModelAndView main(HttpSession session, ModelAndView mnv) throws Exception {
		
		String url = "myPage/main";
		UserVO user = (UserVO) session.getAttribute("loginUser");
		
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
		
		UserVO user = (UserVO) request.getSession().getAttribute("loginUser");
		String userId = user.getUserId();
		Map<String, Object> dataMap = service.selectCalendar(userId);
		@SuppressWarnings("unchecked")
		List<PrjUserVO> prjUserList = (List<PrjUserVO>) dataMap.get("prjUserList");
		
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
				data.put("url",request.getContextPath()+"/myPage/modifyName.open?schedNo="+vo.getSchedNo());
				data.put("backgroundColor","#8fc7cb");
				data.put("borderColor","#8fc7cb");
				
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
				data.put("url",request.getContextPath()+"/myPage/modifyName.open?schedNo="+vo.getSchedNo());
				data.put("backgroundColor","#52915e");
				data.put("borderColor","#52915e");
				
				dataList.add(data);
			}
		}
		if(prjUserList != null) {
			for(PrjUserVO list : prjUserList) {
				int prjNo = list.getPrjNo();
				if(mapJson.containsValue(Integer.toString(prjNo))) {
					
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
						data.put("url",request.getContextPath()+"/myPage/modifyName.open?schedNo="+vo.getSchedNo());
						data.put("backgroundColor","#eec44b");
						data.put("borderColor","#eec44b");
						
						dataList.add(data);
					}
					
					
//						Map<String, String> data = new HashMap<String, String>();
//						if(list.getEndDt() != null) {
//							cal.setTime(list.getEndDt());
//							cal.add(Calendar.DATE, 1); //1일 더하기
//						}
//						
//						data.put("title",list.getPrjNm());
//						data.put("start",simpleDateFormat.format(list.getBgnDt()));
//						data.put("end",simpleDateFormat.format(cal.getTime()));
////						data.put("url",request.getContextPath()+"/project/home.do?prjNo="+list.getPrjNo());
//						data.put("backgroundColor",list.getColor());
//						data.put("borderColor",list.getColor());
//						
//						dataList.add(data);
				}
			}
		}
		
		String json = new ObjectMapper().writeValueAsString(dataList);
		return json;
		
	}
	
	@RequestMapping("/registForm")
	public ModelAndView registForm(HttpServletRequest request, ModelAndView mnv) throws Exception {
		
		String url = "myPage/regist";
		
		UserVO user = (UserVO) request.getSession().getAttribute("loginUser");
		Map<String, Object> dataMap = service.selectCalendar(user.getUserId());
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String regist(ScheduleRegistCommand regCommand, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		String url = "redirect:/myPage/registForm.open";
		
		UserVO loginUser = (UserVO) request.getSession().getAttribute("loginUser");
		ScheduleVO schedule = regCommand.toScheduleVO();
		int schedNo = service.getSchedNo();
		schedule.setSchedNo(schedNo);
		
		service.insertSchedule(schedule);
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1")) && schedule.getPrjNo() !=0) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(schedule.getPrjNo());
			history.setFromWhere("schedule");
			history.setUrl("/myPage/detail.do?schedNo="+schedule.getSchedNo());
			history.setTitle(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 등록하였습니다.");
			history.setContent(schedule.getContent());
			historyService.regist(history);
		}
		
		rttr.addFlashAttribute("from", "regist");
		
		return url;
		
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int schedNo, String from, ModelAndView mnv,HttpSession session) throws Exception {
		
		String url = "myPage/detail";
		
		ScheduleVO schedule = service.selectScheduleBySchedNo(schedNo);
		
		UserVO user = (UserVO) session.getAttribute("loginUser");
		Map<String, Object> dataMap = service.selectCalendar(user.getUserId());
		
		
		mnv.addObject("dataMap", dataMap);
		mnv.addObject("schedule", schedule);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(ModelAndView mnv, int schedNo,HttpSession session) throws Exception {
		
		String url = "myPage/modify";
		
		UserVO user = (UserVO) session.getAttribute("loginUser");
		Map<String, Object> dataMap = service.selectCalendar(user.getUserId());
		
		
		ScheduleVO schedule = service.selectScheduleBySchedNo(schedNo);
		
		mnv.addObject("dataMap", dataMap);
		mnv.addObject("schedule", schedule);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/modifyName")
	public ModelAndView modifyName(ModelAndView mnv, int schedNo,HttpSession session) throws Exception {
		
		String url = "myPage/modifyName";
		
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
		
		String url = "myPage/modifyContentName";
		
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
		
		String url = "redirect:/myPage/delete.do";
		
		UserVO loginUser = (UserVO) request.getSession().getAttribute("loginUser");
		ScheduleVO schedule = modifyCommand.toScheduleVO();
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1")) && schedule.getPrjNo() !=0) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(schedule.getPrjNo());
			history.setFromWhere("schedule");
			history.setUrl("/myPage/calendar/detail.do?schedNo="+schedule.getSchedNo());
			history.setTitle(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 수정하였습니다.");
			history.setContent(schedule.getContent());
			historyService.regist(history);
		}
		
		service.modifySchedule(schedule);
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("schedNo", schedule.getSchedNo());
		
		return url;
		
	}
	
	@RequestMapping("/modifyTitle")
	public String modifyTitle(String schedNo,String title, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		String url = "myPage/modifySuccess";
		
		UserVO loginUser = (UserVO) request.getSession().getAttribute("loginUser");
		ScheduleVO schedule = service.selectScheduleBySchedNo(Integer.parseInt(schedNo));
		schedule.setTitle(title);
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1")) && schedule.getPrjNo() !=0) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(schedule.getPrjNo());
			history.setFromWhere("schedule");
			history.setUrl("/myPage/calendar/detail.do?schedNo="+schedule.getSchedNo());
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
	public String modifyContent(String schedNo,String content, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		String url = "myPage/modifySuccess";
		
		UserVO loginUser = (UserVO) request.getSession().getAttribute("loginUser");
		ScheduleVO schedule = service.selectScheduleBySchedNo(Integer.parseInt(schedNo));
		schedule.setContent(content);
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1")) && schedule.getPrjNo() !=0) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(schedule.getPrjNo());
			history.setFromWhere("schedule");
			history.setUrl("/myPage/calendar/detail.do?schedNo="+schedule.getSchedNo());
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
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1")) && schedule.getPrjNo() !=0) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(schedule.getPrjNo());
			history.setFromWhere("schedule");
			history.setUrl("/myPage/detail.do?schedNo="+schedule.getSchedNo());
			history.setTitle(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 수정하였습니다.");
			history.setContent(schedule.getContent());
			historyService.regist(history);
		}
		
		service.modifySchedule(schedule);
		
		String json = new ObjectMapper().writeValueAsString(jsonMap);
		return json;
		
	}
	
	@RequestMapping("/remove")
	public ModelAndView remove(int schedNo, RedirectAttributes rttr,HttpSession session, ModelAndView mnv) throws Exception {
		
		String url = "redirect:/myPage/main.do";
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		
		ScheduleVO schedule = service.selectScheduleBySchedNo(schedNo);
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		if(!((schedule.getKeyCode()).equals("2") && (schedule.getIpcrCode()).equals("1")) && schedule.getPrjNo() !=0) {
			history.setUserId(schedule.getRegister());
			history.setPrjNo(schedule.getPrjNo());
			history.setFromWhere("schedule");
			history.setUrl("/myPage/detail.do?schedNo="+schedule.getSchedNo());
			history.setTitle(loginUser.getUserNm()+" 님이 "+schedule.getTitle()+" 일정을 삭제하였습니다.");
			history.setContent(schedule.getContent());
			historyService.regist(history);
		}
		
		service.deleteSchedule(schedNo);
		
		rttr.addFlashAttribute("from", "remove");
		rttr.addAttribute("schedNo", schedNo);
			
//		mnv.addObject("schedNo", schedNo);
		mnv.setViewName(url);
		
		return mnv;
		
	}
}
