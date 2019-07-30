package com.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 游客收藏
 * @author 45度炸
 *
 */
@Entity
@Table(name="collectionItem",catalog="mysoccer")
public class CollectionItem {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String collectionId;
	

	private String collectionTourist; //游客
	

	private String collectionCNews; //俱乐部动态
	

	private String collectionShare; //教练分享
	

	private String collectionDiary; //球员日记
	

	private String collectionTNews; //游客资讯
	

	private String collectionANews; //官方新闻
	
	/*@ManyToOne(targetEntity=Tourist.class)
	@JoinColumn(name="c_tourist")
	private Tourist collectionTourist; //游客
	
	@ManyToOne(targetEntity=CNews.class)
	@JoinColumn(name="c_cnews")
	private CNews collectionCNews; //俱乐部动态
	
	@ManyToOne(targetEntity=Share.class)
	@JoinColumn(name="c_share")
	private Share collectionShare; //教练分享
	
	@ManyToOne(targetEntity=Diary.class)
	@JoinColumn(name="c_diary")
	private Diary collectionDiary; //球员日记
	
	@ManyToOne(targetEntity=TNews.class)
	@JoinColumn(name="c_tnews")
	private TNews collectionTNews; //游客资讯
	
	@ManyToOne(targetEntity=ANews.class)
	@JoinColumn(name="c_anews")
	private ANews collectionANews; //官方新闻
*/	
	public CollectionItem() {
		
	}
	
	

	public CollectionItem(String collectionId, String collectionTourist,
			String collectionCNews, String collectionShare,
			String collectionDiary, String collectionTNews,
			String collectionANews) {
		super();
		this.collectionId = collectionId;
		this.collectionTourist = collectionTourist;
		this.collectionCNews = collectionCNews;
		this.collectionShare = collectionShare;
		this.collectionDiary = collectionDiary;
		this.collectionTNews = collectionTNews;
		this.collectionANews = collectionANews;
	}



	public String getCollectionId() {
		return collectionId;
	}



	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}



	public String getCollectionTourist() {
		return collectionTourist;
	}



	public void setCollectionTourist(String collectionTourist) {
		this.collectionTourist = collectionTourist;
	}



	public String getCollectionCNews() {
		return collectionCNews;
	}



	public void setCollectionCNews(String collectionCNews) {
		this.collectionCNews = collectionCNews;
	}



	public String getCollectionShare() {
		return collectionShare;
	}



	public void setCollectionShare(String collectionShare) {
		this.collectionShare = collectionShare;
	}



	public String getCollectionDiary() {
		return collectionDiary;
	}



	public void setCollectionDiary(String collectionDiary) {
		this.collectionDiary = collectionDiary;
	}



	public String getCollectionTNews() {
		return collectionTNews;
	}



	public void setCollectionTNews(String collectionTNews) {
		this.collectionTNews = collectionTNews;
	}



	public String getCollectionANews() {
		return collectionANews;
	}



	public void setCollectionANews(String collectionANews) {
		this.collectionANews = collectionANews;
	}



}
