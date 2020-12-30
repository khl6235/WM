package com.intern.assignment.content;

public class ContentVO {
	
	private int contentIdx;
	private String question;
	private String description;
	private int type;
	
	
	public int getContentIdx() {
		return contentIdx;
	}
	public void setContentIdx(int contentIdx) {
		this.contentIdx = contentIdx;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "ContentVO [contentIdx = "+contentIdx+", question = "+question+", description = "+description+", type = "+type+"]";
	}

}
