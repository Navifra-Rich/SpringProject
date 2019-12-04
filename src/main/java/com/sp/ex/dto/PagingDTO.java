package com.sp.ex.dto;

public class PagingDTO {
	private int range=50;		//페이지 범위 개수
	private int curPage=1;	//현재 페이지
	private int startPage=1;	//현재 시작페이지
	private int endPage;	//현재 마지막페이지
	private int postCount;	//전체 포스트 개수
	private int pageCount;	//전체 페이지 개수
	private int listSize = 10;	//한 페이지당 포스트 개수
	private boolean prev=true;	//이전 페이지 있는가
	private boolean next=true;	//다음 페이지 있는가
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
	
	public void setPageInfo(int curPage, int postCount) {
		this.curPage=curPage;
		this.postCount=postCount;
		
		pageCount=(int)Math.ceil((double)postCount/listSize);
		startPage=this.curPage*(range-1)/range+1;
		endPage=startPage+range;
		if(startPage==1) {
			prev=false;
		}
		if(pageCount<curPage+range) {
			next=false;
		}
	}
	
}
