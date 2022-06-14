package kr.or.pms.scheduler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import kr.or.pms.dao.RemoveUnusedFileDAO;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.util.ServerToServerFileIo;

public class RemoveUnusedFileScheduler {
	
	private RemoveUnusedFileDAO removeUnusedFileDAO;
	private String filePath;
	
	public void setRemoveUnusedFileDAO(RemoveUnusedFileDAO removeUnusedFileDAO) {
		this.removeUnusedFileDAO = removeUnusedFileDAO;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public void removeUnusedFile() {
		Map<String, Object> filePathData = new HashMap<String, Object>();
		
		List<String> attachPathList = null;
		List<String> cloudPathList = null;
		List<String> userPicPathList = null;
		
		try {
			attachPathList = getAttachPathList();
			cloudPathList = getCloudPathList();
			userPicPathList = getUserPicPathList();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		for (String path : userPicPathList) {
			path += filePath + path;
		}
		
		filePathData.put("attachPathList", attachPathList);
		filePathData.put("cloudPathList", cloudPathList);
		filePathData.put("userPicturePathList", userPicPathList);
		
		try {
			ServerToServerFileIo.removeUnusedFile(filePathData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getAttachPathList() throws SQLException{
		List<AttachVO> attachList = removeUnusedFileDAO.selectAllAttachPath();
		List<String> attachPathList = new ArrayList<String>();
		
		for (AttachVO attach : attachList) {
			String savedPath = attach.getAttachPath() + "/" + attach.getStroredNm();
			attachPathList.add(savedPath);
		}
		
		return attachPathList;
	}
	
	public List<String> getCloudPathList() throws SQLException{
		List<String> cloudPathList = removeUnusedFileDAO.selectAllCloudPath();
		
		return cloudPathList;
	}
	
	public List<String> getUserPicPathList() throws SQLException {
		List<String> userPicPathList = removeUnusedFileDAO.selectAllUserPicturePath();
		
		return userPicPathList;
	}
}
