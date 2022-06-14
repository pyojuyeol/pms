package kr.or.pms.command;

import kr.or.pms.dto.DbRsrvVO;

public class DbRsrvCommand {
	
	private String dbNo;
	private String dbcnNo;
	
	public String getDbNo() {
		return dbNo;
	}
	public void setDbNo(String dbNo) {
		this.dbNo = dbNo;
	}
	public String getDbcnNo() {
		return dbcnNo;
	}
	public void setDbcnNo(String dbcnNo) {
		this.dbcnNo = dbcnNo;
	}

	public DbRsrvVO toDbRsrvVO() {
		DbRsrvVO dbRsrv = new DbRsrvVO();
		dbRsrv.setDbNo(Integer.parseInt(this.dbNo));
		dbRsrv.setDbcnNo(Integer.parseInt(this.dbcnNo));
		return dbRsrv;
	}
}
