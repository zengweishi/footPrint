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
@Table(name="suggestforclub",catalog="mysoccer")
public class SuggestForClub {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String sugClubId; //id
	private String sugClubContent; //内容
	
	private Integer sugClubFlag;//是否标记
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date sugClubTime;  //时间
	
	@ManyToOne(targetEntity=Club.class)
	@JoinColumn(name="sug_for_club")
	private Club sugClub; //俱乐部

	public SuggestForClub() {
		
	}
	
	

	


	public SuggestForClub(String sugClubId, String sugClubContent,
			Integer sugClubFlag, Date sugClubTime, Club sugClub) {
		super();
		this.sugClubId = sugClubId;
		this.sugClubContent = sugClubContent;
		this.sugClubFlag = sugClubFlag;
		this.sugClubTime = sugClubTime;
		this.sugClub = sugClub;
	}






	public Integer getSugClubFlag() {
		return sugClubFlag;
	}






	public void setSugClubFlag(Integer sugClubFlag) {
		this.sugClubFlag = sugClubFlag;
	}






	public String getSugClubId() {
		return sugClubId;
	}

	public void setSugClubId(String sugClubId) {
		this.sugClubId = sugClubId;
	}

	public String getSugClubContent() {
		return sugClubContent;
	}

	public void setSugClubContent(String sugClubContent) {
		this.sugClubContent = sugClubContent;
	}

	public Date getSugClubTime() {
		return sugClubTime;
	}

	public void setSugClubTime(Date sugClubTime) {
		this.sugClubTime = sugClubTime;
	}



	public Club getSugClub() {
		return sugClub;
	}



	public void setSugClub(Club sugClub) {
		this.sugClub = sugClub;
	}

	

}















