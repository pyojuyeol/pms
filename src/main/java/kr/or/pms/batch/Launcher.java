package kr.or.pms.batch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Launcher {

	public static void main(String[] args) throws Exception{
		
		ApplicationContext ctx=
				new GenericXmlApplicationContext("kr/or/pms/context/root-context.xml");
		
		
		BackupDbRuntime runtime=(BackupDbRuntime)ctx.getBean("backupDbRuntime");
		
		runtime.launch();

	}

}
