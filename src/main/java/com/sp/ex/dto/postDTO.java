package com.sp.ex.dto;

public class postDTO {
	int num;
	String author;
	String title;
	String content;
	String time;
	
	public postDTO(int num, String author, String title, String content, String time) {
		super();
		this.num = num;
		this.author = author;
		this.title = title;
		this.content = content;
		this.time = time;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
