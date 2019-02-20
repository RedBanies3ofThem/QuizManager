package fr.epita.quiz.datamodel;

import java.util.List;

/** Interface for Quiz Manager's questions
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
public interface QuestionI {
	
	/** Getter for a question's number relative to the specific quiz
	 * @return (int) question number
	 */
	public int getNumber();

	/** Setter for a question's number relative to the specific quiz
	 * @param number
	 */
	public void setNumber(int number);

	/** Getter for a question's ID# as specified in the H2 database
	 * @return
	 */
	public int getId();

	/** Setter for a question's ID# as specified in the H2 database
	 * @param id (int) question id number
	 */
	public void setId(int id);
	
	/** Getter for a question's prompt
	 * @return (String) question prompt
	 */
	public String getQuestion();
	
	/** Setter for a question's prompt
	 * @param question (String) question prompt
	 */
	public void setQuestion(String question);
	
	/** Getter for the topics tagged to a given question
	 * @return (List of Strings) topics tagged to a given question
	 */
	public List<String> getTopics();

	/** Setter for the topics tagged to a given question
	 * @param topics (List of Strings) topics tagged to a given question
	 */
	public void setTopics(List<String> topics);
	
	/** Add a topic to a given question
	 * @param topic (String) topic that is only one word with no spaces
	 */
	public void addTopic(String topic);
	
	/** Getter for the difficulty of a given question
	 * @return (int) difficulty
	 */
	public int getDifficulty();
	
	/** Setter for the difficulty of a given question
	 * @param difficulty (int) difficulty
	 */
	public void setDifficulty(int difficulty);

	/** Getter to check if the student's answer is correct
	 * @return True if correct
	 */
	public Boolean getIsCorrect();

	/** Setter to check if the student's answer is correct
	 * @param isCorrect True if correct
	 */
	public void setIsCorrect(Boolean isCorrect);
	
	/** Function to check if student's answer is correct
	 * 
	 */
	public void gradeAnswer();

	/** Getter for the multiple choice question the student selected
	 * @return (int) selection number (1 to 4)
	 */
	public int getChoice();
	
	/** Setter for the multiple choice question the student selected
	 * @param choice (int) selection number (1 to 4)
	 */
	public void setChoice(int choice);
	
	/** Getter for the correct answer for a given question as specified by the H2 database
	 * @return (int) number ranging 1 to 4
	 */
	public int getAnswer();

	/** Setter for the correct answer for a given question as specified by the H2 database
	 * @param answer (int) number ranging 1 to 4
	 */
	public void setAnswer(int answer);
	
	/** Getter for the open ended answer the student provided
	 * @return (String) student's answer
	 */
	public String getResponse();

	/** Setter for the open ended answer the student provided
	 * @param response (String) student's answer
	 */
	public void setResponse(String response);
	
	/** Getter for the list of multiple choice options the student can select from
	 * @return (List of Strings) Options that the student can select during the quiz
	 */
	public List<String> getOptions();
}
