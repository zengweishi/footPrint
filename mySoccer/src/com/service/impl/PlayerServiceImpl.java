package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ICoachDao;
import com.dao.IPlayerDao;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Diary;
import com.domain.PageBean;
import com.domain.Player;
import com.domain.PlayerDeeds;
import com.domain.PlayerMemo;
import com.domain.Report;
import com.domain.Share;
import com.service.IPlayerService;
@Service("playerService")
@Transactional
public class PlayerServiceImpl implements IPlayerService {
	@Autowired
	@Qualifier("playerDao")
	private IPlayerDao playerDao;
	
	//创建playerMemo
	@Override
	public void writeNewPlayerMemo(PlayerMemo playerMemo) {
		// TODO Auto-generated method stub
		playerDao.writeNewPlayerMemo(playerMemo);
	}
	
	//根据player查找memo
	@Override
	public List<PlayerMemo> findPlayerMemoByPlayer(Player player) {
		// TODO Auto-generated method stub
		return playerDao.findPlayerMemoByPlayer(player);
	}

	//设置playerMemo为已完成
	@Override
	public PlayerMemo findPlayerMemoByMemoId(String playerMemoId) {
		// TODO Auto-generated method stub
		return playerDao.findPlayerMemoByMemoId(playerMemoId);
	}

	//更新playerMemo
	@Override
	public void updatePlayerMemo(PlayerMemo playerMemo) {
		// TODO Auto-generated method stub
		playerDao.updatePlayerMemo(playerMemo);
	}

	//删除playerMemo
	@Override
	public void deletePlayerMemoById(String playerMemoId) {
		// TODO Auto-generated method stub
		playerDao.deletePlayerMemoById(playerMemoId);
	}

	//创建时间轴事迹
	@Override
	public void createPlayerDeeds(PlayerDeeds playerDeeds) {
		// TODO Auto-generated method stub
		playerDao.createPlayerDeeds(playerDeeds);
	}

	//删除时间轴事迹
	@Override
	public void deletePlayerDeedsById(String playerDeedsId) {
		// TODO Auto-generated method stub
		playerDao.deletePlayerDeedsById(playerDeedsId);
	}

	//查找球员的时间轴事迹
	@Override
	public List<PlayerDeeds> findAllPlayerDeedsByPlayer(Player player) {
		// TODO Auto-generated method stub
		return playerDao.findAllPlayerDeedsByPlayer(player);
	}

	//寻找球员
	@Override
	public Player findPlayerById(String pid) {
		// TODO Auto-generated method stub
		return playerDao.findPlayerById(pid);
	}

	//更新球员
	@Override
	public void updatePlayer(Player player) {
		// TODO Auto-generated method stub
		playerDao.updatePlayer(player);
	}

	//球员写日记
	@Override
	public void createDiary(Diary diary) {
		// TODO Auto-generated method stub
		playerDao.createDiary(diary);
	}

	//寻找所有的日记
	@Override
	public PageBean<Diary> findAllDiary(Integer currPage, Player player) {
		// TODO Auto-generated method stub
		PageBean<Diary> pageBean = new PageBean<Diary>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = playerDao.findTotalDiaryByPlayer(player);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Diary> list = playerDao.findAllDiary(player,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//查找日记
	@Override
	public Diary findDiaryById(String diaryId) {
		// TODO Auto-generated method stub
		return playerDao.findDiaryById(diaryId);
	}

	//删除日记
	@Override
	public void deleteDiaryById(String diaryId) {
		// TODO Auto-generated method stub
		playerDao.deleteDiaryById(diaryId);
	}

	//根据权限查找日记
	@Override
	public PageBean<Diary> findPageDiary(int i, Integer currPage,
			Player player) {
		// TODO Auto-generated method stub
		PageBean<Diary> pageBean = new PageBean<Diary>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = null;
		if(i == 1) {
			//仅自己可见
			totalCount = playerDao.findTotalDiary(1,player);
		}
		else  {
			//教练队友可见(i==2)
			totalCount = playerDao.findTotalDiary(2,player);
		}
		
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Diary> list = null;
		if(i == 1) {
			//仅自己可见
			 list = playerDao.findPageDiary(1,player,begin,pageSize);
		}
		else  { 
			//教练队友可见(i==2)
			 list = playerDao.findPageDiary(2,player,begin,pageSize);
		}
		
		pageBean.setList(list);
		return pageBean;
	}

	//查找队友日记
	@Override
	public PageBean<Diary> findTeammatesDiary(Integer currPage, Player player) {
		// TODO Auto-generated method stub
		PageBean<Diary> pageBean = new PageBean<Diary>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = playerDao.findTotalTeammatesDiary(player);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Diary> list = playerDao.findTeammatesDiary(player,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//寻找球员日记显示到主页
	@Override
	public List<Diary> findDiaryForInfo(Player infoPlayer) {
		// TODO Auto-generated method stub
		return playerDao.findDiaryForInfo(infoPlayer);
	}

	//模糊查询俱乐部
	@Override
	public List<Club> fuzzyFindClub(String clubFuzzyName) {
		// TODO Auto-generated method stub
		return playerDao.fuzzyFindClub(clubFuzzyName);
	}

	//查找球员
	@Override
	public Player findPlayerByNumber(String playerNumber) {
		// TODO Auto-generated method stub
		return playerDao.findPlayerByNumber(playerNumber);
	}

	//新建球员报告
	@Override
	public void createPlayerReport(Report report) {
		// TODO Auto-generated method stub
		playerDao.createPlayerReport(report);
	}

	//查找球员报告
	@Override
	public Report findPlayerReportByPid(String pid) {
		// TODO Auto-generated method stub
		return playerDao.findPlayerReportByPid(pid);
	}

	//更新球员报告
	@Override
	public void updatePlayerReport(Report oldReport) {
		// TODO Auto-generated method stub
		playerDao.updatePlayerReport(oldReport);
	}

	@Override
	public void updateDiary(Diary diary) {
		// TODO Auto-generated method stub
		playerDao.updateDiary(diary);
	}

	@Override
	public List<Player> findMainPagePlayer() {
		// TODO Auto-generated method stub
		return playerDao.findMainPagePlayer();
	}
	
	

}
