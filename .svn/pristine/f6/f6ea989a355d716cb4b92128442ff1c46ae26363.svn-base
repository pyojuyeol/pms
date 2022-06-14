package kr.or.pms.scheduler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

import kr.or.pms.dao.DbDAO;
import kr.or.pms.dao.DbRsrvDAO;
import kr.or.pms.dao.DbcnDAO;
import kr.or.pms.dao.LogDAO;
import kr.or.pms.dto.DbRsrvVO;
import kr.or.pms.dto.DbVO;
import kr.or.pms.dto.DbcnLogVO;
import kr.or.pms.dto.DbcnVO;
import kr.or.pms.util.DbManager;
import kr.or.pms.util.JdbcPropertiesFileIoAPI;

public class DbBackUpTask {

	private DbManager dbManager;
	public void setDbManager(DbManager dbManager) {
		this.dbManager = dbManager;
	}

	private DbcnDAO dbcnDAO;
	public void setDbcnDAO(DbcnDAO dbcnDAO) {
		this.dbcnDAO = dbcnDAO;
	}

	private DbDAO dbDAO;
	public void setDbDAO(DbDAO dbDAO) {
		this.dbDAO = dbDAO;
	}
	
	private DbRsrvDAO dbRsrvDAO;
	public void setDbRsrvDAO(DbRsrvDAO dbRsrvDAO) {
		this.dbRsrvDAO = dbRsrvDAO;
	}
	
	private LogDAO logDAO;
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

	public void backupDB() {
		try {
			List<DbRsrvVO> rsrvList = dbRsrvDAO.selectTodayRsrvList();
			if(rsrvList.size()>0) {
				for(DbRsrvVO rsrv : rsrvList) {
					
					DbcnVO originDbcn = dbDAO.selectDbcnBydbNm("origin");

					//최초설정일때
					if(originDbcn == null) { 
						Properties properties = new Properties();
						String resource = "kr/or/pms/properties/jdbc.properties";
						try {
							Reader reader = Resources.getResourceAsReader(resource);
							properties.load(reader);
							
						} catch(IOException e) {
							e.printStackTrace();
						}
						String dbDriver = properties.getProperty("jdbc.driverClassName");
						String dbcnUrl = properties.getProperty("jdbc.url").split("@")[1].split(":")[0];
						String dbcnUser = properties.getProperty("jdbc.username");
						String dbcnPwd = properties.getProperty("jdbc.password");
						originDbcn = new DbcnVO();
						originDbcn.setDbDriver(dbDriver);
						originDbcn.setDbcnUrl(dbcnUrl);
						originDbcn.setDbcnUser(dbcnUser);
						originDbcn.setDbcnPwd(dbcnPwd);
					}
					
					DbcnVO rsrvDbcn = dbcnDAO.selectDbcnByNo(rsrv.getDbcnNo());
										
					// 예약 디비 정보 업데이트
					Map<String, Object> paramMap = new HashMap<>();
					paramMap.put("stt", 1);
					paramMap.put("dbRsrvNo", rsrv.getDbRsrvNo());
					dbRsrvDAO.updateStt(paramMap);
					
					DbVO dbVO = dbDAO.selectDbByNm(rsrv.getDbNm());
					DbcnVO oldDbcn = dbcnDAO.selectDbcnByNo(dbVO.getDbcnNo());
					// bdcn 정보 업데이트
					if(oldDbcn != null) {//최초설정일때 안함
						oldDbcn.setSttCode("N");
						dbcnDAO.update(oldDbcn);
					}
					rsrvDbcn.setSttCode("Y");
					dbcnDAO.update(rsrvDbcn);

					// 현재 디비정보 업데이트
					dbVO.setDbcnNo(rsrv.getDbcnNo());
					dbDAO.update(dbVO);
					
					//디비 접속 로그 남기기
					DbcnLogVO logVO = new DbcnLogVO();
					logVO.setDbNm(rsrv.getDbNm());
					logVO.setDbcnNo(rsrv.getDbcnNo());
					logVO.setPath(rsrvDbcn.getPath());
					logVO.setFileNm(rsrvDbcn.getFileNm());
					logVO.setDbcnUrl(rsrvDbcn.getDbcnUrl());
					logVO.setDbcnUser(rsrvDbcn.getDbcnUser());
					logVO.setDbcnPwd(rsrvDbcn.getDbcnPwd());
					logVO.setCngDt(new Date());
					
					logDAO.insertDbcnLog(logVO);
					
					//예약된 정보로 백업
					dbManager.executeMoveData(originDbcn, rsrvDbcn);
					
					// CONTAINER 재조립을 위한 WAS 강제실행 
//					String path = "D:/A_TeachingMaterial/7.finalProject/workspace/pms" + "/src/main/resources/kr/or/pms/properties/jdbc.properties";
//					String path = System.getProperty("user.dir") + "/src/main/resources/kr/or/pms/properties/jdbc.properties";
					String path = this.getClass().getResource("/").getPath()+"kr/or/pms/properties/jdbc.properties";
					path = path.replace("/", File.separator);
//					
					if(rsrv.getDbNm().equals("origin")) {
						//프로퍼티스 파일 받아와서 적용하기
						try {
							byte[] multi = JdbcPropertiesFileIoAPI.getJdbcPropertiesFile(rsrvDbcn);
							
							if(multi != null) {
								
								try(FileOutputStream fos = new FileOutputStream(path)) {
									fos.write(multi);
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							}
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			} else {
				dbManager.backupDB();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	
}
