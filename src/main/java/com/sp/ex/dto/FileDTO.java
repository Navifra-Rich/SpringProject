package com.sp.ex.dto;

public class FileDTO {
	String postID;
	String directory;
	String name;
	
	public FileDTO(String postID, String directory, String name) {
		super();
		this.postID = postID;
		this.directory = directory;
		this.name = name;
	}
	public FileDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getPostID() {
		return postID;
	}
	public void setPostID(String postID) {
		this.postID = postID;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
