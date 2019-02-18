package fr.epita.quiz.datamodel;

public class Open extends Question {
	
	private String response;
	
	public Open() {
		this.setResponse("");
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
