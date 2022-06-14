package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.dto.AttachVO;

public class RemoveUnusedFileDAOImpl implements RemoveUnusedFileDAO {
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<AttachVO> selectAllAttachPath() throws SQLException {
		List<AttachVO> attachPathList = session.selectList("RemoveUnusedFile-Mapper.selectAllAttachPath");
		
		return attachPathList;
	}

	@Override
	public List<String> selectAllCloudPath() throws SQLException {
		List<String> cloudPathList = session.selectList("RemoveUnusedFile-Mapper.selectAllCloudPath");
		
		return cloudPathList;
	}
	
	@Override
	public List<String> selectAllUserPicturePath() throws SQLException {
		List<String> cloudPathList = session.selectList("RemoveUnusedFile-Mapper.selectAllUserPicturePath");
		
		return cloudPathList;
	}
}
