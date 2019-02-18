package fr.epita.quiz.datamodel;

import java.util.HashMap;
import java.util.Map;

public class MultipleChoice extends Question {
	
	private int answer;
	private int choice;
	private Map<Integer, String> choices;
	
	public MultipleChoice() {
		super();
		this.setChoices(new HashMap<Integer, String>());
	}

	
	public int getChoice() {
		return choice;
	}
	
	public void setChoice(int choice) {
		this.choice = choice;
	}
	
	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}
	
	public Map<Integer, String> getChoices() {
		return choices;
	}

	public void setChoices(Map<Integer, String> choices) {
		this.choices = choices;
	}
	
	@Override
	public void gradeAnswer() {
		if (this.choice == this.answer) {
			System.out.println("Bien fait! The correct answer is " + this.answer);
			this.isCorrect = true;
		} else {
			System.out.println("Wrong answer for question #" + this.number);
			this.isCorrect = false;
		}
	}

}
