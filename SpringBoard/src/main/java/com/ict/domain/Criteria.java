package com.ict.domain;

import lombok.Data;

@Data
public class Criteria {
	private int pageNum = 1;
	private int number = 10;
	
	public int getPageStart() {
		return(this.pageNum - 1) * number;
	}
	
	public int getPageEnd() {
		return(this.pageNum * number);
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", number=" + number + "]";
	}

	public Criteria(int pageNum, int number) {
		super();
		this.pageNum = pageNum;
		this.number = number;
	}
	public Criteria() {
		// TODO Auto-generated constructor stub
	}
}