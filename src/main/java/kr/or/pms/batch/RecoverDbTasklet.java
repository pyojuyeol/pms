package kr.or.pms.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import kr.or.pms.util.DbManager;

public class RecoverDbTasklet implements Tasklet{

	private DbManager dbManager;
	public void setDbManager(DbManager dbManager) {
		this.dbManager = dbManager;
	}
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		
		dbManager.recoverDB();
		
		System.out.println("Spring Batch RecoverDbTasklet!");
		
		return RepeatStatus.FINISHED;
	}

}
