package com.action;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.domain.Club;
import com.domain.Player;
import com.domain.Report;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IClubService;
import com.service.ICoachService;
import com.service.IPlayerService;
import com.utils.AuthService;
import com.utils.Base64Util;
import com.utils.FileUtil;
import com.utils.GsonUtils;
import com.utils.HandWriteBean;
import com.utils.HandWriteBean.Words_result;
import com.utils.HttpUtil;
import com.utils.UUIDUtils;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class PlayerReportAction extends ActionSupport {
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	
	
	private File reportPhoto; //用于接收上传的文件
	private String reportPhotoFileName; //图片
	
	
	
	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}

	public ICoachService getCoachService() {
		return coachService;
	}

	public void setCoachService(ICoachService coachService) {
		this.coachService = coachService;
	}

	public IClubService getClubService() {
		return clubService;
	}

	public void setClubService(IClubService clubService) {
		this.clubService = clubService;
	}

	public File getReportPhoto() {
		return reportPhoto;
	}

	public void setReportPhoto(File reportPhoto) {
		this.reportPhoto = reportPhoto;
	}

	public String getReportPhotoFileName() {
		return reportPhotoFileName;
	}

	public void setReportPhotoFileName(String reportPhotoFileName) {
		this.reportPhotoFileName = reportPhotoFileName;
	}

	//跳转到球员报告界面
	@Action(value="jumpToPlayerReportUI",results={@Result(name="success",location="/jsp/player/playerReport.jsp")})
	public String jumpToPlayerReportUI() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Player player = playerService.findPlayerById(pid);
		String clubId = player.getpClubId();
		if(clubId != null) {
			Club club = clubService.findClubNameByClubId(clubId);
			player.setpClubName(club.getClubName());
			
		}
		ServletActionContext.getRequest().setAttribute("infoPlayer", player);
		return SUCCESS;
	}
	
	//获取信息显示到模态框
	@Action(value="getPlayerReport")
	public void getPlayerReport() {
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			String pid = ServletActionContext.getRequest().getParameter("pid");
			Report report = playerService.findPlayerReportByPid(pid);
			if(report == null) {
				ServletActionContext.getResponse().getWriter().write("0");
			} else {
				PropertyFilter filter = new PropertyFilter() {
					
					@Override
					public boolean apply(Object arg0, String filedName, Object arg2) {
						if("reportPlayer".equalsIgnoreCase(filedName)) {
							return false;
						}
						
						return true;
					}
					
				};
				String json = JSONArray.toJSONString(report,filter,SerializerFeature.DisableCircularReferenceDetect);
				ServletActionContext.getResponse().getWriter().write(json);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//创建球员报告
	@Action(value="createPlayerReport",results={@Result(name="success",location="findPlayerReport",type="redirectAction")})
	public String createPlayerReport() {
			
		try {
				ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
				Map<String,String[]> map = ServletActionContext.getRequest().getParameterMap();
				String pid = ServletActionContext.getRequest().getParameter("r_player");
				Report oldReport = playerService.findPlayerReportByPid(pid);
				if(oldReport == null) { //新建报告
					Report report = new Report();
					BeanUtils.populate(report, map);
					pid = ServletActionContext.getRequest().getParameter("r_player");
					Player player = playerService.findPlayerById(pid);
					report.setReportPlayer(player);
					playerService.createPlayerReport(report);
					ServletActionContext.getRequest().getSession().setAttribute("thisPid", pid);
					
				} else { //更新报告
					BeanUtils.populate(oldReport, map);
					playerService.updatePlayerReport(oldReport);
					ServletActionContext.getRequest().getSession().setAttribute("thisPid", pid);
					
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			return SUCCESS;
	}
	
	
	
	//显示球员报告
	@Action(value="findPlayerReport",results={@Result(name="success",location="/jsp/player/playerReport.jsp")})
	public String findPlayerReport() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		if(pid == null) {
			pid = (String) ServletActionContext.getRequest().getSession().getAttribute("thisPid");
		}
		Player player = playerService.findPlayerById(pid);
		String clubId = player.getpClubId();
		if(clubId != null) {
			Club club = clubService.findClubNameByClubId(clubId);
			player.setpClubName(club.getClubName());
			
		}
		ServletActionContext.getRequest().setAttribute("infoPlayer", player);
		Report report = playerService.findPlayerReportByPid(pid);
		ServletActionContext.getRequest().setAttribute("report", report);
		if(report!=null) {
			String reportFeature = report.getReportFeatureList();
			String[] str = reportFeature.split("，");
			List<String> reportFeatureList = new ArrayList<String>();
			int i = 0;
			while(i<str.length) {
				reportFeatureList.add(str[i]);
				++i;
			}
			ServletActionContext.getRequest().setAttribute("reportFeatureList", reportFeatureList);
			
		}
		return SUCCESS;
	}
	
	//获取技能评分
	@Action(value="getReportScore") 
	public void getReportScore() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Report report = playerService.findPlayerReportByPid(pid);
		if(report!=null) {
			List<Double> list = new ArrayList<Double>();
			list.add(report.getReportMindScore());
			list.add(report.getReportDefensiveScore());
			list.add(report.getReportShotScore());
			list.add(report.getReportDrawstringScore());
			list.add(report.getReportPassBallScore());
			list.add(report.getReportSpeedScore());
			String json = JSONArray.toJSONString(list);
			try {
				ServletActionContext.getResponse().getWriter().write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	//寻找球员报告显示到首页
	@Action(value="findPlayerReportForInfo")
	public void findPlayerReportForInfo() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		String pid = ServletActionContext.getRequest().getParameter("pid");
		Report report = playerService.findPlayerReportByPid(pid);
		try {
			PropertyFilter filter = new PropertyFilter() {
				
				@Override
				public boolean apply(Object arg0, String filedName, Object arg2) {
					if("reportPlayer".equalsIgnoreCase(filedName)) {
						return false;
					}
					
					return true;
				}
				
			};
			String json = JSONArray.toJSONString(report,filter,SerializerFeature.DisableCircularReferenceDetect);
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//图片识别
		@Action(value="identifyPhoto")
		public void identifyPhoto() {
			
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			if(reportPhotoFileName!=null) {
				reportPhotoFileName = UUIDUtils.getUUID()+reportPhotoFileName;
				//处理上传文件
				File destFile = new File(ServletActionContext.getServletContext().getRealPath("/reportPhoto"),reportPhotoFileName);
				try {
					FileUtils.copyFile(reportPhoto, destFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String reportPhotosrc = ServletActionContext.getServletContext().getContextPath()+"/reportPhoto/"+reportPhotoFileName;
				
				System.out.println(reportPhotosrc);
				 // 通用识别url
		        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
		        // 图片路径
		        String filePath = "D:/tomcat/apache-tomcat-7.0.69/webapps/"+reportPhotosrc;
		        try {
		            byte[] imgData = FileUtil.readFileByBytes(filePath);
		            String imgStr = Base64Util.encode(imgData);
		            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
		            /**
		             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。hong
		             */
		            String accessToken = AuthService.getAuth();
		            String object = HttpUtil.post(otherHost, accessToken, params);
		            HandWriteBean fromJson = GsonUtils.fromJson(object, HandWriteBean.class);
		            List<Words_result> list = fromJson.getWords_result();
		            List<String> resultList = new ArrayList<String>();
		            for (Words_result words_result : list) {
		            	resultList.add( words_result.getWords()) ;
		            }
		            String json = JSONArray.toJSONString(resultList);
		            ServletActionContext.getResponse().getWriter().write(json);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				
			}
			
			
			
		}
		
		@Action(value="autoWrite")
		public void autoWrite() {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			String report = ServletActionContext.getRequest().getParameter("reportPhotoData");
/*			report = report.replaceFirst("精神属性", "'.1");
			report = report.replaceFirst("防守能力", "'.2");
			report = report.replaceFirst("射门能力", "'.3");
			report = report.replaceFirst("盘带能力", "'.4");
			report = report.replaceFirst("传球能力", "'.5");
			report = report.replaceFirst("身体素质", "'.6");*/
			report = report.replaceFirst("精神", "'.1");
			report = report.replaceFirst("防守", "'.2");
			report = report.replaceFirst("射门", "'.3");
			report = report.replaceFirst("盘带", "'.4");
			report = report.replaceFirst("传球", "'.5");
			report = report.replaceFirst("身体素质", "'.6");
			System.out.println(report);
			String[] str = report.split("'.");
			List<String> list = new ArrayList<String>();
			for(int i=0;i<str.length;i++) {
				/*String firstStr = str[i].substring(0, 1);
				if()*/
				list.add(str[i]);
			}
			String json = JSONArray.toJSONString(list);
			try {
				ServletActionContext.getResponse().getWriter().write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
}









