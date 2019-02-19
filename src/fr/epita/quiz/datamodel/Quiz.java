package fr.epita.quiz.datamodel;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import fr.epita.quiz.services.QuestionJDBCDAO;

public class Quiz {

	private String title;
	private List<MultipleChoice> availableMCQuestions;
	private List<MultipleChoice> usedMCQuestions;
	private List<String> topics;
	private int grade;
	private double progress;
	private int totalQuestions;

	private Student student;
	private QuestionJDBCDAO dao;
	protected Random random;

	public Quiz(Student student, List<String> topics, int totalQuestions) {
		this.student = student;
		this.topics = topics;
		this.totalQuestions = totalQuestions;
		this.dao = new QuestionJDBCDAO();
		this.random = new Random();
		String temp = "";
		for (String topic : this.topics) {
			temp += " " + topic;
		}
		this.title = this.student.getName() + " |" + temp;
		this.usedMCQuestions = new LinkedList<MultipleChoice>();
	}
	
	public void loadNewQuiz() {
		this.grade = 0;
		this.progress = 0;
		this.availableMCQuestions = this.dao.search(this.topics);
		this.usedMCQuestions.clear();
	}
	
	public MultipleChoice getNewQuestion(int difficulty) {
		// extract a specified difficulty
		List<MultipleChoice> temp = this.availableMCQuestions.stream()
														.filter(q -> q.difficulty == difficulty)
														.collect(Collectors.toList());
		int size = temp.size();
		if (size < 1) {
			System.out.println("getNewQuestion --> ran out of questions");
			return null;
		}
		MultipleChoice newQuestion = temp.remove(this.random.nextInt(size));
		if (!this.availableMCQuestions.remove(newQuestion)) {
			System.out.println("getNewQuestion --> Warning! Question not found in available list.");
		}
		return newQuestion;
	}
	
	public void processNewQuestion(MultipleChoice question) {
		question.setNumber(this.usedMCQuestions.size() + 1);
		question.gradeAnswer();
		this.usedMCQuestions.add(question);
		
		if (question.getIsCorrect()) {
			this.grade += 1;
		}
		this.progress = (double)this.usedMCQuestions.size() / (double)this.totalQuestions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public List<MultipleChoice> getMultipleChoice() {
		return availableMCQuestions;
	}

	public void setMultipleChoice(List<MultipleChoice> multipleChoice) {
		this.availableMCQuestions = multipleChoice;
	}

	public List<MultipleChoice> getUsedMCQuestions() {
		return usedMCQuestions;
	}

	public void setUsedMCQuestions(List<MultipleChoice> usedMCQuestions) {
		this.usedMCQuestions = usedMCQuestions;
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public Random getRandom() {
		return random;
	}
}
