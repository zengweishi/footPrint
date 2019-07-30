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
 * 教练分享
 * @author 45度炸
 *
 */
@Entity
@Table(name="share",catalog="mysoccer")
public class Share {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String shareId; //分享id
	
	//@Temporal(TemporalType.TIMESTAMP)
	@JSONField (format="yyyy-MM-dd hh:mm:ss") 
	private Date shareDate; //分享日期
	
	private String shareTitle; //分享标题
	
	private Integer viewNumber;//浏览数
	
	private Integer shareTopFlag; //置顶
	
	@Lob
	private String shareContents; //分享内容
	@Lob   
	@Column(name="content", columnDefinition="TEXT", nullable=true)
	public String getShareContents() {
		return shareContents;
	}
	
	private String shareHeadContent;
	//private String shareContent; //分享内容
	private Integer shareReadingNumber; //分享阅读数
	
	@ManyToOne(targetEntity=Coach.class)
	@JoinColumn(name="s_coach")
	private Coach shareCoach; //教练

	/*@OneToMany(targetEntity = CollectionItem.class,mappedBy="collectionShare")
	private List<CollectionItem> collectionList;*/
	
	public Share() {
		
	}
	

	public Share(String shareId, Date shareDate, String shareTitle,
			Integer viewNumber, Integer shareTopFlag, String shareContents,
			String shareHeadContent, Integer shareReadingNumber,
			Coach shareCoach) {
		super();
		this.shareId = shareId;
		this.shareDate = shareDate;
		this.shareTitle = shareTitle;
		this.viewNumber = viewNumber;
		this.shareTopFlag = shareTopFlag;
		this.shareContents = shareContents;
		this.shareHeadContent = shareHeadContent;
		this.shareReadingNumber = shareReadingNumber;
		this.shareCoach = shareCoach;
	}


	public Integer getViewNumber() {
		return viewNumber;
	}


	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}


	public Integer getShareTopFlag() {
		return shareTopFlag;
	}





	public void setShareTopFlag(Integer shareTopFlag) {
		this.shareTopFlag = shareTopFlag;
	}





	public String getShareHeadContent() {
		return shareHeadContent;
	}


	public void setShareHeadContent(String shareHeadContent) {
		this.shareHeadContent = shareHeadContent;
	}


	public void setShareContents(String shareContents) {
		this.shareContents = shareContents;
	}



	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}



	public Integer getShareReadingNumber() {
		return shareReadingNumber;
	}

	public void setShareReadingNumber(Integer shareReadingNumber) {
		this.shareReadingNumber = shareReadingNumber;
	}

	public Coach getShareCoach() {
		return shareCoach;
	}

	public void setShareCoach(Coach shareCoach) {
		this.shareCoach = shareCoach;
	}

	
	
	
}










