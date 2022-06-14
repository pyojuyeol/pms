package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.TeamVO;

public interface TeamDAO {
	
	// 팀 리스트 조회
	List<TeamVO> selectTeamCriteria(Criteria cri) throws Exception;
	List<TeamVO> selectTeamList() throws Exception;
	
	// 리스트 전체 개수
	int selectTeamListTotalCount(Criteria cri) throws Exception;
	
	// 팀 정보 조회
	TeamVO selectTeamByTno(int teamNo) throws SQLException;
	
	// 팀 추가
	void insertTeam(TeamVO team) throws SQLException;
	
	// 팀 수정
	void updateTeam(TeamVO team) throws SQLException;
	
	// 팀 삭제
	void deleteTeam(int teamNo) throws SQLException;
	
	int getSeqNextValue() throws SQLException;
}
