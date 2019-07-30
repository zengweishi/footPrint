package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IClubDao;
import com.domain.Announcement;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.PageBean;
import com.domain.Player;
import com.service.IClubService;

@Service("clubService")
@Transactional
public class ClubServiceImpl implements IClubService{
	@Autowired
	@Qualifier("clubDao")
	private IClubDao clubDao;

	//俱乐部注册
	@Override
	public void clubRegist(Club club) {
		// TODO Auto-generated method stub
		clubDao.clubRegist(club);
	}

	//俱乐部登录
	@Override
	public Club clubLogin(Club club) {
		// TODO Auto-generated method stub
		return clubDao.clubLogin(club);
	}

	//发布新公告
	@Override
	public void newAnnouncement(Announcement annoucement) {
		// TODO Auto-generated method stub
		clubDao.newAnnouncement(annoucement);
	}

	//查询所有公告
	@Override
	public PageBean<Announcement> findAllAnnouncement(
			Integer currPage, Club club) {
		PageBean<Announcement> pageBean = new PageBean<Announcement>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 6;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = clubDao.findTotalByCid(club);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Announcement> list = clubDao.findPageByCid(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//查询公告内容
	@Override
	public Announcement findAnnouncementContent(String aId) {
		// TODO Auto-generated method stub
		return clubDao.findAnnouncementContent(aId);
	}

	//删除公告
	@Override
	public void deleteAnnouncement(String aId) {
		// TODO Auto-generated method stub
		clubDao.deleteAnnouncement(aId);
	}
	
	//添加教练
	@Override
	public void addNewCoach(Coach coach) {
		// TODO Auto-generated method stub
		clubDao.addNewCoach(coach);
		
	}

	//查询所有教练
	@Override
	public PageBean<Coach> findAllCoach(Integer currPage, Club club) {
		// TODO Auto-generated method stub
		PageBean<Coach> pageBean = new PageBean<Coach>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 6;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = clubDao.findTotalCoachByCid(club);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Coach> list = clubDao.findAllCoach(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//将教练与俱乐部解除绑定
	@Override
	public void deleteCoachFromClub(String coachId) {
		// TODO Auto-generated method stub
		clubDao.deleteCoachFromClub(coachId);
	}

	//发布新文章
	@Override
	public void writeCNews(CNews cnews) {
		// TODO Auto-generated method stub
		clubDao.writeCNews(cnews);
	}
	//显示所有文章
	@Override
	public PageBean<CNews> showAllCNews(Integer currPage, Club club) {
		// TODO Auto-generated method stub
		PageBean<CNews> pageBean = new PageBean<CNews>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = clubDao.findTotalCNewsByCid(club);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<CNews> list = clubDao.showAllCNews(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	//查找俱乐部名称
	@Override
	public Club findClubNameByClubId(String clubId) {
		// TODO Auto-generated method stub
		return clubDao.findClubNameByClubId(clubId);
	}

	//显示文章内容
	@Override
	public CNews findCNewsArtical(String newsId) {
		// TODO Auto-generated method stub
		return clubDao.findCNewsArtical(newsId);
	}

	//删除文章
	@Override
	public void deletCNews(String newsId) {
		// TODO Auto-generated method stub
		clubDao.deleteCNews(newsId);
	}

	//修改新闻内容
	@Override
	public void updateCNewsArtical(CNews cnews) {
		// TODO Auto-generated method stub
		clubDao.updateCNewsArtical(cnews);
	}

	//俱乐部教练登录
	@Override
	public Coach coachLogin(Coach coach) {
		// TODO Auto-generated method stub
		return clubDao.coachLogin(coach);
	}

	//寻找球员
	@Override
	public Player findPlayerByPid(String pid) {
		// TODO Auto-generated method stub
		return clubDao.findPlayerByPid(pid);
	}

	//更新球员
	@Override
	public void updatePlayer(Player rePlayer) {
		// TODO Auto-generated method stub
		clubDao.updatePlayer(rePlayer);
	}

	//显示未有教练球员
	@Override
	public PageBean<Player> findPlayerWithoutCoach(Integer currPage,
			Club club) {
		// TODO Auto-generated method stub
		PageBean<Player> pageBean = new PageBean<Player>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 9;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = clubDao.findTotalPlayerWithoutCoach(club);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Player> list = clubDao.findPlayerWithoutCoach(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//寻找教练
	@Override
	public Coach findCoachByCoachId(String coachId) {
		// TODO Auto-generated method stub
		return clubDao.findCoachByCoachId(coachId);
	}

	//寻找未接收球员
	@Override
	public PageBean<Player> findUnreceivePlayer(Integer currPage, Club club) {
		// TODO Auto-generated method stub
		PageBean<Player> pageBean = new PageBean<Player>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 9;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = clubDao.findTotaUnReceivelPlayer(club);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Player> list = clubDao.findUnreceivePlayer(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//寻找已有教练球员
	@Override
	public PageBean<Player> findPlayerWithCoach(Integer currPage, Club club) {
		// TODO Auto-generated method stub
		PageBean<Player> pageBean = new PageBean<Player>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 9;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = clubDao.findTotalWithCoachPlayer(club);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Player> list = clubDao.findPlayerWithCoach(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//寻找所有教练
	@Override
	public List<Coach> findAllCoachByClub(Club club) {
		// TODO Auto-generated method stub
		return clubDao.findAllCoachByClub(club);
	}

	//寻找未接收的教练
	@Override
	public PageBean<Coach> findUnReceiveCoach(Integer currPage, Club club) {
		// TODO Auto-generated method stub
		PageBean<Coach> pageBean = new PageBean<Coach>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 9;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = clubDao.findTotaUnReceivelCoach(club);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Coach> list = clubDao.findUnreceiveCoach(club,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//更新教练
	@Override
	public void updateCoach(Coach reCoach) {
		// TODO Auto-generated method stub
		clubDao.updateCoach(reCoach);
	}

	//修改俱乐部信息
	@Override
	public void updateClub(Club club) {
		// TODO Auto-generated method stub
		clubDao.updateClub(club);
	}

	//获取俱乐部公告显示到主页上
	@Override
	public List<Announcement> findAnnouncementForInfo(Club club) {
		// TODO Auto-generated method stub
		return clubDao.findAnnouncementForInfo(club);
	}

	//查找俱乐部动态
	@Override
	public List<CNews> findCNewsForInfo(Club club) {
		// TODO Auto-generated method stub
		return clubDao.findCNewsForInfo(club);
	}

	//查找俱乐部
	@Override
	public Club findClubByName(String clubName) {
		// TODO Auto-generated method stub
		return clubDao.findClubByName(clubName);
	}

	//查找和被推上首页的俱乐部
	@Override
	public List<Club> findMainPageClub() {
		// TODO Auto-generated method stub
		return clubDao.findMainPageClub();
	}

	
	
	
	
}
