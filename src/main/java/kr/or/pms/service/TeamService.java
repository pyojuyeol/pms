package kr.or.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.pms.command.Criteria;
import kr.or.pms.dto.TeamVO;

public interface TeamService {

	// 팀리스트 조회
	Map<String, Object> getTeamList(Criteria cri) throws Exception;
	List<TeamVO> getTeamList() throws Exception;
	
	// 팀 상세조회
	public TeamVO getTeam(int teamNo) throws Exception;
	
	// 팀 등록
	void regist(TeamVO team, String register) throws Exception;
	
	// 팀 수정
	void modify(TeamVO team) throws Exception;
	
	// 팀 삭제 (상태변경)
	void remove(int teamNo) throws Exception;
	

	int getSeqNextValue() throws SQLException;
	
}
