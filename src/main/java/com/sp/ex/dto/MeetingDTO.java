package com.sp.ex.dto;

public class MeetingDTO {
	int post_ID;
	String organizer_ID;
	String summary;
	int max_attendee;
	int cur_attendee;
	String location;
	String category;
	
	public MeetingDTO(int post_ID, String organizer_ID, String summary, int max_attendee, int cur_attendee,
			String location, String category) {
		super();
		this.post_ID = post_ID;
		this.organizer_ID = organizer_ID;
		this.summary = summary;
		this.max_attendee = max_attendee;
		this.cur_attendee = cur_attendee;
		this.location = location;
		this.category = category;
	}
	public int getPost_ID() {
		return post_ID;
	}
	public void setPost_ID(int post_ID) {
		this.post_ID = post_ID;
	}
	public String getOrganizer_ID() {
		return organizer_ID;
	}
	public void setOrganizer_ID(String organizer_ID) {
		this.organizer_ID = organizer_ID;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getMax_attendee() {
		return max_attendee;
	}
	public void setMax_attendee(int max_attendee) {
		this.max_attendee = max_attendee;
	}
	public int getCur_attendee() {
		return cur_attendee;
	}
	public void setCur_attendee(int cur_attendee) {
		this.cur_attendee = cur_attendee;
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
