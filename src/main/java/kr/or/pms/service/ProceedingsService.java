package kr.or.pms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.ProceedingsCriteria;
import kr.or.pms.dto.PrcdUserVO;
import kr.or.pms.dto.ProceedingsVO;

public interface ProceedingsService {
	
	Map<String, Object> getList(ProceedingsCriteria cri) throws SQLException;
	
	List<ProceedingsVO> selectPrcdNoByUserId(String userId, int prjNo) throws SQLException;
	List<PrcdUserVO> selectUserIdByPrcdNo(int prcdNo) throws SQLException;
	
	ProceedingsVO selectProceedingsByPrcdNo(int prcdNo, int prjNo) throws SQLException;
	public void registProceedings(ProceedingsVO proceedings) throws SQLException, IOException, Exception;
	

	void modifyProceedings(ProceedingsVO proceedings) throws SQLException;
	void returnProceedings(ProceedingsVO proceedings) throws SQLException;
	
	// pdf 파일 읽기
	public byte[] getPdfFile(int prcdNo) throws SQLException;
	
	// excel 파일 읽기
	public byte[] getExcelFile(int prcdNo) throws SQLException;
}
