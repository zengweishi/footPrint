package com.action;

import java.io.IOException;
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

import com.domain.Admin;
import com.domain.Club;
import com.domain.Coach;
import com.domain.PageBean;
import com.domain.Player;
import com.domain.Tourist;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.IAdminService;
import com.service.IClubService;
import com.service.ICoachService;
import com.service.IPlayerService;
import com.service.ITouristService;
import com.service.impl.PlayerServiceImpl;

/**
 * 管理员
 * @author 45度炸
 *
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {

	@Autowired
	@Qualifier("adminService")
	private IAdminService adminService;
	
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	@Autowired
	@Qualifier("touristService")
	private ITouristService touristService;
	
	private Admin admin = new Admin();
	
	@Override
	public Admin getModel() {
		// TODO Auto-generated method stub
		return admin;
	}
	
	//添加管理员
	@Action(value="addNewAdmin",results={@Result(name="success",location="/jsp/success.jsp")})
	public String addANewdmin() {
		Admin admin = new Admin();
		String adminName = ServletActionContext.getRequest().getParameter("adminName");
		String adminPassword = ServletActionContext.getRequest().getParameter("adminPassword");
		admin.setAdminName(adminName);
		admin.setAdminPassword(adminPassword);
		String[] adminPermission = ServletActionContext.getRequest().getParameterValues("permission");
		
		for(int i = 0;i < adminPermission.length;++i) {
			String permission = adminPermission[i];
			System.out.println(permission);
			Integer flag = Integer.parseInt(permission);
			if(flag == 1) { //用户管理权限
				admin.setUserFlag(1); 
			} else if(flag == 2) { //俱乐部管理权限
				admin.setClubFlag(1);
			} else if(flag == 3) { //管理员管理权限
				admin.setAdminerFlag(1);
			} else if(flag == 4) { //文章管理权限
				admin.setArticleFlag(1);
			} else if(flag == 5) { //新闻管理权限
				admin.setNewsFlag(1);
			} else if(flag == 6) { //轮播图管理权限
				admin.setPhotoFlag(1);
			}
		}
		adminService.addNewAdmin(admin);
		return SUCCESS;
	}
	
	//修改管理员：跳转到修改界面
	@Action(value="jumpToUpdateAdmin",results={@Result(name="success",location="/jsp/admin/updateAdmin.jsp")})
	public String jumpToUpdateAdmin() {
		String  adminId = ServletActionContext.getRequest().getParameter("adminId");
		Admin admin = adminService.findAdminById(adminId);
		ServletActionContext.getRequest().setAttribute("updateAdmin", admin);
		return SUCCESS;
	}
	
	//修改管理员内容
	@Action(value="updateAdmin",results={@Result(name="success",location="findAllAdmin",type="redirectAction")})
	public String updateAdmin() {
		String adminId = ServletActionContext.getRequest().getParameter("adminId");
		Admin admin = adminService.findAdminById(adminId);
		String adminPassword = ServletActionContext.getRequest().getParameter("adminPassword");
		String adminRealName = ServletActionContext.getRequest().getParameter("adminRealName");
		String adminTelePhone = ServletActionContext.getRequest().getParameter("adminTelePhone");
		admin.setAdminPassword(adminPassword);
		admin.setAdminRealName(adminRealName);
		admin.setAdminTelePhone(adminTelePhone);
		String[] adminPermission = ServletActionContext.getRequest().getParameterValues("permission");
		//将原来的权限置为空
		admin.setUserFlag(null);
		admin.setClubFlag(null);
		admin.setArticleFlag(null);
		admin.setNewsFlag(null);
		admin.setPhotoFlag(null);
		admin.setAdminerFlag(null);
		for(int i = 0;i < adminPermission.length;++i) {
			String permission = adminPermission[i];
			System.out.println(permission);
			Integer flag = Integer.parseInt(permission);
			if(flag == 1) { //用户管理权限
				admin.setUserFlag(1); 
			} else if(flag == 2) { //俱乐部管理权限
				admin.setClubFlag(1);
			} else if(flag == 3) { //管理员管理权限
				admin.setAdminerFlag(1);
			} else if(flag == 4) { //文章管理权限
				admin.setArticleFlag(1);
			} else if(flag == 5) { //新闻管理权限
				admin.setNewsFlag(1);
			} else if(flag == 6) { //轮播图管理权限
				admin.setPhotoFlag(1);
			}
		}
		adminService.updateAdmin(admin);
		return SUCCESS;
	}
	
	//查找所有的管理员
	@Action(value="findAllAdmin",results={@Result(name="success",location="/jsp/admin/findAllAdmin.jsp")})
	public String findAllAdmin() {
		List<Admin> adminList = adminService.findAllAdmin();
		Admin existAdmin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("existAdmin");
		if(!(existAdmin.getAdminId().equals("38492756374285983724172381223486723864"))) {
			//注意要用equals方法,不能用"=="
			for (Admin admin : adminList) {
				//System.out.println(admin.getAdminName());
				if(admin.getAdminId().equals("38492756374285983724172381223486723864")) {
					//System.out.println(admin.getAdminId());
					adminList.remove(admin);
					break;//删除完后必须马上跳出循环，否则继续遍历会出错
					
				}
			}
		}
		ServletActionContext.getRequest().setAttribute("adminList", adminList);
		return SUCCESS;
		
	}
	
	//删除管理员
	@Action(value="deleteAdmin",results={@Result(name="success",location="findAllAdmin",type="redirectAction")})
	public String deleteAdmin() {
		String adminId = ServletActionContext.getRequest().getParameter("adminId");
		adminService.deleteAdmin(adminId);
		return SUCCESS;
		
	}
	
	//管理员登录
	@Action(value="adminLogin",results={@Result(name="success",location="/jsp/admin/adminHomePage.jsp"),
			@Result(name="fail",location="/jsp/userLogin.jsp")})
	public String adminLogin() {
		String username = ServletActionContext.getRequest().getParameter("username");
		String password = ServletActionContext.getRequest().getParameter("password");
		Admin loginAdmin = new Admin();
		loginAdmin.setAdminName(username);
		loginAdmin.setAdminPassword(password);
		Admin existAdmin = adminService.adminLogin(loginAdmin);
		if(existAdmin != null) {
			ServletActionContext.getRequest().getSession().setAttribute("existAdmin", existAdmin);
			return SUCCESS;
		} else {
			ServletActionContext.getRequest().setAttribute("msg", "用户名或者密码错误");
			return "fail";
		}
	}
	
	//寻找球员
	@Action(value="findAllPlayerByAdmin",results={@Result(name="success",location="/jsp/admin/findAllPlayer.jsp")})
	public String findAllPlayerByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Player> pageBean = adminService.findAllPlayer(currPage);
		//给球员设置俱乐部名称
		List<Player> list = pageBean.getList();
		for (Player player : list) {
			String clubId = player.getpClubId();
			if(clubId != null) {
				Club club = clubService.findClubNameByClubId(clubId);
				player.setpClubName(club.getClubName());
				
			}
		}
		pageBean.setList(list);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
		
	}
	
	//寻找教练
	@Action(value="findAllCoachByAdmin",results={@Result(name="success",location="/jsp/admin/findAllCoach.jsp")})
	public String findAllCoachByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Coach> pageBean = adminService.findAllCoach(currPage);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
		
	}
	
	//删除球员
	@Action(value="deletePlayerByAdmin",results={@Result(name="success",location="findAllPlayerByAdmin?currPage=1",type="redirectAction")})
	public String deletePlayerByAdmin() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		adminService.deletePlayerByAdmin(pid);
		return SUCCESS;
	}
	
	//删除教练
	@Action(value="deleteCoachByAdmin",results={@Result(name="success",location="findAllCoachByAdmin?currPage=1",type="redirectAction")})
	public String deleteCoachByAdmin() {
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		adminService.deleteCoachByAdmin(coachId);
		return SUCCESS;
	}
	
	
	//查找未审核俱乐部
	@Action(value="findUnCheckClubByAdmin",results={@Result(name="success",location="/jsp/admin/findUnCheckClub.jsp")})
	public String findUnCheckClubByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Club> pageBean = adminService.findClubByAdmin(0,currPage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}
	
	//查找已审核俱乐部
	@Action(value="findCheckClubByAdmin",results={@Result(name="success",location="/jsp/admin/findClub.jsp")})
	public String findCheckClubByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Club> pageBean = adminService.findClubByAdmin(1,currPage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		ServletActionContext.getRequest().setAttribute("clubList", pageBean.getList());
		return SUCCESS;
	}
	
	//审核俱乐部
	@Action(value="checkClub",results={@Result(name="success",location="findUnCheckClubByAdmin?currPage=1",type="redirectAction")})
	public String checkClub() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		Club club = clubService.findClubNameByClubId(cid);
		club.setClubCode(1);
		clubService.updateClub(club);
		return SUCCESS;
	}
	
	//审核未通过,直接删除
	@Action(value="refuseClub",results={@Result(name="success",location="findUnCheckClubByAdmin?currPage=1",type="redirectAction")})
	public String refuseClub() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		adminService.deleteClubByAdmin(cid);
		return SUCCESS;
	}
	
	//删除俱乐部
	@Action(value="deleteClub",results={@Result(name="success",location="findCheckClubByAdmin?currPage=1",type="redirectAction")})
	public String deleteClub() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		adminService.deleteClubByAdmin(cid);
		return SUCCESS;
	}
	
	//寻找游客
	@Action(value="findTouristByAdmin",results={@Result(name="success",location="/jsp/admin/findTourist.jsp")})
	public String findTouristByAdmin() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Tourist> pageBean = adminService.findTouristByAdmin(currPage);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}

	//删除游客
	@Action(value="deleteTouristByAdmin",results={@Result(name="success",location="findTouristByAdmin?currPage=1",type="redirectAction")})
	public String deleteTouristByAdmin() {
		String tId = ServletActionContext.getRequest().getParameter("tId");
		adminService.deleteTourist(tId);
		return SUCCESS;
	}
	
	//俱乐部上首页
	@Action(value="setMainPage")
	public void setMainPage() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		Club club = clubService.findClubNameByClubId(cid);
		club.setClubMainPage(1);
		clubService.updateClub(club);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//取消俱乐部首页
	@Action(value="downMainPage")
	public void downMainPage() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		Club club = clubService.findClubNameByClubId(cid);
		club.setClubMainPage(null);
		clubService.updateClub(club);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//球员上首页
	@Action(value="setPlayerMainPage")
	public void setPlayerMainPage() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Player player = playerService.findPlayerById(pid);
		player.setPlayerMainPage(1);
		playerService.updatePlayer(player);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//取消球员首页
	@Action(value="downPlayerMainPage")
	public void downPlayerMainPage() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Player player = playerService.findPlayerById(pid);
		player.setPlayerMainPage(null);
		playerService.updatePlayer(player);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//教练上首页
	@Action(value="setCoachMainPage")
	public void setCoachMainPage() {
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		Coach coach = coachService.findCoachById(coachId);
		coach.setCoachMainPage(1);
		coachService.updateCoach(coach);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//取消教练首页
	@Action(value="downCoachMainPage")
	public void downCoachMainPage() {
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		Coach coach = coachService.findCoachById(coachId);
		coach.setCoachMainPage(null);
		coachService.updateCoach(coach);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}




















