package com.nongguin.model.service;

import java.util.List;

import com.nongguin.model.dto.Match;
import com.nongguin.model.dto.SearchCondition;
import com.nongguin.model.dto.SearchResult;

public interface MatchService {

	List<SearchResult> getMatchsByDay(String matchDate);

	List<SearchResult> getMatchsBySearchCondition(SearchCondition condition);

	SearchResult getMatchByMatchId(int matchId);

	List<SearchResult> getMatchsByUserId(List matchIds);

	List<SearchResult> getRemainingMatchsByUserId(List matchIds);

	int getCourtCapacity(int matchId);

	int getMatchApplicantCnt(int matchId);

}
