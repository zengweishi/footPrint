package com.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 教练实体
 * @author 45度炸
 *
 */
@Entity
@Table(name="coach",catalog="mysoccer")
public class Coach {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String coachId; //教练id
	private String coachNumber; //账号
	private String coachName; //昵称
	private String coachPassword;//密码
	private String coachIntroduction; //简介
	private String coachRealName; //真实姓名
	private String coachPhoto; //头像
	private String coachBirthday; //生日
	private String coachAddress; //籍贯
	private String coachGender; //性别
	private String coachApplyId;//俱乐部申请的Id
	private String coachTelPhone;//教练的电话
	private Integer coachMainPage;//上首页
	
	@ManyToOne(targetEntity=Club.class)
	@JoinColumn(name="c_club") //coach表中设置外键为t_club
	private Club coachClub; //俱乐部
	
	@OneToMany(targetEntity=Player.class,mappedBy="pCoach")
	private List<Player> coachPlayer; //球员
	
	@OneToMany(targetEntity=CoachPlan.class,mappedBy="planCoach")
	private List<CoachPlan> coachPlan; //训练计划
	
	@OneToMany(targetEntity=CoachMemo.class,mappedBy="memoCoach")
	private Map<String,CoachMemo> coachMemo; //备忘录
	
	/*@OneToMany(targetEntity=SuggestForClub.class,mappedBy="sugClubCoach")
	private Map<String,SuggestForClub> coachSuggest; //建议
*/	
	@OneToMany(targetEntity=SuggestForCoach.class,mappedBy="sugCoach")
	private List<SuggestForCoach> coachSuggest; //建议
	
	@OneToMany(targetEntity=Share.class,mappedBy="shareCoach")
	private Map<String,Share> coachShare; //教练分享
	
	@OneToMany(targetEntity=CoachDeeds.class,mappedBy="deedsCoach")
	private Map<String,CoachDeeds> coachDeeds; //时间轴事迹 
	private String coachAssessment; //俱乐部评价
	
	public Coach() {
		
	}

	




	public Coach(String coachId, String coachNumber, String coachName,
			String coachPassword, String coachIntroduction,
			String coachRealName, String coachPhoto, String coachBirthday,
			String coachAddress, String coachGender, String coachApplyId,
			String coachTelPhone, Integer coachMainPage, Club coachClub,
			List<Player> coachPlayer, List<CoachPlan> coachPlan,
			Map<String, CoachMemo> coachMemo,
			List<SuggestForCoach> coachSuggest, Map<String, Share> coachShare,
			Map<String, CoachDeeds> coachDeeds, String coachAssessment) {
		super();
		this.coachId = coachId;
		this.coachNumber = coachNumber;
		this.coachName = coachName;
		this.coachPassword = coachPassword;
		this.coachIntroduction = coachIntroduction;
		this.coachRealName = coachRealName;
		this.coachPhoto = coachPhoto;
		this.coachBirthday = coachBirthday;
		this.coachAddress = coachAddress;
		this.coachGender = coachGender;
		this.coachApplyId = coachApplyId;
		this.coachTelPhone = coachTelPhone;
		this.coachMainPage = coachMainPage;
		this.coachClub = coachClub;
		this.coachPlayer = coachPlayer;
		this.coachPlan = coachPlan;
		this.coachMemo = coachMemo;
		this.coachSuggest = coachSuggest;
		this.coachShare = coachShare;
		this.coachDeeds = coachDeeds;
		this.coachAssessment = coachAssessment;
	}





	public Integer getCoachMainPage() {
		return coachMainPage;
	}






	public void setCoachMainPage(Integer coachMainPage) {
		this.coachMainPage = coachMainPage;
	}






	public String getCoachTelPhone() {
		return coachTelPhone;
	}


	public void setCoachTelPhone(String coachTelPhone) {
		this.coachTelPhone = coachTelPhone;
	}


	public String getCoachApplyId() {
		return coachApplyId;
	}





	public void setCoachApplyId(String coachApplyId) {
		this.coachApplyId = coachApplyId;
	}





	public void setCoachPlayer(List<Player> coachPlayer) {
		this.coachPlayer = coachPlayer;
	}


	public List<Player> getCoachPlayer() {
		return coachPlayer;
	}


	public String getCoachPassword() {
		return coachPassword;
	}


	public void setCoachPassword(String coachPassword) {
		this.coachPassword = coachPassword;
	}


	public String getCoachId() {
		return coachId;
	}
	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}
	public String getCoachNumber() {
		return coachNumber;
	}
	public void setCoachNumber(String coachNumber) {
		this.coachNumber = coachNumber;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getCoachIntroduction() {
		return coachIntroduction;
	}
	public void setCoachIntroduction(String coachIntroduction) {
		this.coachIntroduction = coachIntroduction;
	}
	public String getCoachRealName() {
		return coachRealName;
	}
	public void setCoachRealName(String coachRealName) {
		this.coachRealName = coachRealName;
	}
	public String getCoachPhoto() {
		return coachPhoto;
	}
	public void setCoachPhoto(String coachPhoto) {
		this.coachPhoto = coachPhoto;
	}
	public String getCoachBirthday() {
		return coachBirthday;
	}
	public void setCoachBirthday(String coachBirthday) {
		this.coachBirthday = coachBirthday;
	}
	public String getCoachAddress() {
		return coachAddress;
	}
	public void setCoachAddress(String coachAddress) {
		this.coachAddress = coachAddress;
	}
	public String getCoachGender() {
		return coachGender;
	}
	public void setCoachGender(String coachGender) {
		this.coachGender = coachGender;
	}
	public Club getCoachClub() {
		return coachClub;
	}
	public void setCoachClub(Club coachClub) {
		this.coachClub = coachClub;
	}
	
	public List<CoachPlan> getCoachPlan() {
		return coachPlan;
	}


	public void setCoachPlan(List<CoachPlan> coachPlan) {
		this.coachPlan = coachPlan;
	}


	public Map<String, CoachMemo> getCoachMemo() {
		return coachMemo;
	}

	public void setCoachMemo(Map<String, CoachMemo> coachMemo) {
		this.coachMemo = coachMemo;
	}


	public List<SuggestForCoach> getCoachSuggest() {
		return coachSuggest;
	}



	public void setCoachSuggest(List<SuggestForCoach> coachSuggest) {
		this.coachSuggest = coachSuggest;
	}



	public Map<String, Share> getCoachShare() {
		return coachShare;
	}
	public void setCoachShare(Map<String, Share> coachShare) {
		this.coachShare = coachShare;
	}
	public Map<String, CoachDeeds> getCoachDeeds() {
		return coachDeeds;
	}
	public void setCoachDeeds(Map<String, CoachDeeds> coachDeeds) {
		this.coachDeeds = coachDeeds;
	}
	public String getCoachAssessment() {
		return coachAssessment;
	}
	public void setCoachAssessment(String coachAssessment) {
		this.coachAssessment = coachAssessment;
	}

	
}
