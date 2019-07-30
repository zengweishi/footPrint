package com.service;

import java.util.List;

import com.domain.ANews;
import com.domain.AdPhoto;
import com.domain.Admin;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Comments;
import com.domain.Diary;
import com.domain.PageBean;
import com.domain.Player;
import com.domain.Share;
import com.domain.TNews;
import com.domain.Tourist;

public interface IAdminService {

	public Admin adminLogin(Admin admin);

	public PageBean<Player> findAllPlayer(Integer currPage);

	public PageBean<Coach> findAllCoach(Integer currPage);

	public void deletePlayerByAdmin(String pid);

	public void deleteCoachByAdmin(String coachId);

	public PageBean<Club> findClubByAdmin(int i, Integer currPage);

	public void deleteClubByAdmin(String cid);

	public List<Coach> findAllCoachByClub(Club club);

	public PageBean<CNews> findAllCNewsByAdmin(Integer currPage);

	public PageBean<Share> findAllShareByAdmin(Integer currPage);

	public PageBean<Diary> findAllDiaryByAdmin(Integer currPage);

	public void createANews(ANews anews);

	public PageBean<ANews> findANews(Integer currPage, Admin amdin);

	public ANews findANewsById(String anewsId);

	public void updateANews(ANews anews);

	public void deleteANewsById(String anewsId);

	public void addNewAdmin(Admin admin);

	public List<Admin> findAllAdmin();

	public void deleteAdmin(String adminId);

	public Admin findAdminById(String adminId);

	public void updateAdmin(Admin admin);

	public PageBean<ANews> findAllANews(Integer currPage);

	public PageBean<Diary> findDiaryShowByAdmin(Integer currPage);

	public PageBean<Tourist> findTouristByAdmin(Integer currPage);

	public void deleteTourist(String tId);

	public PageBean<TNews> findTNews(Integer currPage);

	public void addAdPhoto(AdPhoto adPhoto);

	public List<AdPhoto> findAllAdPhoto();

	public void deleteAdPhotoById(String adId);

	public AdPhoto findAdPhotoById(String adId);

	public void updateAdPhoto(AdPhoto adPhoto);

	public Admin findAdminByName(String adminName);

	public void addComments(Comments comments);

	public List<Comments> findCommentsByArticleId(int i, String cnewsId);

	public void deleteCommentsById(String commentsId);

	public List<CNews> findCNewsByViewNumber();

	public List<ANews> findANewsByViewNumber();

	public List<Diary> findDiaryByViewNumber();

	public List<Share> findShareByViewNumber();

	public List<TNews> findTNewsByViewNumber();

	

}
