package com.intern.assignment.form;

import java.util.Date;

public class FormVO {
	
	private int formIdx;
	private int userIdx; //writer
	private String title;
	private Date createdAt;
	
	
	public int getFormIdx() {
		return formIdx;
	}

	public void setFormIdx(int formIdx) {
		this.formIdx = formIdx;
	}

	public int getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "FormVO [formIdx = "+formIdx+", userIdx = "+userIdx+", title = "+title+", createdAt = "+createdAt+"]";
	}

}
