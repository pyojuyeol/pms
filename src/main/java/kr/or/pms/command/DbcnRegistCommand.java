package kr.or.pms.command;

import kr.or.pms.dto.DbcnVO;

public class DbcnRegistCommand {
	
	private String dbcnUrl;
	private String dbcnUser;
	private String dbcnPwd;
	private String dbType;
	private String path;
	private String fileNm;
	private String maxIdle;
	private String maxTotal;
	private String dbDriver;
	
	
	public String getDbcnUrl() {
		return dbcnUrl;
	}

	public void setDbcnUrl(String dbcnUrl) {
		this.dbcnUrl = dbcnUrl;
	}

	public String getDbcnUser() {
		return dbcnUser;
	}

	public void setDbcnUser(String dbcnUser) {
		this.dbcnUser = dbcnUser;
	}

	public String getDbcnPwd() {
		return dbcnPwd;
	}

	public void setDbcnPwd(String dbcnPwd) {
		this.dbcnPwd = dbcnPwd;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	public String getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(String maxIdle) {
		this.maxIdle = maxIdle;
	}

	public String getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(String maxTotal) {
		this.maxTotal = maxTotal;
	}

	public DbcnVO toDbcnVO() {
		DbcnVO dbcn = new DbcnVO();
		dbcn.setDbcnUser(this.dbcnUser);
		dbcn.setDbcnPwd(this.dbcnPwd);
		dbcn.setDbcnUrl(this.dbcnUrl);
		dbcn.setDbType(this.dbType);
		dbcn.setFileNm(this.fileNm);
		dbcn.setPath(this.path);
		dbcn.setDbDriver(this.dbDriver);
		if(!this.maxIdle.equals(""))
			dbcn.setMaxIdle(Integer.parseInt(this.maxIdle));
		if(!this.maxTotal.equals(""))
			dbcn.setMaxTotal(Integer.parseInt(this.maxTotal));
		return dbcn;
	}
}
