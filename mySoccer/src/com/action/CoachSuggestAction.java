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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.domain.Coach;
import com.domain.Player;
import com.domain.SuggestForCoach;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.ICoachService;
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CoachSuggestAction extends ActionSupport implements ModelDriven<SuggestForCoach> {
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	private SuggestForCoach suggestForCoach = new SuggestForCoach();
	@Override
	public SuggestForCoach getModel() {
		// TODO Auto-generated method stub
		return suggestForCoach;
	}
	
	//球员给教练写建议
	@Action(value="writeNewSuggestForCoach",results={@Result(name="success",location="/jsp/player/playerSuggestForCoach.jsp")})
	public String writeNewSuggestForCoach() {
		suggestForCoach.setSugCoachTime(new Date());
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		suggestForCoach.setSugCoach(player.getpCoach());
		suggestForCoach.setSugCoachFlag(0);//新建的未标记
		coachService.writeNewSuggestForCoach(suggestForCoach);
		
		return SUCCESS;
	}
	
	//教练删除建议
	@Action(value="deleteSuggestForCoach",results={@Result(name="undone",location="showUnMarkSuggest",type="redirectAction"),
			@Result(name="done",location="showMarkSuggest",type="redirectAction")})
	public String deleteSuggestForCoach() {
		String sugCoachId = ServletActionContext.getRequest().getParameter("sugCoachId");
		SuggestForCoach suggest = coachService.findSuggestForCoachById(sugCoachId);
		coachService.deleteSuggestForCoach(sugCoachId);
		if(suggest.getSugCoachFlag() == 0) {
			return "undone";
		} else {
			return "done";
		}
	}
	
	//展示所有未标记的建议
	@Action(value="showUnMarkSuggest",results={@Result(name="success",location="/jsp/coach/coachSuggest.jsp")})
	public String showUnMarkSuggest() {
		Coach coach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existCoach");
		List<SuggestForCoach> list = coachService.findUnMarkSuggestByCoach(coach);
		ServletActionContext.getRequest().setAttribute("unMarkList", list);
	/*	for (SuggestForCoach suggestForCoach : list) {
			System.out.println(suggestForCoach.getSugCoachContent()+"------------------");
		}*/
		return SUCCESS;
	}
	
	//显示已标记的建议
	@Action(value="showMarkSuggest",results={@Result(name="success",location="/jsp/coach/coachSuggestMark.jsp")})
	public String showMarkSuggest() {
		Coach coach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existCoach");
		List<SuggestForCoach> list = coachService.findMarkSuggest(coach);
		ServletActionContext.getRequest().setAttribute("markList", list);
		return SUCCESS;
	}
	
	//显示建议的具体内容
	@Action(value="showSuggestForCoachContent")
	public void showSuggestForCoachContent() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		String sugCoachId = ServletActionContext.getRequest().getParameter("sugCoachId");
		SuggestForCoach suggestForCoach = coachService.findSuggestForCoachById(sugCoachId);
		String sugForCoachContent = suggestForCoach.getSugCoachContent();
		PropertyFilter filter = new PropertyFilter() {

			@Override
			public boolean apply(Object arg0, String filedName, Object arg2) {
				if("sugCoachId".equalsIgnoreCase(filedName)) {
					return false;
				}
				return true;
			}
			
		};
		String json = JSONArray.toJSONString(sugForCoachContent,filter,SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(json);
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return SUCCESS;
		
	}

	//设置标记
	@Action(value="setSugForCoachFlag",results={@Result(name="success",location="showUnMarkSuggest",type="redirectAction")})
	public String setSugForCoachFlag() {
		String sugCoachId = ServletActionContext.getRequest().getParameter("sugCoachId");
		
		System.out.println(sugCoachId);
		SuggestForCoach  suggestForCoach = coachService.findSuggestForCoachById(sugCoachId);
		System.out.println(suggestForCoach.getSugCoachId()+"---------------"+suggestForCoach.getSugCoachContent());
		suggestForCoach.setSugCoachFlag(1);
		coachService.updateSugForCoach(suggestForCoach);
		return SUCCESS;
	}
	
	
}

















