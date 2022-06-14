package kr.or.pms.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.or.pms.dao.DbDAO;
import kr.or.pms.dto.DbcnVO;

public class DbManager {

	private String dbDumpPath;
	public void setDbDumpPath(String dbDumpPath) {
		this.dbDumpPath = dbDumpPath;
	}
	private String dropTablePath;
	public void setDropTablePath(String dropTablePath) {
		this.dropTablePath = dropTablePath;
	}
	private DbDAO dbDAO;
	public void setDbDAO(DbDAO dbDAO) {
		this.dbDAO = dbDAO;
	}

	public void backupDB() {
		try {
			
			DbcnVO originDbcn = dbDAO.selectDbcnBydbNm("origin");
			DbcnVO backupDbcn = dbDAO.selectDbcnBydbNm("backup");
			
			executeMoveData(originDbcn, backupDbcn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void recoverDB() {
		try {
			
			DbcnVO originDbcn = dbDAO.selectDbcnBydbNm("origin");
			DbcnVO backupDbcn = dbDAO.selectDbcnBydbNm("backup");
			System.out.println(backupDbcn.getDbcnUser());
			executeMoveData(backupDbcn, originDbcn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executeMoveData(DbcnVO from, DbcnVO to) throws Exception {
		SystemLockerManager.makeLocker();
		
		try {
			String targetFile = makeDumpFile(from);
			dataRestore(targetFile, to);
			
		} catch (Exception e) {
			throw e;
		} finally {	
			SystemLockerManager.removeLocker();
		}
	}
	
	public String makeDumpFile(DbcnVO from) throws Exception {
		
		String targetFile = "";

		String cnUser = from.getDbcnUser();
		String cnPwd = from.getDbcnPwd();
		String cnUrl = from.getDbcnUrl();

		String userInfo = cnUser + "/" + cnPwd + "@" + cnUrl + ":1521/XE";

		String savePath = dbDumpPath.replace("/", File.separator);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_");
		Date now = new Date();
		String nowTime = sdf.format(now);
		String fileName = nowTime + from.getDbcnUser() + ".dmp";

		targetFile = savePath + File.separator + fileName;
		File file = new File(targetFile);
		if(file.exists()) {
			file.delete();
		}
		
		File backupFile = new File(savePath);
		backupFile.mkdirs();

		try(FileWriter fw = new FileWriter(targetFile);) {

			Runtime runtime = Runtime.getRuntime();
			String command = "cmd /c start /wait exp " + userInfo + " file='" + targetFile + "'";
			Process child = runtime.exec(command);
			InputStreamReader irs = new InputStreamReader(child.getInputStream());

			child.waitFor();

			BufferedReader br = new BufferedReader(irs);

			boolean isFirst = true; 
			String line;
			while ((line = br.readLine()) != null) {
				if(isFirst) {
					fw.write(line);
				}else{
					fw.write("\r\n"+line);
				}
			}
			
			irs.close();
			br.close();

		} catch (IOException ex) {
			throw ex;
		} finally {
		}

		return targetFile;

	}

	public void dataRestore(String targetFile, DbcnVO to) throws Exception {

		String cnUser = to.getDbcnUser();
		String cnPwd = to.getDbcnPwd();
		String cnUrl = to.getDbcnUrl();

		String userInfo = cnUser + "/" + cnPwd + "@" + cnUrl + ":1521/XE";

		try {

			dropTables(userInfo);
			
			String command = "cmd /c start /wait imp " + userInfo + " file='" + targetFile + "' ignore=Y full=Y";
			
			Runtime runtime = Runtime.getRuntime();
			Process child = runtime.exec(command);
			child.waitFor();

		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public void dropTables(String userInfo) throws IOException {
		
		String savePath = dropTablePath.replace("/", File.separator);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_");
		Date now = new Date();
		String nowTime = sdf.format(now);
		String fileName = nowTime + "dropTableDDL.sql";
		
		String dropSqlFile = savePath + File.separator + fileName;
		File backupFile = new File(savePath);
		backupFile.mkdirs();
		
		BufferedReader br = null;
		FileWriter fw = null;

		String loginStmtCommand = "sqlplus " + userInfo + ":1521/XE";
		String getDropTableStmtCommand = "SELECT 'DROP TABLE ' || object_name || ' CASCADE CONSTRAINTS;' from user_objects WHERE  object_type = 'TABLE';";

		
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
            
        } catch (IOException e) {
            throw e;
        } finally {
        	try {
        		fw.close();
        		br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		Process p = null;
		//드롭 테이블 
		try {
			String dropTables = "@"+dropSqlFile;

			ProcessBuilder b = new ProcessBuilder("cmd");
			b.redirectErrorStream(true);
			p = b.start();
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			
			writer.write(loginStmtCommand + "\n");
			writer.flush();
			
			writer.write(dropTables + "\n");
			writer.flush();
			
			writer.close();
			
			/* 
			 * 없으면 실행이 안됨 지우지 마세요. (결과물을 흘려보내야 한다.)
			 */
	        InputStreamReader irs = new InputStreamReader(p.getInputStream());
	        BufferedReader br1 = new BufferedReader(irs);
	        @SuppressWarnings("unused")
			String line;
	        while ((line = br1.readLine()) != null) {
//        		System.out.println(line);
	        }
	        br1.close();
			/* end line */
	        
		} catch (IOException e) {
			throw e;
		} 
	}
	
	
	
}
