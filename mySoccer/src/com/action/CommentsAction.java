package com.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.domain.Club;
import com.domain.Coach;
import com.domain.Comments;
import com.domain.Player;
import com.domain.Tourist;
import com.google.gson.JsonArray;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.service.IClubService;
import com.service.ICoachService;
import com.service.IPlayerService;
import com.service.ITouristService;
import com.utils.AuthServiceCom;
import com.utils.CommentsBean;
import com.utils.CommentsBean.BackResult;
import com.utils.GsonUtils;
import com.utils.HttpUtil;

/**
 * 评论的Action
 * @author 45度炸
 *
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CommentsAction extends ActionSupport {
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
	
	//添加评论
	@Action(value="addComments")
	public void addComments() {
		try {
		String contents = ServletActionContext.getRequest().getParameter("contents");
		String cnewsId = ServletActionContext.getRequest().getParameter("cnewsId");
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		String tnewsId = ServletActionContext.getRequest().getParameter("tnewsId");
		String anewsId = ServletActionContext.getRequest().getParameter("anewsId");
		Club club = (Club) ServletActionContext.getRequest().getSession().getAttribute("existClub");
		Coach coach = (Coach) ServletActionContext.getRequest().getSession().getAttribute("existCoach");
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
		Comments comments = new Comments();
		comments.setContents(contents);
		//和谐相关的评论
		String otherHost = "https://aip.baidubce.com/rest/2.0/antispam/v2/spam";
		String accessToken = AuthServiceCom.getAuth();
	    String param = "content="+URLEncoder.encode(contents,"UTF-8")+"&command_no=500071";
		String object = HttpUtil.post(otherHost, accessToken, param);
		CommentsBean fromJson = GsonUtils.fromJson(object, CommentsBean.class);
			if((fromJson.getResult().getSpam() == 1)||(fromJson.getResult().getSpam() == 2)) {
				ServletActionContext.getResponse().getWriter().write("1");		
			} else {
				//设置评论用户和头像
				if(club != null) {
					comments.setClubId(club.getCid());
					comments.setUserId(club.getCid());
					comments.setUserName(club.getClubName());
					comments.setUserPhoto(club.getClubPhoto());
				} else if(coach != null) {
					comments.setCoachId(coach.getCoachId());
					comments.setUserId(coach.getCoachId());
					comments.setUserName(coach.getCoachName());
					comments.setUserPhoto(coach.getCoachPhoto());
				} else if(player != null) {
					comments.setPlayerId(player.getPid());
					comments.setUserId(player.getPid());
					comments.setUserName(player.getpName());
					comments.setUserPhoto(player.getpPhoto());
				} else {
					comments.setTouristId(tourist.gettId());
					comments.setUserId(tourist.gettId());
					comments.setUserName(tourist.gettName());
					comments.setUserPhoto(tourist.gettPhoto());
				}
				
				//设置文章ID
				if(cnewsId != null) {
					comments.setCnewsId(cnewsId);
				
				} else if(shareId != null) {
					comments.setShareId(shareId);
					
				} else if(diaryId != null) {
					comments.setDiaryId(diaryId);
					
				} else if(tnewsId != null) {
					comments.setTnewsId(tnewsId);
					
				} else {
					comments.setAnewsId(anewsId);
					
				}
				
				comments.setCommentsTime(new Date());
				adminService.addComments(comments);
			}
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//查找评论
	@Action(value="findCommentsByArticleId")
	public void findCommentsByArticleId() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		String cnewsId = ServletActionContext.getRequest().getParameter("cnewsId");
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		String tnewsId = ServletActionContext.getRequest().getParameter("tnewsId");
		String anewsId = ServletActionContext.getRequest().getParameter("anewsId");
		List<Comments> list = new ArrayList<Comments>();
		if(cnewsId != null) {
			list = adminService.findCommentsByArticleId(1,cnewsId);
		} else if(shareId != null) {
			list = adminService.findCommentsByArticleId(2,shareId);

		} else if(diaryId != null) {
			list = adminService.findCommentsByArticleId(3,diaryId);

		} else if(tnewsId != null) {
			list = adminService.findCommentsByArticleId(4,tnewsId);

		} else {
			list = adminService.findCommentsByArticleId(5,anewsId);

		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONArray.toJSONString(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//删除评论
	@Action(value="deleteCommentsById")
	public void deleteCommentsById() {
		String commentsId = ServletActionContext.getRequest().getParameter("commentsId");
		adminService.deleteCommentsById(commentsId);
	}
}













