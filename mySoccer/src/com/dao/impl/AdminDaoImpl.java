package com.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dao.IAdminDao;
import com.domain.ANews;
import com.domain.AdPhoto;
import com.domain.Admin;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.CoachPlan;
import com.domain.Comments;
import com.domain.Diary;
import com.domain.Player;
import com.domain.Share;
import com.domain.TNews;
import com.domain.Tourist;

@Repository("adminDao")
public class AdminDaoImpl extends HibernateDaoSupport implements IAdminDao {
	@Autowired
	@Qualifier("sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	//管理员登录
	@Override
	public Admin adminLogin(Admin admin) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from admin where adminName = ? and adminPassword = ?");
		sqlQuery.setParameter(0,admin.getAdminName());
		sqlQuery.setParameter(1, admin.getAdminPassword());
		sqlQuery.addEntity(Admin.class);
		Admin existAdmin = (Admin) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return existAdmin;
	}

	//查询总数
	@Override
	public Integer findTotalNumber(int i) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = null;
		if(i == 1) {
			//查询球员总数
			sqlQuery = session.createSQLQuery("select count(*) from player");
		}
		if(i == 2) {
			//查询教练总数
			sqlQuery = session.createSQLQuery("select count(*) from coach");
		}
		if(i == 3) {
			//查询未审核俱乐部总数
			sqlQuery = session.createSQLQuery("select count(*) from club where clubCode = ?");
			sqlQuery.setParameter(0, 0);
		}
		if(i == 4) {
			//查询已通过俱乐部总数
			sqlQuery = session.createSQLQuery("select count(*) from club where clubCode = ?");
			sqlQuery.setParameter(0, 1);
		}
		if(i == 5) {
			//查询俱乐部所有文章
			sqlQuery = session.createSQLQuery("select count(*) from cnews");
		}
		if(i == 6) {
			//查询教练分享
			sqlQuery = session.createSQLQuery("select count(*) from share");
		}
		if(i == 7) {
			//查询教练分享
			sqlQuery = session.createSQLQuery("select count(*) from diary");
		}
		if(i == 8) {
			//查询管理员文章
			sqlQuery = session.createSQLQuery("select count(*) from anews");
		}
		if(i == 9) {
			//查询可显示的球员日记
			sqlQuery = session.createSQLQuery("select count(*) from diary where diaryPermission = ? ");
			sqlQuery.setParameter(0, 0);
		}
		if(i == 10) {
			//查询游客数量
			sqlQuery = session.createSQLQuery("select count(*) from tourist");
		}
		if(i == 11) {
			//查询游客文章数量
			sqlQuery = session.createSQLQuery("select count(*) from tnews");
		}
		
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//分页查询球员
	@Override
	public List<Player> findAllPlayer(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Player order by pid desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Player> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//分页查询教练
	@Override
	public List<Coach> findAllCoach(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Coach order by coachId desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Coach> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//删除球员
	@Override
	public void deletePlayerByAdmin(String pid) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Player.class, pid));
	}

	//删除教练
	@Override
	public void deleteCoachByAdmin(String coachId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Coach.class, coachId));
	}

	//寻找俱乐部
	@Override
	public List<Club> findClubByAdmin(Integer i,int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = null;
		if(i == 0) { //查找未审核
			query = session.createQuery("from Club where clubCode= :code order by cid desc");
			query.setParameter("code", 0);			
		} 
		if(i == 1) { //查找已审核
			query = session.createQuery("from Club where clubCode= :code order by cid desc");
			query.setParameter("code", 1);
		}
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Club> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//删除俱乐部
	@Override
	public void deleteClubByAdmin(String cid) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Club.class, cid));
	}

	//查找俱乐部旗下的所有教练
	@Override
	public List<Coach> findAllCoachByClub(Club club) {
		// TODO Auto-generated method stub
		return (List<Coach>) this.getHibernateTemplate().find("from Coach c where c.coachClub = ?", club);
	}

	//查询俱乐部所有文章
	@Override
	public List<CNews> findAllCNewsByAdmin(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from CNews order by newsDate desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<CNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//寻找教练分享
	@Override
	public List<Share> findAllShareByAdmin(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Share order by shareDate desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Share> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//查找球员日记
	@Override
	public List<Diary> findAllDiaryByAdmin(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary order by diaryDate desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//创建新的文章
	@Override
	public void createANews(ANews anews) {
		// TODO Auto-generated method stub
		anews.setViewNumber(1);
		this.getHibernateTemplate().save(anews);
	}

	//查询文章总数
	@Override
	public Integer findTotalANews(Admin admin) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from anews where anews_admin = ?");
		sqlQuery.setParameter(0, admin.getAdminId());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//查询一个管理员用户的文章
	@Override
	public List<ANews> findANews(Admin admin, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from ANews where anews_admin = :adminId order by anewsDate desc");
		query.setParameter("adminId", admin.getAdminId());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<ANews> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//根据ID查找ANews
	@Override
	public ANews findANewsById(String anewsId) {
		// TODO Auto-generated method stub
		ANews aNews = this.getHibernateTemplate().get(ANews.class, anewsId);
		Integer number = aNews.getViewNumber();
		number++;
		aNews.setViewNumber(number);
		this.getHibernateTemplate().update(aNews);
		return this.getHibernateTemplate().get(ANews.class, anewsId);
	}

	//更新文章
	@Override
	public void updateANews(ANews anews) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(anews);
	}

	//删除管理员文章
	@Override
	public void deleteANewsById(String anewsId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(ANews.class, anewsId));
	}

	//添加管理员
	@Override
	public void addNewAdmin(Admin admin) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(admin);
	}

	//查找所有管理员
	@Override
	public List<Admin> findAllAdmin() {
		// TODO Auto-generated method stub
		return (List<Admin>) this.getHibernateTemplate().find("from Admin a order by a.adminId desc");
	}

	//删除管理员
	@Override
	public void deleteAdmin(String adminId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Admin.class, adminId));
	}

	//根据ID寻找管理员
	@Override
	public Admin findAdminById(String adminId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Admin.class, adminId);
	}

	//更新管理员
	@Override
	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(admin);
	}

	//查询所有管理员用户的文章
	@Override
	public List<ANews> findAllANews(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from ANews order by anewsDate desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<ANews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;	
	}

	//寻找可见的日记
	@Override
	public List<Diary> findDiaryShowByAdmin(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary where diaryPermission= :permission order by diaryDate desc");
		query.setParameter("permission", 0);
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//查找游客
	@Override
	public List<Tourist> findTouristByAdmin(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Tourist order by tId desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Tourist> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//删除游客
	@Override
	public void deleteTourist(String tId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Tourist.class, tId));
	}

	//查找所有的游客资讯
	@Override
	public List<TNews> findTNews(int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from TNews order by newsDate desc");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<TNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	//添加轮播图
	@Override
	public void addAdPhoto(AdPhoto adPhoto) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(adPhoto);
	}

	//查找轮播图
	@Override
	public List<AdPhoto> findAllAdPhoto() {
		// TODO Auto-generated method stub
		return (List<AdPhoto>) this.getHibernateTemplate().find("from AdPhoto order by adId desc");
	}

	//删除轮播图
	@Override
	public void deleteAdPhotoById(String adId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(AdPhoto.class, adId));
	}

	//根据ID查找轮播图
	@Override
	public AdPhoto findAdPhotoById(String adId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(AdPhoto.class, adId);
	}

	//更新轮播图
	@Override
	public void updateAdPhoto(AdPhoto adPhoto) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(adPhoto);
	}

	@Override
	public Admin findAdminByName(String adminName) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from admin where adminName = ?");
		sqlQuery.setParameter(0, adminName);
		sqlQuery.addEntity(Club.class);
		Admin admin = (Admin) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return admin;
	}

	//添加评论 
	@Override
	public void addComments(Comments comments) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(comments);
	}

	//查找文章评论
	@Override
	public List<Comments> findCommentsByArticleId(int i, String id) {
		// TODO Auto-generated method stub
		if(i == 1) {
			return (List<Comments>) this.getHibernateTemplate().find("from Comments c where c.cnewsId = ? order by c.commentsTime desc", id);
			
		} else if(i == 2) {
			return (List<Comments>) this.getHibernateTemplate().find("from Comments c where c.shareId = ? order by c.commentsTime desc", id);

		} else if(i == 3) {
			return (List<Comments>) this.getHibernateTemplate().find("from Comments c where c.diaryId = ? order by c.commentsTime desc", id);

		} else if(i == 4) {
			return (List<Comments>) this.getHibernateTemplate().find("from Comments c where c.tnewsId = ? order by c.commentsTime desc", id);

		} else {
			return (List<Comments>) this.getHibernateTemplate().find("from Comments c where c.anewsId = ? order by c.commentsTime desc", id);

		}
	}

	//删除评论
	@Override
	public void deleteCommentsById(String commentsId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Comments.class, commentsId));
	}

	//寻找浏览数高的文章
	@Override
	public List<CNews> findCNewsByViewNumber() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);//计算七天前日期
        Date d = c.getTime();
        String day = format.format(d);
        Date date = null;
        try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from CNews c where c.newsDate >= :limitTime order by c.viewNumber desc");
		query.setParameter("limitTime",date);
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<CNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<ANews> findANewsByViewNumber() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);//计算七天前日期
        Date d = c.getTime();
        String day = format.format(d);
        Date date = null;
        try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from ANews a where a.anewsDate >= :limitTime order by a.viewNumber desc");
		query.setParameter("limitTime",date);
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<ANews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Diary> findDiaryByViewNumber() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);//计算七天前日期
        Date d = c.getTime();
        String day = format.format(d);
        Date date = null;
        try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary d where d.diaryDate >= :limitTime and d.diaryPermission = :permission order by d.viewNumber desc");
		query.setParameter("limitTime",date);
		query.setParameter("permission",0);
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Share> findShareByViewNumber() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);//计算七天前日期
        Date d = c.getTime();
        String day = format.format(d);
        Date date = null;
        try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Share s where s.shareDate >= :limitTime order by s.viewNumber desc");
		query.setParameter("limitTime",date);
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<Share> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<TNews> findTNewsByViewNumber() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);//计算七天前日期
        Date d = c.getTime();
        String day = format.format(d);
        Date date = null;
        try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from TNews t where t.newsDate >= :limitTime order by t.viewNumber desc");
		query.setParameter("limitTime",date);
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<TNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
}















