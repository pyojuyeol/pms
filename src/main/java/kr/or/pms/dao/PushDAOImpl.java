package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.PushVO;

public class PushDAOImpl implements PushDAO {
	
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<PushVO> selectPushCriteria(Map<String, Object> searchMap) throws SQLException {
		
		List<PushVO> pushList = null;
		Criteria cri = (Criteria) searchMap.get("cri");
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		pushList = session.selectList("Push-Mapper.selectPushList",searchMap, rowBounds);	
		
		return pushList;
	}
	
	@Override
	public int selectPushCriteriaTotalCount(Map<String, Object> searchMap) throws SQLException {
		return session.selectOne("Push-Mapper.selectPushListCount",searchMap);
	}
	

	// 메뉴바 알림 리스트 조회
	@Override
	public List<PushVO> selectMenuPushList(String userId) {
		List<PushVO> menuPushList = session.selectList("Push-Mapper.selectMenuPushList", userId);
		return menuPushList;
	}
	// 메뉴바 읽지 않은 알림 갯수 조회
	@Override
	public int selectReadCodeNCount(String userId) {
		return session.selectOne("Push-Mapper.selectReadCodeNCount", userId);
	}


	@Override
	public int insert(PushVO push) throws SQLException {
		return session.update("Push-Mapper.insert", push);
	}

	@Override
	public int updateReadCode(List<Integer> targetPushNoList) throws SQLException {
		return session.update("Push-Mapper.updateReadCode", targetPushNoList);
	}

	@Override
	public int delete(List<Integer> targetPushNoList) throws SQLException {
		return session.update("Push-Mapper.delete", targetPushNoList);
	}



}
