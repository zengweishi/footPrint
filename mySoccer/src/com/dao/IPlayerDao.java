package com.dao;

import java.util.List;

import com.domain.Club;
import com.domain.Diary;
import com.domain.Player;
import com.domain.PlayerDeeds;
import com.domain.PlayerMemo;
import com.domain.Report;

public interface IPlayerDao {

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

	public Integer findTotalDiaryByPlayer(Player player);

	public List<Diary> findAllDiary(Player player, int begin, Integer pageSize);

	public Diary findDiaryById(String diaryId);

	public void deleteDiaryById(String diaryId);

	public Integer findTotalDiary(int i, Player player);

	public List<Diary> findPageDiary(int i, Player player, int begin,
			Integer pageSize);

	public Integer findTotalTeammatesDiary(Player player);

	public List<Diary> findTeammatesDiary(Player player, int begin,
			Integer pageSize);

	public List<Diary> findDiaryForInfo(Player infoPlayer);

	public List<Club> fuzzyFindClub(String clubFuzzyName);

	public Player findPlayerByNumber(String playerNumber);

	public void createPlayerReport(Report report);

	public Report findPlayerReportByPid(String pid);

	public void updatePlayerReport(Report oldReport);

	public void updateDiary(Diary diary);

	public List<Player> findMainPagePlayer();

}
