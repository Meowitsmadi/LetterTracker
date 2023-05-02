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
	
	// inserts data from student's form into the DB
	public static void InsertFormData(Student s) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
		PreparedStatement pst = null;
		
		String query = "INSERT OR IGNORE INTO recommendations(firstName, lastName, gender, firstSem, program, targetSchool, firstYear, currentDate,"
					+ " courses, courseGrades, personalChar, academicChar) "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1,s.getFirstName());
			pst.setString(2,s.getLastName());
			pst.setString(3,s.getGender()); 
			pst.setString(4,s.getFirstSem());
			pst.setString(5,s.getProgram()); 
			pst.setString(6,s.getTargetSchool());
			pst.setString(7,s.getFirstYear());
			pst.setString(8,s.getCurrentDate()); 
			pst.setString(9,s.getCourses()); 
			pst.setString(10,s.getCourseGrades()); 
			pst.setString(11,s.getPersonalChar()); 
			pst.setString(12,s.getAcademicChar()); 
			pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst.close();
		}
	}
	
	public static void EditRecommendation(Student s, int entryID) throws SQLException, IOException {
		// switch to create new lor BUT without the new
		System.out.println("first");
		String last = s.getLastName();
		String first = s.getFirstName();
		System.out.println("last");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
		Statement pst = null;
		
		// selects from recommendation students
		String query1 = "UPDATE recommendations SET lastName = ? WHERE id = "+entryID+";";
		String query2 = "UPDATE recommendations SET firstName = ? WHERE id = "+entryID+";";
		try {
			pst = conn.createStatement();
			pst.executeUpdate(query1);
			pst.executeUpdate(query2);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst.close();
		}
	}
	
}