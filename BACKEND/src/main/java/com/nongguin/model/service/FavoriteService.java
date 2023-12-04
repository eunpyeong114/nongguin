package com.nongguin.model.service;

import java.util.List;

import com.nongguin.model.dto.Court;
import com.nongguin.model.dto.Favorite;

public interface FavoriteService {

	Boolean registFavorite(Favorite favorite);

	Boolean cancelFavorite(Favorite favorite);

	List<Court> getFavoritesByUserId(int userId);

}
