package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dao.LogDAO;
import kr.or.pms.dao.PdsDAO;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.FileLogVO;
import kr.or.pms.dto.PdsVO;

public class PdsServiceImpl implements PdsService{

	private PdsDAO pdsDAO;
	
	
	public void setPdsDAO(PdsDAO pdsDAO) {
		this.pdsDAO = pdsDAO;
	}
	private LogDAO logDAO;
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

	@Override
	public Map<String, Object> getPdsList(Criteria cri) throws Exception {
		
		List<PdsVO> pdsList = pdsDAO.selectPdsListCriteria(cri);
		
		if(pdsList != null)
			for(PdsVO pds : pdsList )
				addAttachList(pds);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(pdsDAO.selectPdsListCriteriaTotalCount(cri));
		
		Map<String,Object>dataMap = new HashMap<String, Object>();
		dataMap.put("pdsList", pdsList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

//	@Override
//	public Map<String, Object> getSearchPdsList(Criteria cri) throws Exception {
//		Criteria searchCri = (Criteria)cri;
//		Map<String,Object>dataMap = new HashMap<String, Object>();
//		
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(cri);
//		pageMaker.setTotalCount(pdsDAO.selectPdsListCriteriaTotalCount(searchCri));
//		
//		List<PdsVO>pdsList = pdsDAO.selectPdsListCriteria(searchCri);
//		
//		dataMap.put("pdsList", pdsList);
//		dataMap.put("pageMaker", pageMaker);
//		
//		return dataMap;
//	}

	@Override
	public void regist(PdsVO pds) throws Exception {
		
		if(pds.getAttachList() != null) {
			int attachNo = pdsDAO.getSeqNextValue();
			for(AttachVO attach : pds.getAttachList()) {
				attach.setRegister(pds.getRegister());
				attach.setAttachNo(attachNo);
				pdsDAO.insertAttach(attach);;
			}
			pds.setAttachNo(attachNo);
		}
		
		pdsDAO.insertPds(pds);
		
	}

	@Override
	public void modify(PdsVO pds) throws Exception {

		if(pds.getAttachNo() ==0 && pds.getAttachList() != null) {
			int attachNo = pdsDAO.getSeqNextValue();
			pds.setAttachNo(attachNo);
			for(AttachVO attach : pds.getAttachList()) {
				attach.setRegister(pds.getRegister());
				attach.setAttachNo(attachNo);
				pdsDAO.insertAttach(attach);
			}
		} else if(pds.getAttachNo() != 0 && pds.getAttachList() != null) {
			for(AttachVO attach : pds.getAttachList()) {
				attach.setRegister(pds.getRegister());
				attach.setAttachNo(pds.getAttachNo());
				pdsDAO.insertAttach(attach);
			}
		}
		
		
		pdsDAO.updatePds(pds);
	}

	@Override
	public void remove(int pdsNO) throws Exception {
		pdsDAO.deletePds(pdsNO);
	}

	@Override
	public PdsVO detail(int pdsNO) throws Exception {
		PdsVO pds = pdsDAO.selectPdsByPdsNo(pdsNO);
		addAttachList(pds);
		return pds;
	}
	
	@Override
	public PdsVO read(int pds_no) throws Exception {
		PdsVO pds = pdsDAO.selectPdsByPdsNo(pds_no);
		pdsDAO.increaseViewCnt(pds_no);
		addAttachList(pds);
		return pds;
	}

	@Override
	public PdsVO getPds(int pdsNO) throws Exception {

		PdsVO pds = pdsDAO.selectPdsByPdsNo(pdsNO);
		addAttachList(pds);
		return pds;
	}

	@Override
	public void increaseViewCnt(int pds_no) throws Exception {
		pdsDAO.increaseViewCnt(pds_no);
	}

	
	/* Attach File */
	@Override
	public AttachVO getAttachByAttachSeq(int attachSeq, String ip) throws SQLException {

		AttachVO attach = pdsDAO.selectAttachByAttachSeq(attachSeq);
		
		FileLogVO fileLog = new FileLogVO();
		int fileDownLogNo = logDAO.selectFileLogSeqNext();
		
		fileLog.setIp(ip);
		fileLog.setFileDownLogNo(fileDownLogNo);
		fileLog.setUserId(attach.getRegister());
		fileLog.setFileKey("pds");
		fileLog.setFileNo(attach.getAttachNo());
		fileLog.setFileNm(attach.getStroredNm());
		
		logDAO.insertFileDownloadLog(fileLog);
		
		return attach;
	}

	@Override
	public void removeAttachByAttachSeq(int attachSeq) throws SQLException {

		pdsDAO.deleteAttach(attachSeq);

	}
	
	@Override
	public void deleteAllAttach(int attachNo) throws SQLException {

		pdsDAO.deleteAllAttach(attachNo);
		
	}
	
	private void addAttachList(PdsVO pds) throws SQLException {

		if (pds == null)
			return;

		int attachNo = pds.getAttachNo();
		List<AttachVO> attachList = pdsDAO.selectAttachesByAttachNo(attachNo);

		pds.setAttachList(attachList);
	}

	
}
