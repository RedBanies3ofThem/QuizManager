package fr.epita.quiz.datamodel;

import java.util.List;

/**
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
public interface QuestionI {
	
	public int getNumber();

	public void setNumber(int number);

	public int getId();

	public void setId(int id);
	
	public String getQuestion();
	
	public void setQuestion(String question);
	
	public List<String> getTopics();

	public void setTopics(List<String> topics);
	
	public void addTopic(String topic);
	
	public int getDifficulty();
	
	public void setDifficulty(int difficulty);

	public Boolean getIsCorrect();

	public void setIsCorrect(Boolean isCorrect);
	
	public void gradeAnswer();

	public int getChoice();
	
	public void setChoice(int choice);
	
	public int getAnswer();

	public void setAnswer(int answer);
	
	public String getResponse();

	public void setResponse(String response);
	
	public List<String> getOptions();
}
