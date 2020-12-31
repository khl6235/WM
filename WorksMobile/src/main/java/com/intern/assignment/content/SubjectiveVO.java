package com.intern.assignment.content;

public class SubjectiveVO {
	
	private int answerIdx;
	private int contentIdx;
	private String answer;
	
	
	public int getAnswerIdx() {
		return answerIdx;
	}
	public void setAnswerIdx(int answerIdx) {
		this.answerIdx = answerIdx;
	}
	public int getContentIdx() {
		return contentIdx;
	}
	public void setContentIdx(int contentIdx) {
		this.contentIdx = contentIdx;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "SubjectiveVO [answerIdx = "+ answerIdx+", contentIdx = "+contentIdx+", answer = "+answer+"]";
	}
}
