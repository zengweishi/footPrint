package com.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 管理员
 * @author 45度炸
 *
 */
@Entity
@Table(name="admin",catalog="mysoccer")
public class Admin {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String adminId; //id	
	private String adminName; //名称
	private String adminPassword; //密码
	private Integer articleFlag; //文章管理权限
	private Integer userFlag;	//用户管理权限
	private Integer adminerFlag; //管理员管理权限
	private Integer photoFlag; //轮播图管理权限
	private Integer clubFlag; //俱乐部管理权限
	private Integer newsFlag; //新闻管理权限
	private String adminRealName;//管理者姓名
	private String adminTelePhone;//管理者电话
	
	
	@OneToMany(targetEntity = ANews.class,mappedBy="anewsAdmin")
	private List<ANews> adminNews; //新闻动态
	
	public Admin() {
		
	}

	
	public Admin(String adminId, String adminName, String adminPassword,
			Integer articleFlag, Integer userFlag, Integer adminerFlag,
			Integer photoFlag, Integer clubFlag, Integer newsFlag,
			String adminRealName, String adminTelePhone, List<ANews> adminNews) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.articleFlag = articleFlag;
		this.userFlag = userFlag;
		this.adminerFlag = adminerFlag;
		this.photoFlag = photoFlag;
		this.clubFlag = clubFlag;
		this.newsFlag = newsFlag;
		this.adminRealName = adminRealName;
		this.adminTelePhone = adminTelePhone;
		this.adminNews = adminNews;
	}


	public String getAdminTelePhone() {
		return adminTelePhone;
	}


	public void setAdminTelePhone(String adminTelePhone) {
		this.adminTelePhone = adminTelePhone;
	}


	public Integer getClubFlag() {
		return clubFlag;
	}



	public void setClubFlag(Integer clubFlag) {
		this.clubFlag = clubFlag;
	}



	public Integer getNewsFlag() {
		return newsFlag;
	}



	public void setNewsFlag(Integer newsFlag) {
		this.newsFlag = newsFlag;
	}



	public String getAdminRealName() {
		return adminRealName;
	}



	public void setAdminRealName(String adminRealName) {
		this.adminRealName = adminRealName;
	}



	public List<ANews> getAdminNews() {
		return adminNews;
	}

	public void setAdminNews(List<ANews> adminNews) {
		this.adminNews = adminNews;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}


	public Integer getArticleFlag() {
		return articleFlag;
	}




	public void setArticleFlag(Integer articleFlag) {
		this.articleFlag = articleFlag;
	}




	public Integer getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(Integer userFlag) {
		this.userFlag = userFlag;
	}

	public Integer getAdminerFlag() {
		return adminerFlag;
	}

	public void setAdminerFlag(Integer adminerFlag) {
		this.adminerFlag = adminerFlag;
	}

	public Integer getPhotoFlag() {
		return photoFlag;
	}

	public void setPhotoFlag(Integer photoFlag) {
		this.photoFlag = photoFlag;
	}
	
	
}
