package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dao.IClubDao;
import com.domain.Announcement;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Player;
import com.domain.Share;

@Repository("clubDao")
public class ClubDaoImpl extends HibernateDaoSupport implements IClubDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}


	//俱乐部注册
	@Override
	public void clubRegist(Club club) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(club);
	}

	//俱乐部登录
	@Override
	public Club clubLogin(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from club where clubName = ? and clubPassword = ?");
		sqlQuery.setParameter(0, club.getClubName());
		sqlQuery.setParameter(1, club.getClubPassword());
		sqlQuery.addEntity(Club.class);
		Club newClub = (Club) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return newClub;
	}


	//发布新公告
	@Override
	public void newAnnouncement(Announcement annoucement) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(annoucement);
	}

	//查询公告总数
	@Override
	public Integer findTotalByCid(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from announcement where a_club = ?");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
		
	}

	//分页查询公告
	@Override
	public List<Announcement> findPageByCid(Club club, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Announcement where a_club = :clubId order by aTime desc");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Announcement> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	//查看公告内容
	@Override
	public Announcement findAnnouncementContent(String aId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Announcement.class, aId);
	}

	//删除公告
	@Override
	public void deleteAnnouncement(String aId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Announcement.class,aId));
	}

	//添加教练
	@Override
	public void addNewCoach(Coach coach) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(coach);
	}

	//查询教练总数
	@Override
	public Integer findTotalCoachByCid(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from coach where c_club = ?");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//分页查询教练
	@Override
	public List<Coach> findAllCoach(Club club, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Coach where c_club = :clubId order by coachId desc");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Coach> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
		
	}

	//将教练与俱乐部解除绑定
	@Override
	public void deleteCoachFromClub(String coachId) {
		// TODO Auto-generated method stub
		Coach coach = this.getHibernateTemplate().get(Coach.class, coachId);
		coach.setCoachClub(null);
		this.getHibernateTemplate().update(coach);
	}

	//写新文章
	@Override
	public void writeCNews(CNews cnews) {
		// TODO Auto-generated method stub
		cnews.setViewNumber(1);
		this.getHibernateTemplate().save(cnews);
	}

	//查询文章总数
	@Override
	public Integer findTotalCNewsByCid(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from cnews where n_club = ?");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//分页显示所有文章
	@Override
	public List<CNews> showAllCNews(Club club, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from CNews where n_club = :clubId order by newsDate desc");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<CNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//获取俱乐部名称
	@Override
	public Club findClubNameByClubId(String clubId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Club.class, clubId);
	}

	//获取新闻内容
	@Override
	public CNews findCNewsArtical(String newsId) {
		// TODO Auto-generated method stub
		CNews cNews = this.getHibernateTemplate().get(CNews.class, newsId);
		Integer number = cNews.getViewNumber();
		number++;
		cNews.setViewNumber(number);
		this.getHibernateTemplate().update(cNews);
		return cNews;
	}

	//删除新闻
	@Override
	public void deleteCNews(String newsId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(CNews.class, newsId));
	}

	//修改新闻内容
	@Override
	public void updateCNewsArtical(CNews cnews) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(cnews);
	}

	//俱乐部教练登录
	@Override
	public Coach coachLogin(Coach coach) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from coach where coachNumber = ? and coachPassword = ?");
		sqlQuery.setParameter(0, coach.getCoachNumber());
		sqlQuery.setParameter(1, coach.getCoachPassword());
		sqlQuery.addEntity(Coach.class);
		Coach newCoach = (Coach) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return newCoach;
	}

	//寻找球员
	@Override
	public Player findPlayerByPid(String pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Player.class, pid);
	}

	//更新球员
	@Override
	public void updatePlayer(Player rePlayer) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(rePlayer);
	}

	//查找未有教练球员总数
	@Override
	public Integer findTotalPlayerWithoutCoach(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from player where pClubId = ? and p_coach is null");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//查找未有教练球员
	@Override
	public List<Player> findPlayerWithoutCoach(Club club, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Player where pClubId = :clubId and p_coach is null order by pid desc");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Player> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//寻找未接收球员
	@Override
	public List<Player> findUnreceivePlayer(Club club, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Player where pApplyId = :clubId  order by pid desc");
		//System.out.println("未被接收111-------------------------");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Player> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//寻找已有教练球员
	@Override
	public List<Player> findPlayerWithCoach(Club club, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Player where pClubId = :clubId and p_coach is not null order by pid desc");
		//System.out.println("已被接收------------------------");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Player> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//寻找coach
	@Override
	public Coach findCoachByCoachId(String coachId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Coach.class, coachId);
	}

	//寻找有教练的球员总数
	@Override
	public Integer findTotalWithCoachPlayer(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from player where pClubId = ? and p_coach is not null");
		//System.out.println("已有教练------------------------");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//寻找未被接收的球员总数
	@Override
	public Integer findTotaUnReceivelPlayer(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from player where pApplyId = ?");
		//System.out.println("未被接收-------------------------------");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//寻找所有的教练
	@Override
	public List<Coach> findAllCoachByClub(Club club) {
		// TODO Auto-generated method stub
		//System.out.println(club.getCid()+"---------------------------------");
		return (List<Coach>) this.getHibernateTemplate().find("from Coach c where c.coachClub = ?", club);
	}

	//寻找未接收的教练总数
	@Override
	public Integer findTotaUnReceivelCoach(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from coach where coachApplyId = ?");
		//System.out.println("未被接收-------------------------------");
		sqlQuery.setParameter(0, club.getCid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//显示未接收的教练
	@Override
	public List<Coach> findUnreceiveCoach(Club club, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Coach where coachApplyId = :clubId  order by coachId desc");
		//System.out.println("已被接收------------------------");
		query.setParameter("clubId", club.getCid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Coach> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//更新教练
	@Override
	public void updateCoach(Coach reCoach) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(reCoach);
	}

	//修改俱乐部信息
	@Override
	public void updateClub(Club club) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(club);
	}

	//获取俱乐部公告显示到主页上
	@Override
	public List<Announcement> findAnnouncementForInfo(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Announcement where a_club = :clubId order by aTime desc");
		query.setParameter("clubId", club.getCid());
		List<Announcement> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	//查找俱乐部新闻显示到主页上
	@Override
	public List<CNews> findCNewsForInfo(Club club) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from CNews where n_club = :clubId order by newsDate desc");
		query.setParameter("clubId", club.getCid());
		List<CNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
		
	}

	
	//根据名字查找俱乐部
	@Override
	public Club findClubByName(String clubName) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from club where clubName = ?");
		sqlQuery.setParameter(0, clubName);
		sqlQuery.addEntity(Club.class);
		Club newClub = (Club) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return newClub;
	}

	//查找被推上首页的俱乐部
	@Override
	public List<Club> findMainPageClub() {
		// TODO Auto-generated method stub
		return (List<Club>) this.getHibernateTemplate().find("from Club c where c.clubMainPage = ?", 1);
	}


	


	
	

}










