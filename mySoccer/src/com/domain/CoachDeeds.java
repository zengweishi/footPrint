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

@Entity
@Table(name="coachdeeds",catalog="mysoccer")
public class CoachDeeds {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String coachDeedsId; //事迹Id
	
	private String coachDeedsTitle;	//事迹标题
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date coachDeedsDate;//事迹时间
	
	private String coachDeedsContent; //事迹内容
	
	@ManyToOne(targetEntity=Coach.class)
	@JoinColumn(name="deeds_coach")
	private Coach deedsCoach; //俱乐部

	public CoachDeeds() {
		
	}
	
	public CoachDeeds(String coachDeedsId, String coachDeedsTitle,
			Date coachDeedsDate, String coachDeedsContent, Coach deedsCoach) {
		super();
		this.coachDeedsId = coachDeedsId;
		this.coachDeedsTitle = coachDeedsTitle;
		this.coachDeedsDate = coachDeedsDate;
		this.coachDeedsContent = coachDeedsContent;
		this.deedsCoach = deedsCoach;
	}

	public String getCoachDeedsId() {
		return coachDeedsId;
	}

	public void setCoachDeedsId(String coachDeedsId) {
		this.coachDeedsId = coachDeedsId;
	}

	public String getCoachDeedsTitle() {
		return coachDeedsTitle;
	}

	public void setCoachDeedsTitle(String coachDeedsTitle) {
		this.coachDeedsTitle = coachDeedsTitle;
	}

	public Date getCoachDeedsDate() {
		return coachDeedsDate;
	}

	public void setCoachDeedsDate(Date coachDeedsDate) {
		this.coachDeedsDate = coachDeedsDate;
	}

	public String getCoachDeedsContent() {
		return coachDeedsContent;
	}

	public void setCoachDeedsContent(String coachDeedsContent) {
		this.coachDeedsContent = coachDeedsContent;
	}

	public Coach getDeedsCoach() {
		return deedsCoach;
	}

	public void setDeedsCoach(Coach deedsCoach) {
		this.deedsCoach = deedsCoach;
	}
	
	

}
