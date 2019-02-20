package fr.epita.quiz.datamodel;

import java.util.LinkedList;
import java.util.List;

public class MultipleChoice extends Question {
	
	private int answer;
	private int choice;
	private List<String> options;
	
	public MultipleChoice() {
		super();
		this.setOptions(new LinkedList<String>());
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}
	
	public void addOption(String option) {
		this.options.add(option);
	}
	
	@Override
	public String toString() {
		return "MultipleChoice [answer=" + answer + ", choice=" + choice + ", options=" + options + ", question="
				+ question + ", topics=" + topics + ", difficulty=" + difficulty + ", isCorrect=" + isCorrect
				+ ", number=" + number + ", id=" + id + "]";
	}

	@Override
	public void setChoice(int choice) {
		this.choice = choice;
	}

	@Override
	public int getChoice() {
		return this.choice;
	}

	@Override
	public int getAnswer() {
		return answer;
	}

	@Override
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	@Override
	public void gradeAnswer() {
		if (this.choice == this.answer) {
			System.out.println("Question #"+ this.number + " : Bien fait!");
			this.isCorrect = true;
		} else {
			System.out.println("Question #"+ this.number + " : Wrong answer.");
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

}
