package fr.epita.quiz.datamodel;

import java.util.List;

public class Quiz {

	private String title;
	private List<Answer> answers;
	private List<MCQAnswer> mCQAnswers;

	@Override
	public String toString() {
		return "Quiz [title=" + title + ", getTitle()=" + getTitle() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<MCQAnswer> getmCQAnswers() {
		return mCQAnswers;
	}

	public void setmCQAnswers(List<MCQAnswer> mCQAnswers) {
		this.mCQAnswers = mCQAnswers;
	}
}
