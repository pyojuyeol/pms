package kr.or.pms.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.pms.command.HistoryCriteria;
import kr.or.pms.dto.HistoryVO;
import kr.or.pms.dto.IssueVO;
import kr.or.pms.dto.ScheduleVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.HistoryService;
import kr.or.pms.service.IssueService;

@Controller
@RequestMapping("/project/history")
public class HistoryController {
	
	@Resource(name="historyService")
	private HistoryService service;
	
	@Resource(name="issueService")
	private IssueService issueService;
	
	@RequestMapping("/list")
	public ModelAndView list(HistoryCriteria cri, ModelAndView mnv, HttpSession session) throws Exception {
		
		String url = "project/history/list";
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		
		IssueVO issue = issueService.getprjName(prjNo);
		mnv.addObject("prjNm", issue.getPrjNm());
		
		cri.setPrjNo(prjNo);
		Map<String, Object> dataMap = service.getList(cri);
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/callList")
	@ResponseBody
	public String callList(@RequestBody Map<String, String> numJson, HistoryCriteria cri, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		
		int pageNum = Integer.parseInt(numJson.get("num"));
		String category = numJson.get("category");
		String searchType = numJson.get("searchType");
		String keyword = numJson.get("keyword");
		
		int prjNo = (int) request.getSession().getAttribute("currentPrjNo");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
		
		cri.setPage(pageNum);
		cri.setPrjNo(prjNo);
		cri.setCategory(category);
		cri.setSearchType(searchType);
		cri.setKeyword(keyword);
		Map<String, Object> dataMap = service.getList(cri);
		
		@SuppressWarnings("unchecked")
		List<HistoryVO> historyList = (List<HistoryVO>) dataMap.get("historyList");
		String date = "";				
		for(HistoryVO vo : historyList) {
			
			if(date.equals(simpleDateFormat.format(vo.getRegDt()))) {
				vo.setRegDtTime(simpleTimeFormat.format(vo.getRegDt()));
			} else {
				vo.setRegDtStr(simpleDateFormat.format(vo.getRegDt())); 
				vo.setRegDtTime(simpleTimeFormat.format(vo.getRegDt()));
			}
			
			date = simpleDateFormat.format(vo.getRegDt());
		}
		
		String json = new ObjectMapper().writeValueAsString(historyList);
		return json;
		
	}
	
	
	
}
