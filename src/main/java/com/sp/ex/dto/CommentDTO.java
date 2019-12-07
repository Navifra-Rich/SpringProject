package com.sp.ex.dto;

public class CommentDTO {
	int idx;
	String id;
	String content;
	String time;
	String postNum;
	
	public CommentDTO(String id, String content, String time, String postNum) {
		super();
		this.id = id;
		this.content = content;
		this.time = time;
		this.postNum = postNum;
	}
	public String getpostNum() {
		return postNum;
	}
	public void setpostNum(String postNum) {
		this.postNum = postNum;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
