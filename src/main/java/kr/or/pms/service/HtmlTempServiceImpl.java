package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.dao.HtmlTempDAO;
import kr.or.pms.dto.HtmlTempVO;

public class HtmlTempServiceImpl implements HtmlTempService {

	private HtmlTempDAO htmlTempDAO;
	public void setHtmlTempDAO(HtmlTempDAO htmlTempDAO) {
		this.htmlTempDAO = htmlTempDAO;
	}

	@Override
	public String getHtmlStr(String upTempNm, String conTempNm, List<String> paramList) throws SQLException {

		String header = "";
		String footer = "";
		if(!upTempNm.equals("")) {
			List<HtmlTempVO> headerList = htmlTempDAO.selectHtmlTemp(upTempNm, "header");
			if(headerList != null) for(HtmlTempVO vo : headerList) {
				header += vo.getContent();
			}
						
			List<HtmlTempVO> footerList = htmlTempDAO.selectHtmlTemp(upTempNm, "footer");
			if(footerList != null) for(HtmlTempVO vo : footerList) {
				footer += vo.getContent();
			}
		}
		
		String content = "";
		int index = 0;
		List<HtmlTempVO> conTempCon = htmlTempDAO.selectHtmlTemp(conTempNm, "container");
		System.out.println(paramList.get(0));
		
		if(conTempCon != null) for(HtmlTempVO vo : conTempCon) {
			content += vo.getContent();
			
			if(index >= paramList.size()) break;			
			content += paramList.get(index);
			index++;
			
		}
		
		String htmlStr = header+content+footer;
		
		return htmlStr;
	}

}
