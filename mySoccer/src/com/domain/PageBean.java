package com.domain;

import java.util.List;

/**
 * 分页显示的一个JavaBean
 * @author 45度炸
 *
 */
public class PageBean<T> {
	private Integer currPage; //当前页数
	private Integer pageSize; //每页显示个数
	private Integer totalCount; //总数
	private Integer totalPage; //总页数
	private List<T> list; //每页显示的一个集合
	
	public PageBean() {
		
	}
	
	
	public PageBean(Integer currPage, Integer pageSize, Integer totalCount,
			Integer totalPage, List<T> list) {
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.list = list;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
