package com.sp.ex.dto;

public class Comment_AlarmDTO {
	String comment_ID;
	String post_ID;
	String author_ID;
	String writer_ID;
	String isChecked;
	String post_Title;

	public Comment_AlarmDTO(String comment_ID, String post_ID, String author_ID, String writer_ID, String isChecked,
			String post_Title) {
		super();
		this.comment_ID = comment_ID;
		this.post_ID = post_ID;
		this.author_ID = author_ID;
		this.writer_ID = writer_ID;
		this.isChecked = isChecked;
		this.post_Title = post_Title;
	}
	public String getComment_ID() {
		return comment_ID;
	}
	public void setComment_ID(String comment_ID) {
		this.comment_ID = comment_ID;
	}
	public String getPost_ID() {
		return post_ID;
	}
	public void setPost_ID(String post_ID) {
		this.post_ID = post_ID;
	}
	public String getUser_ID() {
		return author_ID;
	}
	public void setUser_ID(String author_ID) {
		this.author_ID = author_ID;
	}
	public String getWriter_ID() {
		return writer_ID;
	}
	public void setWriter_ID(String writer_ID) {
		this.writer_ID = writer_ID;
	}
	public String getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
	public String getPost_Title() {
		return post_Title;
	}
	public void setPost_Title(String post_Title) {
		this.post_Title = post_Title;
	}
	
}
