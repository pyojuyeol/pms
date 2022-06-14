package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.ProceedingsCriteria;
import kr.or.pms.dto.PrcdUserVO;
import kr.or.pms.dto.ProceedingsVO;

public interface ProceedingsDAO {
	
	List<ProceedingsVO> selectProceedingsCriteria(ProceedingsCriteria cri) throws SQLException;
	List<Integer> selectPrcdNoByUserId(String userId) throws SQLException;
	List<PrcdUserVO> selectUserIdByPrcdNo(int prcdNo) throws SQLException;
	int selectProceedingsCriteriaTotalCount(ProceedingsCriteria cri) throws SQLException;
	List<Integer> selectSttCodeByPrcdNo(int prcdNo) throws SQLException;
	
	void insertProceedings(ProceedingsVO proceedings) throws SQLException;
	void returnProceedings(int prcdNo) throws SQLException;
	void returnPrcdUser(int prcdNo) throws SQLException;
	void confirmProceedings(int prcdNo) throws SQLException;
	void updatePrcdUser(PrcdUserVO prcdUser) throws SQLException;
	void updateProceedings(ProceedingsVO proceedings) throws SQLException;
	void insertPrcdUser(PrcdUserVO prcdUser) throws SQLException;
	ProceedingsVO selectProceedingsByPrcdNo(ProceedingsVO proceedings) throws SQLException;
	ProceedingsVO selectProceedingsConfirm(ProceedingsVO proceedings) throws SQLException;
	
	int selectProceedingsSeqNext() throws SQLException;
	
	public byte[] selectPdfFile(int prcdNo) throws SQLException;
	public byte[] selectExcelFile(int prcdNo) throws SQLException;
	
}
