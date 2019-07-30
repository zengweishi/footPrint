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
import com.domain.Coach;
import com.domain.Share;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ICoachService;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CoachGetInformationAction extends ActionSupport {
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	//获取主页信息
	@Action(value="getCoachInformation",results={@Result(name="success",location="/jsp/coach/coachFirstPage.jsp")})
	public String getCoachInformation() {
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		if(coachId.length() == 1) {
			//如果没获取到coachId，说明球员是查看自己的主页(查看自己主页时设置传过来的pid为0)
			Coach coach = (Coach)ServletActionContext.getRequest().getSession().getAttribute("existCoach");
			coachId = coach.getCoachId();
		}
		Coach infoCoach = coachService.findCoachById(coachId);
		ServletActionContext.getRequest().setAttribute("infoCoach", infoCoach);
		String pageFlag = ServletActionContext.getRequest().getParameter("pageFlag");
		if(pageFlag != null) {
			ServletActionContext.getRequest().setAttribute("pageFlag", pageFlag);
		}
		return SUCCESS;
	}
	
	//获取教练分享
	@Action(value="findShareForInfo")
	public void findShareForInfo() {
		String coachId = ServletActionContext.getRequest().getParameter("coachId");
		if(coachId.length() == 1) {
			Coach coach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existClub");
			coachId = coach.getCoachId();
		}
		Coach infoCoach = coachService.findCoachById(coachId);
		List<Share> infoShareList = coachService.findShareForInfo(infoCoach);
		/*for (Share share : infoShareList) {
			System.out.println("-------------"+share);
		}*/
		PropertyFilter filter = new PropertyFilter() {

			@Override
			public boolean apply(Object arg0, String filedName, Object arg2) {
				if("shareCoach".equalsIgnoreCase(filedName)) {
					return false; //存在一对多，多对一时要把实体信息过滤掉，这里是shareCoach过滤掉。
				}
				return true;
			}
			
		};
		String json = JSONArray.toJSONString(infoShareList,filter,SerializerFeature.DisableCircularReferenceDetect);
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






















