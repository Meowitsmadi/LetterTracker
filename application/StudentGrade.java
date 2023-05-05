package application;

import javafx.scene.control.TextField;

public class StudentGrade {
		private String course;
		private TextField grade;
		
		public StudentGrade(String course, TextField grade) {
			this.course = course;
			this.grade = grade;
		}
		
		public TextField getGrade() {
			return grade;
		}
		
		public String getCourse() {
			return course;
		}
		
}
