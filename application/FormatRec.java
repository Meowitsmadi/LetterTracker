package application;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FormatRec {
	private StringProperty firstName;
	private StringProperty lastName;
	private String gender;
	private String firstSem;
	private String program;
	private String targetSchool;
	private String firstYear;
	private String currentDate;
	private ArrayList<String> course;
	ArrayList<String> courseGrade;
	private ArrayList<String> personalChar;
	private ArrayList<String> academicChar;
	
	public FormatRec(String firstName, String lastName, String gender, String firstSem, String program, String targetSchool, String firstYear,
			String currentDate, ArrayList<String> course, ArrayList<String> courseGrade, ArrayList<String> personalChar, ArrayList<String> academicChar) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        if(gender.equals("Male")) {
            this.gender = "He";
        }
        else {
            this.gender = "She";
        }
        this.firstSem = firstSem;
        this.program = program;
        this.targetSchool = targetSchool;
        this.firstYear = firstYear;
        this.currentDate = currentDate;
        this.course = course;
        this.courseGrade = courseGrade;
        this.personalChar = personalChar;
        this.academicChar = academicChar;
    }
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public String formatCourse() {
	String out ="";
	int i = 1;
	out += String.format("%s also earned ", gender);
	while(i<course.size()) {
		if(i!=course.size()-1) { 
			out+=String.format("%s from my %s, ", courseGrade.get(i), course.get(i));
		}
		else { out+=String.format("and %s from my %s", courseGrade.get(i), course.get(i)); }
		i++;
	}
	return out+".\n";
	}

public String formatAcaChara() {
	String out = String.format("%s %s",firstName.get(), academicChar.get(0));
	for(int i=1; i<academicChar.size();i++) {
		if(i!=course.size()-1) { 
			out+= String.format("%s, ", academicChar.get(i));
		}
		else { out+= String.format("and %s", academicChar.get(i)); }	
	}
	return out+".\n\n";
	
}

public String formatPerChara() {
	String out = String.format("%s was always %s", gender, personalChar.get(0));
	for(int i=1; i<personalChar.size();i++) {
		if(i!=course.size()-1) { 
			out+= String.format("%s, ", personalChar.get(i));
		}
		else { out+= String.format("and %s.\n", personalChar.get(i)); }	
	}
	return out+".\n\n";
	
}

public String toString() {
		String out = String.format(""
			+ "Letter of Recommendation\r\n\n"
			+ "For: %s %s\r\n"
			+ "Date: %s\r\n"
			+ "To: Graduate Admissions Committee\r\n\n"
			+ "I am writing this letter to recommend my former student %s %s who is applying for the\r\n"
			+ "%s program in your school. \r\n\n"
			+ "I met %s in %s when he enrolled in my \"%s\" course.\r\n\n"
			+ "%s earned \"%s\" from this tough course, and this shows how knowledgeable and hard worker %s is. \r\n", firstName.get(), lastName.get(), currentDate, firstName.get(), lastName.get(), 
			   program, firstName.get(), firstSem, course.get(0), firstName.get(), courseGrade.get(0), gender.toLowerCase());
	if(course.size()>1) {
		out+= formatCourse();
	}
	out+=formatAcaChara();
	out+=formatPerChara();
	
	out+= String.format("Furthermore, I noticed from the term project result, %s developed leadership, time \r\n"
			+ "management, and problem-solving skills. %s worked effectively with the team members and \r\n"
			+ "delegated tasks appropriately. They were able to deliver a successful project in a timely fashion.\r\n\n"
			+ "I believe that %s has the capacity to excel at higher education program and this \r\n"
			+ "is my pleasure to highly recommend him. \r\n\n"
			+ "Please do not hesitate to contact me with further questions.\r\n\n\n\n"
			+ "Very Respectfully,", gender.toLowerCase(), gender, firstName.get());
	return out;
	}
}
