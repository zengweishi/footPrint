package com.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 首页轮播图
 * @author 45度炸
 *
 */
@Entity
@Table(name="adphoto",catalog="mysoccer")
public class AdPhoto {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String adId; //id
	private String adPicture; //图片
	private String adLink; //链接
	private String adBackLink;//后台链接
	
	public AdPhoto() {
		
	}
	
	

	public AdPhoto(String adId, String adPicture, String adLink,
			String adBackLink) {
		super();
		this.adId = adId;
		this.adPicture = adPicture;
		this.adLink = adLink;
		this.adBackLink = adBackLink;
	}



	public String getAdBackLink() {
		return adBackLink;
	}



	public void setAdBackLink(String adBackLink) {
		this.adBackLink = adBackLink;
	}



	public String getAdPicture() {
		return adPicture;
	}



	public void setAdPicture(String adPicture) {
		this.adPicture = adPicture;
	}


	public String getAdId() {
		return adId;
	}






	public void setAdId(String adId) {
		this.adId = adId;
	}






	public String getAdLink() {
		return adLink;
	}

	public void setAdLink(String adLink) {
		this.adLink = adLink;
	}
	
	
	
	
}












