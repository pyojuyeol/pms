package kr.or.pms.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.pms.dto.PrjUserVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.exception.ProjectAccessException;
import kr.or.pms.exception.ProjectUnselectedException;
import kr.or.pms.service.ProjectService;
import kr.or.pms.service.WorkService;

public class ProjectAuthCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private ProjectService prjService;
	
	@Autowired
	private WorkService workService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Object oldPrjNoObj = session.getAttribute("currentPrjNo");
		//기존 세션 프로젝트 번호 임시저장
		int oldPrjNo = 0;
		if(oldPrjNoObj != null) {
//			oldPrjNo = (int) oldPrjNoObj;
		}
		ProjectVO prjVO = getPrjFromSessionOrUrl(request);
		// 요청에도 세션에도 프로젝트가 번호가 없을 때
		if(prjVO == null) {
			throw new ProjectUnselectedException();
		}
		//유저가 권한자체가 없으면
		UserVO userVO = (UserVO) session.getAttribute("loginUser");
		String userAuth = userVO.getAuthGrpCode();
		if(userAuth == null) {
			session.setAttribute("currentPrjNo",oldPrjNoObj);
			throw new ProjectAccessException("유저의 기본 권한 정보를 찾을 수 없습니다.");
		}
		//유저가 관리자,매니저 면 허용(CRUD 모두)
		if(userAuth.equals("ROLE_ADMIN") || userAuth.equals("ROLE_MANAGER") ) {
			return true;
		}
		//유저가 유저권한 없으면
		if(!userAuth.equals("ROLE_USER")) {
			session.setAttribute("currentPrjNo",oldPrjNoObj);
			throw new ProjectAccessException("ROLE_USER 이상만 허용된 기능입니다.");
		}
		//접근하는 프로젝트가 완료된 공개 프로젝트 조회면
		String url = request.getServletPath();
		Boolean isPublicPrj = "y".equals( prjVO.getEnabled() );
		Boolean isCompletedPrj = "c".equals( prjVO.getSttCode() );
		if( url.indexOf("list") > 0 || url.indexOf("home") > 0  || url.indexOf("detail") > 0 || url.indexOf("check") > 0 ) {
			// 완료된 공개 프로젝트 면 허용
//			if(isPublicPrj && isCompletedPrj) {
			if(isPublicPrj ) {
				return true;
			}
		}
		//유저가 프로젝트 참여자가 아니면 비허용
		int prjNo = prjVO.getPrjNo();
		String userId = userVO.getUserId();
		List<PrjUserVO> prjUserList = prjService.getProjectUserList(prjNo);
		PrjUserVO prjUserVO = null;
		for (PrjUserVO prjUser : prjUserList) {
			if(prjUser.getUserId().equals(userId)) {
				prjUserVO = prjUser;
			}
		}
		if(prjUserVO == null) {
			session.setAttribute("currentPrjNo",oldPrjNoObj);
			throw new ProjectAccessException("프로젝트의 멤버가 아닙니다.");
		}
		//프로젝트 유저가 상태코드가 없으면
		String prjUserSttCode = prjUserVO.getSttCode();
		//프로젝트  유저가 상태코드가 대기상태 면
		if( "w".equals(prjUserSttCode) ){
			session.setAttribute("currentPrjNo",oldPrjNoObj);
			throw new ProjectAccessException("프로젝트 권한이 없습니다. : 프로젝트 초대 미수락");
		}
		//프로젝트  유저가 상태코드가 e(?)면
		if( !"e".equals(prjUserSttCode) ){
			session.setAttribute("currentPrjNo",oldPrjNoObj);
			throw new ProjectAccessException("프로젝트 권한이 없습니다. : 상태코드이상");
		}
		//PL,PM이면 모든 CRUD 허용
		String prjRole = prjUserVO.getPrjRole();
		if( "PL".equals(prjRole) || "PM".equals(prjRole) ) {
			return true;
		}
		
		//프로젝트 내 권한 체크
		try {
			//읽기 권한 체크
			if( url.indexOf("list") > 0 || url.indexOf("home") > 0  || url.indexOf("detail") > 0 || url.indexOf("check") > 0 ) {
				if( !prjUserVO.getPrjAuthRead().equals("y") ) {
					session.setAttribute("currentPrjNo",oldPrjNoObj);
					throw new ProjectAccessException("읽기 권한이 없습니다.");
				}
			}
			//완료된 프로젝트 내 기능 조회기능외 사용 불가
			if(isCompletedPrj) {
				session.setAttribute("currentPrjNo",oldPrjNoObj);
				throw new ProjectAccessException("이미 종료된 프로젝트를 수정할 수 없습니다.");
			}
			//쓰기 권한 체크
			if( url.indexOf("regist") > 0 ) {
				if( !prjUserVO.getPrjAuthCreate().equals("y") ) {
					session.setAttribute("currentPrjNo",oldPrjNoObj);
					throw new ProjectAccessException("쓰기 권한이 없습니다.");
				}
			}
			//수정 권한 체크
			if( url.indexOf("modify") > 0 ) {
				if( !prjUserVO.getPrjAuthUpdate().equals("y") ) {
					session.setAttribute("currentPrjNo",oldPrjNoObj);
					throw new ProjectAccessException("수정 권한이 없습니다.");
				}
			}
			//삭제권한 체크
			if( url.indexOf("remove") > 0 ) {
				if( !prjUserVO.getPrjAuthDelete().equals("y") ) {
					session.setAttribute("currentPrjNo",oldPrjNoObj);
					throw new ProjectAccessException("삭제 권한이 없습니다.");
				}
			}
		} catch (ProjectAccessException e) {
			session.setAttribute("currentPrjNo",oldPrjNoObj);
			throw e;
		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ProjectAccessException("권한을 확인할 수 없습니다.");
		}
		return true;
	}
	
	public ProjectVO getPrjFromSessionOrUrl(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
//		String from = request.getParameter("from");
		String workNoStr = request.getParameter("workNo");
		String prjNoStr = request.getParameter("prjNo");
		int workNo = 0;
		int prjNo = 0;
		ProjectVO prj = null;
		
		//요청파라미터에 일감번호가 있을 때
		if(workNoStr != null) {
			try {
				//일감번호가 숫자가 아니면
				workNo = (int) Integer.parseInt(workNoStr);
				prjNo = workService.getPnoByWno(workNo);
				prj = prjService.getProject(prjNo);
				//일감번호로 가져온 프로젝트가 없으면
				if(prj == null) {
					throw new ProjectAccessException("해당 일감의 프로젝트는 이미 삭제처리 되었거나 존재하지 않는 프로젝트입니다.");
				}
				session.setAttribute("currentPrjNo", prjNo);
				return prj;
			} catch (Exception e) {
				e.printStackTrace();
				throw new ProjectAccessException("유효한 일감 번호가 아닙니다.");
			}
		}
		//요청파라미터에 프로젝트번호가 있을 때
		if(prjNoStr != null) {
			try {
				//프로젝트 번호가 숫자가 아니면
				prjNo = (int) Integer.parseInt(prjNoStr);
				//해당 프로젝트 번호를 프로젝트가 없으면
				prj = prjService.getProject(prjNo);
				if(prj == null) {
					throw new ProjectAccessException("해당 프로젝트는 이미 삭제처리 되었거나 존재하지 않는 프로젝트입니다.");
				}
				//유효한 프로젝트 번호면 프로젝트VO 리턴
				session.setAttribute("currentPrjNo", prjNo);
				return prj;
			} catch (Exception e) {
				e.printStackTrace();
				throw new ProjectAccessException("유효한 프로젝트 번호가 아닙니다.");
			}
		}
		try {
			prjNo = (int) session.getAttribute("currentPrjNo");
			prj = prjService.getProject(prjNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectUnselectedException();
		}
		
		return prj;
	}
	
}
