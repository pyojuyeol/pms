package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.dto.HtmlTempVO;

public class HtmlTempDAOImpl implements HtmlTempDAO {

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<HtmlTempVO> selectHtmlTemp(String tempNm, String position) throws SQLException {
		
		HashMap<String, String> tempRequestData = new HashMap<String, String>();
		tempRequestData.put("tempNm", tempNm);
		tempRequestData.put("position", position);
		
		return session.selectList("HtmlTemp-Mapper.selectHtmlTemp", tempRequestData);
	}

}
