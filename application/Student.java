package application;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sql.sqliteDemo;

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
	private ArrayList<String> course;
	private ArrayList<String> courseGrade;
	private ArrayList<String> personalChar;
	private ArrayList<String> academicChar;
	
	public Student(int id, String firstName, String lastName) {
    	this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }

	
	public Student(String firstName, String lastName, String gender, String firstSem, String program, String targetSchool,
			String firstYear, String currentDate, ArrayList<String> course, ArrayList<String> courseGrade,
			ArrayList<String> personalChar, ArrayList<String> academicChar) {
		
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
		//this.lor = lor;
		if(gender.equals("Male")) {
            this.gender = "He";
        }
        else {
            this.gender = "She";
        }
//		this.gender =  gender;
		this.firstSem = firstSem;
		this.program = program;
		this.targetSchool = targetSchool;
		this.firstYear = firstYear;
		this.currentDate = currentDate;
		this.course = course;
		this.courseGrade = courseGrade;
		this.personalChar = personalChar;
		this.academicChar = academicChar;
		lor = this.getString();
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
		for (String courses: course)
			{out += courses + "/";}
		return out;}
	public String getCourseGrades() {
		String out = "";
		for (String grades: courseGrade)
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

	public String formatCourse() {
	String out ="\n";
	int i = 1;
	out += String.format("%s also earned ", gender);
	while(i<course.size()) {
		if(course.size()==2) {
			out+=String.format("'%s' from my '%s'", courseGrade.get(i), course.get(i));
		}
		else if(i!=course.size()-1){
			out+=String.format(", '%s' from my '%s'", courseGrade.get(i), course.get(i));	
		}
		else { out+=String.format(", and '%s' from my '%s'", courseGrade.get(i), course.get(i)); }
		i++;
	}
	return out+".\n";
	}

	public String formatAcaChara() {
		String out = String.format("\n%s %s",firstName.get(), academicChar.get(0));
		for(int i=1; i<academicChar.size();i++) {
			if(i!=academicChar.size()-1) { 
				out+= String.format(", %s", academicChar.get(i));
			}
			else { out+= String.format(", and %s", academicChar.get(i)); }	
		}
		return out+".\n\n";
	
	}

	public String formatPerChara() {
		String out = String.format("%s was always %s", gender, personalChar.get(0));
		for(int i=1; i<personalChar.size();i++) {
			if(i!=personalChar.size()-1) { 
				out+= String.format(", %s", personalChar.get(i));
			}
			else { out+= String.format(", and %s", personalChar.get(i)); }	
		}
		return out+".\n\n";
	
	}
	
	public String signature() {
		ArrayList<String> data = sqliteDemo.getAllData("userData");
		String out = data.get(0);
		out += String.format("\n\n%s\n%s\n%s\n%s", data.get(1), data.get(2), data.get(3), data.get(4));
		return out;
	}
	
	public String altGender() {
		if(gender.equals("He")) {
			return "him";
		}
		return "her";
	}
	
	public void setLor(String lor) {
		this.lor = lor;
	}
	
	public String getLor() {
		return lor;
	}
	
	private String getString() {
		String out = String.format(""
			+ "Letter of Recommendation\r\n\n"
			+ "For: %s %s\r\n"
			+ "Date: %s\r\n"
			+ "To: Graduate Admissions Committee\r\n\n"
			+ "I am writing this letter to recommend my former student %s %s who is applying for the "
			+ "%s program in your school. \r\n\n"
			+ "I met %s %s in %s when %s enrolled in my \"%s\" course.\r\n\n"
			+ "%s earned \"%s\" from this tough course, and this shows how knowledgeable and hard worker %s is. \r\n", firstName.get(), lastName.get(), currentDate, firstName.get(), lastName.get(), 
			   program, firstName.get(), firstSem, firstYear, gender.toLowerCase(), course.get(0), firstName.get(), courseGrade.get(0), gender.toLowerCase());
	if(course.size()>1) {
		out+= formatCourse();
	}
	out+=formatAcaChara();
	out+=formatPerChara();
	
	out+= String.format("Furthermore, I noticed from the term project result, %s developed leadership, time "
			+ "management, and problem-solving skills. %s worked effectively with the team members and "
			+ "delegated tasks appropriately. They were able to deliver a successful project in a timely fashion.\r\n\n"
			+ "I believe that %s has the capacity to excel at higher education program and this "
			+ "is my pleasure to highly recommend %s. \r\n\n"
			+ "Please do not hesitate to contact me with further questions.\r\n\n\n"
			+ "Very Respectfully,\n\n", gender.toLowerCase(), gender, firstName.get(), altGender());
	
	out+= signature();
	return out;
	}
}
