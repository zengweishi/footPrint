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

import com.domain.Player;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IPlayerService;
import com.utils.UUIDUtils;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class PlayerAction extends ActionSupport {
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	private File playerPhoto; //用于接收上传的文件
	private String playerPhotoFileName; //头像
	private String playerNumber; //邮箱账号
	private String playerPassword; //密码
	private String playerName; //昵称
	private String playerIntroduction; //简介
	private String playerRealName; //真实姓名
	private String playerBirthday; //生日
	private String playerGender; //性别
	private String playerAddress; //地址
	private String playerTelePhone; //球员联系电话
	private String playerLocation;//场上位置
	

	public String getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(String playerLocation) {
		this.playerLocation = playerLocation;
	}

	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}

	public File getPlayerPhoto() {
		return playerPhoto;
	}

	public void setPlayerPhoto(File playerPhoto) {
		this.playerPhoto = playerPhoto;
	}

	public String getPlayerPhotoFileName() {
		return playerPhotoFileName;
	}

	public void setPlayerPhotoFileName(String playerPhotoFileName) {
		this.playerPhotoFileName = playerPhotoFileName;
	}

	public String getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(String playerNumber) {
		this.playerNumber = playerNumber;
	}

	public String getPlayerPassword() {
		return playerPassword;
	}

	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerIntroduction() {
		return playerIntroduction;
	}

	public void setPlayerIntroduction(String playerIntroduction) {
		this.playerIntroduction = playerIntroduction;
	}

	public String getPlayerRealName() {
		return playerRealName;
	}

	public void setPlayerRealName(String playerRealName) {
		this.playerRealName = playerRealName;
	}

	public String getPlayerBirthday() {
		return playerBirthday;
	}

	public void setPlayerBirthday(String playerBirthday) {
		this.playerBirthday = playerBirthday;
	}

	public String getPlayerGender() {
		return playerGender;
	}

	public void setPlayerGender(String playerGender) {
		this.playerGender = playerGender;
	}

	public String getPlayerAddress() {
		return playerAddress;
	}

	public void setPlayerAddress(String playerAddress) {
		this.playerAddress = playerAddress;
	}

	public String getPlayerTelePhone() {
		return playerTelePhone;
	}

	public void setPlayerTelePhone(String playerTelePhone) {
		this.playerTelePhone = playerTelePhone;
	}

	//跳转到修改球员信息界面
	@Action(value="JumpToUpdatePlayerInformation",results={@Result(name="success",location="/jsp/player/playerInformation.jsp")})
	public String JumpToUpdatePlayerInformation() {
		Player existPlayer = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		Player player = playerService.findPlayerById(existPlayer.getPid());
		ServletActionContext.getRequest().getSession().setAttribute("updatePlayer", player);
		return SUCCESS;
	}
	
	//修改球员信息
	@Action(value="updatePlayerInformation",results={@Result(name="success",location="/jsp/success.jsp")})
	public String updatePlayerInformation() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		Player player = (Player) ServletActionContext.getRequest().getSession().getAttribute("existPlayer");
		if(playerPhotoFileName != null) { //只有当有在表单中获取到照片时才处理，否则为空时会出错！
			playerPhotoFileName = UUIDUtils.getUUID()+playerPhotoFileName;
			//处理上传文件
			File destFile = new File(ServletActionContext.getServletContext().getRealPath("/pPhoto"),playerPhotoFileName);
			try {
				FileUtils.copyFile(playerPhoto, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String clubPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/pPhoto/"+playerPhotoFileName;
			player.setpPhoto(clubPhotosrc);	
	
		}
		//System.out.println(playerName+playerRealName+playerPassword+"-------------------------------");
		//System.out.println("----------------------------------------------------------");
		
		player.setpName(playerName);
		player.setPassword(playerPassword);
		player.setpAddress(playerAddress);
		player.setpBirthday(playerBirthday);
		player.setpIntroduction(playerIntroduction);
		player.setpGender(playerGender);
		player.setpRealName(playerRealName);
		player.setpTelePhone(playerTelePhone);
		player.setpPosition(playerLocation);
		
		
		playerService.updatePlayer(player);
		
		
		ServletActionContext.getRequest().setAttribute("msg", "恭喜您修改信息成功！");
		return SUCCESS;
	}
}
















