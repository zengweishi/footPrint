package com.action;

import java.io.UnsupportedEncodingException;
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
import com.domain.Player;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IClubService;
import com.service.ICoachService;
import com.service.IPlayerService;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class PlayerFindClubAction extends ActionSupport{
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	//模糊查询俱乐部
	@Action(value="fuzzyFindClub",results={@Result(name="success",location="/jsp/club/showFindClub.jsp"),
			@Result(name="clubHomePage",location="/jsp/homePage/clubHomePage.jsp")})
	public String fuzzyFindClub() {
		try {
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");		
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String clubFuzzyName = ServletActionContext.getRequest().getParameter("clubFuzzyName");
		int length = clubFuzzyName.length();
		char[] clubKw = clubFuzzyName.toCharArray();
		String keywords = "%";
		for(int i = 0;i<length;i++) {
			keywords = keywords+clubKw[i]+"%";
		}
		
		List<Club> clubList = playerService.fuzzyFindClub(keywords);
		
		for (Club club : clubList) {
			System.out.println(club.getClubName());
		}
		ServletActionContext.getRequest().setAttribute("clubList", clubList);
		String pageFlag = ServletActionContext.getRequest().getParameter("pageFlag");
		if(pageFlag != null) {
			return "clubHomePage";
		} else {
			return SUCCESS;
		}
	}
	
	//申请俱乐部
	@Action(value="applyClubByPlayer",results={@Result(name="success",location="findClubApply",type="redirectAction")})
	public String applyClubByPlayer() {
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		String cid = ServletActionContext.getRequest().getParameter("cid");
		player.setpApplyId(cid);
		playerService.updatePlayer(player);
		//System.out.println("--------------------------------------------");
		return SUCCESS;
	}
	
	//查看申请俱乐部
	@Action(value="findClubApply",results={@Result(name="success",location="/jsp/club/clubFirstPage.jsp")})
	public String findClubApply() {
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		String cid = player.getpApplyId();
		Club applyClub = clubService.findClubNameByClubId(cid);
		ServletActionContext.getRequest().setAttribute("infoClub", applyClub);
		return SUCCESS;
	}
	
	//取消申请(回到主页)
	@Action(value="cancelClubApply",results={@Result(name="success",location="getplayerInformation?pid=0",type="redirectAction")})
	public String cancelClubApply() {
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		player.setpApplyId(null);
		playerService.updatePlayer(player);
		return SUCCESS;
	}
	
	
	
	//教练申请俱乐部
	@Action(value="applyClubByCoach",results={@Result(name="success",location="findCoachApply",type="redirectAction")})
	public String applyClubByCoach() {
		//System.out.println("------------------------0000000000000000000000000------------------------");
		Coach coach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existCoach");
		String cid = ServletActionContext.getRequest().getParameter("cid");
		//System.out.println(cid+"------------------------------------------");
		coach.setCoachApplyId(cid);
		
		coachService.updateCoach(coach);
		return SUCCESS;
	}
	
	//查看教练申请的俱乐部
	@Action(value="findCoachApply",results={@Result(name="success",location="/jsp/club/clubFirstPage.jsp")})
	public String findCoachApply() {
		Coach coach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existCoach");
		String cid = coach.getCoachApplyId();
		Club applyClub = clubService.findClubNameByClubId(cid);
		ServletActionContext.getRequest().setAttribute("infoClub", applyClub);
		return SUCCESS;
	}
	
	//取消教练申请
	@Action(value="cancleCoachApply",results={@Result(name="success",location="getCoachInformation?coachId=0",type="redirectAction")})
	public String cancleCoachApply() {
		Coach coach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existCoach");
		coach.setCoachApplyId(null);
		coachService.updateCoach(coach);
		return SUCCESS;
	}
	
	
	
	
	
	
}
