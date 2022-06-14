package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.PdsVO;

public interface PdsDAO {


	/* Pds */
	
	List<PdsVO> selectPdsListCriteria(Criteria cri) throws SQLException;
	
	// pds total count
	int selectPdsListCriteriaTotalCount(Criteria cri) throws SQLException;
	
	// pds 조회(by pdsNo)
	PdsVO selectPdsByPdsNo(int pdsNo) throws SQLException;
	
	// CUD
	public void insertPds(PdsVO pds) throws SQLException;
	public void updatePds(PdsVO pds) throws SQLException;
	public void deletePds(int pdsNo) throws SQLException;
	
	// view cnt+1
	void increaseViewCnt(int pdsNo) throws SQLException;

	
	/* Attach File*/
	int getSeqNextValue() throws SQLException;

	List<AttachVO> selectAttachesByAttachNo(int attachNo)throws SQLException;
	
	AttachVO selectAttachByAttachSeq(int attachSeq)throws SQLException;
	
	void insertAttach(AttachVO attach) throws SQLException;
	
	void deleteAttach(int attachSeq) throws SQLException;
	
	void deleteAllAttach(int attachNo) throws SQLException;
}
