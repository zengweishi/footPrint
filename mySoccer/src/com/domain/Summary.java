package com.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 训练后的球员评价
 * @author 45度炸
 *
 */
@Entity
@Table(name="summary",catalog="mysoccer")
public class Summary {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String summaryId; // 评价ID
	private String summaryPlayerId; //评价对应的球员Id
	private String summaryPlayerRealName; //评价对应的球员名字
	private String summaryContent; //评价内容
	private String summarySuggest;//评价建议
	private Double summaryScore;//评价评分
	private String summaryPlayerSum;//球员自我评价
	
	@ManyToOne(targetEntity=CoachPlan.class)
	@JoinColumn(name="summary_coachPlan")
	private CoachPlan summaryCoachPlan; //关联训练
	
	public Summary() {
		
	}

	

	public Summary(String summaryId, String summaryPlayerId,
			String summaryPlayerRealName, String summaryContent,
			String summarySuggest, Double summaryScore,
			String summaryPlayerSum, CoachPlan summaryCoachPlan) {
		super();
		this.summaryId = summaryId;
		this.summaryPlayerId = summaryPlayerId;
		this.summaryPlayerRealName = summaryPlayerRealName;
		this.summaryContent = summaryContent;
		this.summarySuggest = summarySuggest;
		this.summaryScore = summaryScore;
		this.summaryPlayerSum = summaryPlayerSum;
		this.summaryCoachPlan = summaryCoachPlan;
	}



	public String getSummaryPlayerSum() {
		return summaryPlayerSum;
	}



	public void setSummaryPlayerSum(String summaryPlayerSum) {
		this.summaryPlayerSum = summaryPlayerSum;
	}



	public String getSummarySuggest() {
		return summarySuggest;
	}




	public void setSummarySuggest(String summarySuggest) {
		this.summarySuggest = summarySuggest;
	}

	public Double getSummaryScore() {
		return summaryScore;
	}


	public void setSummaryScore(Double summaryScore) {
		this.summaryScore = summaryScore;
	}


	public String getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}

	public String getSummaryPlayerId() {
		return summaryPlayerId;
	}



	public void setSummaryPlayerId(String summaryPlayerId) {
		this.summaryPlayerId = summaryPlayerId;
	}



	public String getSummaryPlayerRealName() {
		return summaryPlayerRealName;
	}



	public void setSummaryPlayerRealName(String summaryPlayerRealName) {
		this.summaryPlayerRealName = summaryPlayerRealName;
	}



	public String getSummaryContent() {
		return summaryContent;
	}

	public void setSummaryContent(String summaryContent) {
		this.summaryContent = summaryContent;
	}

	public CoachPlan getSummaryCoachPlan() {
		return summaryCoachPlan;
	}

	public void setSummaryCoachPlan(CoachPlan summaryCoachPlan) {
		this.summaryCoachPlan = summaryCoachPlan;
	}
	
	
	
	
	
	
}
