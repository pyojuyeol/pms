package kr.or.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.pms.dto.FavoritesVO;

public class FavoritesDAOImpl implements FavoritesDAO {

	SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<FavoritesVO> selectFavoritesListById(String userId) throws Exception {
		List<FavoritesVO> favoritesList = session.selectList("Favorites-Mapper.selectFavoritesListById", userId);
		return favoritesList;
	}

	@Override
	public void insertFavorites(FavoritesVO favorites) throws Exception {
		session.update("Favorites-Mapper.insertFavorites", favorites);
	}

	@Override
	public void deleteFavorites(FavoritesVO favorites) throws Exception {
		session.delete("Favorites-Mapper.deleteFavorites", favorites);
	}
	
}
