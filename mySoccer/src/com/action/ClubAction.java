package com.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
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
import com.domain.Player;
import com.domain.Tourist;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.service.IClubService;
import com.service.ICoachService;
import com.service.IPlayerService;
import com.service.ITouristService;
import com.utils.UUIDUtils;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ClubAction extends ActionSupport  {
	
	@Autowired
	@Qualifier("touristService")
	private ITouristService touristService;
	
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	@Autowired
	@Qualifier("adminService")
	private IAdminService adminService;
	
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	private File clubPhoto; //用于接收上传的文件
	private String clubPhotoFileName; //头像
	private String clubName; //俱乐部全称
	private String clubPassword; //密码
	private String clubEmail; //邮箱
	private String clubPhone; //联系电话
	private String clubCity; //俱乐部城市
	private String clubIntroduction; //简介

	
	public String getClubIntroduction() {
		return clubIntroduction;
	}

	public void setClubIntroduction(String clubIntroduction) {
		this.clubIntroduction = clubIntroduction;
	}

	public IClubService getClubService() {
		return clubService;
	}

	public void setClubService(IClubService clubService) {
		this.clubService = clubService;
	}

	public File getClubPhoto() {
		return clubPhoto;
	}

	public void setClubPhoto(File clubPhoto) {
		this.clubPhoto = clubPhoto;
	}

	public String getClubPhotoFileName() {
		return clubPhotoFileName;
	}

	public void setClubPhotoFileName(String clubPhotoFileName) {
		this.clubPhotoFileName = clubPhotoFileName;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubPassword() {
		return clubPassword;
	}

	public void setClubPassword(String clubPassword) {
		this.clubPassword = clubPassword;
	}

	public String getClubEmail() {
		return clubEmail;
	}

	public void setClubEmail(String clubEmail) {
		this.clubEmail = clubEmail;
	}

	public String getClubPhone() {
		return clubPhone;
	}

	public void setClubPhone(String clubPhone) {
		this.clubPhone = clubPhone;
	}

	public String getClubCity() {
		return clubCity;
	}

	public void setClubCity(String clubCity) {
		this.clubCity = clubCity;
	}

	
	//跳转到注册页面
	@Action(value="clubRegistUI",results={@Result(name="success",location="/jsp/clubRegist.jsp")})
	public String clubRegistUI() {
		return SUCCESS;
	}
	
	//俱乐部注册
	@Action(value="clubRegist",results={@Result(name="success",location="/jsp/userLogin.jsp")})
	public String clubRegist() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		Club club = new Club();
		if(clubPhotoFileName!=null) {
			clubPhotoFileName = UUIDUtils.getUUID()+clubPhotoFileName;
			//处理上传文件
			File destFile = new File(ServletActionContext.getServletContext().getRealPath("/clubPhoto"),clubPhotoFileName);
			try {
				FileUtils.copyFile(clubPhoto, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String clubPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/clubPhoto/"+clubPhotoFileName;
			club.setClubPhoto(clubPhotosrc);
			
		}
		club.setClubName(clubName);
		club.setClubPassword(clubPassword);
		club.setClubPhone(clubPhone);
		club.setClubEmail(clubEmail);
		club.setClubCity(clubCity);
		club.setCid(UUIDUtils.getUUID());
		club.setClubCode(0);//1为审核通过
		club.setClubCoachFlag(0);//0为没有教练
		clubService.clubRegist(club);
		return SUCCESS;
		
	}
	
	//俱乐部登录
//	@Action(value="delCustomer",results={@Result(name="success",location="findAllCustomer",type="redirectAction")})
	@Action(value="clubLogin",results={@Result(name="success",location="/jsp/clubHomePage.jsp"),@Result(name="fail",location="/jsp/userLogin.jsp")})
	public String clubLogin() {
		Club club = new Club();
		//System.out.println(clubName+clubPassword);
		String clubName = ServletActionContext.getRequest().getParameter("username");
		String clubPassword = ServletActionContext.getRequest().getParameter("password");
		club.setClubName(clubName);
		club.setClubPassword(clubPassword);
		Club existClub = clubService.clubLogin(club);
		if(existClub == null) {
			ServletActionContext.getRequest().setAttribute("msg", "用户名或者密码错误");
			//System.out.println("登录失败---------------------------");
			return "fail";
		}
		else {
			ServletActionContext.getRequest().getSession().setAttribute("existClub", existClub);
			//System.out.println("登录成功---------------------------");
			return SUCCESS;
		}
	}
	
	//俱乐部退出登录
	@Action(value="clubLogOut",results={@Result(name="success",location="/jsp/userLogin.jsp")})
	public String clubLogOut() {
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
	}
	
	
	//跳转到修改用户信息界面
	@Action(value="JumpToUpdateClubInformation",results={@Result(name="success",location="/jsp/club/clubInformation.jsp")})
	public String JumpToUpdateClubInformation() {
		Club existClub = (Club) ServletActionContext.getRequest().getSession().getAttribute("existClub");
		//获取俱乐部
		Club club = clubService.findClubNameByClubId(existClub.getCid());
		ServletActionContext.getRequest().setAttribute("updateClub",club);
		return SUCCESS;
	}

	//修改用户信息
	@Action(value="updateClubInformation",results={@Result(name="success",location="/jsp/success.jsp")})
	public String updateClubInformation() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		Club club = (Club) ServletActionContext.getRequest().getSession().getAttribute("existClub");
		if(clubPhotoFileName != null) { //只有当有在表单中获取到照片时才处理，否则为空时会出错！
			clubPhotoFileName = UUIDUtils.getUUID()+clubPhotoFileName;
			//处理上传文件
			File destFile = new File(ServletActionContext.getServletContext().getRealPath("/clubPhoto"),clubPhotoFileName);
			try {
				FileUtils.copyFile(clubPhoto, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String clubPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/clubPhoto/"+clubPhotoFileName;
			club.setClubPhoto(clubPhotosrc);	
	
		}
		//System.out.println(clubName+clubPassword+clubCity+"----------------------------");
		club.setClubName(clubName);
		club.setClubPassword(clubPassword);
		club.setClubPhone(clubPhone);
		club.setClubEmail(clubEmail);
		club.setClubCity(clubCity);
		club.setClubIntroduction(clubIntroduction);

		
		clubService.updateClub(club);
		ServletActionContext.getRequest().setAttribute("msg", "恭喜您修改信息成功！");
		return SUCCESS;
	}
	
	
	//用户退出登录
	@Action(value="userLogOut",results={@Result(name="success",location="/jsp/userLogin.jsp")})
	public String userLogOut() {
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
	}
	
	//异步校验用户名
	@Action(value="checkUserName")
	public void checkUserName() {
		String clubName = ServletActionContext.getRequest().getParameter("clubName");
		String coachNumber = ServletActionContext.getRequest().getParameter("coachNumber");
		String playerNumber = ServletActionContext.getRequest().getParameter("playerNumber");
		String touristEmail = ServletActionContext.getRequest().getParameter("touristEmail");
		String adminName = ServletActionContext.getRequest().getParameter("adminName");
		try {
			if(clubName != null) {
				Club club = clubService.findClubByName(clubName);
				if(club != null) {
					ServletActionContext.getResponse().getWriter().print(0);
				} else {
					ServletActionContext.getResponse().getWriter().print(1);
				}
			} else if(coachNumber != null) {
				Coach coach = coachService.findCoachByNumber(coachNumber);
				if(coach != null) {
					ServletActionContext.getResponse().getWriter().print(0);
				} else {
					ServletActionContext.getResponse().getWriter().print(1);
				}
			} else if(playerNumber != null) {
				Player player = playerService.findPlayerByNumber(playerNumber);
				if(player != null) {
					ServletActionContext.getResponse().getWriter().print(0);
				} else {
					ServletActionContext.getResponse().getWriter().print(1);
				}
			} else if(touristEmail != null) {
				Tourist tourist = touristService.findTouristByNumber(touristEmail);
				if(tourist != null) {
					ServletActionContext.getResponse().getWriter().print(0);
				} else {
					ServletActionContext.getResponse().getWriter().print(1);
				}
			} else if(adminName != null){
				Admin admin = adminService.findAdminByName(adminName);
				if(admin != null) {
					ServletActionContext.getResponse().getWriter().print(0);
				} else {
					ServletActionContext.getResponse().getWriter().print(1);
				}
				
			} else {
				ServletActionContext.getResponse().getWriter().print(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
	
	
}




















