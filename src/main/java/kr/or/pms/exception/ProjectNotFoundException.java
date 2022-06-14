package kr.or.pms.exception;

public class ProjectNotFoundException extends ProjectAccessException {
	
	public ProjectNotFoundException() {
		super("존재하지 않는 프로젝트 입니다.");
	}
	
}
