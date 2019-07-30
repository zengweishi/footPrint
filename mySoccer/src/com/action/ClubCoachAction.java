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
import com.service.IAdminService;
import com.service.IClubService;

/**
 * 俱乐部教练管理
 * @author 45度炸
 *
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ClubCoachAction extends ActionSupport implements ModelDriven<Coach> {

	private Coach coach = new Coach();
	
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	@Autowired
	@Qualifier("adminService")
	private IAdminService adminService;
	
	@Override
	public Coach getModel() {
		// TODO Auto-generated method stub
		return coach;
	}
	
	//添加新教练
	@Action(value="addNewCoach",results={@Result(name="success",location="findAllCoach?currPage=1&code=0",type="redirectAction")})
	public String addNewCoach() {
		Club club = (Club) ServletActionContext.getRequest().getSession().getAttribute("existClub");
		coach.setCoachClub(club);
		if(club.getClubCoachFlag() == 0) { //判断俱乐部有没有教练
			club.setClubCoachFlag(1); //原来没有教练那么添加教练后则将设置为1
			clubService.updateClub(club);
		}
		clubService.addNewCoach(coach);
		return SUCCESS;
		
	}
	
	//显示所有教练
	@Action(value="findAllCoach",results={@Result(name="success",location="/jsp/club/showAllCoach.jsp"),@Result(name="deletePage",location="/jsp/club/deleteCoach.jsp")})
	public String findAllCoach() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Coach> pageBean = clubService.findAllCoach(currPage,(Club)ServletActionContext.getRequest().getSession().getAttribute("existClub"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		Integer code = Integer.parseInt( ServletActionContext.getRequest().getParameter("code"));
		if(code == 0) {
			//查看教练界面
			return SUCCESS;
		} if(code == 1) {
			//跳到删除教练界面
			return "deletePage";
		}
		return null;
		
	}
	
	//删除教练(将教练与俱乐部解除绑定,coachClub设置为NULL)
	@Action(value="deleteCoachFromClub",results={@Result(name="success",location="findAllCoach?currPage=1&code=1",type="redirectAction")})
	public String deleteCoachFromClub() {
		Club club = (Club) ServletActionContext.getRequest().getSession().getAttribute("existClub");
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		clubService.deleteCoachFromClub(coachId);
		List<Coach> list = clubService.findAllCoachByClub(club);
		if(list.size() == 0) {
			club.setClubCoachFlag(0); //俱乐部中没有教练将clubCoachFlag设置为0
			clubService.updateClub(club);
		}
		return SUCCESS;
	}
	
	//教练登录(教练添加的操作由俱乐部完成，所以教练登录也一并写在这个模块算了)
	@Action(value="coachLogin",results={@Result(name="success",location="/jsp/coach/coachHomePage.jsp"),@Result(name="fail",location="/jsp/userLogin.jsp")})
	public String coachLogin() {
		Coach newCoach = new Coach();
		String coachNumber = ServletActionContext.getRequest().getParameter("username");
		String coachPassword = ServletActionContext.getRequest().getParameter("password");
		newCoach.setCoachNumber(coachNumber);
		newCoach.setCoachPassword(coachPassword);
		Coach existCoach = clubService.coachLogin(newCoach);
		if(existCoach == null) {
			ServletActionContext.getRequest().setAttribute("msg", "用户名或者密码错误");
			//System.out.println("登录失败---------------------------");
			return "fail";
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("existCoach", existCoach);
			//System.out.println("登录成功---------------------------");
			return SUCCESS;
		}
	}
	
	//显示未接收的教练
	@Action(value="findUnReceiveCoach",results={@Result(name="success",location="/jsp/club/receiveCoach.jsp")})
	public String findUnReceiveCoach() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Coach> pageBean = clubService.findUnReceiveCoach(currPage,(Club)ServletActionContext.getRequest().getSession().getAttribute("existClub"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}
	
	//引进教练
	@Action(value="receiveCoach",results={@Result(name="success",location="findUnReceiveCoach?currPage=1",type="redirectAction")})
	public String receiveCoach() {
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		Club club = (Club) ServletActionContext.getRequest().getSession().getAttribute("existClub");
		Coach reCoach = clubService.findCoachByCoachId(coachId);
		reCoach.setCoachClub(club);
		reCoach.setCoachApplyId(null);
		clubService.updateCoach(reCoach);
		return SUCCESS;
	}
	
	//拒绝教练
	@Action(value="refuseCoach",results={@Result(name="success",location="findUnReceiveCoach?currPage=1",type="redirectAction")})
	public String refuseCoach() {
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		Coach reCoach = clubService.findCoachByCoachId(coachId);
		reCoach.setCoachApplyId(null);
		clubService.updateCoach(reCoach);
		return SUCCESS;
	}
	
}
















