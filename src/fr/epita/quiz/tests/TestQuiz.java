package fr.epita.quiz.tests;

import java.util.LinkedList;
import java.util.List;

import fr.epita.quiz.datamodel.MultipleChoice;
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.Quiz;

public class TestQuiz {

	public static void main(String[] args) {
		
		// Instantiate Quiz class
		Student student = new Student("Thibault", "123");
		List<String> topics = new LinkedList<String>();
		topics.add("France");
		Quiz quiz = new Quiz(student, topics, 5);
		quiz.loadNewQuiz();
		
		// Take the quiz
		for (int i=0; i<quiz.getTotalQuestions(); i++) {
			MultipleChoice question = quiz.getNewQuestion(1);
			System.out.println("New Question: " + question.getQuestion());
			question.setChoice(quiz.getRandom().nextInt(4));
			quiz.processNewQuestion(question);
			System.out.println("Progress ...  " + quiz.getProgress());
		}
		
		System.out.println("Quiz completed.");
		System.out.println("Score: " + quiz.getGrade());
		
	}

}
