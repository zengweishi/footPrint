package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IAdminDao;
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
import com.service.IAdminService;

@Service("adminService")
@Transactional
public class AdminService implements IAdminService {
	@Autowired
	@Qualifier("adminDao")
	private IAdminDao adminDao;

	@Override
	public Admin adminLogin(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.adminLogin(admin);
	}

	

	@Override
	public PageBean<Player> findAllPlayer(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Player> pageBean = new PageBean<Player>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = adminDao.findTotalNumber(1);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Player> list = adminDao.findAllPlayer(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public PageBean<Coach> findAllCoach(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Coach> pageBean = new PageBean<Coach>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = adminDao.findTotalNumber(2);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Coach> list = adminDao.findAllCoach(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//删除球员
	@Override
	public void deletePlayerByAdmin(String pid) {
		// TODO Auto-generated method stub
		adminDao.deletePlayerByAdmin(pid);
	}


	//删除教练
	@Override
	public void deleteCoachByAdmin(String coachId) {
		// TODO Auto-generated method stub
		adminDao.deleteCoachByAdmin(coachId);
	}


	//寻找俱乐部
	@Override
	public PageBean<Club> findClubByAdmin(int i, Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Club> pageBean = new PageBean<Club>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		Integer totalCount = null;
		// 设置总记录数:
		if(i == 0) {
			totalCount = adminDao.findTotalNumber(3);
		}
		if(i == 1) {
			totalCount = adminDao.findTotalNumber(4);
		}
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Club> list = null;
		System.out.println(begin+"---"+pageSize);
		System.out.println("----------------------");
		if(i == 0) {
			list = adminDao.findClubByAdmin(0,begin,pageSize);			
		}
		if(i == 1) {
			list = adminDao.findClubByAdmin(1,begin,pageSize);
		}
		pageBean.setList(list);
		return pageBean;
	}


	//审核未通过，删除俱乐部
	@Override
	public void deleteClubByAdmin(String cid) {
		// TODO Auto-generated method stub
		adminDao.deleteClubByAdmin(cid);
	}


	//查找俱乐部旗下教练
	@Override
	public List<Coach> findAllCoachByClub(Club club) {
		// TODO Auto-generated method stub
		return adminDao.findAllCoachByClub(club);
	}


	//查询所有的俱乐部新闻
	@Override
	public PageBean<CNews> findAllCNewsByAdmin(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<CNews> pageBean = new PageBean<CNews>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = adminDao.findTotalNumber(5);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<CNews> list = adminDao.findAllCNewsByAdmin(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//查找教练分享
	@Override
	public PageBean<Share> findAllShareByAdmin(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Share> pageBean = new PageBean<Share>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = adminDao.findTotalNumber(6);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Share> list = adminDao.findAllShareByAdmin(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//查找球员日记
	@Override
	public PageBean<Diary> findAllDiaryByAdmin(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Diary> pageBean = new PageBean<Diary>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = adminDao.findTotalNumber(7);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Diary> list = adminDao.findAllDiaryByAdmin(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//管理员写新的文章
	@Override
	public void createANews(ANews anews) {
		// TODO Auto-generated method stub
		adminDao.createANews(anews);
	}


	//查询一个管理员的文章
	@Override
	public PageBean<ANews> findANews(Integer currPage, Admin admin) {
		// TODO Auto-generated method stub
		PageBean<ANews> pageBean = new PageBean<ANews>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = adminDao.findTotalANews(admin);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<ANews> list = adminDao.findANews(admin,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//根据ID寻找一篇ANews
	@Override
	public ANews findANewsById(String anewsId) {
		// TODO Auto-generated method stub
		return adminDao.findANewsById(anewsId);
	}

	//更新管理员文章
	@Override
	public void updateANews(ANews anews) {
		// TODO Auto-generated method stub
		adminDao.updateANews(anews);
	}

	//删除管理员文章
	@Override
	public void deleteANewsById(String anewsId) {
		// TODO Auto-generated method stub
		adminDao.deleteANewsById(anewsId);
	}

	//添加新的管理员
	@Override
	public void addNewAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.addNewAdmin(admin);
	}

	//查找所有管理员
	@Override
	public List<Admin> findAllAdmin() {
		// TODO Auto-generated method stub
		return adminDao.findAllAdmin();
	}

	//删除管理员
	@Override
	public void deleteAdmin(String adminId) {
		// TODO Auto-generated method stub
		adminDao.deleteAdmin(adminId);
	}

	//查找系统管理员
	@Override
	public Admin findAdminById(String adminId) {
		// TODO Auto-generated method stub
		return adminDao.findAdminById(adminId);
	}

	//修改管理员信息
	@Override
	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.updateAdmin(admin);
	}

	//寻找所有的管理员文章
	@Override
	public PageBean<ANews> findAllANews(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<ANews> pageBean = new PageBean<ANews>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = adminDao.findTotalNumber(8);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<ANews> list = adminDao.findAllANews(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//寻找可见的日记到首页
	@Override
	public PageBean<Diary> findDiaryShowByAdmin(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Diary> pageBean = new PageBean<Diary>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = adminDao.findTotalNumber(9);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Diary> list = adminDao.findDiaryShowByAdmin(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//寻找所有游客
	@Override
	public PageBean<Tourist> findTouristByAdmin(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Tourist> pageBean = new PageBean<Tourist>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = adminDao.findTotalNumber(10);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Tourist> list = adminDao.findTouristByAdmin(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//删除游客
	@Override
	public void deleteTourist(String tId) {
		// TODO Auto-generated method stub
		adminDao.deleteTourist(tId);
	}


	//寻找游客资讯
	@Override
	public PageBean<TNews> findTNews(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<TNews> pageBean = new PageBean<TNews>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = adminDao.findTotalNumber(11);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<TNews> list = adminDao.findTNews(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	//添加轮播图
	@Override
	public void addAdPhoto(AdPhoto adPhoto) {
		// TODO Auto-generated method stub
		adminDao.addAdPhoto(adPhoto);
	}

	//查找轮播图
	@Override
	public List<AdPhoto> findAllAdPhoto() {
		// TODO Auto-generated method stub
		return adminDao.findAllAdPhoto();
	}

	//删除轮播图
	@Override
	public void deleteAdPhotoById(String adId) {
		// TODO Auto-generated method stub
		adminDao.deleteAdPhotoById(adId);
	}

	//根据ID查找轮播图
	@Override
	public AdPhoto findAdPhotoById(String adId) {
		// TODO Auto-generated method stub
		return adminDao.findAdPhotoById(adId);
	}

	//更新轮播图
	@Override
	public void updateAdPhoto(AdPhoto adPhoto) {
		// TODO Auto-generated method stub
		adminDao.updateAdPhoto(adPhoto);
	}



	@Override
	public Admin findAdminByName(String adminName) {
		// TODO Auto-generated method stub
		return adminDao.findAdminByName(adminName);
	}


	//添加评论
	@Override
	public void addComments(Comments comments) {
		// TODO Auto-generated method stub
		adminDao.addComments(comments);
	}
	
	//查找文章的评论
	@Override
	public List<Comments> findCommentsByArticleId(int i, String id) {
		// TODO Auto-generated method stub
		return adminDao.findCommentsByArticleId(i,id);
	}

	//删除文章评论
	@Override
	public void deleteCommentsById(String commentsId) {
		// TODO Auto-generated method stub
		adminDao.deleteCommentsById(commentsId);
	}


	//查找浏览数高的文章
	@Override
	public List<CNews> findCNewsByViewNumber() {
		// TODO Auto-generated method stub
		return adminDao.findCNewsByViewNumber();
	}



	@Override
	public List<ANews> findANewsByViewNumber() {
		// TODO Auto-generated method stub
		return adminDao.findANewsByViewNumber();
	}



	@Override
	public List<Diary> findDiaryByViewNumber() {
		// TODO Auto-generated method stub
		return adminDao.findDiaryByViewNumber();
	}



	@Override
	public List<Share> findShareByViewNumber() {
		// TODO Auto-generated method stub
		return adminDao.findShareByViewNumber();
	}



	@Override
	public List<TNews> findTNewsByViewNumber() {
		// TODO Auto-generated method stub
		return adminDao.findTNewsByViewNumber();
	}
}

















