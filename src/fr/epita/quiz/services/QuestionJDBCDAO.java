package fr.epita.quiz.services;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import fr.epita.quiz.datamodel.MultipleChoice;
import fr.epita.quiz.datamodel.Open;
import fr.epita.quiz.datamodel.Question;


/** Data access object class Quiz Manager to use the H2 database
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
public class QuestionJDBCDAO {

   private static String INSERT_STATEMENT = "INSERT INTO MCQ (QUESTION, DIFFICULTY, TOPICS, "
		   + "OP_1, OP_2, OP_3, OP_4, ANSWER) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
   private static String INSERT_STATEMENT_OPEN = "INSERT INTO OQ (QUESTION, DIFFICULTY, TOPICS, ANSWER) "+
	   														"VALUES (?, ?, ?, ?)";

   private static String READ_STATEMENT = "SELECT * FROM MCQ";
   private static String READ_STATEMENT_OPEN = "SELECT * FROM OQ";

   private static String UPDATE_STATEMENT = "UPDATE MCQ SET QUESTION=?, DIFFICULTY=?, " 
		   + "TOPICS=?, OP_1=?, OP_2=?, OP_3=?, OP_4=?, ANSWER=? WHERE ID=?";
   private static String UPDATE_STATEMENT_OPEN = "UPDATE OQ SET QUESTION=?, DIFFICULTY=?, " 
		   + "TOPICS=?, ANSWER=? WHERE ID=?";

   private static String DELETE_STATEMENT = "DELETE FROM MCQ WHERE ID=?";
   private static String DELETE_STATEMENT_OPEN = "DELETE FROM OQ WHERE ID=?";

   private static String SEARCH_STATEMENT = "SELECT * from MCQ WHERE DIFFICULTY=?";
   private static String SEARCH_STATEMENT_OPEN = "SELECT * from OQ WHERE DIFFICULTY=?";
   
	/** Create new record in database method
	 * @param question (MultipleChoice) Record to insert into the database
	 */
	public void create(MultipleChoice question) {
		try (Connection connection = getConnection();
			PreparedStatement insertStatement = connection.prepareStatement(INSERT_STATEMENT); ) {
			insertStatement.setString(1, question.getQuestion());
			insertStatement.setInt(2, question.getDifficulty());
			Array array = connection.createArrayOf("VARCHAR", question.getTopics().toArray());
			insertStatement.setArray(3, array);
			insertStatement.setString(4, question.getOptions().get(0));
			insertStatement.setString(5, question.getOptions().get(1));
			insertStatement.setString(6, question.getOptions().get(2));
			insertStatement.setString(7, question.getOptions().get(3));
			insertStatement.setInt(8, question.getAnswer());
			insertStatement.execute();
			array.free();
			
			System.out.println("Created MultipleChoice question : " + question.toString());
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Create new record in database method
	 * @param question (Open) Record to insert into the database
	 */
	public void create(Open question) {
		try (Connection connection = getConnection();
			PreparedStatement insertStatement = connection.prepareStatement(INSERT_STATEMENT_OPEN); ) {
			insertStatement.setString(1, question.getQuestion());
			insertStatement.setInt(2, question.getDifficulty());
			Array array = connection.createArrayOf("VARCHAR", question.getTopics().toArray());
			insertStatement.setArray(3, array);
			insertStatement.setString(4, question.getResponse());
			insertStatement.execute();
			array.free();
			
			System.out.println("Created Open question : " + question.toString());
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Select all records in database
	 * @return (List MultipleChoice) List of ALL multiple choice questions found in the database
	 */
	public List<MultipleChoice> read() {
		List<MultipleChoice> resultList = new LinkedList<MultipleChoice>();
		try (Connection connection = getConnection();
			PreparedStatement readStatement = connection.prepareStatement(READ_STATEMENT)){
			ResultSet results = readStatement.executeQuery();
			
			while (results.next()) {
				MultipleChoice mc = new MultipleChoice();
				mc.setQuestion(results.getString("QUESTION"));
				mc.setDifficulty(results.getInt("DIFFICULTY"));
				
				Array array = results.getArray("TOPICS");
				Object[] topics = (Object[]) array.getArray(); 
				List<String> topicsList = new LinkedList<String>();
				for (int i=0; i<topics.length; i++) {
					topicsList.add((String)topics[i]);
				}
				mc.setTopics(topicsList);
				
				mc.addOption(results.getString("OP_1"));
				mc.addOption(results.getString("OP_2"));
				mc.addOption(results.getString("OP_3"));
				mc.addOption(results.getString("OP_4"));
				mc.setAnswer(results.getInt("ANSWER"));
				mc.setId(results.getInt("ID"));
				resultList.add(mc);
			}
			results.close();
			
			System.out.println("read questions. Found " + resultList.size());
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	/** Select all records in database
	 * @return (List Open) List of ALL multiple choice questions found in the database
	 */
	public List<Open> readOpen() {
		List<Open> resultList = new LinkedList<Open>();
		try (Connection connection = getConnection();
			PreparedStatement readStatement = connection.prepareStatement(READ_STATEMENT_OPEN)){
			ResultSet results = readStatement.executeQuery();
			
			while (results.next()) {
				Open oq = new Open();
				oq.setQuestion(results.getString("QUESTION"));
				oq.setDifficulty(results.getInt("DIFFICULTY"));
				
				Array array = results.getArray("TOPICS");
				Object[] topics = (Object[]) array.getArray(); 
				List<String> topicsList = new LinkedList<String>();
				for (int i=0; i<topics.length; i++) {
					topicsList.add((String)topics[i]);
				}
				oq.setTopics(topicsList);
//				oq.setResponse(results.getString("ANSWER"));
				oq.setId(results.getInt("ID"));
				resultList.add(oq);
			}
			results.close();
			
			System.out.println("readOpen questions. Found " + resultList.size());
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	/** Update a specific record in the database
	 * @param question (MultipleChoice) updated question to update in database
	 */
	public void update(MultipleChoice question) {
		try (Connection connection = getConnection();
			PreparedStatement updateStatement = connection.prepareStatement(UPDATE_STATEMENT)){
			updateStatement.setString(1, question.getQuestion());
			updateStatement.setInt(2, question.getDifficulty());
			Array array = connection.createArrayOf("VARCHAR", question.getTopics().toArray());
			updateStatement.setArray(3, array);
			updateStatement.setString(4, question.getOptions().get(0));
			updateStatement.setString(5, question.getOptions().get(1));
			updateStatement.setString(6, question.getOptions().get(2));
			updateStatement.setString(7, question.getOptions().get(3));
			updateStatement.setInt(8, question.getAnswer());
			updateStatement.setLong(9, question.getId());
			updateStatement.execute();
			System.out.println("Updated MultipleChoice question : " + question.toString());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Update a specific record in the database
	 * @param question (Open) updated question to update in database
	 */
	public void update(Open question) {
		try (Connection connection = getConnection();
			PreparedStatement updateStatement = connection.prepareStatement(UPDATE_STATEMENT_OPEN)){
			updateStatement.setString(1, question.getQuestion());
			updateStatement.setInt(2, question.getDifficulty());
			Array array = connection.createArrayOf("VARCHAR", question.getTopics().toArray());
			updateStatement.setArray(3, array);
			updateStatement.setString(4, question.getResponse());
			updateStatement.setLong(5, question.getId());
			updateStatement.execute();
			System.out.println("Updated Open question : " + question.toString());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** Delete a specific record in the database
	 * @param question (MultipleChoice) question to remove from the database
	 */
	public void delete(MultipleChoice question) {
		try (Connection connection = getConnection();
			PreparedStatement deleteStatement = connection.prepareStatement(DELETE_STATEMENT)){
			deleteStatement.setInt(1, question.getId());
			deleteStatement.executeUpdate();
			System.out.println("Deleted question : " + question.toString());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Delete a specific record in the database
	 * @param question (MultipleChoice) question to remove from the database
	 */
	public void delete(Open question) {
		try (Connection connection = getConnection();
			PreparedStatement deleteStatement = connection.prepareStatement(DELETE_STATEMENT_OPEN)){
			deleteStatement.setInt(1, question.getId());
			deleteStatement.executeUpdate();
			System.out.println("Deleted question : " + question.toString());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Search for questions in database for a given difficulty
	 * @param difficulty (int) difficulty setting, ranges from 0 to 5
	 * @return (List MultipleChoice) List of ALL multiple choice questions in database for a given difficulty
	 */
	public List<MultipleChoice> search(int difficulty) {
		List<MultipleChoice> resultList = new LinkedList<MultipleChoice>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_STATEMENT); ) {
			preparedStatement.setInt(1, difficulty);
			ResultSet results = preparedStatement.executeQuery();
			
			while (results.next()) {
				MultipleChoice mc = new MultipleChoice();
				mc.setQuestion(results.getString("QUESTION"));
				mc.setDifficulty(results.getInt("DIFFICULTY"));
				Array array = results.getArray("TOPICS");
				Object[] topics = (Object[]) array.getArray(); 
				List<String> topicsList = new LinkedList<String>();
				for (int i=0; i<topics.length; i++) {
					topicsList.add((String)topics[i]);
				}
				mc.setTopics(topicsList);
				mc.addOption(results.getString("OP_1"));
				mc.addOption(results.getString("OP_2"));
				mc.addOption(results.getString("OP_3"));
				mc.addOption(results.getString("OP_4"));
				mc.setAnswer(results.getInt("ANSWER"));
				mc.setId(results.getInt("ID"));
				resultList.add(mc);
			}
			results.close();
			System.out.println("Searched for DIFFICULTY=" + difficulty + ".  Found " + resultList.size());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	/** Search for questions in database for a given topic
	 * @param topicSearch (List String) List of topics to search for in the database
	 * @return (List MultipleChoice) List of ALL multiple choice questions in database for a given set of topics
	 */
	public List<MultipleChoice> search(List<String> topicSearch) {
		List<MultipleChoice> resultList = new LinkedList<MultipleChoice>();
		String SEARCH_STATEMENT_TOPICS = "SELECT * from MCQ WHERE";
		int numberOfTopics = topicSearch.size();
		
		if (numberOfTopics < 1) {
			System.out.println("Search incomplete. Please add topics.");
			return resultList;
		}
		
		for (int i=0; i<numberOfTopics; i++) {
			SEARCH_STATEMENT_TOPICS += " ARRAY_CONTAINS(TOPICS, '" + topicSearch.get(i) + "')";
			
			if (i + 1 < numberOfTopics) {
				SEARCH_STATEMENT_TOPICS += " OR";
			}
		}
		
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_STATEMENT_TOPICS); ) {
			ResultSet results = preparedStatement.executeQuery();
			
			while (results.next()) {
				MultipleChoice mc = new MultipleChoice();
				mc.setQuestion(results.getString("QUESTION"));
				mc.setDifficulty(results.getInt("DIFFICULTY"));
				Array array = results.getArray("TOPICS");
				Object[] topics = (Object[]) array.getArray(); 
				List<String> topicsList = new LinkedList<String>();
				for (int i=0; i<topics.length; i++) {
					topicsList.add((String)topics[i]);
				}
				mc.setTopics(topicsList);
				mc.addOption(results.getString("OP_1"));
				mc.addOption(results.getString("OP_2"));
				mc.addOption(results.getString("OP_3"));
				mc.addOption(results.getString("OP_4"));
				mc.setAnswer(results.getInt("ANSWER"));
				mc.setId(results.getInt("ID"));
				resultList.add(mc);
			}
			results.close();
			System.out.println("Searched for TOPICS=" + topicSearch.toString() + ".  Found " + resultList.size());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	/** Search for questions in database for a given topic
	 * @param topicSearch (List String) List of topics to search for in the database
	 * @return (List Open) List of ALL multiple choice questions in database for a given set of topics
	 */
	public List<Open> searchOpen(List<String> topicSearch) {
		List<Open> resultList = new LinkedList<Open>();
		String SEARCH_STATEMENT_TOPICS = "SELECT * from OQ WHERE";
		int numberOfTopics = topicSearch.size();
		
		if (numberOfTopics < 1) {
			System.out.println("Search incomplete. Please add topics.");
			return resultList;
		}
		
		for (int i=0; i<numberOfTopics; i++) {
			SEARCH_STATEMENT_TOPICS += " ARRAY_CONTAINS(TOPICS, '" + topicSearch.get(i) + "')";
			
			if (i + 1 < numberOfTopics) {
				SEARCH_STATEMENT_TOPICS += " OR";
			}
		}
		
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_STATEMENT_TOPICS); ) {
			ResultSet results = preparedStatement.executeQuery();
			
			while (results.next()) {
				Open oq = new Open();
				oq.setQuestion(results.getString("QUESTION"));
				oq.setDifficulty(results.getInt("DIFFICULTY"));
				Array array = results.getArray("TOPICS");
				Object[] topics = (Object[]) array.getArray(); 
				List<String> topicsList = new LinkedList<String>();
				for (int i=0; i<topics.length; i++) {
					topicsList.add((String)topics[i]);
				}
				oq.setTopics(topicsList);
				oq.setResponse("");
				oq.setId(results.getInt("ID"));
				resultList.add(oq);
			}
			results.close();
			System.out.println("Searched for TOPICS=" + topicSearch.toString() + ".  Found " + resultList.size());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	/** Create connection with H2 database
	 * @return (Connection) singleton driver connection to the H2 database
	 * @throws SQLException Unable to connect to the H2 database
	 */
	private Connection getConnection() throws SQLException {
		Configuration conf = Configuration.getInstance();
		String jdbcUrl = conf.getConfigurationValue("jdbc.url");
		String user = conf.getConfigurationValue("jdbc.user");
		String password = conf.getConfigurationValue("jdbc.password");
		Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
		return connection;
	}

}
