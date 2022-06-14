package kr.or.pms.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbManagerTest {
	

	/* sqlplus team4_202110F/java@112.220.114.130:1521/XE */
	/* sqlplus hope/java@localhost:1521/XE */
	public static String makeDumpFile()  throws InterruptedException {
		
		String targetFile = "";
		String savePath = "C:/pms/db/FileTSCoverage/FileBackup".replace("/", File.separator);
		String userInfo = "team4_202110F/java@112.220.114.130:1521/XE";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_");
		Date now = new Date();
		String nowTime = sdf.format(now);
		String fileName = nowTime + "origin" + "_backup.dmp";

		targetFile = savePath + File.separator + fileName;
		File backupFile = new File(savePath);
		backupFile.mkdirs();

		try {

			Runtime runtime = Runtime.getRuntime();
			FileWriter fw = new FileWriter(targetFile, false);

			String command = "cmd /c start /wait exp " + userInfo + " file='" + targetFile + "'";

			Process child = runtime.exec(command);

			child.waitFor();

			InputStreamReader irs = new InputStreamReader(child.getInputStream());
			BufferedReader br = new BufferedReader(irs);

			String line;
			while ((line = br.readLine()) != null) {
				fw.write(line);
			}

			fw.close();
			irs.close();
			br.close();

		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}

		return targetFile;
		
	}
	
	
	public static void dropTables(String userInfo) {
		
		String savePath = "c:/pms/db/FileTSCoverage/dropTable".replace("/", File.separator);
		File backupFile = new File(savePath);
		backupFile.mkdirs();
		
		String dropSqlFile = savePath + File.separator + "dropTableDDL.sql";
		BufferedReader br = null;
		FileWriter fw = null;


		String loginStmtCommand = "sqlplus " + userInfo;
		String getDropTableStmtCommand = " SELECT 'DROP TABLE ' || object_name || ' CASCADE CONSTRAINTS;' from user_objects WHERE  object_type = 'TABLE';";
		
		try {
        	
			//DDL 만들기

			ProcessBuilder b = new ProcessBuilder("cmd");
			b.redirectErrorStream(true);
			Process p = b.start();
			

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			
			writer.write(loginStmtCommand + "\n");
			writer.flush();
			
			writer.write(getDropTableStmtCommand + "\n");
			writer.flush();
			
			writer.close();
			
			fw = new FileWriter(dropSqlFile);
			
	        InputStreamReader irs = new InputStreamReader(p.getInputStream());
	        br = new BufferedReader(irs);

	        String line;
	        boolean startFlag = false;
	        while ((line = br.readLine()) != null) {
	        	
	        	if(line.contains("SQL>")) startFlag = false;
	        	if(line.contains("DROPTABLE'||OBJECT_NAME||'CASCADECONSTRAINTS;")) continue;
	        	if(line.contains("rows selected.")) continue;
	        	if(line.contains("------------------")) {
	        		startFlag = true;
	        		continue;
	        	}

	        	
	        	if(startFlag) {
	        		fw.write(line);
	        		fw.write("\n");
	        		
	        		System.out.println(line);
	        	}
	        	
	        	
	        }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
        		fw.close();
        		br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		
		//드롭 테이블 
		try {
			String dropTables = "@"+dropSqlFile;

			ProcessBuilder b = new ProcessBuilder("cmd");
			b.redirectErrorStream(true);
			Process p = b.start();
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			
			writer.write(loginStmtCommand + "\n");
			writer.flush();
			
			writer.write(dropTables + "\n");
			writer.flush();
			
			writer.close();
			
			/* 
			 * 왜인지 모르지만 이게 없으면 실행이 안되네요 지우지 마세요.
			 * 아니면 고쳐주시던가..
			 */
	        InputStreamReader irs = new InputStreamReader(p.getInputStream());
	        BufferedReader br1 = new BufferedReader(irs);
	        String line;
	        while ((line = br1.readLine()) != null) {
        		System.out.println(line);
	        }
	        br1.close();
			/* end line */
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void dataRestore() {

		String userInfo = "team4_202110F/java@112.220.114.130:1521/XE";
		String targetFile = "C:/pms/db/FileTSCoverage/FileBackup/20220518_202840_team4_202110F_backup.dmp";
		targetFile = targetFile.replace("/", File.separator);
		try {

			String command = "cmd /c start /wait imp " + userInfo + " file='" + targetFile + "' ignore=Y full=Y";
			System.out.println(command);
			Runtime runtime = Runtime.getRuntime();
			Process child = runtime.exec(command);
			child.waitFor();

			System.out.println(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
//		List<String> userInfoList = new ArrayList<String>();
//		userInfoList.add("team4_backup/java@192.168.144.20:1521/XE");
//		userInfoList.add("origin_test/java@192.168.144.20:1521/XE");
//		userInfoList.add("backup_test/java@192.168.144.20:1521/XE");
//		for(String info : userInfoList) {			
//			DbManagerTest.dropTables(info);
//		}
		DbManagerTest.dataRestore();
//		classes 폴더 절대경로 얻기
//		String path = this.getClass().getResource("/").getPath()+"kr/or/pms/properties";
		
//		System을 통한 일반적인 작업경로 얻기
//		String path = System.getProperty("user.dir"); 
//		System.out.println("현재 작업 경로: " + path);

//		nio를 통한 작업경로얻기
//		Path currentPath = Paths.get(""); 
//		String path = currentPath.toAbsolutePath().toString(); 
//		System.out.println("현재 작업 경로: " + path);

//		String path = System.getProperty("user.dir") +"/src/main/resources/kr/or/pms/properties";
//		path = path.replace("/", File.separator);
//		System.out.println("원하는 경로: D:/A_TeachingMaterial/7.finalProject/workspace/pms/src/main/resources/kr/or/pms/properties");
//		System.out.println("원하는 경로: " + path);

	}
	
}
