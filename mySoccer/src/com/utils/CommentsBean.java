package com.utils;

import java.util.List;

/**
 * 评论审核返回的JSON封装
 * @author 45度炸
 *
 */
public class CommentsBean {
	private long log_id; //
	private BackResult result; //封装时命名一定要正确
	public CommentsBean() {
		
	}
	

	
	public CommentsBean(long log_id, BackResult result) {
		super();
		this.log_id = log_id;
		this.result = result;
	}



	public BackResult getResult() {
		return result;
	}



	public void setResult(BackResult result) {
		this.result = result;
	}



	public long getLog_id() {
		return log_id;
	}

	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}

	
	public static class BackResult {
		private Integer spam;
		private List<ResultItem> reject;
		private List<ResultItem> review;
		private List<ResultItem> pass;
		public BackResult() {
			
		}
		public BackResult(Integer spam, List<ResultItem> reject,
				List<ResultItem> review, List<ResultItem> pass) {
			super();
			this.spam = spam;
			this.reject = reject;
			this.review = review;
			this.pass = pass;
		}
		public Integer getSpam() {
			return spam;
		}
		public void setSpam(Integer spam) {
			this.spam = spam;
		}
		public List<ResultItem> getReject() {
			return reject;
		}
		public void setReject(List<ResultItem> reject) {
			this.reject = reject;
		}
		public List<ResultItem> getReview() {
			return review;
		}
		public void setReview(List<ResultItem> review) {
			this.review = review;
		}
		public List<ResultItem> getPass() {
			return pass;
		}
		public void setPass(List<ResultItem> pass) {
			this.pass = pass;
		}
		
	}
	
	public static class ResultItem {
		private float score;
		private Integer label;
		private List<String> hit;
		public ResultItem() {
			
		}
		public ResultItem(float score, Integer label, List<String> hit) {
			super();
			this.score = score;
			this.label = label;
			this.hit = hit;
		}
		public float getScore() {
			return score;
		}
		public void setScore(float score) {
			this.score = score;
		}
		public Integer getLabel() {
			return label;
		}
		public void setLabel(Integer label) {
			this.label = label;
		}
		public List<String> getHit() {
			return hit;
		}
		public void setHit(List<String> hit) {
			this.hit = hit;
		}
	
	}
}
