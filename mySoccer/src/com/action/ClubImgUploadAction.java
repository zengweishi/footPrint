package com.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.domain.MyResult;
import com.opensymphony.xwork2.ActionSupport;
import com.utils.ResultUtil;
import com.utils.UUIDUtils;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ClubImgUploadAction extends ActionSupport {
	private File clubImg; //用于接收上传的文件
	private String clubImgFileName; //头像
	
	
	public File getClubImg() {
		return clubImg;
	}


	public void setClubImg(File clubImg) {
		this.clubImg = clubImg;
	}


	public String getClubImgFileName() {
		return clubImgFileName;
	}


	public void setClubImgFileName(String clubImgFileName) {
		this.clubImgFileName = clubImgFileName;
	}

	@Action(value="imgUpload")
	public void imgUpload() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		clubImgFileName = UUIDUtils.getUUID()+clubImgFileName;
//		System.out.println(clubImg.getName());
//		System.out.println(clubImgFileName);
		File destFile = new File(ServletActionContext.getServletContext().getRealPath("/upload"),clubImgFileName);
		try {
			FileUtils.copyFile(clubImg, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String [] str = {ServletActionContext.getRequest().getContextPath() + "/upload/" + filename};					
		MyResult myResult =  ResultUtil.success(str);
        ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(myResult));//返回url地址
*/
		String [] str = {ServletActionContext.getRequest().getContextPath()+"/upload/"+clubImgFileName};
		MyResult myResult =  ResultUtil.success(str);
        try {
			ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(myResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//返回url地址

	}
	
	
	@Action(value="imgShareUpload")
	public void imgShareUpload() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		clubImgFileName = UUIDUtils.getUUID()+clubImgFileName;
//		System.out.println(clubImg.getName());
//		System.out.println(clubImgFileName);
		File destFile = new File(ServletActionContext.getServletContext().getRealPath("/shareUpload"),clubImgFileName);
		try {
			FileUtils.copyFile(clubImg, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String [] str = {ServletActionContext.getRequest().getContextPath() + "/upload/" + filename};					
		MyResult myResult =  ResultUtil.success(str);
        ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(myResult));//返回url地址
*/
		String [] str = {ServletActionContext.getRequest().getContextPath()+"/shareUpload/"+clubImgFileName};
		MyResult myResult =  ResultUtil.success(str);
        try {
			ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(myResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//返回url地址

	}
	
	@Action(value="imgDiaryUpload")
	public void imgDiaryUpload() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		clubImgFileName = UUIDUtils.getUUID()+clubImgFileName;
//		System.out.println(clubImg.getName());
//		System.out.println(clubImgFileName);
		File destFile = new File(ServletActionContext.getServletContext().getRealPath("/diaryUpload"),clubImgFileName);
		try {
			FileUtils.copyFile(clubImg, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String [] str = {ServletActionContext.getRequest().getContextPath() + "/upload/" + filename};					
		MyResult myResult =  ResultUtil.success(str);
        ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(myResult));//返回url地址
*/
		String [] str = {ServletActionContext.getRequest().getContextPath()+"/diaryUpload/"+clubImgFileName};
		MyResult myResult =  ResultUtil.success(str);
        try {
			ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(myResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//返回url地址

	}
	
	
	
	@Action(value="imgANewsUpload")
	public void imgANewsUpload() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		clubImgFileName = UUIDUtils.getUUID()+clubImgFileName;
//		System.out.println(clubImg.getName());
//		System.out.println(clubImgFileName);
		File destFile = new File(ServletActionContext.getServletContext().getRealPath("/anewsUpload"),clubImgFileName);
		try {
			FileUtils.copyFile(clubImg, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String [] str = {ServletActionContext.getRequest().getContextPath() + "/upload/" + filename};					
		MyResult myResult =  ResultUtil.success(str);
        ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(myResult));//返回url地址
*/
		String [] str = {ServletActionContext.getRequest().getContextPath()+"/anewsUpload/"+clubImgFileName};
		MyResult myResult =  ResultUtil.success(str);
        try {
			ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(myResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//返回url地址

	}
	
	
	@Action(value="imgTNewsUpload")
	public void imgTNewsUpload() {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		clubImgFileName = UUIDUtils.getUUID()+clubImgFileName;
//		System.out.println(clubImg.getName());
//		System.out.println(clubImgFileName);
		File destFile = new File(ServletActionContext.getServletContext().getRealPath("/tnewsUpload"),clubImgFileName);
		try {
			FileUtils.copyFile(clubImg, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String [] str = {ServletActionContext.getRequest().getContextPath() + "/upload/" + filename};					
		MyResult myResult =  ResultUtil.success(str);
        ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(myResult));//返回url地址
*/
		String [] str = {ServletActionContext.getRequest().getContextPath()+"/tnewsUpload/"+clubImgFileName};
		MyResult myResult =  ResultUtil.success(str);
        try {
			ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(myResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//返回url地址

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
