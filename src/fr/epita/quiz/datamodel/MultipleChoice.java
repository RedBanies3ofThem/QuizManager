package fr.epita.quiz.datamodel;

import java.util.LinkedList;
import java.util.List;

/** Multiple choice questions class, extends base class Question
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
public class MultipleChoice extends Question {
	
	private int answer;
	private int choice;
	private List<String> options;
	
	/** Default constructor
	 * 
	 */
	public MultipleChoice() {
		super();
		this.setOptions(new LinkedList<String>());
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getOptions()
	 */
	public List<String> getOptions() {
		return options;
	}

	/**
	 * @param options
	 */
	public void setOptions(List<String> options) {
		this.options = options;
	}
	
	/**
	 * @param option
	 */
	public void addOption(String option) {
		this.options.add(option);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MultipleChoice [answer=" + answer + ", choice=" + choice + ", options=" + options + ", question="
				+ question + ", topics=" + topics + ", difficulty=" + difficulty + ", isCorrect=" + isCorrect
				+ ", number=" + number + ", id=" + id + "]";
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setChoice(int)
	 */
	@Override
	public void setChoice(int choice) {
		this.choice = choice;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getChoice()
	 */
	@Override
	public int getChoice() {
		return this.choice;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getAnswer()
	 */
	@Override
	public int getAnswer() {
		return answer;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setAnswer(int)
	 */
	@Override
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#gradeAnswer()
	 */
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

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getResponse()
	 */
	@Override
	public String getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setResponse(java.lang.String)
	 */
	@Override
	public void setResponse(String response) {
		// TODO Auto-generated method stub
		
	}

}
