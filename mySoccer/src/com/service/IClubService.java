package com.service;

import java.util.List;

import com.domain.Announcement;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.PageBean;
import com.domain.Player;

public interface IClubService {
	public void clubRegist(Club club);
	public Club clubLogin(Club club);
	public void newAnnouncement(Announcement annoucement);
	public PageBean<Announcement> findAllAnnouncement(Integer currPage, Club club);
	public Announcement findAnnouncementContent(String aId);
	public void deleteAnnouncement(String aId);
	public void addNewCoach(Coach coach);
	public PageBean<Coach> findAllCoach(Integer currPage, Club attribute);
	public void deleteCoachFromClub(String coachId);
	public void writeCNews(CNews cnews);
	public PageBean<CNews> showAllCNews(Integer currPage, Club attribute);
	public Club findClubNameByClubId(String clubId);
	public CNews findCNewsArtical(String newsId);
	public void deletCNews(String newsId);
	public void updateCNewsArtical(CNews cnews);
	public Coach coachLogin(Coach coach);
	public Player findPlayerByPid(String pid);
	public void updatePlayer(Player rePlayer);
	public PageBean<Player> findPlayerWithoutCoach(Integer currPage,
			Club attribute);
	public Coach findCoachByCoachId(String coachId);
	public PageBean<Player> findUnreceivePlayer(Integer currPage, Club attribute);
	public PageBean<Player> findPlayerWithCoach(Integer currPage, Club attribute);
	public List<Coach> findAllCoachByClub(Club club);
	public PageBean<Coach> findUnReceiveCoach(Integer currPage, Club attribute);
	public void updateCoach(Coach reCoach);
	public void updateClub(Club club);
	public List<Announcement> findAnnouncementForInfo(Club infoClub);
	public List<CNews> findCNewsForInfo(Club club);
	public Club findClubByName(String clubName);
	public List<Club> findMainPageClub();
}
