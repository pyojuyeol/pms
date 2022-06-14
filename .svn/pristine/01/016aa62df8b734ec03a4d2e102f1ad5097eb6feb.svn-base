package kr.or.pms.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.pms.command.GanttCommand;
import kr.or.pms.dto.ComCodeVO;
import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.WorkVO;
import kr.or.pms.service.ComCodeService;
import kr.or.pms.service.ProjectService;
import kr.or.pms.service.UserService;
import kr.or.pms.service.WorkService;

@Controller
@RequestMapping("/project")
public class GanttController {
	
	@Autowired
	private ComCodeService comCodeService;

	@Autowired
	private ProjectService prjService;
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/gantt/list")
	public String list(HttpSession session, Model model) throws Exception {
		String url = "/project/gantt/list";
		Object prjNoObj = session.getAttribute("currentPrjNo");
//		if(prjNoObj == null) {
//			throw new ProjectUnselectedException();
//		}
		int prjNo = (int) prjNoObj;
		
//		Map<String, Object> dataMap = workService.getWorkListCriteriaByPno(cri, prjNo);
		//현재 프로젝트 정보 가져오기
		ProjectVO prj = prjService.getProject(prjNo);
		//프로젝트 참여중인 유저 가져와서 유저이름 넣기
		List<PrjUserVO> prjUserList = prjService.getProjectUserList(prjNo);
		for (PrjUserVO prjUser : prjUserList) {
			String userId = prjUser.getUserId();
			String userNm = userService.getUserNm( userId );
			prjUser.setUserNm( userNm + "(" + userId.substring(0,2) + "***)" );
		}
		//셀렉트 표현용 공통코드 가져오기
		List<ComCodeVO> workSttList = comCodeService.getComCodeListByCodeGrp("WORKSTT");
		List<ComCodeVO> ipcrList = comCodeService.getComCodeListByCodeGrp("IPCR");
		
//		model.addAttribute("dataMap", dataMap);
		//출력
		model.addAttribute("prj", prj);
		model.addAttribute("prjUserList", prjUserList);
		model.addAttribute("workSttList", workSttList);
		model.addAttribute("ipcrList", ipcrList);

		return url;
	}
	
	@RequestMapping(value="/gantt/list", method = RequestMethod.POST)
	public ResponseEntity<List<GanttCommand>> loadWork(HttpServletRequest request, WorkVO workVO) throws Exception {
// 세션에서 프로젝트 번호 가져오기
		HttpSession session = request.getSession();
		int prjNo = (int) session.getAttribute("currentPrjNo");
		ProjectVO prj = prjService.getProject(prjNo);
		
		//검색 조건에 현재 프로젝트 번호 값 설정
		workVO.setPrjNo(prjNo);
		// 간트 객체 리스트
		List<GanttCommand> ganttList = new ArrayList<GanttCommand>();
//		// 검색조건으로 일감 가져오기
		List<WorkVO> workList = workService.getWorkListFromGantt(workVO);
		List<WorkVO> WorkListAll = workService.getWorkListByPno(prjNo);
		// 중요도 공통코드
		List<ComCodeVO> ipcrList = comCodeService.getComCodeListByCodeGrp("IPCR");
		List<ComCodeVO> workSttList = comCodeService.getComCodeListByCodeGrp("WORKSTT");
		
		// 부모 일감 set(프로젝트 기준) 만들기
		Set<Integer> getUpWorkSet = new HashSet<>();
		for(WorkVO work : WorkListAll) {
			getUpWorkSet.add(work.getUpWork());
		}
		// 프로젝트 일정 -> 간트 표현용 객체 변환 -> 간트 객체 리스트에 추가
		GanttCommand prjGantt = new GanttCommand();
		ganttList.add(prjGantt);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		prjGantt.setpName( "<a href='"+request.getContextPath()+"/project/home.do'>"+prj.getPrjNm()+"</a> ");
		prjGantt.setpName( "<a class='projectName' href='"+request.getContextPath()+"/project/home.do'>"+prj.getPrjNm()+"</a> ");
		prjGantt.setpID(-1);
		prjGantt.setpStart(dateFormat.format( prj.getBgnDt() ) );
		prjGantt.setpEnd(dateFormat.format( prj.getEndDt() ) );
		prjGantt.setpClass("ggroupblack");
		// 일감VO -> 간트 표현용 객체 변환 -> 간트 객체 리스트에 추가
		for(WorkVO work : workList) {
			//아이디로 이름 값 넣기
			String userNm = userService.getUserNm( work.getWorker() );
			work.setUserNm(userNm);
			//일감이 자식일감을 가지고 있으면
//			if( getUpWorkSet.contains(work.getWorkNo()) ) {
//				//검색 조건(담당자)가 '전체'가 아닐때 자식일감이 없는 일감만 보여줌.
//				if(!workVO.getWorker().equals("")) {
//					continue;
//				}
//				//검색 조건(상태)가 '전체'가 아닐때 자식일감이 없는 일감만 보여줌.
//				if(!workVO.getSttCode().equals("")) {
//					continue;
//				}
//				//검색 조건(중요도)가 '전체'가 아닐때 자식일감이 없는 일감만 보여줌.
//				if(!workVO.getIpcrCode().equals("")) {
//					continue;
//				}
//			}
				// 일감VO -> 간트객체로 변환하여 리스트에 추가
				ganttList.add( new GanttCommand(work, ipcrList, workSttList) );
		}
		// 부모 일감 set(gantt기준) 만들기
		Set<Integer> getUpWorkPartSet = new HashSet<>();
		// 일감 번호 set(gannt기준) 만들기
		Set<Integer> getWorkNoPartSet = new HashSet<>();
		for(WorkVO work : workList) {
			getUpWorkPartSet.add(work.getUpWork());
			getWorkNoPartSet.add(work.getWorkNo());
		}
		// 일감 파라미터 부분 수정
		for(GanttCommand gantt : ganttList) {
			// 이름에 링크 박기
				//일감 일때(프로젝트 기간이 아니면) 
			String pName = gantt.getpName();
			if( gantt.getpID() != -1) {
//				gantt.setpName( "<a class='workName' href='"+request.getContextPath()+"/project/work/detail.open?open=1&workNo="+ gantt.getpID() +"'>"+pName+"</a> ");
				String workName = pName.substring(pName.indexOf(" ") + 1);
				gantt.setpName( "<a class='workName'id="+ gantt.getpID() +" href=\"javascript:OpenWindow('/pms/project/work/detail.open?open=1&workNo="+ gantt.getpID() +"','일감상세',800,800);\" data-workName='" + workName + "'\">"+pName+"</a>");
			}
			// (프로젝트내에서) 하위일감을 가지고 있으면 그래프 디자인 변경
			if( getUpWorkSet.contains(gantt.getpID()) ) {
				gantt.setpClass("gtaskblue");
//				gantt.setpName( "<a class='workName workParent' href='"+request.getContextPath()+"/project/work/detail.open?open=1&workNo="+ gantt.getpID() +"'>"+pName+"</a> ");
				gantt.setpName( "<a class='workName workParent'id="+ gantt.getpID() +" href=\"javascript:OpenWindow('/pms/project/work/detail.open?open=1&workNo="+ gantt.getpID() +"','일감상세',800,800);\">"+pName+"</a> ");
			}
			// (현재 표현될 창에서) 하위일감을 가지고 있으면 부모타입으로 변경
			if( getUpWorkPartSet.contains(gantt.getpID()) ) {
				gantt.setpGroup(1);
			}
			// (현재 표현될 창에서) 상위일감이 없으면 상위일감을 0로 만듬.
			if( !getWorkNoPartSet.contains(gantt.getpParent()) ) {
				gantt.setpParent(0);
			}
		}
		
		ResponseEntity<List<GanttCommand>> entity = new ResponseEntity<>(ganttList,HttpStatus.OK);
		
		return entity;
	}
}
