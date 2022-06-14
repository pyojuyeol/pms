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
import kr.or.pms.service.ProjectService;

public class PrjSettingAuthCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private ProjectService prjService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ProjectVO prjVO = getPrjFromrUrl(request);
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("loginUser");
		String userAuth = userVO.getAuthGrpCode();
		//유저가 관리자,매니저 면 허용(CRUD 모두)
		if(userAuth.equals("ROLE_ADMIN") || userAuth.equals("ROLE_MANAGER") ) {
			return true;
		}
		//유저가 유저권한 없으면
		if(!userAuth.equals("ROLE_USER")) {
			throw new ProjectAccessException("ROLE_USER 이상만 허용된 기능입니다.");
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
			throw new ProjectAccessException("프로젝트의 멤버가 아닙니다.");
		}
		//프로젝트 유저가 상태코드가 없으면
		String prjUserSttCode = prjUserVO.getSttCode();
		//프로젝트  유저가 상태코드가 대기상태 면
		if( "w".equals(prjUserSttCode) ){
			throw new ProjectAccessException("프로젝트 권한이 없습니다. : 프로젝트 초대 미확인");
		}
		//프로젝트  유저가 상태코드가 e(?)면
		if( !"e".equals(prjUserSttCode) ){
			throw new ProjectAccessException("프로젝트 권한이 없습니다. : 상태코드이상");
		}
		//리더면 모든 내부기능 허용
		String prjRole = prjUserVO.getPrjRole();
		if( "PL".equals(prjRole) || "PM".equals(prjRole) ) {
			return true;
		}
		throw new ProjectAccessException("프로젝트 PL, PM만 접근 가능합니다.");
	}
	
	public ProjectVO getPrjFromrUrl(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String prjNoStr = request.getParameter("prjNo");
		int prjNo = 0;
		ProjectVO prj = null;
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
		return prj;
	}
	
}
