package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.ProceedingsCriteria;
import kr.or.pms.dto.PrcdUserVO;
import kr.or.pms.dto.ProceedingsVO;

public class ProceedingsDAOImpl implements ProceedingsDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<ProceedingsVO> selectProceedingsCriteria(ProceedingsCriteria cri) throws SQLException {
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		List<ProceedingsVO> procList = session.selectList("Proceedings-Mapper.selectProceedingsCriteria",cri,rowBounds);	
			
		return procList;
	}

	@Override
	public List<Integer> selectPrcdNoByUserId(String userId) throws SQLException {
		List<Integer> confirmList = session.selectList("Proceedings-Mapper.selectPrcdNoByUserId",userId);
		return confirmList;
	}
	
	@Override
	public List<PrcdUserVO> selectUserIdByPrcdNo(int prcdNo) throws SQLException {
		List<PrcdUserVO> userList = session.selectList("Proceedings-Mapper.selectUserIdByprcdNo",prcdNo);
		return userList;
	}
	
	@Override
	public int selectProceedingsCriteriaTotalCount(ProceedingsCriteria cri) throws SQLException {
		int count = session.selectOne("Proceedings-Mapper.selectProceedingsCriteriaTotalCount",cri);
		return count;
	}

	@Override
	public void insertProceedings(ProceedingsVO proceedings) throws SQLException {
		session.update("Proceedings-Mapper.insertProceedings",proceedings);
		
	}

	@Override
	public ProceedingsVO selectProceedingsByPrcdNo(ProceedingsVO proceedings) throws SQLException {
		ProceedingsVO procVO = (ProceedingsVO) session.selectOne("Proceedings-Mapper.selectProceedingsByPrcdNo",proceedings);
		return procVO;
	}

	@Override
	public int selectProceedingsSeqNext() throws SQLException {
		int prcdNo = session.selectOne("Proceedings-Mapper.selectProceedingsSeqNext");
		return prcdNo;
	}

	@Override
	public void returnPrcdUser(int prcdNo) throws SQLException {
		session.update("Proceedings-Mapper.returnPrcdUser",prcdNo);
	}
	
	@Override
	public void updatePrcdUser(PrcdUserVO prcdUser) throws SQLException {
		session.update("Proceedings-Mapper.updatePrcdUser",prcdUser);
	}
	
	@Override
	public void insertPrcdUser(PrcdUserVO prcdUser) throws SQLException {
		session.update("Proceedings-Mapper.insertPrcdUser",prcdUser);
	}
	
	@Override
	public void returnProceedings(int prcdNo) throws SQLException {
		session.update("Proceedings-Mapper.returnProceedings",prcdNo);
	}

	@Override
	public void confirmProceedings(int prcdNo) throws SQLException {
		session.update("Proceedings-Mapper.confirmProceedings",prcdNo);
	}

	@Override
	public List<Integer> selectSttCodeByPrcdNo(int prcdNo) throws SQLException {
		List<Integer> sttList = session.selectList("Proceedings-Mapper.selectSttCodeByPrcdNo",prcdNo);
		return sttList;
	}

	@Override
	public byte[] selectPdfFile(int prcdNo) throws SQLException {
		ProceedingsVO proceeding = session.selectOne("Proceedings-Mapper.selectPdfFile", prcdNo);
		byte[] pdfArray = proceeding.getPdfContent();
		
		return pdfArray;
	}

	@Override
	public ProceedingsVO selectProceedingsConfirm(ProceedingsVO proceedings) throws SQLException {
		ProceedingsVO procVO = (ProceedingsVO) session.selectOne("Proceedings-Mapper.selectProceedingsConfirm",proceedings);
		return procVO;
	}

	@Override
	public void updateProceedings(ProceedingsVO proceedings) throws SQLException {
		session.update("Proceedings-Mapper.updateProceedings",proceedings);
	}

	@Override
	public byte[] selectExcelFile(int prcdNo) throws SQLException {
		ProceedingsVO proceeding = session.selectOne("Proceedings-Mapper.selectExcelFile", prcdNo);
		byte[] excelArray = proceeding.getExcelContent();
		
		return excelArray;
	}
}
