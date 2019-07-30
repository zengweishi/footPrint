package com.service.impl;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ICoachDao;
import com.domain.Coach;
import com.domain.CoachPlan;
import com.domain.PageBean;
import com.domain.Player;
import com.domain.Share;
import com.domain.SuggestForCoach;
import com.domain.Summary;
import com.service.ICoachService;

@Service("coachService")
@Transactional
public class CoachServiceImpl implements ICoachService {

	@Autowired
	@Qualifier("coachDao")
	private ICoachDao coachDao;
	
	//教练添加球员
	@Override
	public void addPlayerByCoach(Player player) {
		// TODO Auto-generated method stub
		coachDao.addPlayerByCoach(player);
	}
                      
	//显示所有的球员
	@Override
	public PageBean<Player> showAllPlayer(Integer currPage, Coach coach ) {
		// TODO Auto-generated method stub
		PageBean<Player> pageBean = new PageBean<Player>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 6;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = coachDao.findTotalPlayerByCoach(coach);
		//标记总数小于单页数，则不进行分页
		if(totalCount < pageSize) {			
			ServletActionContext.getRequest().getSession().setAttribute("totalPlayer",totalCount.toString());
		}
		else {			
			ServletActionContext.getRequest().getSession().setAttribute("totalPlayer","");
		}
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Player> list = coachDao.showAllPlayer(coach,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	 //教练解除球员
	@Override
	public void deletePlayerByCoach(Player player) {
		// TODO Auto-generated method stub
		coachDao.deletePlayerByCoach(player);
	}
	
	//发布新分享
	@Override
	public void wiriteShare(Share share) {
		// TODO Auto-generated method stub
		coachDao.wiriteShare(share);
	}

	//显示所有文章
	@Override
	public PageBean<Share> showAllShare(Integer currPage, Coach coach) {
		// TODO Auto-generated method stub
		PageBean<Share> pageBean = new PageBean<Share>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = coachDao.findTotalShareByCoach(coach);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<Share> list = coachDao.showAllShare(coach,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//显示分享内容
	@Override
	public Share showShareArtical(String shareId) {
		// TODO Auto-generated method stub
		return coachDao.showShareArtical(shareId);
	}

	//修改分享
	@Override
	public void updateShareArtical(Share share) {
		// TODO Auto-generated method stub
		coachDao.updateShareArtical(share);
	}

	//删除分享
	@Override
	public void deleteShare(String shareId) {
		// TODO Auto-generated method stub
		coachDao.deleteShare(shareId);
	}

	//球员登录
	@Override
	public Player playerLogin(Player player) {
		// TODO Auto-generated method stub
		return coachDao.playerLogin(player);
	}

	//新建训练
	@Override
	public void writeCoachPlan(CoachPlan coachPlan) {
		// TODO Auto-generated method stub
		coachDao.writeCoachPlan(coachPlan);
	}

	//显示所有完成训练
	@Override
	public PageBean<CoachPlan> showAllCoachPlan(Integer currPage,
			Coach coach) {
		// TODO Auto-generated method stub
		PageBean<CoachPlan> pageBean = new PageBean<CoachPlan>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 6;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = coachDao.findTotalshowAllCoachPlan(coach);
		//标记总数小于单页数，则不进行分页
		if(totalCount < pageSize) {			
			ServletActionContext.getRequest().getSession().setAttribute("totalCoachPlan",totalCount.toString());
		}
		else {			
			ServletActionContext.getRequest().getSession().setAttribute("totalCoachPlan","");
		}
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<CoachPlan> list = coachDao.showAllCoachPlan(coach,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//查找某个训练
	@Override
	public CoachPlan findCoachPlanByPlanId(String planId) {
		// TODO Auto-generated method stub
		return coachDao.findCoachPlanByPlanId(planId);
	}

	//删除训练
	@Override
	public void deleteCoachPlan(String planId) {
		// TODO Auto-generated method stub
		coachDao.deleteCoachPlan(planId);
	}

	//显示未完成训练
	@Override
	public PageBean<CoachPlan> showUndoneCoachPlan(Integer currPage,
			Coach coach) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
			PageBean<CoachPlan> pageBean = new PageBean<CoachPlan>();
			// 设置参数：
			// 设置当前页数：
			pageBean.setCurrPage(currPage);
			// 设置每页显示的记录数:
			Integer pageSize = 6;
			pageBean.setPageSize(pageSize);
			// 设置总记录数:
			Integer totalCount = coachDao.findTotalUndoneCoachPlan(coach);
			//标记总数小于单页数，则不进行分页
			if(totalCount < pageSize) {			
				ServletActionContext.getRequest().getSession().setAttribute("totalCoachPlan",totalCount.toString());
			}
			else {			
				ServletActionContext.getRequest().getSession().setAttribute("totalCoachPlan","");
			}
			pageBean.setTotalCount(totalCount);
			// 设置总页数:
			double tc = totalCount;
			Double num = Math.ceil(tc / pageSize);
			pageBean.setTotalPage(num.intValue());
			// 设置每页显示的数据的集合:
			int begin = (currPage - 1) * pageSize;
			List<CoachPlan> list = coachDao.showUndoneCoachPlan(coach,begin,pageSize);
			pageBean.setList(list);
			return pageBean;
	}

	//修改训练计划
	@Override
	public void updateCoachPlan(CoachPlan oldCoachPlan) {
		// TODO Auto-generated method stub
		coachDao.updateCoachPlan(oldCoachPlan);
	}

	//查找所有球员
	@Override
	public List<Player> findAllPlayerByCoach(Coach planCoach) {
		// TODO Auto-generated method stub
		return coachDao.findAllPlayerByCoach(planCoach);
	}

	//根据ID查找球员 
	@Override
	public Player findPlayerByPid(String pId) {
		// TODO Auto-generated method stub
		return coachDao.findPlayerByPid(pId);
	}

	//保存summary
	@Override
	public void writeCoachPlanSummary(Summary summary) {
		// TODO Auto-generated method stub
		coachDao.writeCoachPlanSummary(summary);
	}

	//寻找plan下的summary
	@Override
	public List<Summary> findSummaryByPlan(String planId) {
		// TODO Auto-generated method stub
		return coachDao.findSummaryByPlan(planId);
	}

	//寻找summary
	@Override
	public Summary findSummaryBySummaryId(String summaryId) {
		// TODO Auto-generated method stub
		return coachDao.findSummaryBySummaryId(summaryId);
	}

	//更新训练评价
	@Override
	public void updateSummary(Summary summary) {
		// TODO Auto-generated method stub
		coachDao.updateSummary(summary);
	}

	//根据planId与pId查找summary
	@Override
	public String findSummaryByPlanIdAndPId(String planId,String pid) {
		// TODO Auto-generated method stub
		return coachDao.findSummaryByPlanIdAndPId(planId,pid);
	}

	//删除summary
	@Override
	public void deleteSummary(Summary checkSummary) {
		// TODO Auto-generated method stub
		coachDao.deleteSummary(checkSummary);
	}

	//球员给教练写建议
	@Override
	public void writeNewSuggestForCoach(SuggestForCoach suggestForCoach) {
		// TODO Auto-generated method stub
		coachDao.writeNewSuggestForCoach(suggestForCoach);
	}

	//删除球员给教练的建议
	@Override
	public void deleteSuggestForCoach(String sugCoachId) {
		// TODO Auto-generated method stub
		coachDao.deleteSuggestForCoach(sugCoachId);
	}

	//寻找未标记建议
	@Override
	public List<SuggestForCoach> findUnMarkSuggestByCoach(Coach coach) {
		// TODO Auto-generated method stub
		return coachDao.findUnMarkSuggestByCoach(coach);
	}

	//寻找某个建议
	@Override
	public SuggestForCoach findSuggestForCoachById(String sugCoachId) {
		// TODO Auto-generated method stub
		return coachDao.findSuggestForCoachById(sugCoachId);
	}

	//更新建议
	@Override
	public void updateSugForCoach(SuggestForCoach suggestForCoach) {
		// TODO Auto-generated method stub
		coachDao.updateSugForCoach(suggestForCoach);
	}

	//寻找已标记的建议
	@Override
	public List<SuggestForCoach> findMarkSuggest(Coach coach) {
		// TODO Auto-generated method stub
		return coachDao.findMarkSuggest(coach);
	}

	//寻找前七次训练
	@Override
	public List<CoachPlan> getPracticeScore(CoachPlan plan) {
		// TODO Auto-generated method stub
		return coachDao.getPracticeScore(plan);
	}

	//修改教练信息
	@Override
	public void updateCoach(Coach coach) {
		// TODO Auto-generated method stub
		coachDao.updateCoach(coach);
	}

	//寻找教练
	@Override
	public Coach findCoachById(String coachId) {
		// TODO Auto-generated method stub
		return coachDao.findCoachById(coachId);
	}

	//寻找share显示到主页
	@Override
	public List<Share> findShareForInfo(Coach infoCoach) {
		// TODO Auto-generated method stub
		return coachDao.findShareForInfo(infoCoach);
	}

	//获取头10次summary
	@Override
	public List<Summary> getSummaryForInfo(String pid) {
		// TODO Auto-generated method stub
		return coachDao.getSummaryForInfo(pid);
	}

	//获取最新的10次训练
	@Override
	public List<CoachPlan> getPracticeScoreForInfo(String pid) {
		// TODO Auto-generated method stub
		return coachDao.getPracticeScoreForInfo(pid);
	}

	//根据账号查找教练
	@Override
	public Coach findCoachByNumber(String coachNumber) {
		// TODO Auto-generated method stub
		return coachDao.findCoachByNumber(coachNumber);
	}

	//寻找某球员所有的训练评价
	@Override
	public List<Summary> findSummaryByPlayer(Player player) {
		// TODO Auto-generated method stub
		return coachDao.findSummaryByPlayer(player);
	}

	//寻找上首页的教练
	@Override
	public List<Coach> findMainPageCoach() {
		// TODO Auto-generated method stub
		return coachDao.findMainPageCoach();
	}

}











