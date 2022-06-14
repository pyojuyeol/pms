package kr.or.pms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.pms.command.IssueCriteria;
import kr.or.pms.dto.BookMarkVO;
import kr.or.pms.dto.IssueVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.BookMarkService;
import kr.or.pms.service.HistoryService;
import kr.or.pms.service.IssueService;
import kr.or.pms.service.ProjectService;
import kr.or.pms.service.UserService;

@Controller
@RequestMapping("/project/bookmark")
public class BookMarkController {
	
	@Resource(name="bookMarkService")
	private BookMarkService service;
	
	@Resource(name="issueService")
	private IssueService issueService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="projectService")
	private ProjectService projectService;
	
	@Resource(name="historyService")
	private HistoryService historyService;
	
//	@RequestMapping("/list")
//	public void list() {};
	
	
	@RequestMapping("/list")
	public ModelAndView list(IssueCriteria cri, ModelAndView mnv, HttpSession session) throws Exception{
		
		String url="project/bookmark/list";
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		cri.setPrjNo(prjNo);
		
		Map<String, Object> dataMap = service.getAllBMKList(cri);
		
		IssueVO issue = issueService.getprjName(prjNo);
		
		List<BookMarkVO> bmkList = (List<BookMarkVO>) dataMap.get("bmkList");
		
		int bmkNo = 0;
		int totalIssueCnt = 0;
//		int totalOpenedIssueCnt = 0;
		int totalClosedIssueCnt = 0;
		if(bmkList!=null) {
			for (BookMarkVO bmk : bmkList) {
				IssueVO issueCntVO = new IssueVO();
				bmkNo = bmk.getBookmarkNo();
				issueCntVO.setBookmarkNo(bmkNo);
				issueCntVO.setPrjNo(prjNo);
				
				totalIssueCnt = service.countTotalIssue(issueCntVO);
				totalClosedIssueCnt = service.countTotalClosedIssue(issueCntVO);
				bmk.setTotalIssueCnt(totalIssueCnt);  
				bmk.setTotalClosedIssueCnt(totalClosedIssueCnt);  
				bmk.setTotalOpenedIssueCnt(totalIssueCnt-totalClosedIssueCnt);
				if(totalIssueCnt>0) {
					Double progress = (totalClosedIssueCnt*1.0D/totalIssueCnt*1.0D)*100.0;
					bmk.setProgress((int)Math.round(progress));
				} else {
					bmk.setProgress(0);
				}
				
			}
			
		}
		dataMap.put("bmkList", bmkList);
		
		String prjNm=issue.getPrjNm();

		
		
		mnv.addObject("prjNo", prjNo);
		mnv.addObject("dataMap",dataMap);
		mnv.addObject("prjNm",prjNm);
		mnv.setViewName(url);
		return mnv;
	}
	
	
	@RequestMapping(value="/addIntoBmk", produces = "text/plain;charset-utf-8")
	@ResponseBody
	public ResponseEntity<String> addIssueIntoBookMark(@RequestBody Map<String,Object>map, HttpSession session) throws Exception{
		ResponseEntity<String> entity = null;
		
		int bmkNo = 0;
//		System.out.println(">>>>>>>>>>>>>>"+map.get("bmkNo"));
		if(!map.get("bmkNo").equals("")) {
			bmkNo = Integer.parseInt((String) map.get("bmkNo"));
			
			@SuppressWarnings("unchecked")
			List<Integer> issueNoList = (List<Integer>) map.get("issueNoList");
			
			Map<String, Object> bmk = new HashedMap<String, Object>();
			
			if(issueNoList!=null) {
				for (int i = 0; i < issueNoList.size(); i++) {
					bmk.put("bookmarkNo", bmkNo);
					bmk.put("issueNo", issueNoList.get(i));
					service.addIssueIntoBMK(bmk);
				}
			}
		}
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> remove(int bookmarkNo, HttpSession session) throws Exception {
		ResponseEntity<String> entity = null;
		
		//int bmkNo=Integer.parseInt(bookmarkNo);
		
		// 해당 북마크 번호가 있는 이슈 북마크 번호 리셋
		int prjNo = (int)session.getAttribute("currentPrjNo");
		
		// issueList 뿌리기
		Map<String, Integer>issue = new HashedMap<String, Integer>();
		
		issue.put("bookmarkNo", bookmarkNo);
		issue.put("prjNo", prjNo);
		
		List<IssueVO>issueList = service.getIssueListByBmkNo(issue);
		
		
		if(issueList!=null) {
			for (int i = 0; i < issueList.size(); i++) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+issueList.get(i).getBookmarkNo());
				service.excludeIssueIntoBMK(bookmarkNo);
				System.out.println("#######################");
			}
		}
		
		System.out.println("??????????????");
		
		// 북마크 삭제
		service.removeBMK(bookmarkNo);
		
		new ResponseEntity<String>("success",HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value="/modifyStt/close", produces = "text/plain;charset-utf-8")
	@ResponseBody
	public ResponseEntity<String> modifyIssueSttToClose(@RequestBody List<Integer>bmkNoList) throws Exception{
		ResponseEntity<String> entity = null;
		
		int result = 0;
		for (int i = 0; i < bmkNoList.size(); i++) {
			service.closeBMK(bmkNoList.get(i));
			result += 1;
		}
		
		System.out.println("close ===========================================>");

		if(result > 0) {
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>(HttpStatus.OK);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/modifyStt/open", produces = "text/plain;charset-utf-8")
	@ResponseBody
	public ResponseEntity<String> modifyIssueSttToOpen(@RequestBody List<Integer>bmkNoList) throws Exception{
		ResponseEntity<String> entity = null;
		int result = 0;
		for (int i = 0; i < bmkNoList.size(); i++) {
			service.openBMK(bmkNoList.get(i));
			result += 1;
		}
		System.out.println("open ===========================================>");

		if(result > 0) {
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>(HttpStatus.OK);
		}
		
		return entity;
	}
	
	
	@RequestMapping("/registForm")
	public ModelAndView registForm(ModelAndView mnv, HttpSession session) throws Exception {
		String url = "/project/bookmark/registForm";
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		ProjectVO prj = projectService.getProject(prjNo);
		String prjNm = prj.getPrjNm();
		
		mnv.addObject("prjNm",prjNm);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value="/regist",method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ModelAndView regist(BookMarkVO bmk, HttpSession session,RedirectAttributes rttr, ModelAndView mnv) throws Exception{
		String url = "redirect:/project/bookmark/list";
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		UserVO user = (UserVO)session.getAttribute("loginUser");
		String creator = user.getUserId();
		

		bmk.setPrjNo(prjNo);
		bmk.setCreator(creator);
		
		service.registBMK(bmk);
		rttr.addFlashAttribute("from","regist");
		
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping("/detail")
	public ModelAndView datail(ModelAndView mnv, int bookmarkNo, HttpSession session) throws Exception {
		String url = "/project/bookmark/detail";
		int prjNo = (int)session.getAttribute("currentPrjNo");
		
		ProjectVO prj = projectService.getProject(prjNo);
		String prjNm = prj.getPrjNm();
		
		
		// bookmark detail
		IssueVO issueCntVO = new IssueVO();
		issueCntVO.setBookmarkNo(bookmarkNo);
		issueCntVO.setPrjNo(prjNo);
		
		int totalIssueCnt = service.countTotalIssue(issueCntVO);
		int totalClosedIssueCnt = service.countTotalClosedIssue(issueCntVO);

		BookMarkVO bmk = new BookMarkVO(); 
		bmk = service.getBMKDetailBmkNo(bookmarkNo);

		bmk.setTotalIssueCnt(totalIssueCnt);  
		bmk.setTotalClosedIssueCnt(totalClosedIssueCnt);  
		bmk.setTotalOpenedIssueCnt(totalIssueCnt-totalClosedIssueCnt);
		if(totalIssueCnt>0) {
			Double progress = (totalClosedIssueCnt*1.0D/totalIssueCnt*1.0D)*100.0;
			bmk.setProgress((int)Math.round(progress));
		} else {
			bmk.setProgress(0);
		}
		
		// issueList 뿌리기
		Map<String, Integer>issue = new HashedMap<String, Integer>();
		
		issue.put("bookmarkNo", bookmarkNo);
		issue.put("prjNo", prjNo);
		
		List<IssueVO>issueList = service.getIssueListByBmkNo(issue);
				
		mnv.addObject("bookmark",bmk);
		mnv.addObject("issueList",issueList);
		mnv.addObject("prjNm",prjNm);
		mnv.setViewName(url);
		
		return mnv;
	};
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(ModelAndView mnv,int bookmarkNo) throws Exception{
		String url = "/project/bookmark/modify";
		
		BookMarkVO bmk = service.getBMKDetailBmkNo(bookmarkNo);
		
		mnv.addObject("bmk",bmk);
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="/modify",method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ModelAndView modify(BookMarkVO bmk, int bookmarkNo,RedirectAttributes rttr, ModelAndView mnv) throws Exception{
		
		String url = "redirect:/project/bookmark/list.do";
		
		service.modifyBMK(bmk);
		
		rttr.addFlashAttribute("from","modify");
		
		mnv.setViewName(url);
		
		return mnv;
	}
	
}
