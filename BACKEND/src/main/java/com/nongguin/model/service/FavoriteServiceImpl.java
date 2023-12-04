package com.nongguin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongguin.model.dao.FavoriteDao;
import com.nongguin.model.dto.Court;
import com.nongguin.model.dto.Favorite;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	private FavoriteDao favoriteDao;

	@Override
	public Boolean registFavorite(Favorite favorite) {
		int result = favoriteDao.registFavorite(favorite);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public Boolean cancelFavorite(Favorite favorite) {
		int result = favoriteDao.cancelFavorite(favorite);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public List<Court> getFavoritesByUserId(int userId) {
		return favoriteDao.getFavoritesByUserId(userId);
	}

}
