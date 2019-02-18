package fr.epita.quiz.datamodel;

import java.util.Arrays;
import java.util.List;

public class Question {
	
	protected String question;
	protected String[] topics;
	protected int difficulty;
	protected Boolean isCorrect;
	protected int number;
	
	public Question() {
		this.isCorrect = false;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String[] getTopics() {
		return topics;
	}
	
	public void setTopics(String[] topics) {
		this.topics = topics;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	public void gradeAnswer() {
		
	}

}
