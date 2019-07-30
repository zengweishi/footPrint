package com.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 球探报告
 * @author 45度炸
 *
 */
@Entity
@Table(name="report",catalog="mysoccer")
public class Report {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String reportId; //报告ID
	private String reportPower; //力量
	private String reportMind; //精神
	private String reportAwareness; //意识
	private String reportDefensive; //防守
	private String reportShot; //射门
	private String reportDrawstring; //盘带
	private String reportPassBall; //传球
	private String reportSpeed; //速度
	private String reportFeatureList; //特性
	
	//对应的各项评分
	private Double reportPowerScore;
	private Double reportMindScore;
	private Double reportAwarenessScore;
	private Double reportDefensiveScore;
	private Double reportShotScore;
	private Double reportDrawstringScore;
	private Double reportPassBallScore;
	private Double reportSpeedScore;
	
	
	@OneToOne(targetEntity=Player.class)
	@JoinColumn(name="r_player")
	private Player reportPlayer; //球员

	public Report() {
		
	}

	

	public Report(String reportId, String reportPower, String reportMind,
			String reportAwareness, String reportDefensive, String reportShot,
			String reportDrawstring, String reportPassBall, String reportSpeed,
			String reportFeatureList, Double reportPowerScore,
			Double reportMindScore, Double reportAwarenessScore,
			Double reportDefensiveScore, Double reportShotScore,
			Double reportDrawstringScore, Double reportPassBallScore,
			Double reportSpeedScore, Player reportPlayer) {
		super();
		this.reportId = reportId;
		this.reportPower = reportPower;
		this.reportMind = reportMind;
		this.reportAwareness = reportAwareness;
		this.reportDefensive = reportDefensive;
		this.reportShot = reportShot;
		this.reportDrawstring = reportDrawstring;
		this.reportPassBall = reportPassBall;
		this.reportSpeed = reportSpeed;
		this.reportFeatureList = reportFeatureList;
		this.reportPowerScore = reportPowerScore;
		this.reportMindScore = reportMindScore;
		this.reportAwarenessScore = reportAwarenessScore;
		this.reportDefensiveScore = reportDefensiveScore;
		this.reportShotScore = reportShotScore;
		this.reportDrawstringScore = reportDrawstringScore;
		this.reportPassBallScore = reportPassBallScore;
		this.reportSpeedScore = reportSpeedScore;
		this.reportPlayer = reportPlayer;
	}



	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportPower() {
		return reportPower;
	}

	public void setReportPower(String reportPower) {
		this.reportPower = reportPower;
	}

	public String getReportMind() {
		return reportMind;
	}

	public void setReportMind(String reportMind) {
		this.reportMind = reportMind;
	}

	public String getReportAwareness() {
		return reportAwareness;
	}

	public void setReportAwareness(String reportAwareness) {
		this.reportAwareness = reportAwareness;
	}

	public String getReportDefensive() {
		return reportDefensive;
	}

	public void setReportDefensive(String reportDefensive) {
		this.reportDefensive = reportDefensive;
	}

	public String getReportShot() {
		return reportShot;
	}

	public void setReportShot(String reportShot) {
		this.reportShot = reportShot;
	}

	public String getReportDrawstring() {
		return reportDrawstring;
	}

	public void setReportDrawstring(String reportDrawstring) {
		this.reportDrawstring = reportDrawstring;
	}

	public String getReportPassBall() {
		return reportPassBall;
	}

	public void setReportPassBall(String reportPassBall) {
		this.reportPassBall = reportPassBall;
	}

	public String getReportSpeed() {
		return reportSpeed;
	}

	public void setReportSpeed(String reportSpeed) {
		this.reportSpeed = reportSpeed;
	}


	public String getReportFeatureList() {
		return reportFeatureList;
	}



	public void setReportFeatureList(String reportFeatureList) {
		this.reportFeatureList = reportFeatureList;
	}



	public Double getReportPowerScore() {
		return reportPowerScore;
	}

	public void setReportPowerScore(Double reportPowerScore) {
		this.reportPowerScore = reportPowerScore;
	}

	public Double getReportMindScore() {
		return reportMindScore;
	}

	public void setReportMindScore(Double reportMindScore) {
		this.reportMindScore = reportMindScore;
	}

	public Double getReportAwarenessScore() {
		return reportAwarenessScore;
	}

	public void setReportAwarenessScore(Double reportAwarenessScore) {
		this.reportAwarenessScore = reportAwarenessScore;
	}

	public Double getReportDefensiveScore() {
		return reportDefensiveScore;
	}

	public void setReportDefensiveScore(Double reportDefensiveScore) {
		this.reportDefensiveScore = reportDefensiveScore;
	}

	public Double getReportShotScore() {
		return reportShotScore;
	}

	public void setReportShotScore(Double reportShotScore) {
		this.reportShotScore = reportShotScore;
	}

	public Double getReportDrawstringScore() {
		return reportDrawstringScore;
	}

	public void setReportDrawstringScore(Double reportDrawstringScore) {
		this.reportDrawstringScore = reportDrawstringScore;
	}

	public Double getReportPassBallScore() {
		return reportPassBallScore;
	}

	public void setReportPassBallScore(Double reportPassBallScore) {
		this.reportPassBallScore = reportPassBallScore;
	}

	public Double getReportSpeedScore() {
		return reportSpeedScore;
	}

	public void setReportSpeedScore(Double reportSpeedScore) {
		this.reportSpeedScore = reportSpeedScore;
	}

	public Player getReportPlayer() {
		return reportPlayer;
	}

	public void setReportPlayer(Player reportPlayer) {
		this.reportPlayer = reportPlayer;
	}
	
	
}
