package sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import application.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccessFunctions {

	// Deletes the recommendation in the database associated with the student's ID.
	public static void DeleteRecommendation(int entryID) throws SQLException, IOException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
		PreparedStatement pst = null;
		
		String query = "DELETE FROM recommendations WHERE id = ?"; 
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1,entryID);
			pst.executeUpdate();
		}		
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst.close();
		}
	}
	
	// returns a list of students where the searched name equals the last name in the database.
	public static ObservableList<Student> getData(String searchedName) throws SQLException, IOException {
		ObservableList<Student> studentData = FXCollections.observableArrayList();
		Connection conn = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		// selects from recommendation students
		String query = "SELECT * FROM recommendations WHERE LastName = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, searchedName); // selects when searchedName = LastName in DB
			rs = pst.executeQuery();
			while (rs.next()) { //there exists an entry
				Student student = new Student(rs.getInt("ID"), rs.getString("FirstName"), rs.getString("LastName"));
				studentData.add(student); // adding student into list
			}
			rs.close();
		}		
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst.close();
		}
		
		return studentData;
	}
	
	// // returns a student with the associated ID from the database.
	public static Student getData(int id) throws SQLException, IOException {
		ObservableList<Student> studentData = FXCollections.observableArrayList();
		Connection conn = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		// selects from recommendation students
		String query = "SELECT * FROM recommendations WHERE ID = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id); // selects when id = ID in the database.
			rs = pst.executeQuery();
			List<String> course = Arrays.asList(rs.getString("courses").split("/")); // splits up data into multiple strings
			List<String> courseG = Arrays.asList(rs.getString("courseGrades").split("/"));
			List<String> perC = Arrays.asList(rs.getString("personalChar").split("/"));
			List<String> acaC = Arrays.asList(rs.getString("academicChar").split("/"));
			
			while (rs.next()) { //there exists an entry
				Student student = new Student(rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getString("firstSem"),
						rs.getString("program"), rs.getString("targetSchool"), rs.getString("firstYear"), rs.getString("currentDate"), new ArrayList<String>(course),
						new ArrayList<String>(courseG), new ArrayList<String>(perC), new ArrayList<String>(acaC));
				studentData.add(student); // adding student into list
			}
			rs.close();
		}		
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst.close();
		}
		System.out.println(studentData.size());
		return studentData.get(0); // gets the first student in the list and returns it. 
	}
	
	// Updates the previous form data in the database.
	public static void EditRecommendation(Student s, int entryID) throws SQLException, IOException {
		// switch to create new lor BUT without the new
		String last = s.getLastName();
		String first = s.getFirstName();
		String gen = s.getGender();
		String sem = s.getFirstSem();
		String pro = s.getProgram();
		String ts = s.getTargetSchool();
		String year = s.getFirstYear();
		String date = s.getCurrentDate();
		String course = s.getCourses();
		String courseG = s.getCourseGrades();
		String perC = s.getPersonalChar();
		String acaC = s.getAcademicChar();
		Connection conn = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
		PreparedStatement pst = null;
		
		// updates current data in the recommendations database
		String query1 = "UPDATE recommendations SET firstName = ? WHERE id = "+entryID+";";
		String query2 = "UPDATE recommendations SET lastName = ? WHERE id = "+entryID+";";
		String query3 = "UPDATE recommendations SET gender = ? WHERE id = "+entryID+";";
		String query4 = "UPDATE recommendations SET firstSem = ? WHERE id = "+entryID+";";
		String query5 = "UPDATE recommendations SET program = ? WHERE id = "+entryID+";";
		String query6 = "UPDATE recommendations SET targetSchool = ? WHERE id = "+entryID+";";
		String query7 = "UPDATE recommendations SET firstYear = ? WHERE id = "+entryID+";";
		String query8 = "UPDATE recommendations SET currentDate = ? WHERE id = "+entryID+";";
		String query9 = "UPDATE recommendations SET courses = ? WHERE id = "+entryID+";";
		String query10 = "UPDATE recommendations SET courseGrades = ? WHERE id = "+entryID+";";
		String query11 = "UPDATE recommendations SET personalChar = ? WHERE id = "+entryID+";";
		String query12 = "UPDATE recommendations SET academicChar = ? WHERE id = "+entryID+";";
		String query13 = "UPDATE recommendations SET LOR = ? WHERE id = "+entryID+";";
		try {
			pst = conn.prepareStatement(query1);
			pst.setString(1, first); // updates the first name in the database
			pst.executeUpdate();
			pst.close();

			pst = conn.prepareStatement(query2);
			pst.setString(1, last); // updates the last name in the database
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query3);
			pst.setString(1, gen); // updates the gender in the database
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query4);
			pst.setString(1, sem); // updates the student's first semester in the database
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query5);
			pst.setString(1, pro); // updates the student's program in the database
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query6);
			pst.setString(1, ts); // updates the student's target school in the database
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query7);
			pst.setString(1, year); // updates the student's first year in the database
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query8);
			pst.setString(1, date); // updates the date in the database
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query9);
			pst.setString(1, course); // updates the student's course in the database
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query10);
			pst.setString(1, courseG); // updates the student's course grade in the database
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query11);
			pst.setString(1, perC); // updates the student's personal characteristics in the database
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query12);
			pst.setString(1, acaC); // updates the student's academic characteristics in the database
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query13);
			pst.setString(1, s.toString()); // updates the letter of recommendation text in the database
			pst.executeUpdate();
			pst.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst.close();
		}
	}
	
}
