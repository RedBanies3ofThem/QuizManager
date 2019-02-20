package fr.epita.quiz.launcher;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import fr.epita.quiz.datamodel.MultipleChoice;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.Exporter;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Launcher extends Application {

	private Button buttonCreateTest, buttonSubmitAnswer, clear, buttonHome, buttonExport;
	private Stage window;
	private Scene sceneMain, sceneQuiz, sceneSummary;
	private Quiz quiz;
	private MultipleChoice currentQuestion;
	private Exporter exporter;

	public static void main(String[] args) {
		System.out.println("Running main method");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.window = primaryStage;
		this.exporter = new Exporter("testExporter.txt");
		this.window.setTitle("Quiz Wizard");

		Label name = new Label();
		Label grade = new Label(); 
		Label questionCount = new Label();
		
		// Main Scene -------------------------------------------------
		
		TextField textName = new TextField();
		textName.setPromptText("Enter Student Name");
		textName.setPrefColumnCount(30);
		
		TextField textId = new TextField();
		textId.setPromptText("Enter Student ID");
		textId.setPrefColumnCount(5);

		TextField textTopics = new TextField();
		textTopics.setPromptText("Enter topics (comma delimited)");
		textTopics.setPrefColumnCount(30);	
		
		TextField textQuizSize = new TextField();
		textQuizSize.setPromptText("Enter the number of questions");
		textQuizSize.setPrefColumnCount(2);		

		Label labelQuestionNumber = new Label();
		Label labelQuestion = new Label();
		ToggleGroup group = new ToggleGroup();
		RadioButton labelOp1 = new RadioButton();
		labelOp1.setToggleGroup(group);
		RadioButton labelOp2 = new RadioButton();
		labelOp2.setToggleGroup(group);
		RadioButton labelOp3 = new RadioButton();
		labelOp3.setToggleGroup(group);
		RadioButton labelOp4 = new RadioButton();
		labelOp4.setToggleGroup(group);
		
		this.buttonCreateTest = new Button();
		this.buttonCreateTest.setText("Start New Test");
		this.buttonCreateTest.setOnAction( a -> {
			System.out.println("Start Button clicked");
			
			if (textName.getText().equals("")) {
				textName.setText("Bob Smith");
			}
			if (textId.getText().equals("")) {
				textId.setText("12345");
			}
			if (textTopics.getText().equals("")) {
				textTopics.setText("France,Italy");
			}
			if (textQuizSize.getText().equals("")) {
				textQuizSize.setText("5");
			}
			
			Student student = new Student(textName.getText(), textId.getText());
			List<String> topics = new LinkedList<String>(Arrays.asList(textTopics.getText().split(",")));
			int numberOfQuestions = Math.max(Integer.parseInt(textQuizSize.getText()), 1);
			this.quiz = new Quiz(student, topics, numberOfQuestions);
			this.quiz.loadNewQuiz();
			
			if (this.quiz.getAvailableMCQuestions().size() < numberOfQuestions) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Please add a valid topic");
				alert.setHeaderText(null);
				if (this.quiz.getAvailableMCQuestions().size() == 0) {
					alert.setContentText("The topics you entered are not found in the quiz database. Please enter another topic.");
				}
				else {
					alert.setContentText("Not enough questions for that topic." +
										" Please reduce the number of questions to " + 
										(this.quiz.getAvailableMCQuestions().size()-1));
				}
				alert.showAndWait();
				return;
			}
			
			updateQuizUI(labelQuestionNumber, labelQuestion, labelOp1, labelOp2, labelOp3, labelOp4);
			
			this.window.setScene(sceneQuiz);
		});
		
		//Creating a GridPane container
		GridPane layoutMain = new GridPane();
		layoutMain.setPadding(new Insets(10, 10, 10, 10));
		layoutMain.setVgap(5);
		layoutMain.setHgap(5);
		GridPane.setConstraints(textName, 0, 0);
		layoutMain.getChildren().add(textName);
		GridPane.setConstraints(textId, 0, 1);
		layoutMain.getChildren().add(textId);
		GridPane.setConstraints(textTopics, 0, 2);
		layoutMain.getChildren().add(textTopics);
		GridPane.setConstraints(textQuizSize, 0, 3);
		layoutMain.getChildren().add(textQuizSize);
		
		GridPane.setConstraints(this.buttonCreateTest, 1, 0);
		layoutMain.getChildren().add(this.buttonCreateTest);
		this.clear = new Button("Clear");
		clear.setOnAction( a -> {
			System.out.println("Clear button got clicked");
			textName.setText("");
			textId.setText("");
			textTopics.setText("");
			textQuizSize.setText("");
		});
		GridPane.setConstraints(clear, 1, 1);
		layoutMain.getChildren().add(clear);
		
		sceneMain = new Scene(layoutMain, 500, 200);
		this.window.setScene(sceneMain);
		
		
		// Quiz Scene -------------------------------------------------
		
		this.buttonSubmitAnswer = new Button();
		this.buttonSubmitAnswer.setText("Submit Answer");
		this.buttonSubmitAnswer.setOnAction( a -> {
			System.out.println("Submit button clicked");
			
			this.currentQuestion.setChoice(getUserChoice(labelOp1, labelOp2, labelOp3, labelOp4));
			this.quiz.processNewQuestion(this.currentQuestion);
			
			updateQuizUI(labelQuestionNumber, labelQuestion, labelOp1, labelOp2, labelOp3, labelOp4);
			
			System.out.println("this.quiz.getProgress() " + this.quiz.getProgress());
			
			if (this.quiz.getProgress() >= 1.0) {
				name.setText("Student: " + this.quiz.getStudent().getName());
				grade.setText("Correct: " + Integer.toString(this.quiz.getGrade()));
				questionCount.setText("Total: " + Integer.toString(this.quiz.getUsedMCQuestions().size()));
				
				this.window.setScene(sceneSummary);
			}			
		});
		
		VBox layoutQuiz = new VBox();
		layoutQuiz.setPadding(new Insets(10, 10, 10, 10));
		layoutQuiz.getChildren().add(labelQuestionNumber);
		layoutQuiz.getChildren().add(labelQuestion);
		layoutQuiz.getChildren().add(labelOp1);
		layoutQuiz.getChildren().add(labelOp2);
		layoutQuiz.getChildren().add(labelOp3);
		layoutQuiz.getChildren().add(labelOp4);
		layoutQuiz.getChildren().add(this.buttonSubmitAnswer);
		
		sceneQuiz = new Scene(layoutQuiz, 500, 300);
		
		
		// Summary Scene -------------------------------------------------
		
		this.buttonHome = new Button();
		this.buttonHome.setText("Return to Main Menu");
		this.buttonHome.setOnAction( a -> {
			System.out.println("Home button clicked");
			this.window.setScene(sceneMain);			
		});
		
		this.buttonExport = new Button();
		this.buttonExport.setText("Export Quiz");
		this.buttonExport.setOnAction( a -> {
			System.out.println("Export Quiz button clicked");
			this.exporter.exportAll(this.quiz);
		});
		
		VBox layoutSummary = new VBox();
		layoutSummary.setPadding(new Insets(10, 10, 10, 10));
		layoutSummary.getChildren().add(name);
		layoutSummary.getChildren().add(grade);
		layoutSummary.getChildren().add(questionCount);
		layoutSummary.getChildren().add(this.buttonHome);
		layoutSummary.getChildren().add(this.buttonExport);
		
		sceneSummary = new Scene(layoutSummary, 500, 300);
		
		
		this.window.show();
	}  // End of Start()

	private void updateQuizUI(Label labelQuestionNumber, Label labelQuestion, 
			RadioButton labelOp1, RadioButton labelOp2, RadioButton labelOp3, RadioButton labelOp4) {
		this.currentQuestion = this.quiz.getNewQuestion(1);
		if (this.currentQuestion == null) {
			this.currentQuestion = this.quiz.getNewQuestion();
			System.out.println("currentQuestion : " + this.currentQuestion.toString());
		}
		this.currentQuestion.setNumber(this.quiz.getUsedMCQuestions().size() + 1);
		labelQuestionNumber.setText("# " + this.currentQuestion.getNumber() );
		labelQuestion.setText(this.currentQuestion.getQuestion());
		labelOp1.setText(this.currentQuestion.getOptions().get(0));
		labelOp2.setText(this.currentQuestion.getOptions().get(1));
		labelOp3.setText(this.currentQuestion.getOptions().get(2));
		labelOp4.setText(this.currentQuestion.getOptions().get(3));
	}  // End of updateQuizUI()
	
	private int getUserChoice(RadioButton labelOp1, RadioButton labelOp2, RadioButton labelOp3, RadioButton labelOp4) {
		int ret = 0;
		if (labelOp1.isSelected()) {
			ret = 1;
		} 
		else if (labelOp2.isSelected()) {
			ret = 2;
		} 
		else if (labelOp3.isSelected()) {
			ret = 3;
		} 
		else if (labelOp4.isSelected()) {
			ret = 4;
		}

		labelOp1.setSelected(false);
		labelOp2.setSelected(false);
		labelOp3.setSelected(false);
		labelOp4.setSelected(false);
		
		return ret;
	}  // End of getUserChoice()
}
