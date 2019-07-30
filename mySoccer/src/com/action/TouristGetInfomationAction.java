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
import com.domain.TNews;
import com.domain.Tourist;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ITouristService;
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class TouristGetInfomationAction extends ActionSupport {
	@Autowired
	@Qualifier("touristService")
	private ITouristService touristService;
	
	//获取信息显示到主页
	@Action(value="getTouristInformation",results={@Result(name="success",location="/jsp/tourist/touristFirstPage.jsp")})
	public String getTouristInformation() {
		String tId = ServletActionContext.getRequest().getParameter("tId");
		if(tId.length() == 1) {
			//如果没获取到tId，说明球员是查看自己的主页(查看自己主页时设置传过来的pid为0)
			Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
			tId = tourist.gettId();
		}
		Tourist infoTourist = touristService.findTourist(tId);
		ServletActionContext.getRequest().setAttribute("infoTourist", infoTourist);
		String pageFlag = ServletActionContext.getRequest().getParameter("pageFlag");
		if(pageFlag != null) {
			ServletActionContext.getRequest().setAttribute("pageFlag", pageFlag);
		}
		return SUCCESS;
		
	}

	//显示游客用户文章
	@Action(value="findTNewsForInfo")
	public void findTNewsForInfo() {
		String tId = ServletActionContext.getRequest().getParameter("tId");
		if(tId.length() == 1) {
			//如果没获取到tId，说明球员是查看自己的主页(查看自己主页时设置传过来的pid为0)
			Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
			tId = tourist.gettId();
		}
		Tourist tourist = touristService.findTourist(tId);
		List<TNews> infoTnewsList =  touristService.findTNewsForInfo(tourist);
		PropertyFilter filter = new PropertyFilter() {

			@Override
			public boolean apply(Object arg0, String filedName, Object arg2) {
				if("newsTourist".equalsIgnoreCase(filedName)) {
					return false; //存在一对多，多对一时要把实体信息过滤掉，这里是newsTourist过滤掉。
				}
				if("comments".equalsIgnoreCase(filedName)) {
					return false;
				}
				return true;
			}
			
		};
		String json = JSONArray.toJSONString(infoTnewsList,filter,SerializerFeature.DisableCircularReferenceDetect);
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














