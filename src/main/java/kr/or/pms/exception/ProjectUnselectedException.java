package kr.or.pms.exception;

public class ProjectUnselectedException extends ProjectAccessException {
	
	public ProjectUnselectedException() {
		super("프로젝트가 선택되지 않았습니다.");
	}
	
	
	
}
