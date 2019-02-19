package fr.epita.quiz.launcher;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import fr.epita.quiz.datamodel.MultipleChoice;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.datamodel.Student;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Launcher extends Application {

	private Button buttonCreateTest, buttonSubmitAnswer;
	private Stage window;
	private Scene sceneMain, sceneQuiz;
	private Quiz quiz;
	private MultipleChoice currentQuestion;

	public static void main(String[] args) {
		System.out.println("Running main method");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.window = primaryStage;
		
		// Main Scene -------------------------------------------------
		
		this.window.setTitle("Quiz Manager");
		
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
		RadioButton labelOp1 = new RadioButton();
		RadioButton labelOp2 = new RadioButton();
		RadioButton labelOp3 = new RadioButton();
		RadioButton labelOp4 = new RadioButton();
		
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
			Student student = new Student(textName.getText(), textId.getText());
			List<String> topics = new LinkedList<String>(Arrays.asList(textTopics.getText().split(",")));
			this.quiz = new Quiz(student, topics, 5);
			this.quiz.loadNewQuiz();
			
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
		Button clear = new Button("Clear");
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
			
			if (this.quiz.getProgress() >= 1.0d) {
				this.window.setScene(sceneMain);
			}
			
			
		});
		
		VBox layoutQuiz = new VBox();
		layoutQuiz.getChildren().add(labelQuestionNumber);
		layoutQuiz.getChildren().add(labelQuestion);
		layoutQuiz.getChildren().add(labelOp1);
		layoutQuiz.getChildren().add(labelOp2);
		layoutQuiz.getChildren().add(labelOp3);
		layoutQuiz.getChildren().add(labelOp4);
		layoutQuiz.getChildren().add(this.buttonSubmitAnswer);
		
		sceneQuiz = new Scene(layoutQuiz, 500, 300);
		
		this.window.show();
		
	}

	private void updateQuizUI(Label labelQuestionNumber, Label labelQuestion, 
			RadioButton labelOp1, RadioButton labelOp2, RadioButton labelOp3, RadioButton labelOp4) {
		this.currentQuestion = this.quiz.getNewQuestion(1);
		labelQuestionNumber.setText("# " + this.currentQuestion.getNumber() );
		labelQuestion.setText(this.currentQuestion.getQuestion());
		labelOp1.setText(this.currentQuestion.getOptions().get(0));
		labelOp2.setText(this.currentQuestion.getOptions().get(1));
		labelOp3.setText(this.currentQuestion.getOptions().get(2));
		labelOp4.setText(this.currentQuestion.getOptions().get(3));
	}
	
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
			ret =  4;
		}

		labelOp1.setSelected(false);
		labelOp2.setSelected(false);
		labelOp3.setSelected(false);
		labelOp4.setSelected(false);
		
		return ret;
	}
}
