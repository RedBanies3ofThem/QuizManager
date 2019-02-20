package fr.epita.quiz.tests;


import java.util.LinkedList;
import java.util.List;

import fr.epita.quiz.datamodel.*;


public class TestDataModels {

	public static void main(String[] args) {
		
		System.out.println("Starting TestDataModels...");
		
		List<Question> questions = new LinkedList<Question>();
		
		for (int i=0; i<10; i++) {
			
			MultipleChoice mcq = new MultipleChoice();
			mcq.setQuestion("What is " + i +" x " + i + " ?");
			mcq.setDifficulty(3);
			LinkedList<String> topicsList = new LinkedList<String>();
			topicsList.add("Math");
			topicsList.add("Science");
			topicsList.add("Masters");
			topicsList.add("EPITA");
			mcq.setTopics(topicsList);
			mcq.addOption(Double.toString(Math.pow(i, i)));
			mcq.addOption(Double.toString(Math.pow(i, i+1)));
			mcq.addOption(Double.toString(Math.pow(i, i+2)));
			mcq.addOption(Double.toString(Math.pow(i, i+3)));
			mcq.setAnswer(1);
			
			questions.add(mcq);
			
			Open oq = new Open();
			oq.setQuestion("What is " + i +" x " + i + " ?");
			oq.setDifficulty(3);
			LinkedList<String> topicsListOp = new LinkedList<String>();
			topicsListOp.add("Math");
			topicsListOp.add("Science");
			topicsListOp.add("Masters");
			topicsListOp.add("EPITA");
			oq.setTopics(topicsList);
			oq.setAnswer(1);
			
			questions.add(oq);
			
		}  // End of for loop
		
		int counter = 0;
		for (Question q : questions) {
			
			System.out.println("Question #" + counter + " : " + q.toString());
			counter += 1;
		}
		
		System.out.println("DONE testing TestDataModels.");
	}  // End of main()

}  // End of TestDataModels class
