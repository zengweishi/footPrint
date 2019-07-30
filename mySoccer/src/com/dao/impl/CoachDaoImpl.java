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

import com.dao.ICoachDao;
import com.domain.ANews;
import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.CoachPlan;
import com.domain.Diary;
import com.domain.Player;
import com.domain.Share;
import com.domain.SuggestForCoach;
import com.domain.Summary;

@Repository("coachDao")
public class CoachDaoImpl extends HibernateDaoSupport implements ICoachDao {
	@Autowired
	@Qualifier("sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	//教练添加球员
	@Override
	public void addPlayerByCoach(Player player) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(player);
	}
	
	//查询教练所带的球员总数
	@Override
	public Integer findTotalPlayerByCoach(Coach coach) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from player where p_coach = ?");
		sqlQuery.setParameter(0, coach.getCoachId());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//分页显示所有球员
	@Override
	public List<Player> showAllPlayer(Coach coach, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Player where p_coach = :coachId order by pid desc");
		query.setParameter("coachId", coach.getCoachId());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Player> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//教练解除球员
	@Override
	public void deletePlayerByCoach(Player player) {
		// TODO Auto-generated method stub
		Player newPlayer = this.getHibernateTemplate().get(Player.class, player.getPid());
		newPlayer.setpCoach(null);
		this.getHibernateTemplate().update(newPlayer);
		
	}

	//发布新分享
	@Override
	public void wiriteShare(Share share) {
		// TODO Auto-generated method stub
		share.setViewNumber(1);
		this.getHibernateTemplate().save(share);
	}

	//查询教练个人的分享总数
	@Override
	public Integer findTotalShareByCoach(Coach coach) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from share where s_coach = ?");
		sqlQuery.setParameter(0, coach.getCoachId());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//分页显示教练分享
	@Override
	public List<Share> showAllShare(Coach coach, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Share where s_coach = :coachId order by shareDate desc");
		query.setParameter("coachId", coach.getCoachId());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Share> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//显示新闻内容
	@Override
	public Share showShareArtical(String shareId) {
		// TODO Auto-generated method stub
		Share share = this.getHibernateTemplate().get(Share.class, shareId);
		Integer number = share.getViewNumber();
		number++;
		share.setViewNumber(number);
		this.getHibernateTemplate().update(share);
		return share;
	}

	//修改新闻
	@Override
	public void updateShareArtical(Share share) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(share);
	}

	//删除分享
	@Override
	public void deleteShare(String shareId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Share.class, shareId));
	}

	//球员登录
	@Override
	public Player playerLogin(Player player) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from player where pNumber = ? and password = ?");
		sqlQuery.setParameter(0, player.getpNumber());
		sqlQuery.setParameter(1, player.getPassword());
		sqlQuery.addEntity(Player.class);
		Player newPlayer = (Player) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return newPlayer;
	}

	//新建训练
	@Override
	public void writeCoachPlan(CoachPlan coachPlan) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(coachPlan);
	}

	//查询每个教练的完成训练总数
	@Override
	public Integer findTotalshowAllCoachPlan(Coach coach) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from coachplan where plan_coach = ? and planFlag = ?");
		sqlQuery.setParameter(0, coach.getCoachId());
		sqlQuery.setParameter(1, 1);
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//分页显示完成训练
	@Override
	public List<CoachPlan> showAllCoachPlan(Coach coach, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from CoachPlan where plan_coach = :coachId and planFlag = :planFlag order by planTime desc");
		query.setParameter("coachId", coach.getCoachId());
		query.setParameter("planFlag", 1);
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<CoachPlan> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//查找某个训练
	@Override
	public CoachPlan findCoachPlanByPlanId(String planId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(CoachPlan.class, planId);
	}

	//删除训练
	@Override
	public void deleteCoachPlan(String planId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(CoachPlan.class, planId));
	}

	//查询每个教练的未完成训练总数
		@Override
		public Integer findTotalUndoneCoachPlan(Coach coach) {
			// TODO Auto-generated method stub
			Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery("select count(*) from coachplan where plan_coach = ? and planFlag = ?");
			sqlQuery.setParameter(0, coach.getCoachId());
			sqlQuery.setParameter(1, 0);
			String strTotal = sqlQuery.uniqueResult().toString();
			Integer totalCount = Integer.valueOf(strTotal);
			session.getTransaction().commit();
			session.close();
			return totalCount;
		}

		//分页显示未完成训练
		@Override
		public List<CoachPlan> showUndoneCoachPlan(Coach coach, int begin,
				Integer pageSize) {
			// TODO Auto-generated method stub
			Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from CoachPlan where plan_coach = :coachId and planFlag = :planFlag order by planTime desc");
			query.setParameter("coachId", coach.getCoachId());
			query.setParameter("planFlag", 0);
			query.setFirstResult(begin);
			query.setMaxResults(pageSize);
			List<CoachPlan> list = query.list();
			session.getTransaction().commit();
			session.close();
			
			return list;	
		}

		//修改训练计划
		@Override
		public void updateCoachPlan(CoachPlan oldCoachPlan) {
			// TODO Auto-generated method stub
			this.getHibernateTemplate().update(oldCoachPlan);
		}

		//查找所有球员
		@Override
		public List<Player> findAllPlayerByCoach(Coach planCoach) {
			// TODO Auto-generated method stub
			//return this.getHibernateTemplate().f;
			return null;
		}

		//根据球员ID查找球员
		@Override
		public Player findPlayerByPid(String pId) {
			// TODO Auto-generated method stub
			return this.getHibernateTemplate().get(Player.class, pId);
		}

		//保存summary
		@Override
		public void writeCoachPlanSummary(Summary summary) {
			// TODO Auto-generated method stub
			this.getHibernateTemplate().save(summary);
		}

		//寻找summary
		@Override
		public List<Summary> findSummaryByPlan(String planId) {
			// TODO Auto-generated method stub
			Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Summary where summary_coachPlan = :planId order by summaryId desc");
			query.setParameter("planId", planId);
			List<Summary> list = query.list();
			session.getTransaction().commit();
			session.close();
			
			return list;	
		}

		//寻找summary
		@Override
		public Summary findSummaryBySummaryId(String summaryId) {
			// TODO Auto-generated method stub
			return this.getHibernateTemplate().get(Summary.class, summaryId);
		}
		//更新summary
		@Override
		public void updateSummary(Summary summary) {
			// TODO Auto-generated method stub
			this.getHibernateTemplate().update(summary);
		}

		//根据planId与pId查找summary
		@Override
		public String findSummaryByPlanIdAndPId(String planId, String pid) {
			// TODO Auto-generated method stub
			Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery("select summaryId from summary where summary_coachPlan = ? and summaryPlayerId = ?");
			sqlQuery.setParameter(0, planId);
			sqlQuery.setParameter(1, pid);

			System.out.println(planId+"---"+pid+"-----------------------------");
			
		    String summaryId = sqlQuery.uniqueResult().toString();
		    System.out.println(summaryId+"------------------------------");
			session.getTransaction().commit();
			session.close();
			return summaryId;
			//String hql = "from Summary  where summary_coachPlan=? and s.pid=?";
		}

		//删除summary
		@Override
		public void deleteSummary(Summary checkSummary) {
			// TODO Auto-generated method stub
			this.getHibernateTemplate().delete(checkSummary);
		}

		//球员给教练写建议
		@Override
		public void writeNewSuggestForCoach(SuggestForCoach suggestForCoach) {
			// TODO Auto-generated method stub
			this.getHibernateTemplate().save(suggestForCoach);
		}

		//删除球员给教练的建议
		@Override
		public void deleteSuggestForCoach(String sugCoachId) {
			// TODO Auto-generated method stub
			this.getHibernateTemplate().delete(this.getHibernateTemplate().get(SuggestForCoach.class, sugCoachId));
		}

		//查询未标记的建议
		@Override
		public List<SuggestForCoach> findUnMarkSuggestByCoach(Coach coach) {
			// TODO Auto-generated method stub
			Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from SuggestForCoach sfc where sfc.sugCoachFlag = :sugCoachFlag and sfc.sugCoach = :sugCoach order by sfc.sugCoachTime desc");
			//或者 Query query = session.createQuery("from SuggestForCoach  where sugCoachFlag = :sugCoachFlag and sug_for_Coach = :sugCoach order by sugCoachTime desc");
			query.setParameter("sugCoachFlag", 0);
			query.setParameter("sugCoach", coach);
			List<SuggestForCoach> list = query.list();
			session.getTransaction().commit();
			session.close();
			
			return list;		
		}

		//寻找某个建议
		@Override
		public SuggestForCoach findSuggestForCoachById(String sugCoachId) {
			// TODO Auto-generated method stub
			return this.getHibernateTemplate().get(SuggestForCoach.class, sugCoachId);
		}

		//更新建议
		@Override
		public void updateSugForCoach(SuggestForCoach suggestForCoach) {
			// TODO Auto-generated method stub
			this.getHibernateTemplate().update(suggestForCoach);
		}

		//寻找已标记建议
		@Override
		public List<SuggestForCoach> findMarkSuggest(Coach coach) {
			// TODO Auto-generated method stub
			Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from SuggestForCoach sfc where sfc.sugCoachFlag = :sugCoachFlag and sfc.sugCoach = :sugCoach order by sfc.sugCoachTime desc");
			//或者 Query query = session.createQuery("from SuggestForCoach  where sugCoachFlag = :sugCoachFlag and sug_for_Coach = :sugCoach order by sugCoachTime desc");
			query.setParameter("sugCoachFlag", 1);
			query.setParameter("sugCoach", coach);
			List<SuggestForCoach> list = query.list();
			session.getTransaction().commit();
			session.close();
			
			return list;	
		}

		//寻找前七次训练
		@Override
		public List<CoachPlan> getPracticeScore(CoachPlan plan) {
			// TODO Auto-generated method stub
			Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from CoachPlan cp where cp.planTime <= :thisPlanTime and cp.planFlag =:flag order by cp.planTime desc");
			query.setParameter("thisPlanTime", plan.getPlanTime());
			query.setParameter("flag", 1);
			query.setFirstResult(0);
			query.setMaxResults(7);
			List<CoachPlan> list = query.list();
			/*System.out.println(list.size()+"------------------");
			for (CoachPlan coachPlan : list) {
				System.out.println(coachPlan.getPlanTime()+"---------------");
			}*/
			session.getTransaction().commit();
			session.close();
			
			return list;
		}

		//修改教练信息
		@Override
		public void updateCoach(Coach coach) {
			// TODO Auto-generated method stub
			this.getHibernateTemplate().update(coach);
		}

		//寻找教练
		@Override
		public Coach findCoachById(String coachId) {
			// TODO Auto-generated method stub
			return this.getHibernateTemplate().get(Coach.class, coachId);
		}

		//寻找share显示到主页
		@Override
		public List<Share> findShareForInfo(Coach infoCoach) {
			// TODO Auto-generated method stub
			Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Share where s_coach = :coachId order by shareDate desc");
			query.setParameter("coachId", infoCoach.getCoachId());
			List<Share> list = query.list();
			session.getTransaction().commit();
			session.close();
			
			return list;
		}

		//获取头七次summary
		@Override
		public List<Summary> getSummaryForInfo(String pid) {
			// TODO Auto-generated method stub
			Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Summary s where s.summaryPlayerId = :playerId order by s.summaryId desc ");
			query.setParameter("playerId", pid);
			query.setFirstResult(0);
			query.setMaxResults(10);
			List<Summary> list = query.list();
			/*System.out.println(list.size()+"------------------");
			for (CoachPlan coachPlan : list) {
				System.out.println(coachPlan.getPlanTime()+"---------------");
			}*/
			session.getTransaction().commit();
			session.close();
			
			return list;
		}

		//获取最新的10次训练
		@Override
		public List<CoachPlan> getPracticeScoreForInfo(String pid) {
			// TODO Auto-generated method stub
			/*Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from CoachPlan cp where cp.planTime <= :thisPlanTime order by cp.planTime desc");
			query.setParameter("thisPlanTime", plan.getPlanTime());
			query.setFirstResult(0);
			query.setMaxResults(7);
			List<CoachPlan> list = query.list();
			System.out.println(list.size()+"------------------");
			for (CoachPlan coachPlan : list) {
				System.out.println(coachPlan.getPlanTime()+"---------------");
			}
			session.getTransaction().commit();
			session.close();
			
			return list;*/

			return null;
		}

		//根据名字查找教练
		@Override
		public Coach findCoachByNumber(String coachNumber) {
			// TODO Auto-generated method stub
			Session session = this.getSessionFactory().openSession();
			session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery("select * from coach where coachNumber = ?");
			sqlQuery.setParameter(0, coachNumber);
			sqlQuery.addEntity(Club.class);
			Coach coach = (Coach) sqlQuery.uniqueResult();
			session.getTransaction().commit();
			session.close();
			return coach;
		}

		//寻找某球员所有的训练评价
		@Override
		public List<Summary> findSummaryByPlayer(Player player) {
			// TODO Auto-generated method stub
			return (List<Summary>) this.getHibernateTemplate().find("from Summary s where s.summaryPlayerId = ?", player.getPid());
		}

		//寻找上首页的教练
		@Override
		public List<Coach> findMainPageCoach() {
			// TODO Auto-generated method stub
			return (List<Coach>) this.getHibernateTemplate().find("from Coach c where c.coachMainPage = ?", 1);
		}

		

	
}






