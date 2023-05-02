package sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccessFunctions {

	
	public static void DeleteRecommendation(int entryID) throws SQLException, IOException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
		PreparedStatement pst = null;
		
		// deletes from the DB based on ID
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
		}		
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst.close();
			rs.close();
		}
		
		return studentData;
	}
	
	public static Student getData(int id) throws SQLException, IOException {
		ObservableList<Student> studentData = FXCollections.observableArrayList();
		Connection conn = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		// selects from recommendation students
		String query = "SELECT * FROM recommendations WHERE ID = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(0, id); // selects when searchedName = LastName in DB
			rs = pst.executeQuery();
			while (rs.next()) { //there exists an entry
				Student student = new Student(rs.getInt("ID"), rs.getString("FirstName"), rs.getString("LastName"));
				studentData.add(student); // adding student into list
			}
		}		
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst.close();
			rs.close();
		}
		
		return studentData.get(0);
	}
	
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
		
		// selects from recommendation students
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
			pst.setString(1, first); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();

			pst = conn.prepareStatement(query2);
			pst.setString(1, last); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query3);
			pst.setString(1, gen); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query4);
			pst.setString(1, sem); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query5);
			pst.setString(1, pro); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query6);
			pst.setString(1, ts); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query7);
			pst.setString(1, year); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query8);
			pst.setString(1, date); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query9);
			pst.setString(1, course); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query10);
			pst.setString(1, courseG); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query11);
			pst.setString(1, perC); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query12);
			pst.setString(1, acaC); // selects when searchedName = LastName in DB
			pst.executeUpdate();
			pst.close();
			
			pst = conn.prepareStatement(query13);
			pst.setString(1, s.toString()); // selects when searchedName = LastName in DB
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
