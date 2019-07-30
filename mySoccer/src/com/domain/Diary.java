package com.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 日记
 * @author 45度炸
 *
 */
@Entity
@Table(name="diary",catalog="mysoccer")
public class Diary {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String diaryId; //日记id
	
	//@Temporal(TemporalType.TIMESTAMP)
	@JSONField (format="yyyy-MM-dd hh:mm:ss") 
	private Date diaryDate; //日记日期
	
	private String diaryTitle; //日记标题
	
	private Integer viewNumber;//浏览数
	
	private Integer diaryTopFlag; //置顶
	
	@Lob
	private String diaryContents; //日记内容
	@Lob   
	@Column(name="content", columnDefinition="TEXT", nullable=true)
	public String getDiaryContents() {
		return diaryContents;
	}
	
	private Integer diaryPermission; //日记权限
	private Integer diaryReadingNumber; //日记阅读数
	private String diaryHeadContent;//提示头
	
	@ManyToOne(targetEntity=Player.class)
	@JoinColumn(name="d_player")
	private Player diaryPlayer; //球员

/*	@OneToMany(targetEntity = CollectionItem.class,mappedBy="collectionDiary")
	private List<CollectionItem> collectionList;*/
	
	public Diary() {
		
	}
	
	
	


	public Diary(String diaryId, Date diaryDate, String diaryTitle,
		Integer viewNumber, Integer diaryTopFlag, String diaryContents,
		Integer diaryPermission, Integer diaryReadingNumber,
		String diaryHeadContent, Player diaryPlayer) {
	super();
	this.diaryId = diaryId;
	this.diaryDate = diaryDate;
	this.diaryTitle = diaryTitle;
	this.viewNumber = viewNumber;
	this.diaryTopFlag = diaryTopFlag;
	this.diaryContents = diaryContents;
	this.diaryPermission = diaryPermission;
	this.diaryReadingNumber = diaryReadingNumber;
	this.diaryHeadContent = diaryHeadContent;
	this.diaryPlayer = diaryPlayer;
}





	public Integer getViewNumber() {
		return viewNumber;
	}





	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}





	public Integer getDiaryTopFlag() {
		return diaryTopFlag;
	}


	public void setDiaryTopFlag(Integer diaryTopFlag) {
		this.diaryTopFlag = diaryTopFlag;
	}


	public String getDiaryHeadContent() {
		return diaryHeadContent;
	}


	public void setDiaryHeadContent(String diaryHeadContent) {
		this.diaryHeadContent = diaryHeadContent;
	}


	public String getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	public Date getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	

	

	public void setDiaryContents(String diaryContents) {
		this.diaryContents = diaryContents;
	}


	public Integer getDiaryPermission() {
		return diaryPermission;
	}

	public void setDiaryPermission(Integer diaryPermission) {
		this.diaryPermission = diaryPermission;
	}

	public Integer getDiaryReadingNumber() {
		return diaryReadingNumber;
	}

	public void setDiaryReadingNumber(Integer diaryReadingNumber) {
		this.diaryReadingNumber = diaryReadingNumber;
	}

	public Player getDiaryPlayer() {
		return diaryPlayer;
	}

	public void setDiaryPlayer(Player diaryPlayer) {
		this.diaryPlayer = diaryPlayer;
	}

//	@OneToMany(targetEntity=Comments.class,mappedBy="commentsClub")
//	private Comments Comments; //评论

	
}
