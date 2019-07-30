package com.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
 * 游客新闻
 * @author 45度炸
 *
 */
@Entity
@Table(name="tnews",catalog="mysoccer")
public class TNews {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String newsId; //文章id
	
	//@Temporal(TemporalType.TIMESTAMP)
	@JSONField (format="yyyy-MM-dd hh:mm:ss") 
	private Date newsDate; //文章日期
	
	private String newsTitle; //文章标题
	
	private Integer newsTopFlag; //置顶
	
	@Lob
	private String newsContents; //文章内容
	@Lob   
	@Column(name="content", columnDefinition="TEXT", nullable=true)
	public String getNewsContents() {
		return newsContents;
	}
	
	private String newsHeadContent;
	
	
	private Integer newsCode; //文章状态



	private Integer viewNumber; //阅读数
	
	@ManyToOne(targetEntity=Tourist.class)
	@JoinColumn(name="n_tourist")
	private Tourist newsTourist; //游客

	@OneToMany(targetEntity=TourComments.class,mappedBy="commentsNews")
	private Map<String,TourComments> comments; //评论

	/*@OneToMany(targetEntity = CollectionItem.class,mappedBy="collectionTNews")
	private List<CollectionItem> collectionList;*/
	
	public TNews() {
		
	}
	
	
	public TNews(String newsId, Date newsDate, String newsTitle,
			Integer newsTopFlag, String newsContents, String newsHeadContent,
			Integer newsCode, Integer viewNumber, Tourist newsTourist,
			Map<String, TourComments> comments) {
		super();
		this.newsId = newsId;
		this.newsDate = newsDate;
		this.newsTitle = newsTitle;
		this.newsTopFlag = newsTopFlag;
		this.newsContents = newsContents;
		this.newsHeadContent = newsHeadContent;
		this.newsCode = newsCode;
		this.viewNumber = viewNumber;
		this.newsTourist = newsTourist;
		this.comments = comments;
	}


	public Integer getViewNumber() {
		return viewNumber;
	}


	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}


	public Integer getNewsTopFlag() {
		return newsTopFlag;
	}


	public void setNewsTopFlag(Integer newsTopFlag) {
		this.newsTopFlag = newsTopFlag;
	}


	public String getNewsHeadContent() {
		return newsHeadContent;
	}



	public void setNewsHeadContent(String newsHeadContent) {
		this.newsHeadContent = newsHeadContent;
	}



	public void setNewsContents(String newsContents) {
		this.newsContents = newsContents;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public Date getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	
	public Integer getNewsCode() {
		return newsCode;
	}

	public void setNewsCode(Integer newsCode) {
		this.newsCode = newsCode;
	}


	public Tourist getNewsTourist() {
		return newsTourist;
	}

	public void setNewsTourist(Tourist newsTourist) {
		this.newsTourist = newsTourist;
	}



	public Map<String, TourComments> getComments() {
		return comments;
	}



	public void setComments(Map<String, TourComments> comments) {
		this.comments = comments;
	}

	
	

	
	
}
