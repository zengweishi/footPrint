package com.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;
@Entity
@Table(name="playerdeeds",catalog="mysoccer")
public class PlayerDeeds {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String playerDeedsId; //事迹Id
	
	private String playerDeedsTitle;	//事迹标题
	
	//@Temporal(TemporalType.TIMESTAMP)

	@JSONField (format="yyyy-MM-dd hh:mm:ss") 
	private Date playerDeedsDate;//事迹时间
	
	//@Temporal(TemporalType.TIMESTAMP)

	@JSONField (format="yyyy-MM-dd") 
	private Date playerDeedsTitleTime;//标题时间
	
	private String playerDeedsContent; //事迹内容
	
	@ManyToOne(targetEntity=Player.class)
	@JoinColumn(name="deeds_player")
	private Player deedsPlayer; //球员

	public PlayerDeeds() {
		
	}
	
	

	public PlayerDeeds(String playerDeedsId, String playerDeedsTitle,
			Date playerDeedsDate, Date playerDeedsTitleTime,
			String playerDeedsContent, Player deedsPlayer) {
		super();
		this.playerDeedsId = playerDeedsId;
		this.playerDeedsTitle = playerDeedsTitle;
		this.playerDeedsDate = playerDeedsDate;
		this.playerDeedsTitleTime = playerDeedsTitleTime;
		this.playerDeedsContent = playerDeedsContent;
		this.deedsPlayer = deedsPlayer;
	}



	public String getPlayerDeedsId() {
		return playerDeedsId;
	}

	

	public Date getPlayerDeedsTitleTime() {
		return playerDeedsTitleTime;
	}

	public void setPlayerDeedsTitleTime(Date playerDeedsTitleTime) {
		this.playerDeedsTitleTime = playerDeedsTitleTime;
	}

	public void setPlayerDeedsId(String playerDeedsId) {
		this.playerDeedsId = playerDeedsId;
	}

	public String getPlayerDeedsTitle() {
		return playerDeedsTitle;
	}

	public void setPlayerDeedsTitle(String playerDeedsTitle) {
		this.playerDeedsTitle = playerDeedsTitle;
	}

	public Date getPlayerDeedsDate() {
		return playerDeedsDate;
	}

	public void setPlayerDeedsDate(Date playerDeedsDate) {
		this.playerDeedsDate = playerDeedsDate;
	}

	public String getPlayerDeedsContent() {
		return playerDeedsContent;
	}

	public void setPlayerDeedsContent(String playerDeedsContent) {
		this.playerDeedsContent = playerDeedsContent;
	}

	public Player getDeedsPlayer() {
		return deedsPlayer;
	}

	public void setDeedsPlayer(Player deedsPlayer) {
		this.deedsPlayer = deedsPlayer;
	}



	@Override
	public String toString() {
		return "PlayerDeeds [playerDeedsId=" + playerDeedsId
				+ ", playerDeedsTitle=" + playerDeedsTitle
				+ ", playerDeedsDate=" + playerDeedsDate
				+ ", playerDeedsTitleTime=" + playerDeedsTitleTime
				+ ", playerDeedsContent=" + playerDeedsContent
				+ ", deedsPlayer=" + deedsPlayer + "]";
	}
	
	


}
