package com.utils;

import com.domain.MyResult;

public class ResultUtil {
	public static MyResult success (String[] images) {
		MyResult myResult = new MyResult();
		myResult.setErrno(0);
		myResult.setData(images);
		return myResult;	
	}
	public static MyResult success() {
		return success(null);
	}
}
