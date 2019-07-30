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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.domain.Announcement;
import com.domain.CNews;
import com.domain.Club;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IClubService;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ClubGetInformationAction extends ActionSupport {
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	//获取主页信息
	@Action(value="getClubInformation",results={@Result(name="success",location="/jsp/club/clubFirstPage.jsp")})
	public String getClubInformation() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		if(cid.length() == 1) {
			//如果没获取到cid，说明是查看自己的主页(查看自己主页时设置传过来的cid为0)
			Club club = (Club)ServletActionContext.getRequest().getSession().getAttribute("existClub");
			cid = club.getCid();
		}
		Club infoClub = clubService.findClubNameByClubId(cid);
		ServletActionContext.getRequest().setAttribute("infoClub", infoClub);
		String pageFlag = ServletActionContext.getRequest().getParameter("pageFlag");
		if(pageFlag != null) {
			ServletActionContext.getRequest().setAttribute("pageFlag", pageFlag);
		}
		return SUCCESS;
	}
	
	//获取俱乐部公告
	@Action("getClubAnnouncement")
	public void getClubAnnouncement() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		if(cid.length() == 1) {
			//如果没获取到cid，说明球员是查看自己的主页(查看自己主页时设置传过来的pid为0)
			Club club = (Club)ServletActionContext.getRequest().getSession().getAttribute("existClub");
			cid = club.getCid();
		}
		Club infoClub = clubService.findClubNameByClubId(cid);
		List<Announcement> infoAnnouncementList = clubService.findAnnouncementForInfo(infoClub);
		PropertyFilter filter = new PropertyFilter() {

			@Override
			public boolean apply(Object arg0, String filedName, Object arg2) {
				if("announcementClub".equalsIgnoreCase(filedName)) {
					return false; //存在一对多，多对一时要把实体信息过滤掉，这里是shareCoach过滤掉。
				}
				return true;
			}
			
		};
		String json = JSONArray.toJSONString(infoAnnouncementList,filter,SerializerFeature.DisableCircularReferenceDetect);
		try {
			//将数据响应回去
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");//改编码
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获得俱乐部动态
	@Action(value="getClubNewsForInfo")
	public void getClubNewsForInfo() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		if(cid.length() == 1) {
			//如果没获取到cid，说明球员是查看自己的主页(查看自己主页时设置传过来的pid为0)
			Club club = (Club)ServletActionContext.getRequest().getSession().getAttribute("existClub");
			cid = club.getCid();
		}
		Club infoClub = clubService.findClubNameByClubId(cid);
		List<CNews> infoCNewsList = clubService.findCNewsForInfo(infoClub); 
		PropertyFilter filter = new PropertyFilter() {

			@Override
			public boolean apply(Object arg0, String filedName, Object arg2) {
				if("newsClub".equalsIgnoreCase(filedName)) {
					return false; //存在一对多，多对一时要把实体信息过滤掉，这里是shareCoach过滤掉。
				}
				return true;
			}
			
		};
		String json = JSONArray.toJSONString(infoCNewsList,filter,SerializerFeature.DisableCircularReferenceDetect);
		try {
			//将数据响应回去
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");//改编码
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}















