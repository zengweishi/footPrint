package com.domain;

import java.io.Serializable;

import com.utils.ResultUtil;

public class MyResult implements Serializable {
	private Integer errno; 
	private String[] data; //Õº∆¨µÿ÷∑
	public MyResult() {}
	public Integer getErrno() {
		return errno;
	}
	public void setErrno(Integer errno) {
		this.errno = errno;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] images) {
		this.data = images;
	}
}
