package fr.epita.quiz.datamodel;

import java.util.List;

/** Class to capture student information before taking a quiz
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
public class Student {

	private String name;
	private String id;
	
	/** Default constructor
	 * @param name (String) name of student
	 * @param id (String) student id number
	 */
	public Student(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	/** Getter for the name of the student
	 * @return (String) student's name
	 */
	public String getName() {
		return name;
	}
	
	/** Set the student's name
	 * @param name student's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** Getter for a student's ID
	 * @return (String) student id
	 */
	public String getId() {
		return id;
	}
	
	/** Setter for a student's ID
	 * @param id (String) student ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
