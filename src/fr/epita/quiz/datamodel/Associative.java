package fr.epita.quiz.datamodel;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** Associative questions class, extends base class Question
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
public class Associative extends Question {

	private int answer;
	private int choice;
	private Map<Integer, String> choices;
	
	/** Default constructor
	 * 
	 */
	public Associative() {
		super();
		this.setChoices(new HashMap<Integer, String>());		
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getAnswer()
	 */
	public int getAnswer() {
		return answer;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setAnswer(int)
	 */
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getChoice()
	 */
	public int getChoice() {
		return choice;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setChoice(int)
	 */
	public void setChoice(int choice) {
		this.choice = choice;
	}

	/**
	 * @return
	 */
	public Map<Integer, String> getChoices() {
		return choices;
	}

	/**
	 * @param choices
	 */
	public void setChoices(Map<Integer, String> choices) {
		this.choices = choices;
	}
	
	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#gradeAnswer()
	 */
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

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getOptions()
	 */
	@Override
	public List<String> getOptions() {
		// TODO Auto-generated method stub
		return null;
	}
}
