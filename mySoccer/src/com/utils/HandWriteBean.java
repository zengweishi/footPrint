package com.utils;

import java.util.List;

public class HandWriteBean {
	private long log_id;
	private int words_result_num;
	private List<Words_result> words_result;
	
	/**
	 * @return the log_id
	 */
	public long getLog_id() {
		return log_id;
	}
	/**
	 * @param log_id 
	 * log_id
	 */
	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}
	/**
	 * @return the words_result_num
	 */
	public int getWords_result_num() {
		return words_result_num;
	}
	/**
	 * @param words_result_num 
	 * words_result_num
	 */
	public void setWords_result_num(int words_result_num) {
		this.words_result_num = words_result_num;
	}
	
	/**
	 * @return the words_result
	 */
	public List<Words_result> getWords_result() {
		return words_result;
	}
	/**
	 * @param words_result 
	 * words_result
	 */
	public void setWords_result(List<Words_result> words_result) {
		this.words_result = words_result;
	}

	public static class Words_result{
		private Location location;
		private String words;
		/**
		 * @return the location
		 */
		public Location getLocation() {
			return location;
		}
		/**
		 * @param location 
		 * location
		 */
		public void setLocation(Location location) {
			this.location = location;
		}
		/**
		 * @return the words
		 */
		public String getWords() {
			return words;
		}
		/**
		 * @param words 
		 * words
		 */
		public void setWords(String words) {
			this.words = words;
		}
	}
	public static class Location{
		private int width;
		private int top;
		private int left;
		private int height;
		/**
		 * @return the width
		 */
		public int getWidth() {
			return width;
		}
		/**
		 * @param width 
		 * width
		 */
		public void setWidth(int width) {
			this.width = width;
		}
		/**
		 * @return the top
		 */
		public int getTop() {
			return top;
		}
		/**
		 * @param top 
		 * top
		 */
		public void setTop(int top) {
			this.top = top;
		}
		/**
		 * @return the left
		 */
		public int getLeft() {
			return left;
		}
		/**
		 * @param left 
		 * left
		 */
		public void setLeft(int left) {
			this.left = left;
		}
		/**
		 * @return the height
		 */
		public int getHeight() {
			return height;
		}
		/**
		 * @param height 
		 * height
		 */
		public void setHeight(int height) {
			this.height = height;
		}
	}

}
