package fr.epita.quiz.tests;

import java.util.LinkedList;
import java.util.List;

import fr.epita.quiz.datamodel.MultipleChoice;
import fr.epita.quiz.datamodel.Open;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.Quiz;

public class TestQuiz {

	public static void main(String[] args) {
		
		// Instantiate Quiz class
		Student student = new Student("Thibault", "123");
		List<String> topics = new LinkedList<String>();
		topics.add("EPITA");
		Quiz quiz = new Quiz(student, topics, 15, true);
		quiz.loadNewQuiz();
		
		// Take the quiz
		for (int i=0; i<quiz.getTotalQuestions(); i++) {
			Question question = quiz.getNewQuestion();
			
			if (question.getClass() == MultipleChoice.class) {
				System.out.println("Question is a MCQ");
			}
			else if (question.getClass() == Open.class) {
				System.out.println("Question is a OPEN");
			}
			
			System.out.println("New Question: " + question.toString());
			question.setChoice(quiz.getRandom().nextInt(4));
			question.setResponse("My name is Jonathan");
			quiz.processNewQuestion(question);
			System.out.println("Progress ...  " + quiz.getProgress());
		}
		
		System.out.println("Quiz completed.");
		System.out.println("Score: " + quiz.getGrade());
		
	}

}
