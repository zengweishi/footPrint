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
@Table(name="coachmemo",catalog="mysoccer")
public class CoachMemo {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String cocahMemoId; //Id
	private String coachMemoContent; //内容
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date coachMemoTime; //时间
	
	@ManyToOne(targetEntity=Coach.class)
	@JoinColumn(name="memo_coach")
	private Coach memoCoach; //教练

	
	public CoachMemo() {
		
	}
	
	public CoachMemo(String cocahMemoId, String coachMemoContent,
			Date coachMemoTime, Coach memoCoach) {
		super();
		this.cocahMemoId = cocahMemoId;
		this.coachMemoContent = coachMemoContent;
		this.coachMemoTime = coachMemoTime;
		this.memoCoach = memoCoach;
	}

	public String getCocahMemoId() {
		return cocahMemoId;
	}

	public void setCocahMemoId(String cocahMemoId) {
		this.cocahMemoId = cocahMemoId;
	}

	public String getCoachMemoContent() {
		return coachMemoContent;
	}

	public void setCoachMemoContent(String coachMemoContent) {
		this.coachMemoContent = coachMemoContent;
	}

	public Date getCoachMemoTime() {
		return coachMemoTime;
	}

	public void setCoachMemoTime(Date coachMemoTime) {
		this.coachMemoTime = coachMemoTime;
	}

	public Coach getMemoCoach() {
		return memoCoach;
	}

	public void setMemoCoach(Coach memoCoach) {
		this.memoCoach = memoCoach;
	}
	
	
	

}
