package fr.epita.quiz.datamodel;

import java.util.List;

public class MCQQuestion extends Question{

	private List<MCQChoice> mCQChoices;

	public List<MCQChoice> getmCQChoices() {
		return mCQChoices;
	}

	public void setmCQChoices(List<MCQChoice> mCQChoices) {
		this.mCQChoices = mCQChoices;
	}
	
}
