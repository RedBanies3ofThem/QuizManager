package fr.epita.quiz.datamodel;

import java.util.List;

/**
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
public class Open extends Question {

	private String response;
	
	public Open() {
		super();
		this.setResponse("");
	}

	@Override
	public String getResponse() {
		return response;
	}

	@Override
	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public int getChoice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setChoice(int choice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAnswer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAnswer(int answer) {
		// TODO Auto-generated method stub
		
	}

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
	
	@Override
	public String toString() {
		return "Open [response=" + response + ", question=" + question + ", topics=" + topics + ", difficulty="
				+ difficulty + ", isCorrect=" + isCorrect + ", number=" + number + ", id=" + id + "]";
	}

	@Override
	public List<String> getOptions() {
		// TODO Auto-generated method stub
		return null;
	}

}
