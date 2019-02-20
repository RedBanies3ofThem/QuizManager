package fr.epita.quiz.datamodel;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
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

	@Override
	public String getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResponse(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getOptions() {
		// TODO Auto-generated method stub
		return null;
	}
}
