package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.command.CloudViewCriteria;
import kr.or.pms.dto.CloudDirectoryVO;
import kr.or.pms.dto.CloudVO;

public class CloudDAOImpl implements CloudDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	public List<CloudDirectoryVO> selectMainCloudDirectoryList(CloudViewCriteria cvc) throws SQLException {
		List<CloudDirectoryVO> cloudDirectoryList = null;
		cloudDirectoryList = session.selectList("Cloud-Mapper.selectMainCloudDirectoryList", cvc);
		
		return cloudDirectoryList;
	}
	
	public List<CloudVO> selectMainCloudList(CloudViewCriteria cvc) throws SQLException {
		List<CloudVO> cloudList = null;
		cloudList = session.selectList("Cloud-Mapper.selectMainCloudList", cvc);
		
		return cloudList;
	}

	@Override
	public List<CloudDirectoryVO> selectSubCloudDirectoryList(CloudViewCriteria cvc) throws SQLException {
		List<CloudDirectoryVO> cloudDirectoryList = null;
		cloudDirectoryList = session.selectList("Cloud-Mapper.selectSubCloudDirectoryList", cvc);
		
		return cloudDirectoryList;
	}

	@Override
	public List<CloudVO> selectSubCloudList(CloudViewCriteria cvc) throws SQLException {
		List<CloudVO> cloudList = null;
		cloudList = session.selectList("Cloud-Mapper.selectSubCloudList", cvc);
		
		return cloudList;
	}

	@Override
	public CloudDirectoryVO selectCloudDirectoryNoByUserId(String userId) throws SQLException {
		CloudDirectoryVO cloudDirectory = null;
		cloudDirectory = session.selectOne("Cloud-Mapper.selectCloudDirectoryNoByUserId", userId);
		
		return cloudDirectory;
	}

	@Override
	public void insertCloudDirectory(CloudDirectoryVO cdv) throws SQLException {
		session.update("Cloud-Mapper.insertCloudDirectory", cdv);
	}
	
	@Override
	public void insertCloudFile(CloudVO cloud) throws SQLException {
		session.update("Cloud-Mapper.insertCloudFile", cloud);
	}

	@Override
	public int selectCloudDirectorySeqNextValue() throws SQLException {
		int cloudDirectoryNextValue = session.selectOne("Cloud-Mapper.selectCloudDirectorySeqNext");
		
		return cloudDirectoryNextValue;
	}

	@Override
	public int selectCloudFileSeqNextValue() throws SQLException {
		int cloudFileNextValue = session.selectOne("Cloud-Mapper.selectCloudFileSeqNext");
		
		return cloudFileNextValue;
	}

	@Override
	public CloudDirectoryVO selectCloudDirectoryByDirNo(int dirNo) throws SQLException {
		CloudDirectoryVO cdv = session.selectOne("Cloud-Mapper.selectCloudDirectoryByDirNo", dirNo);
		
		return cdv;
	}
	
	@Override
	public int selectHomeDirNoByPrjNo(int prjNo) throws SQLException {
		int dirNo = session.selectOne("Cloud-Mapper.selectHomeDirNoByPrjNo", prjNo);
		
		return dirNo;
	}
	
	@Override
	public CloudVO selectCloudByCldFileNo(int cldFileNo) throws SQLException {
		CloudVO cloud = session.selectOne("Cloud-Mapper.selectCloudByCldFileNo", cldFileNo);
		
		return cloud;
	}

	@Override
	public CloudDirectoryVO selectCloudDirectoryByPath(String dirPath) throws SQLException {
		CloudDirectoryVO cdv = session.selectOne("Cloud-Mapper.selectCloudDirecotryByPath", dirPath);
		
		return cdv;
	}
	
	@Override
	public List<CloudDirectoryVO> selectSubCloudDirectoryByPath(String dirPath) throws SQLException {
		List<CloudDirectoryVO> cloudDirectoryList = session.selectList("Cloud-Mapper.selectSubCloudDirectoryByPath", dirPath);
		
		return cloudDirectoryList;
	}

	@Override
	public List<CloudVO> selectCloudListByPath(String path) throws SQLException {
		List<CloudVO> cloudList = session.selectList("Cloud-Mapper.selectCloudListByPath", path);
		
		return cloudList;
	}

	@Override
	public void updateCloudDownCnt(CloudVO cloud) throws SQLException {
		session.update("Cloud-Mapper.updateCloudDownCnt", cloud);
		
	}

	@Override
	public void updateCloudFileName(CloudVO cloud) throws SQLException {
		session.update("Cloud-Mapper.updateCloudFileName", cloud);
	}

	@Override
	public void updateCloudDirectoryName(CloudDirectoryVO cloudDirectory) throws SQLException {
		session.update("Cloud-Mapper.updateCloudDirectoryName", cloudDirectory);
	}

	@Override
	public void updateCloudDirectoryPath(CloudDirectoryVO cloudDirectory) throws SQLException {
		session.update("Cloud-Mapper.updateCloudDirectoryPath", cloudDirectory);
	}
	
	@Override
	public void updateCloudPath(CloudVO cloud) throws SQLException {
		session.update("Cloud-Mapper.updateCloudPath", cloud);
	}

	@Override
	public void deleteCloudDirectory(int dirNo) throws SQLException {
		session.update("Cloud-Mapper.deleteCloudDirectory", dirNo);
	}

	@Override
	public void deleteCloud(int cldFileNo) throws SQLException {
		session.update("Cloud-Mapper.deleteCloud", cldFileNo);
	}

	@Override
	public void insertUserCloudDirectory(CloudDirectoryVO cdv) throws SQLException {
		session.update("Cloud-Mapper.insertUserCloudDirectory", cdv);
	}

	@Override
	public void insertProjectCloudDirectory(CloudDirectoryVO cdv) throws SQLException {
		session.update("Cloud-Mapper.insertProjectCloudDirectory", cdv);
	}

	
}
