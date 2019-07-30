package com.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 评论
 * @author 45度炸
 *
 */
@Entity
@Table(name="comments",catalog="mysoccer")
public class Comments {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String commentsId; //评论Id
	private String cnewsId; 
	private String anewsId;
	private String tnewsId;
	private String diaryId;
	private String shareId;
	private String clubId;
	private String coachId;
	private String playerId;
	private String touristId;
	private String userId;
	private String userName;
	private String userPhoto;
	private String contents;//内容
	
	@JSONField (format="yyyy-MM-dd hh:mm:ss") 
	private Date commentsTime; //评论时间

	public Comments() {
		
	}
	
	

	public Comments(String commentsId, String cnewsId, String anewsId,
			String tnewsId, String diaryId, String shareId, String clubId,
			String coachId, String playerId, String touristId, String userId,
			String userName, String userPhoto, String contents,
			Date commentsTime) {
		super();
		this.commentsId = commentsId;
		this.cnewsId = cnewsId;
		this.anewsId = anewsId;
		this.tnewsId = tnewsId;
		this.diaryId = diaryId;
		this.shareId = shareId;
		this.clubId = clubId;
		this.coachId = coachId;
		this.playerId = playerId;
		this.touristId = touristId;
		this.userId = userId;
		this.userName = userName;
		this.userPhoto = userPhoto;
		this.contents = contents;
		this.commentsTime = commentsTime;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getContents() {
		return contents;
	}



	public void setContents(String contents) {
		this.contents = contents;
	}



	public String getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(String commentsId) {
		this.commentsId = commentsId;
	}

	public String getCnewsId() {
		return cnewsId;
	}

	public void setCnewsId(String cnewsId) {
		this.cnewsId = cnewsId;
	}

	public String getAnewsId() {
		return anewsId;
	}

	public void setAnewsId(String anewsId) {
		this.anewsId = anewsId;
	}

	public String getTnewsId() {
		return tnewsId;
	}

	public void setTnewsId(String tnewsId) {
		this.tnewsId = tnewsId;
	}

	public String getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getCoachId() {
		return coachId;
	}

	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getTouristId() {
		return touristId;
	}

	public void setTouristId(String touristId) {
		this.touristId = touristId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public Date getCommentsTime() {
		return commentsTime;
	}

	public void setCommentsTime(Date commentsTime) {
		this.commentsTime = commentsTime;
	}

	
}








