package application;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
	private IntegerProperty id;
	private StringProperty firstName;
	private StringProperty lastName;
	private String lor;
	private String gender;
	private String firstSem;
	private String program;
	private String targetSchool;
	private String firstYear;
	private String currentDate;
	private ArrayList<String> courses;
	private ArrayList<String> courseGrades;
	private ArrayList<String> personalChar;
	private ArrayList<String> academicChar;
	
	public Student(int id, String firstName, String lastName) {
    	this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }
	
	public Student(String firstName, String lastName, String gender, String firstSem, String program, String targetSchool,
			String firstYear, String currentDate, ArrayList<String> courses, ArrayList<String> courseGrades,
			ArrayList<String> personalChar, ArrayList<String> academicChar, String lor) {
		
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
		this.lor = lor;
		this.gender =  gender;
		this.firstSem = firstSem;
		this.program = program;
		this.targetSchool = targetSchool;
		this.firstYear = firstYear;
		this.currentDate = currentDate;
		this.courses = courses;
		this.courseGrades = courseGrades;
		this.personalChar = personalChar;
		this.academicChar = academicChar;
    }
	
    public Student() {}
	
	public IntegerProperty idProperty() { 
         if (id == null) id = new SimpleIntegerProperty(this, "id");
         return id; 
     }
	public int getId() { return idProperty().get(); }
	public void setId(int value) { idProperty().set(value); }
	
	public StringProperty firstNameProperty() { 
         if (firstName == null) firstName = new SimpleStringProperty(this, "firstName");
         return firstName; 
     }
	public String getFirstName() { return firstNameProperty().get(); }
	public void setFirstName(String value) { firstNameProperty().set(value); }
	
	public StringProperty lastNameProperty() { 
         if (lastName == null) lastName = new SimpleStringProperty(this, "lastName");
         return lastName; 
     }
	public String getLastName() { return lastNameProperty().get(); }
	public void setLastName(String value) { lastNameProperty().set(value); }
	
	public String getLOR() { return lor; }
	
	public String getGender() {return gender;}
	public String getFirstSem() {return firstSem;}
	public String getProgram() {return program;}
	public String getTargetSchool() {return targetSchool;}
	public String getFirstYear() {return firstYear;}
	public String getCurrentDate() {return currentDate;}
	public String getCourses() {
		String out = "";
		for (String course: courses)
			{out += course + "/";}
		return out;}
	public String getCourseGrades() {
		String out = "";
		for (String grades: courseGrades)
			{out += grades + "/";}
		return out;}
	public String getPersonalChar() {
		String out = "";
		for (String PChar: personalChar)
			{out += PChar + "/";}
		return out;}
	public String getAcademicChar() {
		String out = "";
		for (String AChar: academicChar)
			{out += AChar + "/";}
		return out;}
}
