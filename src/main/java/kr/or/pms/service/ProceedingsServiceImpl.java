package kr.or.pms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.or.pms.command.ProceedingsCriteria;
import kr.or.pms.command.ProceedingsPageMaker;
import kr.or.pms.dao.HistoryDAO;
import kr.or.pms.dao.ProceedingsDAO;
import kr.or.pms.dao.PushDAO;
import kr.or.pms.dao.UserDAO;
import kr.or.pms.dto.HistoryVO;
import kr.or.pms.dto.PrcdUserVO;
import kr.or.pms.dto.ProceedingsVO;
import kr.or.pms.dto.PushVO;

public class ProceedingsServiceImpl implements ProceedingsService {

	private ProceedingsDAO proceedingsDAO;

	public void setProceedingsDAO(ProceedingsDAO proceedingsDAO) {
		this.proceedingsDAO = proceedingsDAO;
	}
	private PushDAO pushDAO;
	public void setPushDAO(PushDAO pushDAO) {
		this.pushDAO = pushDAO;
	}
	private HistoryDAO historyDAO;
	public void setHistoryDAO(HistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}
	private UserDAO userDAO;
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public Map<String, Object> getList(ProceedingsCriteria cri) throws SQLException {
		List<ProceedingsVO> procList = proceedingsDAO.selectProceedingsCriteria(cri);

		ProceedingsPageMaker pageMaker = new ProceedingsPageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(proceedingsDAO.selectProceedingsCriteriaTotalCount(cri));

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("procList", procList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	@Override
	public List<ProceedingsVO> selectPrcdNoByUserId(String userId, int prjNo) throws SQLException {
		List<Integer> procNoList = proceedingsDAO.selectPrcdNoByUserId(userId);
		List<ProceedingsVO> procList = new ArrayList<ProceedingsVO>();
		
		ProceedingsVO procVO = new ProceedingsVO();
		for (Integer prcdNo : procNoList) {
			procVO.setPrjNo(prjNo);
			procVO.setPrcdNo(prcdNo);
			ProceedingsVO proceedings = proceedingsDAO.selectProceedingsConfirm(procVO);
			if(proceedings != null) {
				procList.add(proceedings);
			}
		}

		return procList;
	}

	@Override
	public List<PrcdUserVO> selectUserIdByPrcdNo(int prcdNo) throws SQLException {
		List<PrcdUserVO> userList = proceedingsDAO.selectUserIdByPrcdNo(prcdNo);
		return userList;
	}

	@Override
	public ProceedingsVO selectProceedingsByPrcdNo(int prcdNo, int prjNo) throws SQLException {
		
		ProceedingsVO proceedings = new ProceedingsVO();
		proceedings.setPrcdNo(prcdNo);
		proceedings.setPrjNo(prjNo);
		
		ProceedingsVO procVO = (ProceedingsVO) proceedingsDAO.selectProceedingsByPrcdNo(proceedings);

		return procVO;
	}

	@Override
	public void registProceedings(ProceedingsVO proceedings) throws SQLException, IOException, Exception {

		/** 파라미터 구성 */
		MultipartFile pdfFile = proceedings.getPdfFile();
		MultipartFile excelFile = proceedings.getExcelFile();
		

		/** 파라미터 검증 */
		if (isEmptyFile(pdfFile) && isEmptyFile(excelFile)) {
			throw new Exception("파일이 존재하지 않습니다.");
		}
		/** 비즈니스 로직 */
		// multipartfile을 file객체로 반환 받는 메서드 정의
		
		byte[] pdfContent = proceedings.getPdfFile().getBytes();
		byte[] excelContent = proceedings.getExcelFile().getBytes();
		
		proceedings.setPdfContent(pdfContent);
		proceedings.setExcelContent(excelContent);

		// prcdNo nextvalue 가져오기
		int prcdNo = proceedingsDAO.selectProceedingsSeqNext();
		proceedings.setPrcdNo(prcdNo);

		// insert 작업
		proceedingsDAO.insertProceedings(proceedings);
		
		// insert prcdUser
		PrcdUserVO prcdUser = new PrcdUserVO();
		prcdUser.setPrcdNo(prcdNo);
		prcdUser.setSttCode("1");
		for(String userId : proceedings.getUserId()) {
			prcdUser.setUserId(userId);
			proceedingsDAO.insertPrcdUser(prcdUser);
		}
		
		HistoryVO history = new HistoryVO();
		history.setUserId(proceedings.getRegister());
		history.setPrjNo(proceedings.getPrjNo());
		history.setFromWhere("proceedings");
		history.setUrl("/project/proceedings/detail.do?prcdNo="+prcdNo+"&prjNo="+proceedings.getPrjNo());
		history.setTitle(proceedings.getUserNm()+" 님이 "+proceedings.getTitle()+" 회의록을 등록하였습니다.");
		history.setContent("회의록 내용은 상세페이지에서 확인 가능합니다.");
		
		historyDAO.insertHistory(history);
		
		PushVO push = new PushVO();
		for(String userId : proceedings.getUserId()) {
			push.setReceiver(userId);
			push.setFromWhere("회의록");
			push.setPrjNo(Integer.toString(proceedings.getPrjNo()));
			push.setUrl("/project/proceedings/detail.do?prcdNo="+prcdNo+"&prjNo="+proceedings.getPrjNo());
			push.setMessage(proceedings.getUserNm()+" 님이 "+proceedings.getTitle()+" 회의록을 등록하였습니다.");
			pushDAO.insert(push);
		}
		
		
	}
	
	@Override
	public void modifyProceedings(ProceedingsVO proceedings) throws SQLException {
		
		String message = "";
		String message2 = "";
		
		PrcdUserVO prcdUser = new PrcdUserVO();
		prcdUser.setPrcdNo(proceedings.getPrcdNo());
		prcdUser.setUserId(proceedings.getParticipant());
		prcdUser.setSttCode(proceedings.getSttCode());
		
		proceedingsDAO.updateProceedings(proceedings);
		
		if(proceedings.getSttCode().equals("2")) {
			proceedingsDAO.updatePrcdUser(prcdUser);
			List<PrcdUserVO> userList = proceedingsDAO.selectUserIdByPrcdNo(proceedings.getPrcdNo());
			int i = 0;
			for(PrcdUserVO vo : userList) {
				if(vo.getSttCode().equals("2")) {
					i++;
					if(i==userList.size()) {
						proceedingsDAO.confirmProceedings(proceedings.getPrcdNo());
					}
					message = "승인";
					message2 = "회의록 내용은 상세페이지에서 확인 가능합니다.";
				}
			}
		} else if(proceedings.getSttCode().equals("3")) {
			proceedingsDAO.updatePrcdUser(prcdUser);
//			proceedingsDAO.returnPrcdUser(proceedings.getPrcdNo());
			proceedingsDAO.returnProceedings(proceedings.getPrcdNo());
			message = "반려";
			message2 = "반려 사유 : " + proceedings.getReturnMsg();
		}
		
		String register = userDAO.selectUserNmById(proceedings.getRegister());
		
		HistoryVO history = new HistoryVO();
		history.setUserId(proceedings.getRegister());
		history.setPrjNo(proceedings.getPrjNo());
		history.setFromWhere("proceedings");
		history.setUrl("/project/proceedings/detail.do?prcdNo="+proceedings.getPrcdNo()+"&prjNo="+proceedings.getPrjNo());
		history.setTitle(register+" 님이 "+proceedings.getTitle()+" 회의록을 "+message+"하였습니다.");
		history.setContent(message2);
		
		historyDAO.insertHistory(history);
		
		List<PrcdUserVO> userList = proceedingsDAO.selectUserIdByPrcdNo(proceedings.getPrcdNo());
		PushVO push = new PushVO();
		for(PrcdUserVO vo : userList) {
			push.setReceiver(vo.getUserId());
			push.setFromWhere("회의록");
			push.setPrjNo(Integer.toString(proceedings.getPrjNo()));
			push.setUrl("/project/proceedings/detail.do?prcdNo="+proceedings.getPrcdNo()+"&prjNo="+proceedings.getPrjNo());
			push.setMessage(register+" 님이 "+proceedings.getTitle()+" 회의록을 "+message+"하였습니다.");
			pushDAO.insert(push);
		}
		
		
		
	}

	@Override
	public void returnProceedings(ProceedingsVO proceeding) throws SQLException {
		
		PrcdUserVO prcdUser = new PrcdUserVO();
		prcdUser.setPrcdNo(proceeding.getPrcdNo());
		prcdUser.setUserId(proceeding.getRegister());
		prcdUser.setSttCode("3");
		
		proceedingsDAO.updatePrcdUser(prcdUser);
		
//		proceedingsDAO.returnPrcdUser(proceeding.getPrcdNo());
		proceedingsDAO.returnProceedings(proceeding.getPrcdNo());
		
		proceedingsDAO.updateProceedings(proceeding);
		
		ProceedingsVO proceedings = proceedingsDAO.selectProceedingsByPrcdNo(proceeding);
		
		String userNm = userDAO.selectUserNmById(proceedings.getRegister());
		HistoryVO history = new HistoryVO();
		history.setUserId(proceedings.getRegister());
		history.setPrjNo(proceedings.getPrjNo());
		history.setFromWhere("proceedings");
		history.setUrl("/project/proceedings/detail.do?prcdNo="+proceedings.getPrcdNo()+"&prjNo="+proceedings.getPrjNo());
		history.setTitle(userNm+" 님이 "+proceedings.getTitle()+" 회의록을 반려하였습니다.");
		history.setContent("반려 사유 : " + proceedings.getReturnMsg());
		
		historyDAO.insertHistory(history);
		
		List<PrcdUserVO> userList = proceedingsDAO.selectUserIdByPrcdNo(proceedings.getPrcdNo());
		PushVO push = new PushVO();
		for(PrcdUserVO vo : userList) {
			push.setReceiver(vo.getUserId());
			push.setFromWhere("회의록");
			push.setPrjNo(Integer.toString(proceedings.getPrjNo()));
			push.setUrl("/project/proceedings/detail.do?prcdNo="+proceedings.getPrcdNo()+"&prjNo="+proceedings.getPrjNo());
			push.setMessage(userNm+" 님이 "+proceedings.getTitle()+" 회의록을 반려하였습니다.");
			pushDAO.insert(push);
		}
	}

	@Override
	public byte[] getPdfFile(int prcdNo) throws SQLException {
		byte[] pdfArray = proceedingsDAO.selectPdfFile(prcdNo);
		
		return pdfArray;
	}

	private boolean isEmptyFile(MultipartFile multi) {
		boolean result = multi.isEmpty() && multi == null;

		return result;
	}

	@Override
	public byte[] getExcelFile(int prcdNo) throws SQLException {
		byte[] excelArray = proceedingsDAO.selectExcelFile(prcdNo);
		
		return excelArray;
	}
	
}
