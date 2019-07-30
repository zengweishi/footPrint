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
 * 建议
 * @author 45度炸
 *
 */
@Entity
@Table(name="suggestforcoach",catalog="mysoccer")
public class SuggestForCoach {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String sugCoachId; //id
	private String sugCoachContent; //内容
	
	private Integer sugCoachFlag;//是否标记
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date sugCoachTime;  //时间
	
	@ManyToOne(targetEntity=Coach.class)
	@JoinColumn(name="sug_for_Coach")
	private Coach sugCoach; //教练

	public SuggestForCoach() {
		
	}
	
	
	public SuggestForCoach(String sugCoachId, String sugCoachContent,
			Integer sugCoachFlag, Date sugCoachTime, Coach sugCoach) {
		super();
		this.sugCoachId = sugCoachId;
		this.sugCoachContent = sugCoachContent;
		this.sugCoachFlag = sugCoachFlag;
		this.sugCoachTime = sugCoachTime;
		this.sugCoach = sugCoach;
	}


	public Integer getSugCoachFlag() {
		return sugCoachFlag;
	}


	public void setSugCoachFlag(Integer sugCoachFlag) {
		this.sugCoachFlag = sugCoachFlag;
	}


	public String getSugCoachId() {
		return sugCoachId;
	}

	public void setSugCoachId(String sugCoachId) {
		this.sugCoachId = sugCoachId;
	}

	public String getSugCoachContent() {
		return sugCoachContent;
	}

	public void setSugCoachContent(String sugCoachContent) {
		this.sugCoachContent = sugCoachContent;
	}

	public Date getSugCoachTime() {
		return sugCoachTime;
	}

	public void setSugCoachTime(Date sugCoachTime) {
		this.sugCoachTime = sugCoachTime;
	}



	public Coach getSugCoach() {
		return sugCoach;
	}



	public void setSugCoach(Coach sugCoach) {
		this.sugCoach = sugCoach;
	}



	

	
	
	

}













