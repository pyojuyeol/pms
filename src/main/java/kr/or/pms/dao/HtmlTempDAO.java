package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.dto.HtmlTempVO;

public interface HtmlTempDAO {

	List<HtmlTempVO> selectHtmlTemp(String tempNm, String position) throws SQLException;


}
