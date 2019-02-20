package fr.epita.quiz.tests;


import java.util.LinkedList;
import java.util.List;

import fr.epita.quiz.datamodel.MultipleChoice;
import fr.epita.quiz.datamodel.Open;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.QuestionJDBCDAO;


public class TestDAO {

	public static void main(String[] args) {
		
		// Instantiate data access object
		QuestionJDBCDAO dao = new QuestionJDBCDAO();
		MultipleChoice mcq;
		Open oq;
		List<String> topicsList, topicsListOp;
		for (int i=0; i<10; i++) {
			
			mcq = new MultipleChoice();
			mcq.setQuestion("What is " + i +" x " + i + " ?");
			mcq.setDifficulty(4);

			topicsList = new LinkedList<String>();
			
			if ( i % 2 == 0) {
				topicsList.add("Math");
				topicsList.add("Spanish");				
			}
			topicsList.add("Masters");
			topicsList.add("EPITA");
			mcq.setTopics(topicsList);
			mcq.addOption(Double.toString(Math.multiplyExact(i, i*3)));
			mcq.addOption(Double.toString(Math.multiplyExact(i, i+1)));
			mcq.addOption(Double.toString(Math.multiplyExact(i, i*10)));
			mcq.addOption(Double.toString(Math.multiplyExact(i, i)));
			mcq.setAnswer(4);

			dao.create(mcq);
			
			oq = new Open();
			oq.setQuestion("What is " + i +" x " + i + " ?");
			oq.setDifficulty(4);
			
			topicsListOp = new LinkedList<String>();	
			
			if ( i % 2 == 0) {
				topicsListOp.add("Math");
				topicsListOp.add("Spanish");				
			}
			topicsListOp.add("Masters");
			topicsListOp.add("EPITA");
			oq.setTopics(topicsList);
			oq.setResponse(Double.toString(Math.multiplyExact(i, i)));
			oq.setAnswer(1);

			dao.create(oq);
			
		}  // End of for loop
		
		/*	READ method Test 		*/
		List<MultipleChoice> results = dao.read();
		List<Open> resultsOpen = dao.readOpen();
		
		/*	UPDATE method Test 		*/
		mcq = new MultipleChoice();
		mcq.setQuestion("Where do you live?");
		topicsList = new LinkedList<String>();	
		topicsList.add("Location");
		topicsList.add("EPITA");
		mcq.setTopics(topicsList);
		mcq.setDifficulty(0);
		mcq.addOption("London");
		mcq.addOption("Paris");
		mcq.addOption("New York");
		mcq.addOption("Hong Kong");
		mcq.setAnswer(2);
		mcq.setId(1);
		dao.update(mcq);

		oq = new Open();
		oq.setQuestion("What is your favorite expression in French?");
		topicsListOp = new LinkedList<String>();	
		topicsListOp.add("Location");
		topicsListOp.add("EPITA");
		oq.setTopics(topicsList);
		oq.setDifficulty(0);
		oq.setResponse("Je ne suis pas d'accord avec vous.");
		oq.setId(1);
		dao.update(oq);
		
		
		/*	DELETE method Test 		*/
		mcq.setId(3);
		dao.delete(mcq);
		
		oq.setId(3);
		dao.delete(oq);
		
		/*	SEARCH method Test 		*/
		
		results = dao.search(3);
		
		// search by topic	
		topicsList = new LinkedList<String>();
		topicsListOp = new LinkedList<String>();
		
		topicsList.add("Math");
		topicsList.add("French");	
		
		topicsListOp.add("Math");
		topicsListOp.add("French");
		
		mcq.setTopics(topicsList);

		results = dao.search(topicsList);
		resultsOpen = dao.searchOpen(topicsListOp);
		
	}  // end of main()

}  // end of TestDAO()
