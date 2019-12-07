package com.sp.ex.dto;

public class PagingDTO {
	private int range=5;			//페이지 범위 개수
	private int curPage=1;			//현재 페이지
	private int startPage=1;		//현재 시작페이지
	private int endPage;			//현재 마지막페이지
	private int postCount;			//전체 포스트 개수
	private int pageCount;			//전체 페이지 개수
	private int listSize = 5;		//한 페이지당 포스트 개수
	private boolean prev=true;		//이전 페이지 있는가
	private boolean next=true;		//다음 페이지 있는가
	private int startPost=1;		//이 페이지의 시작 포스트 번호
	private int endPost;			//이 페이지의 끝 포스트 번호
	private String query;			//검색 쿼리문
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postNum) {
		this.postCount = postNum;
	}
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int getStartPost() {
		return startPost;
	}
	public void setStartPost(int startPost) {
		this.startPost = startPost;
	}
	public int getEndPost() {
		return endPost;
	}
	public void setEndPost(int endPost) {
		this.endPost = endPost;
	}
	public void setPageInfo(int curPage, int postCount,String query) {

		this.curPage=curPage;
		this.postCount=postCount;
		this.query=query;
		pageCount=(int)Math.ceil((double)postCount/listSize);
		startPage=(curPage-1)/range*range+1;
		endPage=startPage+range;
		if(startPage==1) {
			prev=false;
		}
		if(pageCount<=curPage) {
			curPage=pageCount;
		}
		if(pageCount<=endPage) {
			next=false;
			endPage=pageCount+1;
		}
		startPost=(curPage-1)*listSize+1;
		endPost=(curPage*listSize);
		System.out.println(startPost+" 부터 "+endPost+" 까지");
		System.out.println("선택 페이지 = "+curPage);
		System.out.println("시작 페이지 = "+startPage);
		System.out.println("끝 페이지 = "+endPage);
		System.out.println("전체 페이지 = "+pageCount);
		System.out.println("이전버튼 = "+prev);
		System.out.println("다음버튼 = "+next);
	}
	
}
