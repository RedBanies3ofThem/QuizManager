package fr.epita.quiz.datamodel;

import java.util.List;

public class MCQChoice {
	
	private String choice;
	private Boolean valid;
	private List<MCQAnswer> mCQAnswers;

	@Override
	public String toString() {
		return "MCQChoice [choice=" + choice + ", valid=" + valid + ", getChoice()=" + getChoice() + ", getValid()="
				+ getValid() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	public String getChoice() {
		return choice;
	}
	
	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	public Boolean getValid() {
		return valid;
	}
	
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public List<MCQAnswer> getmCQAnswers() {
		return mCQAnswers;
	}

	public void setmCQAnswers(List<MCQAnswer> mCQAnswers) {
		this.mCQAnswers = mCQAnswers;
	}
}
