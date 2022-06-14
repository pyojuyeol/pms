package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;

public interface HtmlTempService {

	String getHtmlStr(String upTempNm, String conTempNm, List<String> paramList) throws SQLException;

}
