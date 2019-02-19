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
import fr.epita.quiz.datamodel.Question;

public class QuestionJDBCDAO {
	
   private static String INSERT_STATEMENT = "INSERT INTO BANK (QUESTION, DIFFICULTY, TOPICS, "
		   + "OP_1, OP_2, OP_3, OP_4, ANSWER) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
   private static String READ_STATEMENT = "SELECT * FROM BANK";
   private static String UPDATE_STATEMENT = "UPDATE BANK SET QUESTION=?, DIFFICULTY=?, " 
		   + "TOPICS=?, OP_1=?, OP_2=?, OP_3=?, OP_4=?, ANSWER=? WHERE ID=?";
   private static String DELETE_STATEMENT = "DELETE FROM BANK WHERE ID=?";
   private static String SEARCH_STATEMENT = "SELECT * from BANK WHERE DIFFICULTY=?";
   
   public void create(MultipleChoice question) {
		try (Connection connection = getConnection();
				PreparedStatement insertStatement = connection.prepareStatement(INSERT_STATEMENT);) {
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
			
			System.out.println("Created question : " + question.toString());
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
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
			
			System.out.println("Read questions. Found " + resultList.size());
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
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
			System.out.println("Updated question : " + question.toString());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
	
	public List<MultipleChoice> search(int difficulty) {
		List<MultipleChoice> resultList = new LinkedList<MultipleChoice>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_STATEMENT);
				) {
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
	
	public List<MultipleChoice> search(List<String> topicSearch) {
		List<MultipleChoice> resultList = new LinkedList<MultipleChoice>();
		String SEARCH_STATEMENT_TOPICS = "SELECT * from BANK WHERE";
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
				PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_STATEMENT_TOPICS);
				) {
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
	
	private Connection getConnection() throws SQLException {
		Configuration conf = Configuration.getInstance();
		String jdbcUrl = conf.getConfigurationValue("jdbc.url");
		String user = conf.getConfigurationValue("jdbc.user");
		String password = conf.getConfigurationValue("jdbc.password");
		Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
		return connection;
	}

}
