package com.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.domain.Announcement;
import com.domain.Club;
import com.domain.Coach;
import com.domain.PageBean;
import com.domain.Player;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.ICoachService;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CoachPlayerAction extends ActionSupport implements ModelDriven<Player> {
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;

	private Player player = new Player();
	
	@Override
	public Player getModel() {
		// TODO Auto-generated method stub
		return player;
	}

	//教练添加球员
	@Action(value="addPlayerByCoach",results={@Result(name="success",location="/jsp/success.jsp")})
	public String addPlayerByCoach() {
		//Player player = new Player();
		Coach pCoach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existCoach");
		player.setpCoach(pCoach);
		player.setpClubId(pCoach.getCoachClub().getCid());
		String pNumber = ServletActionContext.getRequest().getParameter("pNumber");
		String password = ServletActionContext.getRequest().getParameter("password");
		String pRealName = ServletActionContext.getRequest().getParameter("pRealName");
		String pGender = ServletActionContext.getRequest().getParameter("pGender");
		String pTelePhone = ServletActionContext.getRequest().getParameter("pTelePhone");
		player.setpNumber(pNumber);
		player.setPassword(password);
		player.setpRealName(pRealName);
		player.setpGender(pGender);
		player.setpTelePhone(pTelePhone);
	
		//coachService.addPlayerByCoach(player);
		coachService.addPlayerByCoach(player);
		return SUCCESS;
	}
	
	
	//显示所有球员
	@Action(value="showAllPlayer",results={@Result(name="success",location="/jsp/coach/showAllPlayer.jsp")})
	public String showAllPlayer() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Player> pageBean = coachService.showAllPlayer(currPage,(Coach)ServletActionContext.getRequest().getSession().getAttribute("existCoach"));
		Integer code = Integer.parseInt( ServletActionContext.getRequest().getParameter("code"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		if(code == 1) {
			//跳到删除教练界面
			String flag = "1";
			ServletActionContext.getRequest().setAttribute("delete", flag);
		}
		return SUCCESS;
	}
	
	//解除球员
	@Action(value="deletePlayerByCoach",results={@Result(name="success",location="showAllPlayer?currPage=1&code=1",type="redirectAction")})
	public String deletePlayerByCoach() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Player player = new Player();
		player.setPid(pid);
		coachService.deletePlayerByCoach(player);
		return SUCCESS;
	}

	//球员登录
	@Action(value="playerLogin",results={@Result(name="success",location="/jsp/player/playerHomePage.jsp"),@Result(name="fail",location="/jsp/userLogin.jsp")})
	public String playerLogin() {
		String pNumber = ServletActionContext.getRequest().getParameter("username");
		String password = ServletActionContext.getRequest().getParameter("password");
		Player player = new Player();
		player.setpNumber(pNumber);
		player.setPassword(password);
		Player existPlayer = coachService.playerLogin(player);
		if(existPlayer == null) {
			ServletActionContext.getRequest().setAttribute("msg", "用户名或者密码错误");
			//System.out.println("登录失败---------------------------");
			return "fail";
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("existPlayer", existPlayer);
			//System.out.println("登录成功---------------------------");
			return SUCCESS;
		}
	}
	
	//球员界面显示我的队友
	@Action(value="showMyTeammate",results={@Result(name="success",location="/jsp/coach/showAllPlayer.jsp")})
	public String showMyTeammate() {
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		Coach playerCoach = player.getpCoach();
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Player> pageBean = coachService.showAllPlayer(currPage,playerCoach);
		Integer code = Integer.parseInt( ServletActionContext.getRequest().getParameter("code"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		if(code == 1) {
			//跳到删除教练界面
			String flag = "1";
			ServletActionContext.getRequest().setAttribute("delete", flag);
		}
		return SUCCESS;
	}
	
}








