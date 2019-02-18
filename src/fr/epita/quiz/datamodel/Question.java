package fr.epita.quiz.datamodel;

import java.sql.Array;
import java.util.LinkedList;
import java.util.List;

public class Question {
	
	protected String question;
	protected List<String> topics;
	protected int difficulty;
	protected Boolean isCorrect;
	protected int number;
	protected int id;

	public Question() {
		this.isCorrect = false;
		this.topics = new LinkedList<String>();
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public List<String> getTopics() {
		return this.topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	
	public void addTopic(String topic) {
		this.topics.add(topic);
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
