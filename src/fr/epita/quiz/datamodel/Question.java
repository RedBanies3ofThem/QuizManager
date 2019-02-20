package fr.epita.quiz.datamodel;


import java.util.LinkedList;
import java.util.List;

/** Base class for quiz questions. Extended for multiple choice, open-ended, and associative questions
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
public abstract class Question implements QuestionI {
	
	protected String question;
	protected List<String> topics;
	protected int difficulty;
	protected Boolean isCorrect;
	protected int number;
	protected int id;

	/** Default constructor
	 * 
	 */
	public Question() {
		this.isCorrect = false;
		this.topics = new LinkedList<String>();
	}
	

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getNumber()
	 */
	public int getNumber() {
		return number;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setNumber(int)
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getId()
	 */
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setId(int)
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getQuestion()
	 */
	public String getQuestion() {
		return question;
	}
	
	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setQuestion(java.lang.String)
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	
	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getTopics()
	 */
	public List<String> getTopics() {
		return this.topics;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setTopics(java.util.List)
	 */
	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	
	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#addTopic(java.lang.String)
	 */
	public void addTopic(String topic) {
		this.topics.add(topic);
	}
	
	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getDifficulty()
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setDifficulty(int)
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#getIsCorrect()
	 */
	public Boolean getIsCorrect() {
		return isCorrect;
	}

	/* (non-Javadoc)
	 * @see fr.epita.quiz.datamodel.QuestionI#setIsCorrect(java.lang.Boolean)
	 */
	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	

}
