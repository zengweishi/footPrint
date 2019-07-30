package com.dao;

import java.util.List;

import com.domain.Announcement;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Player;

public interface IClubDao {
	public void clubRegist(Club club);
	public Club clubLogin(Club club);
	public void newAnnouncement(Announcement annoucement);
	public Integer findTotalByCid(Club club);
	public List<Announcement> findPageByCid(Club club, int begin,
			Integer pageSize);
	public Announcement findAnnouncementContent(String aId);
	public void deleteAnnouncement(String aId);
	public void addNewCoach(Coach coach);
	public Integer findTotalCoachByCid(Club club);
	public List<Coach> findAllCoach(Club club, int begin, Integer pageSize);
	public void deleteCoachFromClub(String coachId);
	public void writeCNews(CNews cnews);
	public Integer findTotalCNewsByCid(Club club);
	public List<CNews> showAllCNews(Club club, int begin, Integer pageSize);
	public Club findClubNameByClubId(String clubId);
	public CNews findCNewsArtical(String newsId);
	public void deleteCNews(String newsId);
	public void updateCNewsArtical(CNews cnews);
	public Coach coachLogin(Coach coach);
	public Player findPlayerByPid(String pid);
	public void updatePlayer(Player rePlayer);
	public Integer findTotalPlayerWithoutCoach(Club club);
	public List<Player> findPlayerWithoutCoach(Club club, int begin,
			Integer pageSize);
	public List<Player> findUnreceivePlayer(Club club, int begin,
			Integer pageSize);
	public List<Player> findPlayerWithCoach(Club club, int begin,
			Integer pageSize);
	public Coach findCoachByCoachId(String coachId);
	public Integer findTotalWithCoachPlayer(Club club);
	public Integer findTotaUnReceivelPlayer(Club club);
	public List<Coach> findAllCoachByClub(Club club);
	public Integer findTotaUnReceivelCoach(Club club);
	public List<Coach> findUnreceiveCoach(Club club, int begin, Integer pageSize);
	public void updateCoach(Coach reCoach);
	public void updateClub(Club club);
	public List<Announcement> findAnnouncementForInfo(Club club);
	public List<CNews> findCNewsForInfo(Club club);
	public Club findClubByName(String clubName);
	public List<Club> findMainPageClub();

}
