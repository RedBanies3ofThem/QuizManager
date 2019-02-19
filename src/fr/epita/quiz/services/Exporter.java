package fr.epita.quiz.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import fr.epita.quiz.datamodel.MultipleChoice;


public class Exporter {

	private PrintWriter writer;
	private Scanner scanner;
	private File file;

	public Exporter(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		this.setFile(file);
		this.writer = new PrintWriter(new FileOutputStream(file, true));
		this.setScanner(new Scanner(file));
	}

	public void writeQuestion(MultipleChoice question) {
		writer.println("-------Question #" + question.getNumber() + "-------");
		writer.println(question.getQuestion());
		writer.println("");
		writer.println("A) " + question.getOptions().get(0));
		writer.println("B) " + question.getOptions().get(1));
		writer.println("C) " + question.getOptions().get(2));
		writer.println("D) " + question.getOptions().get(3));
		writer.println("");
		writer.flush();
	}
	
	public void write(String msg, int num) {
		writer.println("");
		writer.println(msg + " : " + num);
		writer.flush();
	}
	
	public void write(String msg, String num) {
		writer.println("");
		writer.println(msg + " : " + num);
		writer.flush();
	}
	
	public void write(String msg, double num) {
		writer.println("");
		writer.println(msg + " : " + num);
		writer.flush();
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
