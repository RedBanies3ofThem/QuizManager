package fr.epita.quiz.datamodel;

import java.util.List;

public class Student {

	private String name;
	private String id;
	private List<MCQAnswer> mCQAnswers;
	private List<Answer> answers;
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", getName()=" + getName() + ", getId()=" + getId()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public List<MCQAnswer> getmCQAnswers() {
		return mCQAnswers;
	}

	public void setmCQAnswers(List<MCQAnswer> mCQAnswers) {
		this.mCQAnswers = mCQAnswers;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
}
