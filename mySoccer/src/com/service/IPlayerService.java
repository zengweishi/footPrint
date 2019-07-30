package com.service;

import java.util.List;

import com.domain.Club;
import com.domain.Coach;
import com.domain.Diary;
import com.domain.PageBean;
import com.domain.Player;
import com.domain.PlayerDeeds;
import com.domain.PlayerMemo;
import com.domain.Report;

public interface IPlayerService {

	public void writeNewPlayerMemo(PlayerMemo playerMemo);

	public List<PlayerMemo> findPlayerMemoByPlayer(Player player);

	public PlayerMemo findPlayerMemoByMemoId(String playerMemoId);

	public void updatePlayerMemo(PlayerMemo playerMemo);

	public void deletePlayerMemoById(String playerMemoId);

	public void createPlayerDeeds(PlayerDeeds playerDeeds);

	public void deletePlayerDeedsById(String playerDeedsId);

	public List<PlayerDeeds> findAllPlayerDeedsByPlayer(Player player);

	public Player findPlayerById(String pid);

	public void updatePlayer(Player player);

	public void createDiary(Diary diary);

	public PageBean<Diary> findAllDiary(Integer currPage, Player player);

	public Diary findDiaryById(String diaryId);

	public void deleteDiaryById(String diaryId);

	public PageBean<Diary> findPageDiary(int i, Integer currPage,
			Player attribute);

	public PageBean<Diary> findTeammatesDiary(Integer currPage, Player attribute);

	public List<Diary> findDiaryForInfo(Player infoPlayer);

	public List<Club> fuzzyFindClub(String clubFuzzyName);

	public Player findPlayerByNumber(String playerNumber);

	public void createPlayerReport(Report report);

	public Report findPlayerReportByPid(String pid);

	public void updatePlayerReport(Report oldReport);

	public void updateDiary(Diary diary);

	public List<Player> findMainPagePlayer();

}
