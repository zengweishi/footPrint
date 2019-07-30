package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IPlayerDao;
import com.dao.ITouristDao;
import com.domain.CollectionItem;
import com.domain.Diary;
import com.domain.PageBean;
import com.domain.TNews;
import com.domain.Tourist;
import com.service.ITouristService;

@Service("touristService")
@Transactional
public class TouristServiceImpl implements ITouristService {
	@Autowired
	@Qualifier("touristDao")
	private ITouristDao touristDao;

	//游客注册
	@Override
	public void touristRegist(Tourist tourist) {
		// TODO Auto-generated method stub
		touristDao.touristRegist(tourist);
	}

	//游客登录
	@Override
	public Tourist touristLogin(Tourist tourist) {
		// TODO Auto-generated method stub
		return touristDao.touristLogin(tourist);
	}

	//创建新的游客文章
	@Override
	public void createTNews(TNews tnews) {
		// TODO Auto-generated method stub
		touristDao.createTNews(tnews);
	}

	//分页寻找游客文章
	@Override
	public PageBean<TNews> findOwnTNews(Integer currPage, Tourist tourist) {
		// TODO Auto-generated method stub
		PageBean<TNews> pageBean = new PageBean<TNews>();
		// 设置参数：
		// 设置当前页数：
		pageBean.setCurrPage(currPage);
		// 设置每页显示的记录数:
		Integer pageSize = 20;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = touristDao.findTotalTNews(tourist);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (currPage - 1) * pageSize;
		List<TNews> list = touristDao.findOwnTNews(tourist,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//查找文章
	@Override
	public TNews findTNewsArticle(String tId) {
		// TODO Auto-generated method stub
		return touristDao.findTNewsArticle(tId);
	}

	//删除文章
	@Override
	public void deleteTNewsById(String tId) {
		// TODO Auto-generated method stub
		touristDao.deleteTNewsById(tId);
	}

	//修改文章内容
	@Override
	public void updateTNews(TNews tnews) {
		// TODO Auto-generated method stub
		touristDao.updateTNews(tnews);
	}

	//寻找游客
	@Override
	public Tourist findTourist(String gettId) {
		// TODO Auto-generated method stub
		return touristDao.findTourist(gettId);
	}

	//修改游客信息
	@Override
	public void updateTourist(Tourist tourist) {
		// TODO Auto-generated method stub
		touristDao.updateTourist(tourist);
	}

	//查找用户文章显示到主页上
	@Override
	public List<TNews> findTNewsForInfo(Tourist tourist) {
		// TODO Auto-generated method stub
		return touristDao.findTNewsForInfo(tourist);
	}

	//添加收藏
	@Override
	public void addCollection(CollectionItem collectionItem) {
		// TODO Auto-generated method stub
		touristDao.addCollection(collectionItem);
	}

	//取消收藏
	@Override
	public void deleteCollection(int i, CollectionItem collectionItem) {
		// TODO Auto-generated method stub
		touristDao.deleteCollection(i,collectionItem);
	}

	@Override
	public CollectionItem findCollection(int i, CollectionItem collectionItem) {
		// TODO Auto-generated method stub
		return touristDao.findCollection(i,collectionItem);
	}

	@Override
	public List<CollectionItem> findAllCollectionByType(int i, Tourist tourist) {
		// TODO Auto-generated method stub
		return touristDao.findAllCollectionByType(i,tourist);
	}

	@Override
	public Tourist findTouristByNumber(String touristEmail) {
		// TODO Auto-generated method stub
		return touristDao.findTouristByNumber(touristEmail);
	}

}
