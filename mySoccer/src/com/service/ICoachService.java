package com.service;

import java.util.List;

import com.domain.Club;
import com.domain.Coach;
import com.domain.CoachPlan;
import com.domain.PageBean;
import com.domain.Player;
import com.domain.Share;
import com.domain.SuggestForCoach;
import com.domain.Summary;

public interface ICoachService {

	public void addPlayerByCoach(Player player);

	public PageBean<Player> showAllPlayer(Integer currPage, Coach coach);

	public void deletePlayerByCoach(Player player);

	public void wiriteShare(Share share);

	public PageBean<Share> showAllShare(Integer currPage, Coach attribute);

	public Share showShareArtical(String shareId);

	public void updateShareArtical(Share share);

	public void deleteShare(String shareId);

	public Player playerLogin(Player player);

	public void writeCoachPlan(CoachPlan coachPlan);

	public PageBean<CoachPlan> showAllCoachPlan(Integer currPage,
			Coach attribute);

	public CoachPlan findCoachPlanByPlanId(String planId);

	public void deleteCoachPlan(String planId);

	public PageBean<CoachPlan> showUndoneCoachPlan(Integer currPage,
			Coach attribute);

	public void updateCoachPlan(CoachPlan oldCoachPlan);

	public List<Player> findAllPlayerByCoach(Coach planCoach);

	public Player findPlayerByPid(String pId);

	public void writeCoachPlanSummary(Summary summary);

	public List<Summary> findSummaryByPlan(String planId);

	public Summary findSummaryBySummaryId(String summaryId);

	public void updateSummary(Summary summary);

	public String findSummaryByPlanIdAndPId(String planId, String pid);

	public void deleteSummary(Summary checkSummary);

	public void writeNewSuggestForCoach(SuggestForCoach suggestForCoach);

	public void deleteSuggestForCoach(String sugCoachId);

	public List<SuggestForCoach> findUnMarkSuggestByCoach(Coach coach);

	public SuggestForCoach findSuggestForCoachById(String sugCoachId);

	public void updateSugForCoach(SuggestForCoach suggestForCoach);

	public List<SuggestForCoach> findMarkSuggest(Coach coach);

	public List<CoachPlan> getPracticeScore(CoachPlan plan);

	public void updateCoach(Coach coach);

	public Coach findCoachById(String coachId);

	public List<Share> findShareForInfo(Coach infoCoach);

	public List<Summary> getSummaryForInfo(String pid);

	public List<CoachPlan> getPracticeScoreForInfo(String pid);

	public Coach findCoachByNumber(String coachNumber);

	public List<Summary> findSummaryByPlayer(Player player);

	public List<Coach> findMainPageCoach();
}
