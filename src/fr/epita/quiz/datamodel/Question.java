package fr.epita.quiz.datamodel;

import java.sql.Array;

public class Question {
	
	protected String question;
	protected Array topics;
	protected int difficulty;
	protected Boolean isCorrect;
	protected int number;
	protected long id;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Question() {
		this.isCorrect = false;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Array getTopics() {
		return topics;
	}
	
	public void setTopics(Array topics) {
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
		System.out.println("Need to implement method: gradeAnswer()");
	}

}
