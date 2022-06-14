package kr.or.pms.service;

import java.util.List;

import kr.or.pms.dao.FavoritesDAO;
import kr.or.pms.dto.FavoritesVO;

public class FavoritesServiceImpl implements FavoritesService {

	FavoritesDAO favoritesDAO;
	public void setFavoritesDAO(FavoritesDAO favoritesDAO) {
		this.favoritesDAO = favoritesDAO;
	}
	
	@Override
	public List<FavoritesVO> getFavoritesList(String userId) throws Exception {
		List<FavoritesVO> favoritesList = favoritesDAO.selectFavoritesListById(userId);
		return favoritesList;
	}
	
	@Override
	public void registFavorites(FavoritesVO favorites) throws Exception {
		favoritesDAO.insertFavorites(favorites);
	}
	
	@Override
	public void removeFavorites(FavoritesVO favorites) throws Exception {
		favoritesDAO.deleteFavorites(favorites);
	}
	
	
}
