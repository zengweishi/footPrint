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
 * 每周评价
 * @author 45度炸
 *
 */
@Entity
@Table(name="evaluation",catalog="mysoccer")
public class Evaluation {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String evaId; //id
	private String evaContent; //内容
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date evaTime;//时间
	
	@ManyToOne(targetEntity=Player.class)
	@JoinColumn(name="eva_player")
	private Player evaPlayer;  //球员

	public Evaluation() {
		
	}
	
	public Evaluation(String evaId, String evaContent, Date evaTime,
			Player evaPlayer) {
		super();
		this.evaId = evaId;
		this.evaContent = evaContent;
		this.evaTime = evaTime;
		this.evaPlayer = evaPlayer;
	}

	public String getEvaId() {
		return evaId;
	}

	public void setEvaId(String evaId) {
		this.evaId = evaId;
	}

	public String getEvaContent() {
		return evaContent;
	}

	public void setEvaContent(String evaContent) {
		this.evaContent = evaContent;
	}

	public Date getEvaTime() {
		return evaTime;
	}

	public void setEvaTime(Date evaTime) {
		this.evaTime = evaTime;
	}

	public Player getEvaPlayer() {
		return evaPlayer;
	}

	public void setEvaPlayer(Player evaPlayer) {
		this.evaPlayer = evaPlayer;
	}
	
	

}
