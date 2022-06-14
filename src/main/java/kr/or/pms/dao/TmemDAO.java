package kr.or.pms.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.pms.dto.TmemVO;

public interface TmemDAO {
	
	// 팀원 리스트 조회 (활성 상태)
	public List<TmemVO> selectTeamMembersByTno(int teamNo) throws SQLException;
	
	// 팀원 리스트 조회 (비활성 상태)
	public List<TmemVO> selectTeamMembersByTnoD(int teamNo) throws SQLException;
	
	// 팀원 조회
	public TmemVO selectTeamMemberById(TmemVO tmem) throws SQLException;
	
	// 팀원 추가
	public void insertTeamMember(TmemVO tmem) throws SQLException;
	
	// 팀원 수정
	public void updateTeamMember(TmemVO tmem) throws SQLException;
	
	// 팀원 삭제
	public void deleteTeamMember(TmemVO tmem) throws SQLException;
	
	// 전체 팀원 삭제
	public void deleteAllTeamMember(int teamNo) throws SQLException;
	
	int getSeqNextValue() throws SQLException;
}
