package kr.or.pms.service;

import kr.or.pms.dto.WorkVO;

public interface WorkLogService {

	void regist(WorkVO newWork, WorkVO oldWork) throws Exception;
}
