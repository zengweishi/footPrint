package com.action;

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

import com.domain.AdPhoto;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Player;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.service.IClubService;
import com.service.ICoachService;
import com.service.IPlayerService;
/**
 * Ê×Ò³µÄAction
 * @author 45¶ÈÕ¨
 *
 */

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class IndexAction extends ActionSupport {
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
	
	@Action(value="index",results={@Result(name="success",location="/jsp/index.jsp")})
	public String index() {
		List<AdPhoto> indexAdPhotoList = adminService.findAllAdPhoto();
		ServletActionContext.getRequest().getSession().setAttribute("indexAdPhotoList", indexAdPhotoList);
		/*Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<Club> pageBean = adminService.findClubByAdmin(1,currPage);
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);*/
		List<Club> mainPageClubList = clubService.findMainPageClub();
		ServletActionContext.getRequest().setAttribute("mainPageClubList", mainPageClubList);
		
		List<Coach> mainPageCoachList = coachService.findMainPageCoach();
		ServletActionContext.getRequest().setAttribute("mainPageCoachList", mainPageCoachList);

		List<Player> mainPagePlayerList = playerService.findMainPagePlayer();
		ServletActionContext.getRequest().setAttribute("mainPagePlayerList", mainPagePlayerList);

		
		return SUCCESS;
	}

}
