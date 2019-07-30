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
import com.domain.Coach;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.ICoachService;
import com.utils.UUIDUtils;

/**
 * 
 * @author 45度炸
 *
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CoachAction extends ActionSupport {
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	private File coachPhoto; //用于接收上传的文件
	private String coachPhotoFileName; //头像
	private String coachNumber; //账号
	private String coachName; //昵称
	private String coachPassword;//密码
	private String coachIntroduction; //简介
	private String coachRealName; //真实姓名
	private String coachBirthday; //生日
	private String coachAddress; //籍贯
	private String coachGender; //性别
	private String coachTelPhone;//教练的电话
	public ICoachService getCoachService() {
		return coachService;
	}
	public void setCoachService(ICoachService coachService) {
		this.coachService = coachService;
	}
	public File getCoachPhoto() {
		return coachPhoto;
	}
	public void setCoachPhoto(File coachPhoto) {
		this.coachPhoto = coachPhoto;
	}
	public String getCoachPhotoFileName() {
		return coachPhotoFileName;
	}
	public void setCoachPhotoFileName(String coachPhotoFileName) {
		this.coachPhotoFileName = coachPhotoFileName;
	}
	public String getCoachNumber() {
		return coachNumber;
	}
	public void setCoachNumber(String coachNumber) {
		this.coachNumber = coachNumber;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getCoachPassword() {
		return coachPassword;
	}
	public void setCoachPassword(String coachPassword) {
		this.coachPassword = coachPassword;
	}
	public String getCoachIntroduction() {
		return coachIntroduction;
	}
	public void setCoachIntroduction(String coachIntroduction) {
		this.coachIntroduction = coachIntroduction;
	}
	public String getCoachRealName() {
		return coachRealName;
	}
	public void setCoachRealName(String coachRealName) {
		this.coachRealName = coachRealName;
	}
	public String getCoachBirthday() {
		return coachBirthday;
	}
	public void setCoachBirthday(String coachBirthday) {
		this.coachBirthday = coachBirthday;
	}
	public String getCoachAddress() {
		return coachAddress;
	}
	public void setCoachAddress(String coachAddress) {
		this.coachAddress = coachAddress;
	}
	public String getCoachGender() {
		return coachGender;
	}
	public void setCoachGender(String coachGender) {
		this.coachGender = coachGender;
	}
	public String getCoachTelPhone() {
		return coachTelPhone;
	}
	public void setCoachTelPhone(String coachTelPhone) {
		this.coachTelPhone = coachTelPhone;
	}
	
	//跳转到修改教练信息界面
	@Action(value="JumpToupdateCoachInformation",results={@Result(name="success",location="/jsp/coach/coachInformation.jsp")})
	public String JumpToupdateCoachInformation() {
		Coach existCoach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existCoach");
		Coach coach = coachService.findCoachById(existCoach.getCoachId());
		ServletActionContext.getRequest().setAttribute("updateCoach", coach);
		return SUCCESS;
	}
	
	@Action(value="updateCoachInformation",results={@Result(name="success",location="/jsp/success.jsp")})
	public String updateCoachInformation() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		Coach coach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existCoach");
		if(coachPhotoFileName != null) { //只有当有在表单中获取到照片时才处理，否则为空时会出错！
			coachPhotoFileName = UUIDUtils.getUUID()+coachPhotoFileName;
			//处理上传文件
			File destFile = new File(ServletActionContext.getServletContext().getRealPath("/coachPhoto"),coachPhotoFileName);
			try {
				FileUtils.copyFile(coachPhoto, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String clubPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/coachPhoto/"+coachPhotoFileName;
			coach.setCoachPhoto(clubPhotosrc);	
	
		}
		
		coach.setCoachName(coachName);
		coach.setCoachRealName(coachRealName);
		coach.setCoachPassword(coachPassword);
		coach.setCoachTelPhone(coachTelPhone);
		coach.setCoachAddress(coachAddress);
		coach.setCoachBirthday(coachBirthday);
		coach.setCoachIntroduction(coachIntroduction);
		coach.setCoachGender(coachGender);
		
		coachService.updateCoach(coach);
		
		
		ServletActionContext.getRequest().setAttribute("msg", "恭喜您修改信息成功！");
		return SUCCESS;
	}
	
	
}










