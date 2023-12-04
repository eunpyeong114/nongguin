package com.nongguin.model.dao;

import java.util.List;

import com.nongguin.model.dto.Court;
import com.nongguin.model.dto.Favorite;

public interface FavoriteDao {

	int registFavorite(Favorite favorite);

	int cancelFavorite(Favorite favorite);

	List<Court> getFavoritesByUserId(int userId);

}
