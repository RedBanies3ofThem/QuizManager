package fr.epita.quiz.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import fr.epita.quiz.datamodel.MultipleChoice;
import fr.epita.quiz.datamodel.Open;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Student;

public class Quiz {

	private String title;
	private List<Question> availableQuestions;
	private List<Question> usedQuestions;
	
	private List<String> topics;
	private int grade;
	private double progress;
	private int totalQuestions;
	private Boolean includeOpenQuestions;

	private Student student;
	private QuestionJDBCDAO dao;
	protected Random random;

	public Quiz(Student student, List<String> topics, int totalQuestions, Boolean includeOpenQuestions) {
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
		this.usedQuestions = new LinkedList<Question>();
		this.availableQuestions = new LinkedList<Question>();
		this.includeOpenQuestions = includeOpenQuestions;
	}
	
	public void loadNewQuiz() {
		this.grade = 0;
		this.progress = 0;
		
		List<MultipleChoice> mcList = this.dao.search(this.topics);
		for (MultipleChoice q : mcList) {
			this.availableQuestions.add(q);
		}
		
		if (this.includeOpenQuestions) {
			
			List<Open> oqList = this.dao.searchOpen(this.topics);
			for (Open q : oqList) {
				this.availableQuestions.add(q);
			}
		}
		
		this.usedQuestions.clear();
	}
	
	public Question getNewQuestion(int difficulty) {
		// extract a specified difficulty
		List<Question> temp = this.availableQuestions.stream()
														.filter(q -> q.getDifficulty() == difficulty)
														.collect(Collectors.toList());
		int size = temp.size();
		if (size < 1) {
			System.out.println("getNewQuestion --> ran out of questions");
			return null;
		}
		Question newQuestion = temp.remove(this.random.nextInt(size));
		if (!this.availableQuestions.remove(newQuestion)) {
			System.out.println("getNewQuestion --> Warning! Question not found in available list.");
		}
		return newQuestion;
	}
	
	public Question getNewQuestion() {
		// extract a specified difficulty
		List<Question> temp = this.availableQuestions.stream().collect(Collectors.toList());
		int size = temp.size();
		if (size < 1) {
			System.out.println("getNewQuestion --> ran out of questions");
			return null;
		}
		Question newQuestion = temp.remove(this.random.nextInt(size));
		if (!this.availableQuestions.remove(newQuestion)) {
			System.out.println("getNewQuestion --> Warning! Question not found in available list.");
		}
		return newQuestion;
	}
	
	public void processNewQuestion(Question question) {
		question.setNumber(this.usedQuestions.size() + 1);
		question.gradeAnswer();
		this.usedQuestions.add(question);
		
		if (question.getIsCorrect()) {
			this.grade += 1;
		}
		this.progress = (double)this.usedQuestions.size() / (double)this.totalQuestions;
		
		System.out.println("this.progress is now : " + this.progress);
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

	public List<Question> getAvailableQuestions() {
		return availableQuestions;
	}

	public void setAvailableQuestions(List<Question> availableQuestions) {
		this.availableQuestions = availableQuestions;
	}

	public List<Question> getUsedQuestions() {
		return usedQuestions;
	}

	public void setUsedQuestions(List<Question> usedQuestions) {
		this.usedQuestions = usedQuestions;
	}
}
