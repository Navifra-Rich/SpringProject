package com.sp.ex.dto;

public class postDTO {
	int num;
	String author;
	String title;
	String content;
	String time;
	int comCount;
	
	String startDay;
	String endDay;
	String startTime;
	String endTime;
	
	String location;
	String category;
	

	
	@Override
	public String toString() {
		return "postDTO [num=" + num + ", author=" + author + ", title=" + title + ", content=" + content + ", time="
				+ time + ", comCount=" + comCount + ", startDay=" + startDay + ", endDay=" + endDay + ", startTime="
				+ startTime + ", endTime=" + endTime + ", location=" + location + ", category=" + category + "]";
	}

	public postDTO(int num, String author, String title, String content, String time, int comCount, String startDay,
			String endDay, String startTime, String endTime, String location, String category) {
		super();
		this.num = num;
		this.author = author;
		this.title = title;
		this.content = content;
		this.time = time;
		this.comCount = comCount;
		this.startDay = startDay;
		this.endDay = endDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.category = category;
	}
	public postDTO(int num, String author, String title, String content, String time, int comCount,String location, String category) {
		super();
		this.num=num;
		this.author = author;
		this.title = title;
		this.content = content;
		this.time = time;
		this.comCount=comCount;
		this.location=location;
		this.category=category;
	}
	public postDTO(int num, String author, String title, String content, String time, int comCount) {
		super();
		this.num=num;
		this.author = author;
		this.title = title;
		this.content = content;
		this.time = time;
		this.comCount=comCount;
	}
	public postDTO(String userID) {
		super();
		this.author=userID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public int getComCount() {
		return comCount;
	}
	public void setComCount(int comCount) {
		this.comCount = comCount;
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
