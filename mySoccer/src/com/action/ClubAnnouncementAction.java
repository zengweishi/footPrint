package com.action;

import java.io.IOException;
import java.util.Date;
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



import sun.org.mozilla.javascript.internal.regexp.SubString;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.domain.Announcement;
import com.domain.Club;
import com.domain.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IClubService;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ClubAnnouncementAction extends ActionSupport {
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	//发布新公告
	//	@Action(value="delCustomer",results={@Result(name="success",location="findAllCustomer",type="redirectAction")})
	@Action(value="newAnnouncement",results={@Result(name="success",location="findAllAnnouncementByClub?currPage=1",type="redirectAction")})
	public String newAnnouncement() {
		Announcement announcement = new Announcement();
		String clubAnnouncement = ServletActionContext.getRequest().getParameter("aContent");
		announcement.setaContent(clubAnnouncement);
		announcement.setAnnouncementClub((Club)ServletActionContext.getRequest().getSession().getAttribute("existClub"));
		announcement.setaTime(new Date());
		clubService.newAnnouncement(announcement);
		return SUCCESS;
	}
	
	//查看所有公告
	@Action(value="findAllAnnouncementByClub",results={@Result(name="success",location="/jsp/club/announcement.jsp")})
	public String findAllAnnouncementByClub() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Announcement> pageBean = clubService.findAllAnnouncement(currPage,(Club)ServletActionContext.getRequest().getSession().getAttribute("existClub"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
		
	}
	
	//查看公告具体内容
	@Action("findAnnouncementContent")
	public void findAnnouncementContent() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		String aId = ServletActionContext.getRequest().getParameter("aId");
		Announcement announcement = clubService.findAnnouncementContent(aId);
		String aContent = announcement.getaContent()+"(发布于："+announcement.getaTime().toString().substring(0,19)+")";
		String json = JSONArray.toJSONString(aContent);
		System.out.println("json="+json);
		System.out.println("----------------------------------------");
		//将json响应到浏览器
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//删除公告
	@Action(value="deleteAnnouncement",results={@Result(name="success",location="findAllAnnouncementByClub?currPage=1",type="redirectAction")})
	public String deleteAnnouncement() {
		String aId = ServletActionContext.getRequest().getParameter("aId");
		clubService.deleteAnnouncement(aId);
		return SUCCESS;
	}
	
	

}



