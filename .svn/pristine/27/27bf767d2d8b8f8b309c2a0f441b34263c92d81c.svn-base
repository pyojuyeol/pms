package kr.or.pms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dao.PushDAO;
import kr.or.pms.dto.PushVO;

public class PushServiceImpl implements PushService {
	
	private PushDAO pushDAO;
	public void setPushDAO(PushDAO pushDAO) {
		this.pushDAO = pushDAO;
	}

	@Override
	public Map<String, Object> getList(Criteria cri, String userId) throws SQLException {
		
		Map <String, Object> searchMap = new HashMap<>();
		searchMap.put("cri", cri);
		searchMap.put("userId", userId);
		
		List<PushVO> pushList = pushDAO.selectPushCriteria(searchMap);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(pushDAO.selectPushCriteriaTotalCount(searchMap));

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("pageMaker", pageMaker);
		dataMap.put("pushList", pushList);

		return dataMap;
	}

	@Override
	public Map<String, Object> getMenuPushList(String userId) throws SQLException {
		
		int nCnt = pushDAO.selectReadCodeNCount(userId);
		List<PushVO> menuPushList = pushDAO.selectMenuPushList(userId);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("nCnt", nCnt);
		dataMap.put("menuPushList", menuPushList);

		return dataMap;
	}
	
	@Override
	public int regist(PushVO push) throws SQLException {
		return pushDAO.insert(push);
	}
	
	@Override
	public void registPushList(List<PushVO> pushList) throws SQLException {
		
		for(PushVO push : pushList) {
			pushDAO.insert(push);
		}
		
	}

	@Override
	public int modifyReadCode(List<Integer> targetPushNoList) throws SQLException {
		return pushDAO.updateReadCode(targetPushNoList);
	}
	@Override
	public int modifyReadCode(int pushNo) throws SQLException {
		List<Integer> targetPushNoList = new ArrayList<>();
		targetPushNoList.add(pushNo);
		return pushDAO.updateReadCode(targetPushNoList);
	}

	@Override
	public int remove(List<Integer> targetPushNoList) throws SQLException {
		return pushDAO.delete(targetPushNoList);
	}


	


}
