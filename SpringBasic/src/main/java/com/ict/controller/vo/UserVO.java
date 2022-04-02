package com.ict.controller.vo;

// import lombok.Data;

// 가상의 "회원 관리용 VO"

// lombok의 @Data는 해당 VO의 setter, getter, 생성자, toString을 자동생성해줍니다.
// 단, lombok을 사용하기 위해서는 1. lombok설치 -> 2. pom.xml에 lombok관련 세팅을 해야합니다.
// @Data
public class UserVO {
	
	// 유저 테이블 컬럼 5개에 대응하는 VO
	private int userNum;
	private String userId;
	private String userPw;
	private String userName;
	private int userAge;
	
	
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	@Override
	public String toString() {
		return "UserVO [userNum=" + userNum + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userAge=" + userAge + "]";
	}
	public UserVO(int userNum, String userId, String userPw, String userName, int userAge) {
		super();
		this.userNum = userNum;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userAge = userAge;
	}
	
	
	
}
