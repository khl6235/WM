package com.intern.assignment.user;

public class UserVO {
	
	private int userIdx;
	private String id;
	private String password;
	
	
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

	@Override
	public String toString() {
		return "UserVO [userIdx = "+userIdx+", id = "+id+", password = "+password+"]";
	}
	
}
