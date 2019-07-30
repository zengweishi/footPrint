package com.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.domain.CNews;
import com.domain.Club;
import com.domain.Coach;
import com.domain.PageBean;
import com.domain.Share;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ICoachService;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CoachShareAction extends ActionSupport {
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	//发布新分享
	@Action(value="writeShare")
	public void writeShare() {
		String shareContents = ServletActionContext.getRequest().getParameter("shareContents");
		String shareTitle = ServletActionContext.getRequest().getParameter("shareTitle");
		Share share = new Share();
		share.setShareCoach((Coach)ServletActionContext.getRequest().getSession().getAttribute("existCoach"));
		share.setShareTitle(shareTitle);
		share.setShareContents(shareContents);
		share.setShareDate(new Date());
		//对文章提示头进行一个处理
		StringBuilder strBui = new StringBuilder();
		String finalHeadContent;
		if(shareContents.length()>150) {
			String headContent = shareContents.replaceAll(" ", "").substring(0, 150)+" ";
			char[] strHead = headContent.toCharArray();
			for(int i=1;i < strHead.length-1;++i) {
				if(strHead[i-1] == '>') {
					while(strHead[i]!='<') {
						strBui.append(strHead[i]);
						++i;
						if(i>=strHead.length-1) {
							break;
						}
					  	
					}
				}
				
			}
			finalHeadContent = strBui.toString();
		} else {
			String headContent = shareContents.replaceAll(" ", "")+"";
			char[] strHead = headContent.toCharArray();
			for(int i = 0;i<strHead.length;++i) {
				if(strHead[i] == '<') {
					while(strHead[i]!='>') {
						strHead[i] = ' ';
						strBui.append(strHead[i]);
						++i;
					}
					strHead[i] = ' ';
				}
				strBui.append(strHead[i]);
			}
			finalHeadContent = strBui.toString().replaceAll(" ", "");
		}
		share.setShareHeadContent(finalHeadContent);
		coachService.wiriteShare(share);
		try {
			ServletActionContext.getResponse().getWriter().write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//显示所有分享
	@Action(value="showAllShare",results={@Result(name="success",location="/jsp/coach/showAllShare.jsp")})
	public String showAllShare() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Share> pageBean = coachService.showAllShare(currPage,(Coach)ServletActionContext.getRequest().getSession().getAttribute("existCoach"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
		
	}
	
	//显示分享内容
	@Action(value="showShareArtical",results={@Result(name="success",location="/jsp/coach/showShareArtical.jsp")})
	public String showShareArtical() {
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		Share share = coachService.showShareArtical(shareId);
		ServletActionContext.getRequest().setAttribute("share", share);
		String pageFlag = ServletActionContext.getRequest().getParameter("pageFlag");
		if(pageFlag != null) {
			ServletActionContext.getRequest().setAttribute("pageFlag", pageFlag);
		}
		return SUCCESS;
	}
	
	//修改分享第一步：跳转到编辑器
	@Action(value="updateJump",results={@Result(name="success",location="/jsp/coach/coachShareupdateEditor.jsp")})
	public String updateJump() {
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		Share share = coachService.showShareArtical(shareId);
		String shareContents = share.getShareContents();
		shareContents = shareContents.replaceAll("\"", "\"+\"");
		//shareContents = shareContents.replaceAll("<img src=\"", "<img src=\"+\"");
		//shareContents = shareContents.replaceAll(".jpg\" style=\"max-width:100%;\">", ".jpg\" +\" style=\"+\"max-width:100%;\"+\">");
		//shareContents = shareContents.replaceAll(".jpg\" style=\"max-width: 100%;\">", ".jpg\" +\" style=\"+\"max-width:100%;\"+\">");
		share.setShareContents(shareContents);
		ServletActionContext.getRequest().setAttribute("share", share);
		ServletActionContext.getRequest().setAttribute("update", "1");
		return SUCCESS;
	}
	

 	//修改并保存内容
	@Action(value="updateShareArtical")
	public void updateShareArtical() {
		Share share = new Share();
		String shareId = ServletActionContext.getRequest().getParameter("id");
		share.setShareId(shareId);
		//找到对应的share,修改文章以及标题，日期以及教练则不用改
		share = coachService.showShareArtical(shareId);
		String shareContents = ServletActionContext.getRequest().getParameter("shareContents");
		String shareTitle = ServletActionContext.getRequest().getParameter("shareTitle");
		share.setShareTitle(shareTitle);
		share.setShareContents(shareContents);
		//对文章提示头进行一个处理
		StringBuilder strBui = new StringBuilder();
		String finalHeadContent;
		if(shareContents.length()>150) {
			String headContent = shareContents.replaceAll(" ", "").substring(0, 150)+" ";
			char[] strHead = headContent.toCharArray();
			for(int i=1;i < strHead.length-1;++i) {
				if(strHead[i-1] == '>') {
					while(strHead[i]!='<') {
						strBui.append(strHead[i]);
						++i;
						if(i>=strHead.length-1) {
							break;
						}
					  	
					}
				}
				
			}
			finalHeadContent = strBui.toString();
		} else {
			String headContent = shareContents.replaceAll(" ", "")+"";
			char[] strHead = headContent.toCharArray();
			for(int i = 0;i<strHead.length;++i) {
				if(strHead[i] == '<') {
					while(strHead[i]!='>') {
						strHead[i] = ' ';
						strBui.append(strHead[i]);
						++i;
					}
					strHead[i] = ' ';
				}
				strBui.append(strHead[i]);
			}
			finalHeadContent = strBui.toString().replaceAll(" ", "");
		}
		share.setShareHeadContent(finalHeadContent);
		coachService.updateShareArtical(share);
		try {
			ServletActionContext.getResponse().getWriter().write("2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//删除分享
	@Action(value="deleteShare",results={@Result(name="success",location="showAllShare?currPage=1",type="redirectAction")})
	public String deleteShare() {
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		coachService.deleteShare(shareId);
		return SUCCESS;
	}
	
	
	

}
