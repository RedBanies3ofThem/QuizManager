package fr.epita.quiz.launcher;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import fr.epita.quiz.datamodel.MultipleChoice;
import fr.epita.quiz.datamodel.Open;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.Configuration;
import fr.epita.quiz.services.Exporter;
import fr.epita.quiz.services.Quiz;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Launcher extends Application {

	private Button buttonCreateTest, buttonSubmitAnswer, buttonClear, buttonHome, buttonExport, buttonSubmitAnswer2;
	private Stage window;
	private Scene sceneMain, sceneQuizMC, sceneQuizOpen, sceneSummary;
	private Quiz quiz;
	private Question currentQuestion;
	private Exporter exporter;
	private ProgressBar progressBar, progressBar2;
	private Label name, grade, questionCount;
	
	private TextField textName, textId, textTopics, textQuizSize, textQuizResponse;	
	private Label labelQuestionNumber, labelQuestion, labelQuestionNumber2, labelQuestion2;		
	private ToggleGroup group;
	private RadioButton labelOp1, labelOp2, labelOp3, labelOp4;
	private RadioButton useOpenQuestions;

	public static void main(String[] args) {
		System.out.println("Running main method");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.window = primaryStage;
		this.exporter = new Exporter(Configuration.getInstance()
												  .getConfigurationValue("export.filename"));
		this.window.setTitle("Quiz Wizard");

		this.name = new Label();
		this.name.setPadding(new Insets(5, 5, 5, 5));
		
		this.grade = new Label(); 
		this.grade.setPadding(new Insets(5, 5, 5, 5));
		
		this.questionCount = new Label();
		this.questionCount.setPadding(new Insets(5, 5, 5, 5));
		
		// Main Scene -------------------------------------------------
		

		
		this.textName = new TextField();
		this.textName.setPromptText("Enter Student Name");
		this.textName.setPrefColumnCount(30);
		
		this.textId = new TextField();
		this.textId.setPromptText("Enter Student ID");
		this.textId.setPrefColumnCount(5);

		this.textTopics = new TextField();
		this.textTopics.setPromptText("Enter topics (comma delimited)");
		this.textTopics.setPrefColumnCount(30);	
		
		this.textQuizSize = new TextField();
		this.textQuizSize.setPromptText("Enter the number of questions");
		this.textQuizSize.setPrefColumnCount(2);		
		
		this.textQuizResponse = new TextField();
		this.textQuizResponse.setPromptText("Enter your answer here.");
		this.textQuizResponse.setPrefColumnCount(250);	

		this.labelQuestionNumber = new Label();
		this.labelQuestionNumber.setPadding(new Insets(5, 5, 5, 5));		
		
		this.labelQuestionNumber2 = new Label();
		this.labelQuestionNumber2.setPadding(new Insets(5, 5, 5, 5));		
		
		this.labelQuestion = new Label();
		this.labelQuestion.setPadding(new Insets(5, 5, 5, 5));		
		
		this.labelQuestion2 = new Label();
		this.labelQuestion2.setPadding(new Insets(5, 5, 5, 5));	
		
		this.group = new ToggleGroup();
		
		this.labelOp1 = new RadioButton();
		this.labelOp1.setPadding(new Insets(5, 5, 5, 5));
		this.labelOp1.setToggleGroup(group);
		
		this.labelOp2 = new RadioButton();
		this.labelOp2.setPadding(new Insets(5, 5, 5, 5));
		this.labelOp2.setToggleGroup(group);
		
		this.labelOp3 = new RadioButton();
		this.labelOp3.setPadding(new Insets(5, 5, 5, 5));
		this.labelOp3.setToggleGroup(group);
		
		this.labelOp4 = new RadioButton();
		this.labelOp4.setPadding(new Insets(5, 5, 5, 5));
		this.labelOp4.setToggleGroup(group);

		this.useOpenQuestions = new RadioButton();
		this.useOpenQuestions.setText("Use Open Ended Questions?");

		this.progressBar = new ProgressBar();
		this.progressBar2 = new ProgressBar();
		
		this.buttonCreateTest = new Button();
		this.buttonCreateTest.setText("Start New Test");
		this.buttonCreateTest.setPadding(new Insets(5, 5, 5, 5));
		this.buttonCreateTest.setOnAction( a -> {
			System.out.println("Start Button clicked");
			
			if (textName.getText().equals("")) {
				textName.setText("Bob Smith");
			}
			if (textId.getText().equals("")) {
				textId.setText("12345");
			}
			if (textTopics.getText().equals("")) {
				textTopics.setText("EPITA");
			}
			if (textQuizSize.getText().equals("")) {
				textQuizSize.setText("5");
			}
			
			Student student = new Student(textName.getText(), textId.getText());
			List<String> topics = new LinkedList<String>(Arrays.asList(textTopics.getText().split(",")));
			int numberOfQuestions = Math.max(Integer.parseInt(textQuizSize.getText()), 1);
			
			System.out.println("numberOfQuestions " + numberOfQuestions);
			
			this.quiz = new Quiz(student, topics, numberOfQuestions, useOpenQuestions.isSelected());
			this.quiz.loadNewQuiz();
			
			if (this.quiz.getAvailableQuestions().size() < numberOfQuestions) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Please add a valid topic");
				alert.setHeaderText(null);
				if (this.quiz.getAvailableQuestions().size() == 0) {
					alert.setContentText("The topics you entered are not found in the quiz database." +
										" Please enter another topic.");
				}
				else {
					alert.setContentText("Not enough questions for that topic." +
										" Please reduce the number of questions to " + 
										(this.quiz.getAvailableQuestions().size()-1));
				}
				alert.showAndWait();
				return;
			}
			
			updateUI();
		});
		
		this.buttonHome = new Button();
		this.buttonHome.setText("Return to Main Menu");
		this.buttonHome.setPadding(new Insets(10, 10, 10, 10));
		this.buttonHome.setOnAction( a -> {
			System.out.println("Home button clicked");
			this.window.setScene(sceneMain);			
		});
		
		this.buttonExport = new Button();
		this.buttonExport.setText("Export Quiz");
		this.buttonExport.setPadding(new Insets(10, 10, 10, 10));
		this.buttonExport.setOnAction( a -> {
			System.out.println("Export Quiz button clicked");
			this.exporter.exportAll(this.quiz, false);
		});
		
		this.buttonSubmitAnswer = new Button();
		this.buttonSubmitAnswer.setText("Submit Answer");
		this.buttonSubmitAnswer.setPadding(new Insets(5, 5, 5, 5));
		this.buttonSubmitAnswer.setOnAction( a -> {
			System.out.println("Submit button clicked");
			if (this.currentQuestion.getClass() == MultipleChoice.class) {			
				this.currentQuestion.setChoice(getUserChoice(labelOp1, labelOp2, labelOp3, labelOp4));
				this.quiz.processNewQuestion(this.currentQuestion);
			}
			else if (this.currentQuestion.getClass() == Open.class) {			
				this.currentQuestion.setResponse(this.textQuizResponse.getText());
				this.textQuizResponse.setText("");
				this.quiz.processNewQuestion(this.currentQuestion);				
			}			
			updateUI();
		});
		
		this.buttonSubmitAnswer2 = new Button();
		this.buttonSubmitAnswer2.setText("Submit Answer");
		this.buttonSubmitAnswer2.setPadding(new Insets(5, 5, 5, 5));
		this.buttonSubmitAnswer2.setOnAction( a -> {
			System.out.println("Submit2 button clicked");
			if (this.currentQuestion.getClass() == MultipleChoice.class) {			
				this.currentQuestion.setChoice(getUserChoice(labelOp1, labelOp2, labelOp3, labelOp4));
				this.quiz.processNewQuestion(this.currentQuestion);
			}
			else if (this.currentQuestion.getClass() == Open.class) {			
				this.currentQuestion.setResponse(this.textQuizResponse.getText());
				this.textQuizResponse.setText("");
				this.quiz.processNewQuestion(this.currentQuestion);				
			}			
			updateUI();
		});
		
		this.buttonClear = new Button("Clear");
		buttonClear.setOnAction( a -> {
			System.out.println("Clear button got clicked");
			textName.setText("");
			textId.setText("");
			textTopics.setText("");
			textQuizSize.setText("");
			useOpenQuestions.setSelected(false);
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
		GridPane.setConstraints(buttonClear, 1, 1);
		layoutMain.getChildren().add(buttonClear);
		GridPane.setConstraints(useOpenQuestions, 1, 2);
		layoutMain.getChildren().add(useOpenQuestions);		
		sceneMain = new Scene(layoutMain, 600, 300);
		
		
		// Quiz MC Scene -------------------------------------------------		
		VBox layoutQuizMC = new VBox();
		layoutQuizMC.setPadding(new Insets(10, 10, 10, 10));
		layoutQuizMC.getChildren().add(this.progressBar);
		layoutQuizMC.getChildren().add(this.labelQuestionNumber);
		layoutQuizMC.getChildren().add(this.labelQuestion);
		layoutQuizMC.getChildren().add(this.labelOp1);
		layoutQuizMC.getChildren().add(this.labelOp2);
		layoutQuizMC.getChildren().add(this.labelOp3);
		layoutQuizMC.getChildren().add(this.labelOp4);
		layoutQuizMC.getChildren().add(this.buttonSubmitAnswer);		
		this.sceneQuizMC = new Scene(layoutQuizMC, 600, 300);
		
		// Quiz OPEN Scene -------------------------------------------------
		VBox layoutQuizOpen = new VBox();
		layoutQuizOpen.setPadding(new Insets(10, 10, 10, 10));
		layoutQuizOpen.getChildren().add(this.progressBar2);
		layoutQuizOpen.getChildren().add(this.labelQuestionNumber2);
		layoutQuizOpen.getChildren().add(this.labelQuestion2);
		layoutQuizOpen.getChildren().add(this.textQuizResponse);
		layoutQuizOpen.getChildren().add(this.buttonSubmitAnswer2);
		this.sceneQuizOpen = new Scene(layoutQuizOpen, 600, 300);
		
		// Summary Scene -------------------------------------------------
		VBox layoutSummary = new VBox();
		layoutSummary.setPadding(new Insets(10, 10, 10, 10));
		layoutSummary.getChildren().add(this.name);
		layoutSummary.getChildren().add(this.grade);
		layoutSummary.getChildren().add(this.questionCount);
		layoutSummary.getChildren().add(this.buttonHome);
		layoutSummary.getChildren().add(this.buttonExport);		
		this.sceneSummary = new Scene(layoutSummary, 600, 300);

		this.window.setScene(sceneMain);
		this.window.show();
	}  // End of Start()

	private void updateUI() {
		this.currentQuestion = this.quiz.getNewQuestion();
		
		if (this.currentQuestion == null) {
			this.currentQuestion = this.quiz.getNewQuestion();
			System.out.println("currentQuestion : " + this.currentQuestion.toString());
		}
		
		this.currentQuestion.setNumber(this.quiz.getUsedQuestions().size() + 1);
		System.out.println("updateUI->this.quiz.getProgress()" + this.quiz.getProgress());
		if (this.quiz.getProgress() >= 1.0) {
			this.name.setText("Student: " + this.quiz.getStudent().getName());
			this.grade.setText("Correct: " + Integer.toString(this.quiz.getGrade()));
			this.questionCount.setText("Total: " + Integer.toString(this.quiz.getUsedQuestions().size()));			
			this.window.setScene(sceneSummary);
			return;
		}
		else {
			this.progressBar.setProgress(this.quiz.getProgress());
			this.progressBar2.setProgress(this.quiz.getProgress());
		}
		
		if (this.currentQuestion.getClass() == MultipleChoice.class) {
			System.out.println("Question is a MCQ");	
			System.out.println("Options: " + this.currentQuestion.getOptions().toString());
			System.out.println("getNumber: " + this.currentQuestion.getNumber());
			System.out.println("getQuestion: " + this.currentQuestion.getQuestion());	
			
			this.labelQuestionNumber.setText("# " + this.currentQuestion.getNumber() );
			this.labelQuestion.setText(this.currentQuestion.getQuestion());
			this.labelOp1.setText(this.currentQuestion.getOptions().get(0));
			this.labelOp2.setText(this.currentQuestion.getOptions().get(1));
			this.labelOp3.setText(this.currentQuestion.getOptions().get(2));
			this.labelOp4.setText(this.currentQuestion.getOptions().get(3));	
			
			this.window.setScene(this.sceneQuizMC);
		}
		else if (this.currentQuestion.getClass() == Open.class) {
			System.out.println("Question is a OPEN");
			this.labelQuestionNumber2.setText("# " + this.currentQuestion.getNumber() );
			this.labelQuestion2.setText(this.currentQuestion.getQuestion());			
			this.window.setScene(this.sceneQuizOpen);
		}

	}  // End of updateQuizUI()
	
	private int getUserChoice(RadioButton labelOp1, RadioButton labelOp2, 
								RadioButton labelOp3, RadioButton labelOp4) {
		int ret = 0;
		if (labelOp1.isSelected()) {
			ret = 1;
			labelOp1.setSelected(false);
		} 
		if (labelOp2.isSelected()) {
			ret = 2;
			labelOp2.setSelected(false);
		} 
		if (labelOp3.isSelected()) {
			ret = 3;
			labelOp3.setSelected(false);
		} 
		if (labelOp4.isSelected()) {
			ret = 4;
			labelOp4.setSelected(false);
		}
		
		return ret;
	}  // End of getUserChoice()
}
