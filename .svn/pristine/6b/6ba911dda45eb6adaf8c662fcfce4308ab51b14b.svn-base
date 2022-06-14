package kr.or.pms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import kr.or.pms.dao.UserDAO;
import kr.or.pms.dto.RecoverPwdVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.mail.MailMessageCommand;
import kr.or.pms.mail.MimeAttachNotifier;

public class CommonServiceImpl implements CommonService {
	
	private MimeAttachNotifier notifier;
	public void setMimeAttachNotifier(MimeAttachNotifier notifier) {
		this.notifier = notifier;
	}

	private HtmlTempService htmlTempService;
	public void setHtmlTempService(HtmlTempService htmlTempService) {
		this.htmlTempService = htmlTempService;
	}

	private UserDAO userDAO;
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	
	
	@Override
	public void sendPwdMail(Map<String, Object> paramMap) throws SQLException {
		
		String email = (String) paramMap.get("email");
		String id = (String) paramMap.get("id");
		
		
		String message = "";
		String isFail = "true";
		
		
		UserVO user = userDAO.selectUserById(id);
		
		if(user!=null) {
			String userEmail = user.getEmail();
			
			if(userEmail.equals(email)) {
				
					MailMessageCommand mailReq = new MailMessageCommand();
					String key = UUID.randomUUID().toString();
					
					RecoverPwdVO rpVO = new RecoverPwdVO();
					rpVO.setKey(key);
					rpVO.setUserId(id);
					userDAO.insertRecoverKey(rpVO);

					//필요한 파라미터 순서대로 세팅
					List<String> paramList = new ArrayList<String>();
					paramList.add(id);
					paramList.add("http://localhost/pms/common/recoverPwd.do?key="+ key);
					
					//TEMP 가져오기 헤더푸터의 tempNm, 컨테이너 tempNm, 파라미터 리스트 순
					String htmlStr = htmlTempService.getHtmlStr("basic", "recoverPwd", paramList);
					
					mailReq.setReceiver(userEmail);
					mailReq.setTitle("PROSPEC'S 계정 암호 재설정");
					mailReq.setContent(htmlStr);

					//메일 메세지 보내기	
					try {
						notifier.sendMail(mailReq);
						message = "성공적으로 이메일이 보내졌습니다.";
						isFail = "false";
					} catch(Exception e) {
						e.printStackTrace();
						paramMap.put("url", "security/fail");
						message = "메일 보내기를 실패했습니다.";
					}
					

				
				
			} else {				
				message = "사원정보가 일치하지 않습니다.";
			}
		} else {
			message = "존재하지 않는 사원번호입니다.";
		}
		
		paramMap.put("message", message);
		paramMap.put("isFail", isFail);
		
	}

}
