package sql;

import java.sql.*;
import java.util.ArrayList;

import org.controlsfx.control.CheckListView;

import application.ManageDataLORController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class sqliteDemo {
	
	private static Connection c;
	
	// connect to the letterTrackerInfo database when method is called.
	public static Connection connect() {
		c = null;
		
		try {
			Class.forName("org.sqlite.JDBC"); // loading the driver
			c = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
			System.out.println("SQLite DB connected");
		}
		catch(Exception e){
			System.out.println(e);
		}
		return c;
	}
	
	// create database file using inputed string
	public static void createDatabase(String fileName) {
		String url = "jdbc:sqlite:" + fileName;

        try (Connection c = DriverManager.getConnection(url)) {
            if (c != null) {
                DatabaseMetaData meta = c.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        }
        catch(Exception e){
    		System.out.println(e);
   		}
	}
	
	// create table inside given database with given name.
	public static void createTable(String dbName, String tableName) {
		c = null;
		Statement st = null;
		try {
			String sql = "CREATE TABLE IF NOT EXISTS " + tableName +
					"(id INTEGER PRIMARY KEY," +
					" info TEXT UNIQUE)";
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
			System.out.println("Database Opened...\n");
			st = c.createStatement();
			
			st.executeUpdate(sql);
			st.close();
			c.close();
			System.out.println(tableName + " was created successfully.");
		}
		catch(Exception e){
    		System.out.println(e);
   		}
	}
	
	// create table exclusively for recommendations.
	public static void createRecTable(String dbName) {
		c = null;
		Statement st = null;
		try {
			String sql = "CREATE TABLE IF NOT EXISTS recommendations" +
					"(id INTEGER PRIMARY KEY," +
					" firstName TEXT," +
					" lastName TEXT," +
					" LOR TEXT)";
				
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
			System.out.println("Database Opened...\n");
			st = c.createStatement();
			
			st.executeUpdate(sql);
			st.close();
			c.close();
			System.out.println("recommendations was created successfully.");
		}
		catch(Exception e){
	    	System.out.println(e);
	   	}
	}
	
	public static void InsertData(String table, String data) throws SQLException {
		c = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
		Statement st = null;
		String test = "";
		if(table == "userInfo") {
			test = "INSERT OR IGNORE INTO "+table+"(id, info) VALUES(1, \"p\")";
		}
		else {
			test = "INSERT OR IGNORE INTO "+table+"(info) VALUES(\""+ data +"\")";
		}
		//String query = String.format("INSERT INTO %s(%s) VALUES(%s)", table, column, value);
		try {
			st = c.createStatement();
			st.execute(test);
			st.close();
			c.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	public static void InsertRecData(String table, String firstName, String lastName, String LOR) throws SQLException {
	c = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
	Statement st = null;
	String test = "";
	test = "INSERT OR IGNORE INTO "+table+"(firstName, lastName, LOR) VALUES(\""+ firstName +"\", \""+ lastName +"\", \""+ LOR +"\")";
	try {
		st = c.createStatement();
		st.execute(test);
		st.close();
		c.close();
		
		}
	catch (SQLException e) {
		e.printStackTrace();
		}
	} 
	
	public static ArrayList<String> getAllData(String table) {
		ArrayList<String> tableValues = new ArrayList<>();
		try {
			java.sql.Statement stm1 = c.createStatement();
	        java.sql.ResultSet result1 = null;
			result1 = stm1.executeQuery("select * from "+table);
			while(result1.next())
			{
			       // System.out.println(2);
			        tableValues.add(result1.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //table information query
        return tableValues;
      }
	
	public static void PopulateInitialData() throws SQLException {
		// initialize database
		createDatabase("letterTrackerInfo.db");
		
		//initial tables in database
		createTable("letterTrackerInfo", "userInfo");
		createTable("letterTrackerInfo", "userData");
		createTable("letterTrackerInfo", "semester");
		createTable("letterTrackerInfo", "courses");
		createTable("letterTrackerInfo", "programs");
		createTable("letterTrackerInfo", "personalChara");
		createTable("letterTrackerInfo", "academicChara");
		createTable("letterTrackerInfo", "gender");
		createRecTable("letterTrackerInfo");
			    
		// initialize user login info
		InsertData("userInfo", "p");
		
		InsertData("gender", "Male");
		InsertData("gender", "Female");
		
		// initialize faculty data in database
		InsertData("userData", "Ahmad  Yazdankhah");
		InsertData("userData", "Lecturer");
		InsertData("userData", "SJSU, CS Department");
		InsertData("userData", "ahmad.yazdankhah@sjsu.edu");
		InsertData("userData", "(123) 456-7890");
		
		//initialize semesters data in database
		InsertData("semester", "Spring");
		InsertData("semester", "Summer");
		InsertData("semester", "Fall");
		InsertData("semester", "Winter");
		
		//initialize courses data in database
		InsertData("courses", "CS151: Object-Oriented Design");
		InsertData("courses", "CS166: Information Security");
		InsertData("courses", "CS154: Theory of Computation");
		InsertData("courses", "CS160: Software Engineering");
		InsertData("courses", "CS256: Cryptography");
		InsertData("courses", "CS146: Data Structures and Algorithmns");
		InsertData("courses", "CS152: Programming Languages Paradigm");
		
		//initialize programs data in database
		InsertData("programs", "Master of Science (MS)");
		InsertData("programs", "Master of Business administration (MBA)");
		InsertData("programs", "Doctor of philosophy (PhD)");
		
		//initialize semester data in database
		InsertData("personalChara", "very passionate");
		InsertData("personalChara", "very enthusiastic");
		InsertData("personalChara", "punctual");
		InsertData("personalChara", "attentive");
		InsertData("personalChara", "polite");
		
		//initialize semester data in database
		InsertData("academicChara", "submitted well-written assignments");
		InsertData("academicChara", "participated in all of my class activities");
		InsertData("academicChara", "worked hard");
		InsertData("academicChara", "was very well prepared for every exam and assignment");
		InsertData("academicChara", " picked up new skills quickly");
		InsertData("academicChara", " was able to excel academically at the top of my class");

	}
	
	public static void setOptions(ComboBox<String> genders, ComboBox<String> semesters, ComboBox<String> programs,
			CheckListView<String> courses, CheckListView<String> personalChar, CheckListView<String> academicChar) {
		genders.setItems(FXCollections.observableArrayList(sqliteDemo.getAllData("gender")));
		semesters.setItems(FXCollections.observableArrayList(sqliteDemo.getAllData("semester")));
		programs = ManageDataLORController.setAddData(programs, "programs");
		courses = ManageDataLORController.setAddData(courses, "courses");
		personalChar = ManageDataLORController.setAddData(personalChar, "personalChara");
		academicChar = ManageDataLORController.setAddData(academicChar, "academicChara");
		
	}
}
