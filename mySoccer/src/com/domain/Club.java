package com.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 俱乐部实体
 * @author 45度炸
 *
 */
@Entity
@Table(name="club",catalog="mysoccer")
public class Club {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String cid; //俱乐部id
	private String clubName; //俱乐部全称
	private String clubPassword; //密码
	private String clubIntroduction; //简介
	private String clubPhoto; //头像
	private String clubEmail; //邮箱
	private String clubPhone; //联系电话
	private Integer clubCode; //激活状态
	private String clubCity; //俱乐部城市
	private Integer clubCoachFlag;//判断是否有教练
	private Integer clubMainPage;//上首页标志
	
	//一对多，由coachClub维护外键
	@OneToMany(targetEntity = Coach.class,mappedBy="coachClub")
	private List<Coach> clubCoach; //教练
	
	//@OneToMany(targetEntity = Player.class,mappedBy="pClub")
	//private Set<Player> clubPlayer; //球员
	
	@OneToMany(targetEntity=SuggestForClub.class,mappedBy="sugClub")
	private List<SuggestForClub> clubSuggest; //建议
	
	@OneToMany(targetEntity = Announcement.class,mappedBy="announcementClub")
	private Map<String,Announcement> clubAnnouncement; //公告
	
	@OneToMany(targetEntity = ClubDeeds.class,mappedBy="deedsClub")
	private Map<String,ClubDeeds> clubDeeds; //时间轴事迹
	
	@OneToMany(targetEntity = CNews.class,mappedBy="newsClub")
	private Map<String,CNews> clubNews; //新闻动态

	public Club() {
		
	}


	public Club(String cid, String clubName, String clubPassword,
			String clubIntroduction, String clubPhoto, String clubEmail,
			String clubPhone, Integer clubCode, String clubCity,
			Integer clubCoachFlag, Integer clubMainPage, List<Coach> clubCoach,
			List<SuggestForClub> clubSuggest,
			Map<String, Announcement> clubAnnouncement,
			Map<String, ClubDeeds> clubDeeds, Map<String, CNews> clubNews) {
		super();
		this.cid = cid;
		this.clubName = clubName;
		this.clubPassword = clubPassword;
		this.clubIntroduction = clubIntroduction;
		this.clubPhoto = clubPhoto;
		this.clubEmail = clubEmail;
		this.clubPhone = clubPhone;
		this.clubCode = clubCode;
		this.clubCity = clubCity;
		this.clubCoachFlag = clubCoachFlag;
		this.clubMainPage = clubMainPage;
		this.clubCoach = clubCoach;
		this.clubSuggest = clubSuggest;
		this.clubAnnouncement = clubAnnouncement;
		this.clubDeeds = clubDeeds;
		this.clubNews = clubNews;
	}




	public Integer getClubMainPage() {
		return clubMainPage;
	}




	public void setClubMainPage(Integer clubMainPage) {
		this.clubMainPage = clubMainPage;
	}




	public Integer getClubCoachFlag() {
		return clubCoachFlag;
	}


	public void setClubCoachFlag(Integer clubCoachFlag) {
		this.clubCoachFlag = clubCoachFlag;
	}


	public List<SuggestForClub> getClubSuggest() {
		return clubSuggest;
	}


	public void setClubSuggest(List<SuggestForClub> clubSuggest) {
		this.clubSuggest = clubSuggest;
	}


	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubPassword() {
		return clubPassword;
	}

	public void setClubPassword(String clubPassword) {
		this.clubPassword = clubPassword;
	}

	public String getClubIntroduction() {
		return clubIntroduction;
	}

	public void setClubIntroduction(String clubIntroduction) {
		this.clubIntroduction = clubIntroduction;
	}

	public String getClubPhoto() {
		return clubPhoto;
	}

	public void setClubPhoto(String clubPhoto) {
		this.clubPhoto = clubPhoto;
	}

	public String getClubEmail() {
		return clubEmail;
	}

	public void setClubEmail(String clubEmail) {
		this.clubEmail = clubEmail;
	}

	

	public String getClubPhone() {
		return clubPhone;
	}


	public void setClubPhone(String clubPhone) {
		this.clubPhone = clubPhone;
	}


	public Integer getClubCode() {
		return clubCode;
	}


	public void setClubCode(Integer clubCode) {
		this.clubCode = clubCode;
	}


	public String getClubCity() {
		return clubCity;
	}

	public void setClubCity(String clubCity) {
		this.clubCity = clubCity;
	}

	
	public List<Coach> getClubCoach() {
		return clubCoach;
	}




	public void setClubCoach(List<Coach> clubCoach) {
		this.clubCoach = clubCoach;
	}




	public Map<String, Announcement> getClubAnnouncement() {
		return clubAnnouncement;
	}

	public void setClubAnnouncement(Map<String, Announcement> clubAnnouncement) {
		this.clubAnnouncement = clubAnnouncement;
	}

	public Map<String, ClubDeeds> getClubDeeds() {
		return clubDeeds;
	}

	public void setClubDeeds(Map<String, ClubDeeds> clubDeeds) {
		this.clubDeeds = clubDeeds;
	}

	public Map<String, CNews> getClubNews() {
		return clubNews;
	}

	public void setClubNews(Map<String, CNews> clubNews) {
		this.clubNews = clubNews;
	}
	
	
}








