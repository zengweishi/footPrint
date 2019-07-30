package com.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.domain.Club;
import com.domain.Coach;
import com.domain.PageBean;
import com.domain.Player;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.IClubService;

/**
 * 俱乐部球员
 * @author 45度炸
 *
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ClubPlayerAction extends ActionSupport implements ModelDriven<Player>{
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	private Player player = new Player();
	@Override
	public Player getModel() {
		// TODO Auto-generated method stub
		return player;
	}
	
	//接收球员
	@Action(value="receivePlayer",results={@Result(name="success",location="findUnreceivePlayer?currPage=1",type="redirectAction")})
	public String receivePlayer() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Player rePlayer = clubService.findPlayerByPid(pid);
		Club club = (Club) ServletActionContext.getRequest().getSession().getAttribute("existClub");
		rePlayer.setpClubId(club.getCid());
		rePlayer.setpApplyId(null);//申请由俱乐部ID改设为null
		clubService.updatePlayer(rePlayer);
		return SUCCESS;
	}
	
	//拒绝球员（球员的pApplyId设为NULL）
	@Action(value="refusePlayer",results={@Result(name="success",location="findUnreceivePlayer?currPage=1",type="redirectAction")})
	public String refusePlayer() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Player rePlayer = clubService.findPlayerByPid(pid);
		//System.out.println(pid);
		rePlayer.setpApplyId(null);
		clubService.updatePlayer(rePlayer);
		return SUCCESS;
	}
	
	//显示俱乐部未接收的球员，pApplyId=clubId
	@Action(value="findUnreceivePlayer",results={@Result(name="success",location="/jsp/club/receivePlayer.jsp")})
	public String findUnreceivePlayer() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Player> pageBean = clubService.findUnreceivePlayer(currPage,(Club)ServletActionContext.getRequest().getSession().getAttribute("existClub"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}
	
	//显示未有教练球员
	@Action(value="findPlayerWithoutCoach",results={@Result(name="success",location="/jsp/club/playerWithoutCoach.jsp")})
	public String findPlayerWithoutCoach() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Player> pageBean = clubService.findPlayerWithoutCoach(currPage,(Club)ServletActionContext.getRequest().getSession().getAttribute("existClub"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		//查询所有教练显示到分配教练列表
		//System.out.println("----------1111111111111111111111------------------");
		Club club = (Club) ServletActionContext.getRequest().getSession().getAttribute("existClub");
		List<Coach> list = clubService.findAllCoachByClub(club);
		//System.out.println(list.size()+"------------------");
		/*for (Coach coach : list) {
			System.out.println(coach.getCoachRealName()+"---------------------");
		}*/
		//System.out.println("------------111111111111111111111111----------------------");
		ServletActionContext.getRequest().getSession().setAttribute("coachList", list);
		return SUCCESS;
	}
	
	//显示已有教练球员
	@Action(value="findPlayerWithCoach",results={@Result(name="success",location="/jsp/club/playerWithCoach.jsp")})
	public String findPlayerWithCoach() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Player> pageBean = clubService.findPlayerWithCoach(currPage,(Club)ServletActionContext.getRequest().getSession().getAttribute("existClub"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}
	
	//为球员分配教练
	@Action(value="setCoachForPlayer",results={@Result(name="success",location="findPlayerWithoutCoach?currPage=1",type="redirectAction")})
	public String setCoachForPlayer() {
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Player rePlayer = clubService.findPlayerByPid(pid);
		Coach coach = clubService.findCoachByCoachId(coachId);
		rePlayer.setpCoach(coach);
		clubService.updatePlayer(rePlayer);
		return SUCCESS;
	}
	
	//解除球员（俱乐部ID与教练设为NULL）
	@Action(value="releasePlayer",results={@Result(name="success",location="findPlayerWithCoach?currPage=1",type="redirectAction")})
	public String releasePlayer() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Player player = clubService.findPlayerByPid(pid);
		player.setpClubId(null);
		player.setpCoach(null);
		player.setpClubName(null);
		clubService.updatePlayer(player);
		return SUCCESS;
	}
	
	

}










