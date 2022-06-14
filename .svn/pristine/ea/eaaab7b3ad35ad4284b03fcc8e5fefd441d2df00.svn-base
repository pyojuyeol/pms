package kr.or.pms.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.sun.mail.iap.Response;

import kr.or.pms.exception.ConsoleLog;
import kr.or.pms.exception.ProjectAccessException;

@RestControllerAdvice
public class ExceptionRestControllerAdvisor {
	
	
	private HttpStatus result;
	
	@ExceptionHandler(ProjectAccessException.class)
	public ResponseEntity<String> projectNotFoundExceptionPage(Exception e){
		String message =e.getMessage();
		result = HttpStatus.INTERNAL_SERVER_ERROR;
		//헤더 설정
//		HttpHeaders header = new HttpHeaders();
//		header.setContentType(new MediaType("application", "text", Charset.forName("UTF-8")));
//				
//		e.printStackTrace();
		
		return new ResponseEntity<String>(message,result);
	}
	
	@ExceptionHandler(ConsoleLog.class)
	public ResponseEntity<String> ConsoleLogPage(Exception e,Model model,HttpSession session){
		String message =e.getMessage();
		result = HttpStatus.INTERNAL_SERVER_ERROR;
		//헤더 설정
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "text", Charset.forName("UTF-8")));
//		e.printStackTrace();
		
		return new ResponseEntity<String>(message,result);
	}
	
//	@ExceptionHandler(IOException.class)
//	public ResponseEntity<String> ioException(Exception e){
//		String message = "파일이 존재하지 않습니다.";
//		result = HttpStatus.INTERNAL_SERVER_ERROR;
//		
//		e.printStackTrace();
//		
//		return new ResponseEntity<String>(message,result);
//	}
//	
//	@ExceptionHandler(SQLException.class)
//	public ResponseEntity<String> sqlException(Exception e){
//		String message = "DB에 장애로 서비스가 중단됩니다.";
//		result = HttpStatus.INTERNAL_SERVER_ERROR;
//		
//		e.printStackTrace();
//		
//		return new ResponseEntity<String>(message,result);
//	}
//	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> Exception(Exception e){
//		String message = "서버 장애로 서비스가 중단됩니다.";
//		result = HttpStatus.INTERNAL_SERVER_ERROR;
//		
//		//헤더 설정
//		HttpHeaders header = new HttpHeaders();
//		header.setContentType(new MediaType("application", "text", Charset.forName("UTF-8")));
//		
//		e.printStackTrace();
//		
//		return new ResponseEntity<String>(message, header, result);
//	}
	
	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<String> BadRequestException(Exception e){
		String message = "유효하지 않은 요청입니다.";
		result = HttpStatus.BAD_REQUEST;
		
		//헤더 설정
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "text", Charset.forName("UTF-8")));
		
		e.printStackTrace();
		
		return new ResponseEntity<String>(message, header, result);
	}
}









