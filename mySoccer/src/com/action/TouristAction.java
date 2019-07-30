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

import com.domain.Club;
import com.domain.Tourist;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ICoachService;
import com.service.ITouristService;
import com.utils.UUIDUtils;
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class TouristAction extends ActionSupport{
	@Autowired
	@Qualifier("touristService")
	private ITouristService touristService;
	
	//命名要用全称，加上tourist,否则接收不到数据
	private File touristPhoto; //用于接收上传的文件
	private String touristPhotoFileName; //头像
	private String touristPassword; //密码
	private String touristEmail; //邮箱
	private String touristAddress; //籍贯
	private String touristGender; //性别
	private String touristIntroduction;//个人简介
	private String touristName; //昵称
	public ITouristService getTouristService() {
		return touristService;
	}
	
	


	public String getTouristIntroduction() {
		return touristIntroduction;
	}




	public void setTouristIntroduction(String touristIntroduction) {
		this.touristIntroduction = touristIntroduction;
	}




	public String getTouristName() {
		return touristName;
	}




	public void setTouristName(String touristName) {
		this.touristName = touristName;
	}




	public File getTouristPhoto() {
		return touristPhoto;
	}


	public void setTouristPhoto(File touristPhoto) {
		this.touristPhoto = touristPhoto;
	}


	public String getTouristPhotoFileName() {
		return touristPhotoFileName;
	}


	public void setTouristPhotoFileName(String touristPhotoFileName) {
		this.touristPhotoFileName = touristPhotoFileName;
	}


	public String getTouristPassword() {
		return touristPassword;
	}


	public void setTouristPassword(String touristPassword) {
		this.touristPassword = touristPassword;
	}


	public String getTouristEmail() {
		return touristEmail;
	}


	public void setTouristEmail(String touristEmail) {
		this.touristEmail = touristEmail;
	}


	public String getTouristAddress() {
		return touristAddress;
	}


	public void setTouristAddress(String touristAddress) {
		this.touristAddress = touristAddress;
	}


	public String getTouristGender() {
		return touristGender;
	}


	public void setTouristGender(String touristGender) {
		this.touristGender = touristGender;
	}


	public void setTouristService(ITouristService touristService) {
		this.touristService = touristService;
	}


	//跳转到注册页面
	@Action(value="touristRegistUI",results={@Result(name="success",location="/jsp/tourist/touristRegist.jsp")})
	public String touristRegistUI() {
		return SUCCESS;
	}
	
	//俱乐部注册
		@Action(value="touristRegist",results={@Result(name="success",location="/jsp/userLogin.jsp")})
		public String touristRegist() {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			Tourist tourist = new Tourist();
			if(touristPhotoFileName!=null) {
				touristPhotoFileName = UUIDUtils.getUUID()+touristPhotoFileName;
				//处理上传文件
				File destFile = new File(ServletActionContext.getServletContext().getRealPath("/touristPhoto"),touristPhotoFileName);
				try {
					FileUtils.copyFile(touristPhoto, destFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String tPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/touristPhoto/"+touristPhotoFileName;
				tourist.settPhoto(tPhotosrc);
				
			}
			//System.out.println(touristEmail+touristPassword+touristGender);
			//System.out.println("------------------------------");
			tourist.settEmail(touristEmail);
			tourist.settPassword(touristPassword);
			tourist.settAddress(touristAddress);
			tourist.settGender(touristGender);
			touristService.touristRegist(tourist);
			ServletActionContext.getRequest().setAttribute("msg", "注册成功！");
			return SUCCESS;
			
		}
	
		//游客登录
		@Action(value="touristLogin",results={@Result(name="success",location="/jsp/tourist/touristHomePage.jsp"),
				@Result(name="fail",location="/jsp/userLogin.jsp")})
		public String touristLogin() {
			String username = ServletActionContext.getRequest().getParameter("username");
			String password = ServletActionContext.getRequest().getParameter("password");
			Tourist tourist = new Tourist();
			tourist.settEmail(username);
			tourist.settPassword(password);
			Tourist existTourist = touristService.touristLogin(tourist);
			if(existTourist != null) {
				ServletActionContext.getRequest().getSession().setAttribute("existTourist", existTourist);
				return SUCCESS;
			} else {
				ServletActionContext.getRequest().setAttribute("msg", "账号或密码错误");
				return "fail";
			}
			
		}
		
		//跳转到信息修改界面
		@Action(value="updateInformationUI",results={@Result(name="success",location="/jsp/tourist/touristInformation.jsp")})
		public String updateInformationUI() {
			Tourist existTourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
			Tourist tourist = touristService.findTourist(existTourist.gettId());
			ServletActionContext.getRequest().setAttribute("updateTourist", tourist);
			return SUCCESS;
		}
		
		//修改信息
		@Action(value="updateTouristInformation",results={@Result(name="success",location="/jsp/success.jsp")})
		public String updateTouristInformation() {
			Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
			if(touristPhotoFileName!=null) {
				touristPhotoFileName = UUIDUtils.getUUID()+touristPhotoFileName;
				//处理上传文件
				File destFile = new File(ServletActionContext.getServletContext().getRealPath("/touristPhoto"),touristPhotoFileName);
				try {
					FileUtils.copyFile(touristPhoto, destFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String tPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/touristPhoto/"+touristPhotoFileName;
				tourist.settPhoto(tPhotosrc);
				
			}
			tourist.settEmail(touristEmail);
			tourist.settPassword(touristPassword);
			tourist.settAddress(touristAddress);
			tourist.settGender(touristGender);
			tourist.settIntroduction(touristIntroduction);
			tourist.settName(touristName);
			
			touristService.updateTourist(tourist);
			
			ServletActionContext.getRequest().setAttribute("msg", "恭喜您修改信息成功！");
			return SUCCESS;
		}
	
}













