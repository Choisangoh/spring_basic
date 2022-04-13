package com.ict.domain;

import lombok.Data;

@Data
public class SearchCriteria extends Criteria{
	
	private String searchType;
	private String keyword;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
	public SearchCriteria(int pageNum, int number, String searchType, String keyword) {
		super(pageNum, number);
		this.searchType = searchType;
		this.keyword = keyword;
	}
	public SearchCriteria() {
		
	}	
}
