package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.CloudViewCriteria;
import kr.or.pms.dto.CloudDirectoryVO;
import kr.or.pms.dto.CloudVO;

public interface CloudDAO {
	// 메인 cloud 화면 띄우기
	public List<CloudDirectoryVO> selectMainCloudDirectoryList(CloudViewCriteria cvc) throws SQLException;
	public List<CloudVO> selectMainCloudList(CloudViewCriteria cvc) throws SQLException;
	
	// 서브 cloud 화면 띄우기
	public List<CloudDirectoryVO> selectSubCloudDirectoryList(CloudViewCriteria cvc) throws SQLException;
	public List<CloudVO> selectSubCloudList(CloudViewCriteria cvc) throws SQLException;
	
	// 유저의 클라우드 home 리스트 띄우기
	public CloudDirectoryVO selectCloudDirectoryNoByUserId(String userId) throws SQLException;
	
	// 폴더 등록하기
	public void insertCloudDirectory(CloudDirectoryVO cdv) throws SQLException;
	
	// 파일 등록하기
	public void insertCloudFile(CloudVO cv) throws SQLException;
	
	// cloudDirectory Seq의 다음 값 받아오기
	public int selectCloudDirectorySeqNextValue() throws SQLException;
	
	// cloudFile Seq의 다음 값 받아오기
	public int selectCloudFileSeqNextValue() throws SQLException;
	
	// cloudDirectory를 dirNo로 가죠오기
	public CloudDirectoryVO selectCloudDirectoryByDirNo(int dirNo) throws SQLException;
	
	// cldFileNo로 CloudVO 가져오기
	public CloudVO selectCloudByCldFileNo(int cldFileNo) throws SQLException;
	
	// prjNo로 Home dirNO 값 가져오기
	public int selectHomeDirNoByPrjNo(int prjNo) throws SQLException;
	
	// 파일경로, updir로 cld_dir 있는지 check
	public CloudDirectoryVO selectCloudDirectoryByPath(String dirPath) throws SQLException;
	public List<CloudDirectoryVO> selectSubCloudDirectoryByPath(String dirPath) throws SQLException;
	
	// 다운로드 횟수 수정을 위한 cloudList 반환
	public List<CloudVO> selectCloudListByPath(String path) throws SQLException;
	
	// 다운로드 횟수 수정
	public void updateCloudDownCnt(CloudVO cloud) throws SQLException;
	
	// 파일 이름 수정
	public void updateCloudFileName(CloudVO cloud) throws SQLException;
	
	// 폴더 이름 수정
	public void updateCloudDirectoryName(CloudDirectoryVO cloudDirectory) throws SQLException;
	
	// 폴더 이름 수정 
	public void updateCloudDirectoryPath(CloudDirectoryVO cloudDirectory) throws SQLException;
	public void updateCloudPath(CloudVO cloud) throws SQLException;
	
	// 폴더 삭제
	public void deleteCloudDirectory(int dirNo) throws SQLException;
	
	// 파일 삭제
	public void deleteCloud(int cldFileNo) throws SQLException;
	
	// Home cloud 만들기
	public void insertUserCloudDirectory(CloudDirectoryVO cdv) throws SQLException;
	public void insertProjectCloudDirectory(CloudDirectoryVO cdv) throws SQLException;
}
