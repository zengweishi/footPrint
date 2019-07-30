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

/**
 * 时间轴
 * @author 45度炸
 *
 */
@Entity
@Table(name="clubdeeds",catalog="mysoccer")
public class ClubDeeds {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String clubDeedsId; //事迹Id
	
	private String clubDeedsTitle;	//事迹标题
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date clubDeedsDate;//事迹时间
	
	private String clubDeedsContent; //事迹内容
	
	@ManyToOne(targetEntity=Club.class)
	@JoinColumn(name="deeds_club")
	private Club deedsClub; //俱乐部

	public ClubDeeds() {
		
	}
	
	public ClubDeeds(String clubDeedsId, Date clubDeedsDate,
			String clubDeedsContent, Club deedsClub) {
		super();
		this.clubDeedsId = clubDeedsId;
		this.clubDeedsDate = clubDeedsDate;
		this.clubDeedsContent = clubDeedsContent;
		this.deedsClub = deedsClub;
	}

	public String getClubDeedsId() {
		return clubDeedsId;
	}

	public void setClubDeedsId(String clubDeedsId) {
		this.clubDeedsId = clubDeedsId;
	}

	public Date getClubDeedsDate() {
		return clubDeedsDate;
	}

	public void setClubDeedsDate(Date clubDeedsDate) {
		this.clubDeedsDate = clubDeedsDate;
	}

	public String getClubDeedsContent() {
		return clubDeedsContent;
	}

	public void setClubDeedsContent(String clubDeedsContent) {
		this.clubDeedsContent = clubDeedsContent;
	}

	public Club getDeedsClub() {
		return deedsClub;
	}

	public void setDeedsClub(Club deedsClub) {
		this.deedsClub = deedsClub;
	}
	
	
}











