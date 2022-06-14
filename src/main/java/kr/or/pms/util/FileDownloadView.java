package kr.or.pms.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.View;



public class FileDownloadView implements View {

	private String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		String savedPath = (String) model.get("savedPath");
//		String fileName = (String) model.get("fileName");
//		
//		String filePath = savedPath + File.separator + fileName;
		
		// 보낸 파일 설정.
		byte[] bytes = (byte[]) model.get("bytes");
		String fileName = (String) model.get("fileName");
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1); 
		
		InputStream inStream = new ByteArrayInputStream(bytes);

		// 확장로 MIME를 결정한다.
		String mimeType = URLConnection.guessContentTypeFromName(extension);
		if (mimeType != null) {
			this.contentType = mimeType;
		}

		// response 수정.
		response.setContentType(mimeType);
		response.setContentLength((int) bytes.length);

		String headerKey = "Content-Disposition";
		// 한글 깨짐 방지 : ISO-8859-1
		String sendFileName = MakeFileName.parseFileNameFromUUID(fileName, "\\$\\$");
		String header = request.getHeader("User-Agent");
		if (header.contains("MSIE")) {
			sendFileName = URLEncoder.encode(sendFileName, "UTF-8");
		} else {
			sendFileName = new String(sendFileName.getBytes("utf-8"), "ISO-8859-1");
		}
		String headerValue = String.format("attachment; Filename=\"%s\"", sendFileName);
		response.setHeader(headerKey, headerValue);

		// 파일 내보내기
		OutputStream outStream = response.getOutputStream();
		try {
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
		} finally {
			outStream.close();
			inStream.close();
		}

	}

}
