package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.PdsVO;

public class PdsDAOImpl implements PdsDAO {

	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<PdsVO> selectPdsListCriteria(Criteria cri) throws SQLException {

		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<PdsVO> pdsList = session.selectList("Pds-Mapper.selectSearchPdsList",cri,rowBounds);
		
		return pdsList;
	}

	@Override
	public int selectPdsListCriteriaTotalCount(Criteria cri) throws SQLException {
		
		int totalCnt = session.selectOne("Pds-Mapper.selectSearchPdsListCount");
		
		return totalCnt;
	}

	@Override
	public PdsVO selectPdsByPdsNo(int pdsNo) throws SQLException {

		PdsVO pds = session.selectOne("Pds-Mapper.selectPdsByPdsNo", pdsNo);
		return pds;
	}

	@Override
	public void insertPds(PdsVO pds) throws SQLException {

		session.update("Pds-Mapper.insertPds",pds);
	}

	@Override
	public void updatePds(PdsVO pds) throws SQLException {
		session.update("Pds-Mapper.updatePds",pds);
		
	}

	@Override
	public void deletePds(int pdsNo) throws SQLException {
		session.update("Pds-Mapper.deletePds",pdsNo);
	}

	@Override
	public void increaseViewCnt(int pdsNo) throws SQLException {
		session.update("Pds-Mapper.increaseViewCnt", pdsNo);
	}

	
	/* Attach File */

	@Override
	public int getSeqNextValue() throws SQLException {
		int attachNo = session.selectOne("Pds-Mapper.selectAttachNoSeqNext");
		return attachNo;
	}

	@Override
	public List<AttachVO> selectAttachesByAttachNo(int attachNo) throws SQLException {
		List<AttachVO> attachList = session.selectList("Pds-Mapper.selectAttachesByAttachNo",attachNo);
		return attachList;
	}

	@Override
	public AttachVO selectAttachByAttachSeq(int attachSeq) throws SQLException {
		AttachVO attach = session.selectOne("Pds-Mapper.selectAttachByAttachSeq",attachSeq);
		return attach;
	}

	@Override
	public void insertAttach(AttachVO attach) throws SQLException {
		session.update("Pds-Mapper.insertAttach",attach);
	}

	@Override
	public void deleteAttach(int attachSeq) throws SQLException {
		session.update("Pds-Mapper.deleteAttach",attachSeq);
	}

	@Override
	public void deleteAllAttach(int attachNo) throws SQLException {
		session.update("Pds-Mapper.deleteAllAttach",attachNo);
	}
}
