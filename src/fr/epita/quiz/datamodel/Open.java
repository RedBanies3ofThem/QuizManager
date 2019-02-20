package fr.epita.quiz.datamodel;

import java.util.List;

/**
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
public class Open extends Question {

	private String response;
	
	/** Default constructor
	 * 
	 */
	public Open() {
		super();
		this.setResponse("");
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getResponse()
	 */
	@Override
	public String getResponse() {
		return response;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setResponse(java.lang.String)
	 */
	@Override
	public void setResponse(String response) {
		this.response = response;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getChoice()
	 */
	@Override
	public int getChoice() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setChoice(int)
	 */
	@Override
	public void setChoice(int choice) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getAnswer()
	 */
	@Override
	public int getAnswer() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setAnswer(int)
	 */
	@Override
	public void setAnswer(int answer) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#gradeAnswer()
	 */
	@Override
	public void gradeAnswer() {
		if (this.getResponse().equals("")) {
			System.out.println("Question #"+ this.number + " : Wrong answer.");
			this.setIsCorrect(false);
		}
		else {
			System.out.println("Question #"+ this.number + " : Bien fait!");
			this.setIsCorrect(true);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Open [response=" + response + ", question=" + question + ", topics=" + topics + ", difficulty="
				+ difficulty + ", isCorrect=" + isCorrect + ", number=" + number + ", id=" + id + "]";
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
