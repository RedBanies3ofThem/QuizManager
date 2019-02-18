package fr.epita.quiz.datamodel;

import java.util.HashMap;
import java.util.Map;

public class Associative extends Question {

	private int answer;
	private int choice;
	private Map<Integer, String> choices;
	
	public Associative() {
		super();
		this.setChoices(new HashMap<Integer, String>());		
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public Map<Integer, String> getChoices() {
		return choices;
	}

	public void setChoices(Map<Integer, String> choices) {
		this.choices = choices;
	}
}
