package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.domain.ANews;
import com.domain.CNews;
import com.domain.CollectionItem;
import com.domain.Diary;
import com.domain.PageBean;
import com.domain.Share;
import com.domain.TNews;
import com.domain.Tourist;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IAdminService;
import com.service.IClubService;
import com.service.ICoachService;
import com.service.IPlayerService;
import com.service.ITouristService;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class TouristArticleAction extends ActionSupport {
	@Autowired
	@Qualifier("touristService")
	private ITouristService touristService;
	
	@Autowired
	@Qualifier("clubService")
	private IClubService clubService;
	
	@Autowired
	@Qualifier("playerService")
	private IPlayerService playerService;
	
	@Autowired
	@Qualifier("adminService")
	private IAdminService adminService;
	
	@Autowired
	@Qualifier("coachService")
	private ICoachService coachService;
	
	@Action(value="createNewTNews")
	public void createNewTNews() {
		TNews tnews = new TNews();
		String newsContents = ServletActionContext.getRequest().getParameter("newsContents");
		String newsTitle = ServletActionContext.getRequest().getParameter("newsTitle");
		tnews.setNewsContents(newsContents);
		tnews.setNewsTitle(newsTitle);
		tnews.setNewsDate(new Date());
		tnews.setNewsTourist((Tourist)ServletActionContext.getRequest().getSession().getAttribute("existTourist"));
		
		
		//对文章提示头进行一个处理
		StringBuilder strBui = new StringBuilder();
		String finalHeadContent;
		if(newsContents.length()>150) {
			String headContent = newsContents.replaceAll(" ", "").substring(0, 150)+" ";
			char[] strHead = headContent.toCharArray();
			for(int i=1;i < strHead.length-1;++i) {
				if(strHead[i-1] == '>') {
					while(strHead[i]!='<') {
						strBui.append(strHead[i]);
						++i;
						if(i>=strHead.length-1) {
							break;
						}
					  	
					}
				}
				
			}
			finalHeadContent = strBui.toString();
		} else {
			String headContent = newsContents.replaceAll(" ", "")+"";
			char[] strHead = headContent.toCharArray();
			for(int i = 0;i<strHead.length;++i) {
				if(strHead[i] == '<') {
					while(strHead[i]!='>') {
						strHead[i] = ' ';
						strBui.append(strHead[i]);
						++i;
					}
					strHead[i] = ' ';
				}
				strBui.append(strHead[i]);
			}
			finalHeadContent = strBui.toString().replaceAll(" ", "");
		}
		tnews.setNewsHeadContent(finalHeadContent);
		touristService.createTNews(tnews);
		try {
			ServletActionContext.getResponse().getWriter().write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//查询所有的文章
	@Action(value="findOwnTNews",results={@Result(name="success",location="/jsp/tourist/findOwnTNews.jsp")})
	public String findOwnTNews() {
		Integer currPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
		PageBean<TNews> pageBean = touristService.findOwnTNews(currPage,(Tourist)ServletActionContext.getRequest().getSession().getAttribute("existTourist"));
		ServletActionContext.getRequest().getSession().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}
	
	//查询文章内容
	@Action(value="findTNewsArticle",results={@Result(name="success",location="/jsp/tourist/findTNewsArticle.jsp")})
	public String findTNewsArticle() {
		String newsId = ServletActionContext.getRequest().getParameter("newsId");
		TNews tnews = touristService.findTNewsArticle(newsId);
		ServletActionContext.getRequest().setAttribute("tnews", tnews);
		String pageFlag = ServletActionContext.getRequest().getParameter("pageFlag");
		if(pageFlag != null) {
			ServletActionContext.getRequest().setAttribute("pageFlag", pageFlag);
		}
		return SUCCESS;
	}
	
	//删除文章
	@Action(value="deleteTNews",results={@Result(name="success",location="findOwnTNews?currPage=1",type="redirectAction")})
	public String deleteTNews() {
		String newsId = ServletActionContext.getRequest().getParameter("newsId");
		touristService.deleteTNewsById(newsId);
		return SUCCESS;
	}
	
	//跳转到修改文章界面
	@Action(value="jumpToUpdateTNews",results={@Result(name="success",location="/jsp/tourist/updateTNewsEditor.jsp")})
	public String jumpToUpdateTNews() {
		String newsId = ServletActionContext.getRequest().getParameter("newsId");
		TNews updateTnews = touristService.findTNewsArticle(newsId);
		
		//对文章内容格式做一下处理
		String newsContents = updateTnews.getNewsContents();
		newsContents = newsContents.replaceAll("\"", "\"+\"");
		updateTnews.setNewsContents(newsContents);
		
		ServletActionContext.getRequest().setAttribute("updateTNews", updateTnews);
		return SUCCESS;
	}
	
	//修改文章内容
	@Action(value="updateTNews")
	public void updateTNews() {
		String newsId = ServletActionContext.getRequest().getParameter("id");
		TNews tnews = touristService.findTNewsArticle(newsId);
		String newsContents = ServletActionContext.getRequest().getParameter("newsContents");
		String newsTitle = ServletActionContext.getRequest().getParameter("newsTitle");
		tnews.setNewsContents(newsContents);
		tnews.setNewsTitle(newsTitle);
		//对文章提示头进行一个处理
		StringBuilder strBui = new StringBuilder();
		String finalHeadContent;
		if(newsContents.length()>150) {
			String headContent = newsContents.replaceAll(" ", "").substring(0, 150)+" ";
			char[] strHead = headContent.toCharArray();
			for(int i=1;i < strHead.length-1;++i) {
				if(strHead[i-1] == '>') {
					while(strHead[i]!='<') {
						strBui.append(strHead[i]);
						++i;
						if(i>=strHead.length-1) {
							break;
						}
					  	
					}
				}
				
			}
			finalHeadContent = strBui.toString();
		} else {
			String headContent = newsContents.replaceAll(" ", "")+"";
			char[] strHead = headContent.toCharArray();
			for(int i = 0;i<strHead.length;++i) {
				if(strHead[i] == '<') {
					while(strHead[i]!='>') {
						strHead[i] = ' ';
						strBui.append(strHead[i]);
						++i;
					}
					strHead[i] = ' ';
				}
				strBui.append(strHead[i]);
			}
			finalHeadContent = strBui.toString().replaceAll(" ", "");
		}
		tnews.setNewsHeadContent(finalHeadContent);
		
		touristService.updateTNews(tnews);
		try {
			ServletActionContext.getResponse().getWriter().write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//添加收藏
	@Action(value="addCollection")
	public void addCollection() {
		Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
		String cnewsId = ServletActionContext.getRequest().getParameter("cnewsId");
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		String tnewsId = ServletActionContext.getRequest().getParameter("tnewsId");
		String anewsId = ServletActionContext.getRequest().getParameter("anewsId");
		
		CollectionItem collectionItem = new CollectionItem();
		collectionItem.setCollectionTourist(tourist.gettId());
		if(cnewsId != null) {
			collectionItem.setCollectionCNews(cnewsId);
			
		} else if(shareId != null) {
			collectionItem.setCollectionShare(shareId);
			
		} else if(diaryId != null) {
			collectionItem.setCollectionDiary(diaryId);
			
		} else if(tnewsId != null) {
			collectionItem.setCollectionTNews(tnewsId);
			
		} else {
			collectionItem.setCollectionANews(anewsId);
			
		}
		touristService.addCollection(collectionItem);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//取消收藏
	@Action(value="deleteCollection")
	public void deleteCollection() {
		Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
		String cnewsId = ServletActionContext.getRequest().getParameter("cnewsId");
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		String tnewsId = ServletActionContext.getRequest().getParameter("tnewsId");
		String anewsId = ServletActionContext.getRequest().getParameter("anewsId");
		
		CollectionItem collectionItem = new CollectionItem();
		collectionItem.setCollectionTourist(tourist.gettId());
		if(cnewsId != null) {
			collectionItem.setCollectionCNews(cnewsId);
			touristService.deleteCollection(1,collectionItem);
			
		} else if(shareId != null) {
			collectionItem.setCollectionShare(shareId);
			touristService.deleteCollection(2,collectionItem);
			
		} else if(diaryId != null) {
			collectionItem.setCollectionDiary(diaryId);
			touristService.deleteCollection(3,collectionItem);
			
		} else if(tnewsId != null) {
			collectionItem.setCollectionTNews(tnewsId);
			touristService.deleteCollection(4,collectionItem);
			
		} else {
			collectionItem.setCollectionANews(anewsId);
			touristService.deleteCollection(5,collectionItem);
			
		}
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//判断是否收藏
	@Action(value="findCollection")
	public void findCollection() {
		Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
		String cnewsId = ServletActionContext.getRequest().getParameter("cnewsId");
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		String tnewsId = ServletActionContext.getRequest().getParameter("tnewsId");
		String anewsId = ServletActionContext.getRequest().getParameter("anewsId");
		
		CollectionItem collectionItem = new CollectionItem();
		collectionItem.setCollectionTourist(tourist.gettId());
		CollectionItem existCollectionItem = null;
		if(cnewsId != null) {
			collectionItem.setCollectionCNews(cnewsId);
			existCollectionItem = touristService.findCollection(1,collectionItem);
			
		} else if(shareId != null) {
			collectionItem.setCollectionShare(shareId);
			existCollectionItem = touristService.findCollection(2,collectionItem);
			
		} else if(diaryId != null) {
			collectionItem.setCollectionDiary(diaryId);
			existCollectionItem = touristService.findCollection(3,collectionItem);
			
		} else if(tnewsId != null) {
			collectionItem.setCollectionTNews(tnewsId);
			existCollectionItem = touristService.findCollection(4,collectionItem);
			
		} else {
			collectionItem.setCollectionANews(anewsId);
			existCollectionItem = touristService.findCollection(5,collectionItem);
			
		}
		try {
			if(existCollectionItem != null) {
				ServletActionContext.getResponse().getWriter().write("1");
				
			} else {
				ServletActionContext.getResponse().getWriter().write("0");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//展示收藏列表
	@Action(value="findCollectionByType",results={@Result(name="success",location="/jsp/tourist/findCollectionByType.jsp")})
	public String findCollectionByType() {
		Integer type = Integer.parseInt(ServletActionContext.getRequest().getParameter("type"));
		Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
		if(type == 1) {
			List<CNews> cnewsList = new ArrayList<CNews>();
			List<CollectionItem> itemList = touristService.findAllCollectionByType(1,tourist);
			for (CollectionItem collectionItem : itemList) {
				CNews cnews = clubService.findCNewsArtical(collectionItem.getCollectionCNews());
				if(cnews != null) {
					cnewsList.add(cnews);
				} else {
					touristService.deleteCollection(1, collectionItem);
				}
				
			}
			ServletActionContext.getRequest().setAttribute("cnewsList", cnewsList);

			
		}else if(type == 2) {
			List<Share> shareList = new ArrayList<Share>();
			List<CollectionItem> itemList = touristService.findAllCollectionByType(2,tourist);
			for (CollectionItem collectionItem : itemList) {
				Share share = coachService.showShareArtical(collectionItem.getCollectionShare());
				if(share != null) {
					shareList.add(share);
					
				} else {
					touristService.deleteCollection(2, collectionItem);
				}
			}
			ServletActionContext.getRequest().setAttribute("shareList", shareList);

			
		}else if(type == 3) {
			List<Diary> diaryList = new ArrayList<Diary>();
			List<CollectionItem> itemList = touristService.findAllCollectionByType(3,tourist);
			for (CollectionItem collectionItem : itemList) {
				Diary diary = playerService.findDiaryById(collectionItem.getCollectionDiary());
				if(diary != null) {
					diaryList.add(diary);
					
				} else{
					touristService.deleteCollection(3, collectionItem);
				}
			}
			ServletActionContext.getRequest().setAttribute("diaryList", diaryList);

			
		}else if(type == 4) {
			List<TNews> tnewsList = new ArrayList<TNews>();
			List<CollectionItem> itemList = touristService.findAllCollectionByType(4,tourist);
			for (CollectionItem collectionItem : itemList) {
				TNews tnews = touristService.findTNewsArticle(collectionItem.getCollectionTNews());
				if(tnews != null) {
					tnewsList.add(tnews);
					
				} else {
					touristService.deleteCollection(4, collectionItem);
				}
			}
			ServletActionContext.getRequest().setAttribute("tnewsList", tnewsList);

			
		}else {
			List<ANews> anewsList = new ArrayList<ANews>();
			List<CollectionItem> itemList = touristService.findAllCollectionByType(5,tourist);
			for (CollectionItem collectionItem : itemList) {
				ANews anews = adminService.findANewsById(collectionItem.getCollectionANews());
				if(anews != null) {
					anewsList.add(anews);
					
				} else {
					touristService.deleteCollection(5, collectionItem);
				}
			}
			ServletActionContext.getRequest().setAttribute("anewsList", anewsList);

		}
		return SUCCESS;
	}
	
/*	//添加收藏
	@Action(value="addCollection")
	public void addCollection() {
		Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
		String cid = ServletActionContext.getRequest().getParameter("cid");
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		String tId = ServletActionContext.getRequest().getParameter("tId");
		String aId = ServletActionContext.getRequest().getParameter("aId");
		if(cid != null) {
			List<String> list = tourist.gettCNewsCollectionList();
			list.add(cid);
			tourist.settCNewsCollectionList(list);
			
		} else if(shareId != null) {
			List<String> list = tourist.gettShareCollectionList();
			list.add(shareId);
			tourist.settShareCollectionList(list);
			
		} else if(diaryId != null) {
			List<String> list = tourist.gettDiaryCollectionList();
			list.add(diaryId);
			tourist.settDiaryCollectionList(list);
			
		} else if(tId != null) {
			List<String> list = tourist.gettTNewsCollectionList();
			list.add(tId);
			tourist.settTNewsCollectionList(list);
		
		} else {
			List<String> list = tourist.gettANewsCollectionList();
			list.add(aId);
			tourist.settANewsCollectionList(list);
			
		}
		touristService.updateTourist(tourist);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//取消收藏
	@Action(value="deleteCollection")
	public void deleteCollection() {
		Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
		String cid = ServletActionContext.getRequest().getParameter("cid");
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		String tId = ServletActionContext.getRequest().getParameter("tId");
		String aId = ServletActionContext.getRequest().getParameter("aId");
		if(cid != null) {
			List<String> list = tourist.gettCNewsCollectionList();
			for (String string : list) {
				if(string.equals(cid)) {
					list.remove(string);
				}
			}
			tourist.settCNewsCollectionList(list);
			
		} else if(shareId != null) {
			List<String> list = tourist.gettShareCollectionList();
			for (String string : list) {
				if(string.equals(shareId)) {
					list.remove(string);
				}
			}
			tourist.settShareCollectionList(list);
			
		} else if(diaryId != null) {
			List<String> list = tourist.gettDiaryCollectionList();
			for (String string : list) {
				if(string.equals(diaryId)) {
					list.remove(string);
				}
			}
			tourist.settDiaryCollectionList(list);
			
		} else if(tId != null) {
			List<String> list = tourist.gettTNewsCollectionList();
			for (String string : list) {
				if(string.equals(tId)) {
					list.remove(string);
				}
			}
			tourist.settTNewsCollectionList(list);
		
		} else {
			List<String> list = tourist.gettANewsCollectionList();
			for (String string : list) {
				if(string.equals(aId)) {
					list.remove(string);
				}
			}
			tourist.settANewsCollectionList(list);
			
		}
		touristService.updateTourist(tourist);
		try {
			ServletActionContext.getResponse().getWriter().write(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//查看收藏
	@Action(value="findCollection")
	public void findCollection() {
		Tourist tourist = (Tourist) ServletActionContext.getRequest().getSession().getAttribute("existTourist");
		String cid = ServletActionContext.getRequest().getParameter("cid");
		String shareId = ServletActionContext.getRequest().getParameter("shareId");
		String diaryId = ServletActionContext.getRequest().getParameter("diaryId");
		String tId = ServletActionContext.getRequest().getParameter("tId");
		String aId = ServletActionContext.getRequest().getParameter("aId");
		if(cid != null) {
			List<String> list = tourist.gettCNewsCollectionList();
			for (String string : list) {
				if(cid.equals(string)) {
					try {
						ServletActionContext.getResponse().getWriter().write(1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		} else if(shareId != null) {
			List<String> list = tourist.gettShareCollectionList();
			for (String string : list) {
				if(shareId.equals(string)) {
					try {
						ServletActionContext.getResponse().getWriter().write(1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		} else if(diaryId != null) {
			List<String> list = tourist.gettDiaryCollectionList();
			for (String string : list) {
				if(diaryId.equals(string)) {
					try {
						ServletActionContext.getResponse().getWriter().write(1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		} else if(tId != null) {
			List<String> list = tourist.gettTNewsCollectionList();
			for (String string : list) {
				if(tId.equals(string)) {
					try {
						ServletActionContext.getResponse().getWriter().write(1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		} else {
			List<String> list = tourist.gettANewsCollectionList();
			for (String string : list) {
				if(aId.equals(string)) {
					try {
						ServletActionContext.getResponse().getWriter().write(1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			try {
				ServletActionContext.getResponse().getWriter().write(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}*/
	

}
















