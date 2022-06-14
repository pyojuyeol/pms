package kr.or.pms.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import kr.or.pms.util.SystemLockerManager;

public class JobListener implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution exe) {
		
//		SystemLockerManager.makeLocker();
		System.out.println("시스템 점검 시작");
		
	}
	
	@Override
	public void afterJob(JobExecution exe) {
		
//		SystemLockerManager.removeLocker();
		System.out.println("시스템 점검 완료");
	}
	

}
