package com.dao;

import java.util.List;

import com.domain.ANews;
import com.domain.AdPhoto;
import com.domain.Admin;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Comments;
import com.domain.Diary;
import com.domain.Player;
import com.domain.Share;
import com.domain.TNews;
import com.domain.Tourist;

public interface IAdminDao {

	public Admin adminLogin(Admin admin);

	public Integer findTotalNumber(int i);

	public List<Player> findAllPlayer(int begin, Integer pageSize);

	public List<Coach> findAllCoach(int begin, Integer pageSize);

	public void deletePlayerByAdmin(String pid);

	public void deleteCoachByAdmin(String coachId);

	public List<Club> findClubByAdmin( Integer i,int begin, Integer pageSize);

	public void deleteClubByAdmin(String cid);

	public List<Coach> findAllCoachByClub(Club club);

	public List<CNews> findAllCNewsByAdmin(int begin, Integer pageSize);

	public List<Share> findAllShareByAdmin(int begin, Integer pageSize);

	public List<Diary> findAllDiaryByAdmin(int begin, Integer pageSize);

	public void createANews(ANews anews);

	public Integer findTotalANews(Admin amdin);

	public List<ANews> findANews(Admin admin, int begin, Integer pageSize);

	public ANews findANewsById(String anewsId);

	public void updateANews(ANews anews);

	public void deleteANewsById(String anewsId);

	public void addNewAdmin(Admin admin);

	public List<Admin> findAllAdmin();

	public void deleteAdmin(String adminId);

	public Admin findAdminById(String adminId);

	public void updateAdmin(Admin admin);

	public List<ANews> findAllANews(int begin, Integer pageSize);

	public List<Diary> findDiaryShowByAdmin(int begin, Integer pageSize);

	public List<Tourist> findTouristByAdmin(int begin, Integer pageSize);

	public void deleteTourist(String tId);

	public List<TNews> findTNews(int begin, Integer pageSize);

	public void addAdPhoto(AdPhoto adPhoto);

	public List<AdPhoto> findAllAdPhoto();

	public void deleteAdPhotoById(String adId);

	public AdPhoto findAdPhotoById(String adId);

	public void updateAdPhoto(AdPhoto adPhoto);

	public Admin findAdminByName(String adminName);

	public void addComments(Comments comments);

	public List<Comments> findCommentsByArticleId(int i, String id);

	public void deleteCommentsById(String commentsId);

	public List<CNews> findCNewsByViewNumber();

	public List<ANews> findANewsByViewNumber();

	public List<Diary> findDiaryByViewNumber();

	public List<Share> findShareByViewNumber();

	public List<TNews> findTNewsByViewNumber();

}
