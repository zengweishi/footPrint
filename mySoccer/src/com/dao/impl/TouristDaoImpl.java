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

import com.dao.ITouristDao;
import com.domain.Club;
import com.domain.CollectionItem;
import com.domain.Diary;
import com.domain.Player;
import com.domain.TNews;
import com.domain.Tourist;

@Repository("touristDao")
public class TouristDaoImpl extends HibernateDaoSupport implements ITouristDao {
	@Autowired
	@Qualifier("sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	//游客注册
	@Override
	public void touristRegist(Tourist tourist) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(tourist);
	}

	//游客登录
	@Override
	public Tourist touristLogin(Tourist tourist) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from tourist where tEmail = ? and tPassword = ?");
		sqlQuery.setParameter(0, tourist.gettEmail());
		sqlQuery.setParameter(1, tourist.gettPassword());
		sqlQuery.addEntity(Tourist.class);
		Tourist newTourist = (Tourist) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return newTourist;
	}

	//创建新的游客文章
	@Override
	public void createTNews(TNews tnews) {
		// TODO Auto-generated method stub
		tnews.setViewNumber(1);
		this.getHibernateTemplate().save(tnews);
	}

	//寻找游客文章总数
	@Override
	public Integer findTotalTNews(Tourist tourist) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from tnews where n_tourist = ?");
		sqlQuery.setParameter(0, tourist.gettId());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//寻找游客文章
	@Override
	public List<TNews> findOwnTNews(Tourist tourist, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from TNews where n_tourist = :touristId order by newsDate desc");
		query.setParameter("touristId",tourist.gettId());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<TNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//查找文章
	@Override
	public TNews findTNewsArticle(String tId) {
		// TODO Auto-generated method stub
		TNews tNews = this.getHibernateTemplate().get(TNews.class, tId);
		Integer number = tNews.getViewNumber();
		number++;
		tNews.setViewNumber(number);
		this.getHibernateTemplate().update(tNews);
		return tNews;
	}

	//删除文章
	@Override
	public void deleteTNewsById(String tId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(TNews.class, tId));
	}

	//修改游客文章
	@Override
	public void updateTNews(TNews tnews) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(tnews);
	}

	//查找游客
	@Override
	public Tourist findTourist(String gettId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Tourist.class, gettId);
	}

	//修改游客信息
	@Override
	public void updateTourist(Tourist tourist) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(tourist);
	}

	//查找用户文章显示到主页上
	@Override
	public List<TNews> findTNewsForInfo(Tourist tourist) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from TNews where n_tourist = :touristId order by newsDate desc");
		query.setParameter("touristId", tourist.gettId());
		List<TNews> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	//添加收藏
	@Override
	public void addCollection(CollectionItem collectionItem) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(collectionItem);
	}

	//删除收藏
	@Override
	public void deleteCollection(int i, CollectionItem collectionItem) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = null;
		
		if(i == 1) {
			sqlQuery = session.createSQLQuery("select * from collectionitem where collectionCNews = ? and collectionTourist = ?");
			sqlQuery.setParameter(0, collectionItem.getCollectionCNews());
			sqlQuery.setParameter(1, collectionItem.getCollectionTourist());
			
			
		} else if(i == 2) {
			sqlQuery = session.createSQLQuery("select * from collectionitem where collectionShare = ? and collectionTourist = ?");
			sqlQuery.setParameter(0, collectionItem.getCollectionShare());
			sqlQuery.setParameter(1, collectionItem.getCollectionTourist());
			
			
		} else if(i == 3) {
			sqlQuery = session.createSQLQuery("select * from collectionitem where collectionDiary = ? and collectionTourist = ?");
			sqlQuery.setParameter(0, collectionItem.getCollectionDiary());
			sqlQuery.setParameter(1, collectionItem.getCollectionTourist());
			
			
		}else if(i == 4) {
			sqlQuery = session.createSQLQuery("select * from collectionitem where collectionTNews = ? and collectionTourist = ?");
			sqlQuery.setParameter(0, collectionItem.getCollectionTNews());
			sqlQuery.setParameter(1, collectionItem.getCollectionTourist());
			
			
		}else {
			sqlQuery = session.createSQLQuery("select * from collectionitem where collectionANews = ? and collectionTourist = ?");
			sqlQuery.setParameter(0, collectionItem.getCollectionANews());
			sqlQuery.setParameter(1, collectionItem.getCollectionTourist());
			
			
		}
		sqlQuery.addEntity(CollectionItem.class);
		CollectionItem item = (CollectionItem) sqlQuery.uniqueResult();
		System.out.println(item.getCollectionId());
		this.getHibernateTemplate().delete(item);
		session.getTransaction().commit();
		session.close();
	}

	//查询收藏
	@Override
	public CollectionItem findCollection(int i, CollectionItem collectionItem) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = null;
		
		if(i == 1) {
			sqlQuery = session.createSQLQuery("select * from collectionitem where collectionCNews = ? and collectionTourist = ?");
			sqlQuery.setParameter(0, collectionItem.getCollectionCNews());
			sqlQuery.setParameter(1, collectionItem.getCollectionTourist());
			
			
		} else if(i == 2) {
			sqlQuery = session.createSQLQuery("select * from collectionitem where collectionShare = ? and collectionTourist = ?");
			sqlQuery.setParameter(0, collectionItem.getCollectionShare());
			sqlQuery.setParameter(1, collectionItem.getCollectionTourist());
			
			
		} else if(i == 3) {
			sqlQuery = session.createSQLQuery("select * from collectionitem where collectionDiary = ? and collectionTourist = ?");
			sqlQuery.setParameter(0, collectionItem.getCollectionDiary());
			sqlQuery.setParameter(1, collectionItem.getCollectionTourist());
			
			
		}else if(i == 4) {
			sqlQuery = session.createSQLQuery("select * from collectionitem where collectionTNews = ? and collectionTourist = ?");
			sqlQuery.setParameter(0, collectionItem.getCollectionTNews());
			sqlQuery.setParameter(1, collectionItem.getCollectionTourist());
			
			
		}else {
			sqlQuery = session.createSQLQuery("select * from collectionitem where collectionANews = ? and collectionTourist = ?");
			sqlQuery.setParameter(0, collectionItem.getCollectionANews());
			sqlQuery.setParameter(1, collectionItem.getCollectionTourist());
			
			
		}
		sqlQuery.addEntity(CollectionItem.class);
		CollectionItem item = (CollectionItem) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return item;
	}

	//分类查找收藏
	@Override
	public List<CollectionItem> findAllCollectionByType(int i, Tourist tourist) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = null;
		if(i == 1) {
			query = session.createQuery("from CollectionItem c where c.collectionTourist = :tourist and c.collectionCNews != null order by collectionId desc");
		} else if(i == 2) {
			query = session.createQuery("from CollectionItem c where c.collectionTourist = :tourist and c.collectionShare != null order by collectionId desc");

		} else if(i == 3) {
			query = session.createQuery("from CollectionItem c where c.collectionTourist = :tourist and c.collectionDiary != null order by collectionId desc");

		} else if(i == 4) {
			query = session.createQuery("from CollectionItem c where c.collectionTourist = :tourist and c.collectionTNews != null order by collectionId desc");

		} else {
			query = session.createQuery("from CollectionItem c where c.collectionTourist = :tourist and c.collectionANews != null order by collectionId desc");

		}
		query.setParameter("tourist",tourist.gettId());
		List<CollectionItem> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	@Override
	public Tourist findTouristByNumber(String touristEmail) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from tourist where touristEmail = ?");
		sqlQuery.setParameter(0, touristEmail);
		sqlQuery.addEntity(Club.class);
		Tourist tourist = (Tourist) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return tourist;
	}

}













