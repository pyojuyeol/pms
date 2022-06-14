package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.TmemLogVO;

public interface TmemLogDAO {
	
	// 팀원 로그 전체 리스트 조회
	public List<TmemLogVO> selectTeamMemberLogList(Criteria cri) throws SQLException;

	// 팀원 로그 리스트 조회
	public List<TmemLogVO> selectTeamMemberLogsByTno(int teamNo) throws SQLException;
	
	// 로그 조회
	public TmemLogVO selectTeamMemberLogByLno(int tmemLogNo) throws SQLException;
	
	// 팀원 추가 로그
	public void insertTeamMemberLog(TmemLogVO tmemLog) throws SQLException;
	
	// 팀원 삭제 로그
	public void deleteTeamMemberLog(TmemLogVO tmemLog) throws SQLException;
	
	int getSeqNextValue() throws SQLException;
	
}