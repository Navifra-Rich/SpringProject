package com.sp.ex.dto;

public class MemberDTO {
	private String id;
	private String name;
	private String password;
	private String token;
	private String location;
	private String category;

	public MemberDTO() {
		
	}
	
	public MemberDTO(String id, String name, String password, String token, String location, String category) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.token = token;
		this.location = location;
		this.category = category;
	}

	public MemberDTO(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
}
