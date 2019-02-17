package fr.epita.quiz.datamodel;

public class Answer {
	
	private String text;

	@Override
	public String toString() {
		return "Answer [text=" + text + ", getText()=" + getText() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
