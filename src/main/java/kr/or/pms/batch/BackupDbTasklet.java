package kr.or.pms.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import kr.or.pms.util.DbManager;

public class BackupDbTasklet implements Tasklet{

	private DbManager dbManager;
	public void setDbManager(DbManager dbManager) {
		this.dbManager = dbManager;
	}
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		
		dbManager.backupDB();
		
		System.out.println("Spring Batch BackupDbTasklet!");
		
		return RepeatStatus.FINISHED;
	}
	
	
}
