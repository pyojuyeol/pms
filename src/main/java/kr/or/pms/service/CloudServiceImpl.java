package kr.or.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.or.pms.command.CloudDownloadCommand;
import kr.or.pms.command.CloudModifyCommand;
import kr.or.pms.command.CloudRemoveCommand;
import kr.or.pms.command.CloudVOCommand;
import kr.or.pms.command.CloudViewCriteria;
import kr.or.pms.dao.CloudDAO;
import kr.or.pms.dao.LogDAO;
import kr.or.pms.dao.ProjectDAO;
import kr.or.pms.dto.CloudDirectoryVO;
import kr.or.pms.dto.CloudVO;
import kr.or.pms.dto.FileLogVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.util.ServerToServerFileIo;

public class CloudServiceImpl implements CloudService {

	private CloudDAO cloudDAO;
	public void setCloudDAO(CloudDAO cloudDAO) {
		this.cloudDAO = cloudDAO;
	}
	
	private ProjectDAO projectDAO;
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
	private LogDAO logDAO;
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
	
	private String cloudUploadPath;
	public void setCloudUploadPath(String cloudUploadPath) {
		this.cloudUploadPath = cloudUploadPath;
	}

	public Map<String, Object> getCloudItems(CloudViewCriteria cvc) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		List<CloudDirectoryVO> cloudDirectoryList = null;
		List<CloudVO> cloudList = null;

		if (cvc.getUpDir() == 0) {
			cloudDirectoryList = cloudDAO.selectMainCloudDirectoryList(cvc);
			cloudList = cloudDAO.selectMainCloudList(cvc);
		} else {
			cloudDirectoryList = cloudDAO.selectSubCloudDirectoryList(cvc);
			cloudList = cloudDAO.selectSubCloudList(cvc);
		}

		dataMap.put("cloudDirectoryList", cloudDirectoryList);
		dataMap.put("cloudList", cloudList);

		return dataMap;
	}

	@Override
	public CloudDirectoryVO getCloudItemByUserId(String userId) throws Exception {
		CloudDirectoryVO cloudDirectory = null;
		cloudDirectory = cloudDAO.selectCloudDirectoryNoByUserId(userId);

		return cloudDirectory;
	}
	
	@Override
	public List<ProjectVO> getProjectListById(String userId) throws Exception {
		List<ProjectVO> projectList = null;
		projectList = projectDAO.selectJoinProjectById(userId);
		if (projectList != null) {
			for (ProjectVO project : projectList) {
				int dirNo = cloudDAO.selectHomeDirNoByPrjNo(project.getPrjNo());
				
				project.setDirNo(dirNo);
			}
		}
		
		return projectList;
	}

	@Override
	public int registCloudDirectory(CloudDirectoryVO cdv) throws Exception {
		int dirNo = cloudDAO.selectCloudDirectorySeqNextValue();
		cdv.setDirNo(dirNo);
		
		String result = ServerToServerFileIo.registCloudDirectory(cdv.getDirPath());
		if (result.equals("success")) {
			cloudDAO.insertCloudDirectory(cdv);
		}

		return cdv.getDirNo();
	}

	@Override
	public void registCloudFile(CloudVOCommand cvc) throws Exception {
		String userId = cvc.getUserId();
		MultipartFile file = cvc.getUploadFile();
		cvc.setDirPath(cvc.getDirPath().substring(1));
		CloudVO cloud = ServerToServerFileIo.getCloudsByUploadFiles(userId, file, cvc.getDirPath());

		int cldFileNo = cloudDAO.selectCloudFileSeqNextValue();
		cloud.setCldFileNo(cldFileNo);
		cloud.setRegister(cvc.getUserId());
		cloud.setDirNo(cvc.getDirNo());
		cloud.setUpdater(cvc.getUserId());

		cloudDAO.insertCloudFile(cloud);
	}

	@Override
	public CloudDirectoryVO getCloudDirectoryByDirNo(int dirNo) throws Exception {
		CloudDirectoryVO cdv = cloudDAO.selectCloudDirectoryByDirNo(dirNo);
		return cdv;
	}

	@Override
	public void registFolder(CloudVOCommand cvc) throws Exception {
		String relativeFilePath = cvc.getFilePath();
		
		String dirPath = cvc.getDirPath();
		String processedPath = relativeFilePath.substring(0, relativeFilePath.lastIndexOf("/"));

		CloudDirectoryVO cdv = null;

		if (processedPath.indexOf("/") <= 0) {
			cvc.setDirPath(cvc.getDirPath() + "/" + processedPath);
			

			cdv = cloudDAO.selectCloudDirectoryByPath(cvc.getDirPath());
			if (cdv != null) {
				cvc.setDirNo(cdv.getDirNo());
				registCloudFile(cvc);
			} else {
				cvc.setDirNm(processedPath);
				cvc.setDirPath(dirPath + "/" + cvc.getDirNm());
				int dirNo = registCloudDirectory(cvc.toCloudDirectory());

				cvc.setDirNo(dirNo);
				registCloudFile(cvc);

			}
		} else {
			String[] paths = processedPath.split("/");
			for (int i = 0; i < paths.length; i++) {
				dirPath = dirPath + "/" + paths[i];

				cvc.setDirNm(paths[i]);
				cvc.setDirPath(dirPath);
				
				cdv = cloudDAO.selectCloudDirectoryByPath(dirPath);
				if (cdv == null) {
					int dirNo = registCloudDirectory(cvc.toCloudDirectory());
					cvc.setUpDir(dirNo);
					if (i == paths.length - 1) {
						cvc.setDirNo(dirNo);
						cvc.setDirPath(dirPath);
						registCloudFile(cvc);
					}
				} else {
					cvc.setDirNo(cdv.getDirNo());
					cvc.setUpDir(cdv.getDirNo());
					if (i == paths.length - 1) {
						cvc.setDirPath(dirPath);
						registCloudFile(cvc);
					}
				}
			}
		}
	}

	@Override
	public byte[] downloadMultiFile(List<CloudDownloadCommand> downloadInfoList, String userId) throws Exception {
		byte[] bytes = ServerToServerFileIo.getZipFile(downloadInfoList);

		for (CloudDownloadCommand cdc : downloadInfoList) {
			String type = cdc.getType();
			String path = cdc.getPath();

			if (type.equals("folder")) {
				updateDownCnt(path, userId, cdc.getIp());
			}
			if (type.equals("file")) {
				updateDownCnt(path, userId, cdc.getIp());
			}
		}

		return bytes;
	}

	@Override
	public byte[] downloadFile(CloudDownloadCommand downloadInfo, String userId) throws Exception {
		String path = downloadInfo.getPath();
		String savedPath = path.substring(0, path.lastIndexOf("/"));
		String fileName = path.substring(path.lastIndexOf("/") + 1);
		
		byte[] bytes = ServerToServerFileIo.getFile(savedPath, fileName);
		
		updateDownCnt(path, userId, downloadInfo.getIp());
				
		return bytes;
	}
	
	// 다운로드 횟수 증가시켜주는 함수 리펙토링
	public void updateDownCnt(String path, String userId, String ip) throws SQLException {
		List<CloudVO> cloudList = cloudDAO.selectCloudListByPath(path);

		for (CloudVO cloud : cloudList) {
			cloud.setDownCnt(cloud.getDownCnt() + 1);
			cloudDAO.updateCloudDownCnt(cloud);
			
			FileLogVO fileLog = new FileLogVO();
			int fileDownLogNo = logDAO.selectFileLogSeqNext();
			
			fileLog.setIp(ip);
			fileLog.setFileDownLogNo(fileDownLogNo);
			fileLog.setUserId(userId);
			fileLog.setFileKey("cloud");
			fileLog.setFileNo(cloud.getCldFileNo());
			fileLog.setFileNm(cloud.getStoredNm());
			
			logDAO.insertFileDownloadLog(fileLog);
		}
	}

	@Override
	public void modifyCloudName(CloudModifyCommand cmc) throws Exception {
		String oldPath = cmc.getPath();
		if (cmc.getType().equals("file")) {
			cmc = ServerToServerFileIo.modifyCloudName(cmc);
			
			cloudDAO.updateCloudFileName(cmc.toCloudVO());
		} else if(cmc.getType().equals("folder")) {
			
			cmc = ServerToServerFileIo.modifyCloudName(cmc);
			
			String newPath = cmc.getPath();
			newPath = newPath.substring(cloudUploadPath.length());
			
			cmc.setPath(newPath);
			// 부모 폴더 이름 먼저 변경 하고~
			cloudDAO.updateCloudDirectoryPath(cmc.toCloudDirectoryVO());
			
			List<CloudDirectoryVO> cloudDirectoryList = cloudDAO.selectSubCloudDirectoryByPath(oldPath);
			
			for (CloudDirectoryVO cloudDirectory : cloudDirectoryList) {
				if (cloudDirectory.getDirNo() == cmc.getDirNo()) {
					continue;
				}
				String selectedPath = cloudDirectory.getDirPath();
				
				cloudDirectory.setDirPath(newPath + selectedPath.substring(oldPath.length()));
				
				cloudDAO.updateCloudDirectoryPath(cloudDirectory);
			}
			
			List<CloudVO> cloudList = cloudDAO.selectCloudListByPath(cloudUploadPath + oldPath);
			
			for (CloudVO cloud : cloudList) {
				String oldCloudPath = cloud.getPath();
				String oldCloudDirectoryPath = cloudUploadPath + oldPath;
				cloud.setPath(cloudUploadPath + newPath + oldCloudPath.substring(oldCloudDirectoryPath.length()));
				cloudDAO.updateCloudPath(cloud);
			}
			
		}
	}

	@Override
	public void removeCloud(List<CloudRemoveCommand> crcList) throws Exception {
		for (CloudRemoveCommand crc : crcList) {
				crc.getType();
			if (crc.getType().equals("folder")) {
				CloudDirectoryVO cloudDirectory = cloudDAO.selectCloudDirectoryByDirNo(crc.getDirNo());
				
				String savedPath = cloudDirectory.getDirPath().substring(0, cloudDirectory.getDirPath().lastIndexOf("/"));
				
				String fileName = cloudDirectory.getDirPath().substring(cloudDirectory.getDirPath().lastIndexOf("/"));
				
				String result = ServerToServerFileIo.removeCloudFile(cloudUploadPath + savedPath, fileName);
				if (result.equals("success")) {
					List<CloudDirectoryVO> cloudDirectoryList = cloudDAO.selectSubCloudDirectoryByPath(cloudDirectory.getDirPath());
					for (CloudDirectoryVO cloudDir : cloudDirectoryList) {
						cloudDAO.deleteCloudDirectory(cloudDir.getDirNo());
					}
				}
			}
			if (crc.getType().equals("file")) {
				CloudVO cloud = cloudDAO.selectCloudByCldFileNo(crc.getCldFileNo());

				String savedPath = cloud.getPath().substring(0, cloud.getPath().lastIndexOf("/"));
				String fileName = cloud.getStoredNm();
				
				String result = ServerToServerFileIo.removeCloudFile(savedPath, fileName);
				
				if (result.equals("success")) {
					cloudDAO.deleteCloud(crc.getCldFileNo());
				}
			}
		}
	}


}
