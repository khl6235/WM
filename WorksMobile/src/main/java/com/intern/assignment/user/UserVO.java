package com.intern.assignment.user;

public class UserVO {
	
	private int userIdx;
	private String id;
	private String password;
	private String passwordCheck;
	
	
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	
	@Override
	public String toString() {
		return "UserVO [userIdx = "+userIdx+", id = "+id+", password = "+password+"]";
	}
	
}
