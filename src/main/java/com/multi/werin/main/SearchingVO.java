package com.multi.werin.main;

public class SearchingVO {

	private int start;
    private int end;
    private int page;		// 현재 페이지 번호
    private String searching;
    
	public String getSearching() {
		return searching;
	}

	public void setSearching(String searching) {
		this.searching = searching;
	}

	public void setStartEnd() {
    	start = 1+(page-1)*10;
    	end = page*10;
    }

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "SearchingVO [start=" + start + ", end=" + end + ", page=" + page + ", searching=" + searching + "]";
	}
		
}
