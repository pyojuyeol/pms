package kr.or.pms.service;

import java.util.List;
import java.util.Map;

import kr.or.pms.command.CloudDownloadCommand;
import kr.or.pms.command.CloudModifyCommand;
import kr.or.pms.command.CloudRemoveCommand;
import kr.or.pms.command.CloudVOCommand;
import kr.or.pms.command.CloudViewCriteria;
import kr.or.pms.dto.CloudDirectoryVO;
import kr.or.pms.dto.ProjectVO;


public interface CloudService {
	
	public Map<String, Object> getCloudItems(CloudViewCriteria cvc) throws Exception;
	
	public CloudDirectoryVO getCloudItemByUserId(String userId) throws Exception;
	public List<ProjectVO> getProjectListById(String userId) throws Exception;
	
	public int registCloudDirectory(CloudDirectoryVO cdv) throws Exception;
	
	public void registCloudFile(CloudVOCommand cvc) throws Exception;
	
	public CloudDirectoryVO getCloudDirectoryByDirNo(int dirNo) throws Exception;
	
	public void registFolder(CloudVOCommand cvc) throws Exception;
	
	public byte[] downloadMultiFile(List<CloudDownloadCommand> downloadInfoList, String userId) throws Exception; 
	
	public byte[] downloadFile(CloudDownloadCommand downloadInfo, String userId) throws Exception;
	
	public void modifyCloudName(CloudModifyCommand cmc) throws Exception;
	
	public void removeCloud(List<CloudRemoveCommand> crcList) throws Exception;
	
}
