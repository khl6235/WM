package com.intern.assignment.content;

public class ObjectiveVO {
	
	private int contentIdx;
	private String entry;
	private int cnt;
	
	
	public int getContentIdx() {
		return contentIdx;
	}
	public void setContentIdx(int contentIdx) {
		this.contentIdx = contentIdx;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		return "ObjectiveVO [contentIdx = "+contentIdx+", entry = "+entry+", cnt = "+cnt+"]";
	}

}
