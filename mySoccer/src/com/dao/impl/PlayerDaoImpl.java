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

import com.dao.IPlayerDao;
import com.domain.Announcement;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Diary;
import com.domain.Player;
import com.domain.PlayerDeeds;
import com.domain.PlayerMemo;
import com.domain.Report;
import com.domain.Share;
import com.service.IPlayerService;

@Repository("playerDao")
public class PlayerDaoImpl extends HibernateDaoSupport implements IPlayerDao {
	@Autowired
	@Qualifier("sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	//创建新任务
	@Override
	public void writeNewPlayerMemo(PlayerMemo playerMemo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(playerMemo);
	}

	//根据player查找memo
	@Override
	public List<PlayerMemo> findPlayerMemoByPlayer(Player player) {
		// TODO Auto-generated method stub
		return (List<PlayerMemo>) this.getHibernateTemplate().find("from PlayerMemo p where p.memoPlayer = ? order by playerMemoTime desc", player);
	}

	//根据playerMemoId查找memo
	@Override
	public PlayerMemo findPlayerMemoByMemoId(String playerMemoId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(PlayerMemo.class, playerMemoId);
	}

	//更新playerMemo
	@Override
	public void updatePlayerMemo(PlayerMemo playerMemo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(playerMemo);
	}

	//删除playerMemo
	@Override
	public void deletePlayerMemoById(String playerMemoId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(PlayerMemo.class, playerMemoId));
	}

	//创建时间轴事迹
	@Override
	public void createPlayerDeeds(PlayerDeeds playerDeeds) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(playerDeeds);
	}

	//删除时间轴事迹
	@Override
	public void deletePlayerDeedsById(String playerDeedsId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(PlayerDeeds.class, playerDeedsId));
	}

	//查找所有的球员事迹
	@Override
	public List<PlayerDeeds> findAllPlayerDeedsByPlayer(Player player) {
		// TODO Auto-generated method stub
		return (List<PlayerDeeds>) this.getHibernateTemplate().find("from PlayerDeeds pd where pd.deedsPlayer = ? order by pd.playerDeedsTitleTime desc", player);
	}

	//查找球员
	@Override
	public Player findPlayerById(String pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Player.class, pid);
	}

	//修改球员
	@Override
	public void updatePlayer(Player player) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(player);
	}

	//写日记
	@Override
	public void createDiary(Diary diary) {
		// TODO Auto-generated method stub
		diary.setViewNumber(1);
		this.getHibernateTemplate().save(diary);
	}

	//寻找日记总数
	@Override
	public Integer findTotalDiaryByPlayer(Player player) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from diary where d_player = ?");
		sqlQuery.setParameter(0, player.getPid());
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//寻找所有的日记
	@Override
	public List<Diary> findAllDiary(Player player, int begin, Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary where d_player = :playerId order by diaryDate desc");
		query.setParameter("playerId", player.getPid());
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//查找日记
	@Override
	public Diary findDiaryById(String diaryId) {
		// TODO Auto-generated method stub
		Diary diary = this.getHibernateTemplate().get(Diary.class, diaryId);
		Integer number = diary.getViewNumber();
		number++;
		diary.setViewNumber(number);
		this.getHibernateTemplate().update(diary);
		return diary;
	}

	//删除日记
	@Override
	public void deleteDiaryById(String diaryId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Diary.class, diaryId));
	}

	//根据条件查找日记数目
	@Override
	public Integer findTotalDiary(int i, Player player) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = null;
		if(i == 1) { // 仅自己可见
			sqlQuery = session.createSQLQuery("select count(*) from diary where d_player = ? and diaryPermission = ?");
		} else { //教练队友可见
			sqlQuery = session.createSQLQuery("select count(*) from diary where d_player = ? and diaryPermission = ?");
		}                                  //select count(*) from diary where d_player in (select pid from player where p_coach = ?) and diaryPermission != ?;
		sqlQuery.setParameter(0, player.getPid());
		sqlQuery.setParameter(1, i);
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//根据条件查找日记
	@Override
	public List<Diary> findPageDiary(int i, Player player, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = null;
		if(i == 1) {
			query = session.createQuery("from Diary where d_player = :playerId and diaryPermission = :permissionId order by diaryDate desc");
			
		} else {
			query = session.createQuery("from Diary where d_player = :playerId and diaryPermission = :permissionId order by diaryDate desc");
		}
		query.setParameter("playerId", player.getPid());
		query.setParameter("permissionId", i);
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;	
	}

	//寻找队友日记总数
	@Override
	public Integer findTotalTeammatesDiary(Player player) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select count(*) from diary where d_player in (select pid from player where p_coach = ? and pid != ?) and diaryPermission != ?;");
		sqlQuery.setParameter(0, player.getpCoach());
		sqlQuery.setParameter(1, player.getPid());
		sqlQuery.setParameter(2, 1); //去掉仅自己可见部分
		String strTotal = sqlQuery.uniqueResult().toString();
		Integer totalCount = Integer.valueOf(strTotal);
		//System.out.println(totalCount);
		session.getTransaction().commit();
		session.close();
		return totalCount;
	}

	//查找队友日记
	@Override
	public List<Diary> findTeammatesDiary(Player player, int begin,
			Integer pageSize) {
		// TODO Auto-generated method stub
		//(“from Customer c where 1>(select count(o) from c.orders o)”)
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary where d_player in (select pid from Player where p_coach = :coachId and pid != :playerId) and diaryPermission != :permissionId order by diaryDate desc");
		query.setParameter("coachId", player.getpCoach());
		query.setParameter("playerId", player.getPid());
		query.setParameter("permissionId", 1);
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	//选好球员日记显示到主页
	@Override
	public List<Diary> findDiaryForInfo(Player infoPlayer) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Diary where d_player = :playerId and diaryPermission = :permissionId order by diaryDate desc");
		query.setParameter("playerId", infoPlayer.getPid());
		query.setParameter("permissionId", 0);
		List<Diary> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	//模糊查询俱乐部
	@Override
	public List<Club> fuzzyFindClub(String clubFuzzyName) {
		// TODO Auto-generated method stub
		//this.getHibernateTemplate().find("from bean.User u where u.name like ?", "%test%");
		//return (List<Club>) this.getHibernateTemplate().find("from Club c where c.clubName like ?",clubFuzzyName);
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Club where clubName like :clubName and clubCode = :clubCode order by cid desc");
		query.setParameter("clubName", clubFuzzyName);
		query.setParameter("clubCode", 1);
		List<Club> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	//根据账号查找球员
	@Override
	public Player findPlayerByNumber(String playerNumber) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from player where playerNumber = ?");
		sqlQuery.setParameter(0, playerNumber);
		sqlQuery.addEntity(Club.class);
		Player player = (Player) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return player;
	}

	//创建球员报告
	@Override
	public void createPlayerReport(Report report) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(report);
	}

	//查找球员报告
	@Override
	public Report findPlayerReportByPid(String pid) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from report where r_player = ?");
		sqlQuery.setParameter(0, pid);
		sqlQuery.addEntity(Report.class);
		Report report = (Report) sqlQuery.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return report;
	}

	//更新球员报告
	@Override
	public void updatePlayerReport(Report oldReport) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(oldReport);
	}

	@Override
	public void updateDiary(Diary diary) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(diary);
	}

	//查找上首页球员
	@Override
	public List<Player> findMainPagePlayer() {
		// TODO Auto-generated method stub
		return (List<Player>) this.getHibernateTemplate().find("from Player p where p.playerMainPage = ?", 1);
	}
}







