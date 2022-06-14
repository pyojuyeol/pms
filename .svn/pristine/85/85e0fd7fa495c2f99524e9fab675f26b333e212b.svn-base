package kr.or.pms.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.PdsVO;

public interface PdsService {

	public Map<String, Object> getPdsList(Criteria cri) throws Exception;
	
//	public Map<String,Object> getSearchPdsList(Criteria cri) throws Exception;

	public PdsVO getPds(int pds_no)throws Exception;
	
	public PdsVO detail(int pds_no) throws Exception;
	public PdsVO read(int pds_no) throws Exception;
	public void regist(PdsVO pds) throws Exception;
	public void modify(PdsVO pds) throws Exception;
	public void remove(int pds_no) throws Exception;

	public void increaseViewCnt(int pds_no) throws Exception;
	
	
	/* Attach File */
	//첨부파일 조회
	AttachVO getAttachByAttachSeq(int attachSeq, String ip)throws SQLException;
	
	//파일정보 삭제
	void removeAttachByAttachSeq(int attachSeq)throws SQLException;
	
	void deleteAllAttach( int attachNo) throws SQLException;
	
}
