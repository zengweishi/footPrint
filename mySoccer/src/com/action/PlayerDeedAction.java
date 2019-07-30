package com.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.domain.Player;
import com.domain.PlayerDeeds;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.IPlayerService;

/**
 * 球员时间轴
 * @author 45度炸
 *
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class PlayerDeedAction extends ActionSupport implements ModelDriven<PlayerDeeds> {
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	private PlayerDeeds playerDeeds = new PlayerDeeds();
	@Override
	public PlayerDeeds getModel() {
		// TODO Auto-generated method stub
		return playerDeeds;
	}
	
	//创建新的时间轴事迹
	@Action(value="createPlayerDeeds",results={@Result(name="success",location="findAllPlayerDeeds",type="redirectAction")})
	public String createPlayerDeeds() {
		String playerDeedsTime = ServletActionContext.getRequest().getParameter("playerDeedsTime");
		playerDeeds.setPlayerDeedsDate(new Date());
		playerDeeds.setDeedsPlayer((Player)ServletActionContext.getRequest().getSession().getAttribute("existPlayer"));
        
		//将标题时间设置为Date格式以便进行排序
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date playerDeedsTitleTime = null;
        try {
			playerDeedsTitleTime = sdf.parse(playerDeedsTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playerDeeds.setPlayerDeedsTitleTime(playerDeedsTitleTime);
		playerService.createPlayerDeeds(playerDeeds);
		return SUCCESS;
	}
	
	//显示所有的时间轴事迹
	@Action(value="findAllPlayerDeeds",results={@Result(name="success",location="/jsp/player/playerDeeds.jsp")})
	public String findAllPlayerDeeds() {
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		List<PlayerDeeds> playerDeedsList = playerService.findAllPlayerDeedsByPlayer(player);
		ServletActionContext.getRequest().getSession().setAttribute("playerDeedsList", playerDeedsList);
		return SUCCESS;
	}
	
	//删除时间轴事迹
	@Action(value="deletePlayerDeedsById",results={@Result(name="success",location="findAllPlayerDeeds",type="redirectAction")})
	public String deletePlayerDeedsById() {
		String playerDeedsId = ServletActionContext.getRequest().getParameter("playerDeedsId");
		playerService.deletePlayerDeedsById(playerDeedsId);
		return SUCCESS;
		
	}

}











