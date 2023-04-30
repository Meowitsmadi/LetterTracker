package test;

import java.util.ArrayList;

public class LOR {
	private String firstName;
	private String lastName;
	private String gender;
	private String firstSem;
	private String program;
	private String targetSchool;
	private String currentDate;
	private ArrayList<String> course;
	private ArrayList<String> courseGrade;
	private ArrayList<String> personalChar;
	private ArrayList<String> academicChar;
	
	public LOR(String firstName, String lastName, String gender, String firstSem, String program, 
			String targetSchool, String currentDate,ArrayList<String> course, ArrayList<String> courseGrade,
			ArrayList<String> personalChar, ArrayList<String> academicChar) {
		this.firstName = firstName;
		this.lastName = lastName;
		if(gender.equals("Male")) {
			this.gender = "He";
		}
		else {
			this.gender = "She";
		}
		this.firstSem = firstSem;
		this.program = program;
		this.targetSchool = targetSchool;
		this.currentDate = currentDate;
		this.course = course;
		this.courseGrade = courseGrade;
		this.personalChar = personalChar;
		this.academicChar = academicChar;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setFirstSem(String firstSem) {
		this.firstSem = firstSem;
	}
	
	public String getFirstSem() {
		return firstSem;
	}
	
	public void setProgram(String program) {
		this.program = program;
	}
	
	public String getProgram() {
		return program;
	}
	
	public void setTargetSchool(String targetSchool) {
		this.targetSchool = targetSchool;
	}
	
	public String getTargetSchool() {
		return targetSchool;
	}
	
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
	public String getCurrentDate() {
		return currentDate;
	}
	
	public void setCourse(ArrayList<String> course) {
		this.course = course;
	}
	
	public ArrayList<String> getCourse() {
		return course;
	}
	
	public void setCourseGrade(ArrayList<String> courseGrade) {
		this.courseGrade = courseGrade;
	}
	
	public ArrayList<String> getCourseGrade() {
		return courseGrade;
	}
	
	public void setPersonalChar(ArrayList<String> personalChar) {
		this.personalChar = personalChar;
	}
	
	public ArrayList<String> getPersonalChar() {
		return personalChar;
	}
	
	public void setAcademicChar(ArrayList<String> academicChar) {
		this.academicChar = academicChar;
	}
	
	public ArrayList<String> getAcademicChar() {
		return academicChar;
	}
	
	public String toString() {
		String out = String.format(""
				+ "Letter of Recommendation\r\n"
				+ "For: %s %s\r\n"
				+ "Date: %s\r\n"
				+ "To: Graduate Admissions Committee\r\n"
				+ "I am writing this letter to recommend my former student %s %s who is applying for the\r\n"
				+ "%s program in your school. \r\n"
				+ "I met %s in %s when he enrolled in my \"%s\"\r\n"
				+ "course. \r\n"
				+ "%s earned \"%s\" from this tough course, and this shows how knowledgeable and hard worker %s is. \r\n", firstName, lastName, currentDate, firstName, lastName, 
				   program, firstName, firstSem, course.get(0), firstName, courseGrade.get(0), gender.toLowerCase());
		if(course.size()>1) {
			int i = 1;
			out += String.format("%s also earned ", gender);
			while(i<course.size()) {
				out+=String.format("%s from my %s", courseGrade.get(i), course.get(i));
			}
		
		}
		return out;
	}
}
