package com.nongguin.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongguin.model.dao.MatchDao;
import com.nongguin.model.dto.Match;
import com.nongguin.model.dto.SearchCondition;
import com.nongguin.model.dto.SearchResult;

@Service
public class MatchServiceImpl implements MatchService {
	@Autowired
	MatchDao matchDao;

	@Override
	public List<SearchResult> getMatchsByDay(String matchDate) {
		return matchDao.getMatchsByDay(matchDate);
	}

	@Override
	public List<SearchResult> getMatchsBySearchCondition(SearchCondition condition) {
		return matchDao.getMatchsBySearchCondition(condition);
	}

	@Override
	public SearchResult getMatchByMatchId(int matchId) {
		return matchDao.getMatchByMatchId(matchId);
	}

	@Override
	public List<SearchResult> getMatchsByUserId(List matchIds) {
		List<SearchResult> result = new ArrayList<SearchResult>();
		for (int i = 0; i < matchIds.size(); i++) {
			SearchResult match = matchDao.getMatchByMatchId((int) matchIds.get(i));
			result.add(match);
		}
		return result;
	}

	@Override
	public List<SearchResult> getRemainingMatchsByUserId(List matchIds) {
		List<SearchResult> result = new ArrayList<SearchResult>();
		for (int i = 0; i < matchIds.size(); i++) {
			SearchResult match = matchDao.getRemainingMatchsByUserId((int) matchIds.get(i));
			if (match != null)
				result.add(match);
		}
		return result;
	}

	@Override
	public int getCourtCapacity(int matchId) {
		return matchDao.getCourtCapacity(matchId);
	}

	@Override
	public int getMatchApplicantCnt(int matchId) {
		return matchDao.getMatchApplicantCnt(matchId);
	}

}
