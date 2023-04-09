package sql;

import java.sql.*;

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
					" info TEXT)";
			
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
	
	public static void PopulateInitialData() throws SQLException {
		createDatabase("letterTrackerInfo.db");
		createTable("letterTrackerInfo", "userInfo");
		createTable("letterTrackerInfo", "userData");
		createTable("letterTrackerInfo", "semester");
		createTable("letterTrackerInfo", "courses");
		createTable("letterTrackerInfo", "programs");
		createTable("letterTrackerInfo", "personalChara");
		createTable("letterTrackerInfo", "academicChara");
		
		InsertData("userInfo", "p");
		
		InsertData("userData", "Ahmad  Yazdankhah");
		InsertData("userData", "Lecturer");
		InsertData("userData", "SJSU, CS Department");
		InsertData("userData", "ahmad.yazdankhah@sjsu.edu");
		InsertData("userData", "(123) 456-7890");
		
		InsertData("semester", "Spring");
		InsertData("semester", "Summer");
		InsertData("semester", "Fall");
		InsertData("semester", "Winter");
		
		InsertData("courses", "CS151: Object-Oriented Design");
		InsertData("courses", "CS166: Information Security");
		InsertData("courses", "CS154: Theory of Computation");
		InsertData("courses", "CS160: Software Engineering");
		InsertData("courses", "CS256: Cryptography");
		InsertData("courses", "CS146: Data Structures and Algorithmns");
		InsertData("courses", "CS152: Programming Languages Paradigm");
		
		InsertData("programs", "Master of Science (MS)");
		InsertData("userInfo", "Master of Business administration (MBA)");
		InsertData("userInfo", "Doctor of philosophy (PhD)");
		
		InsertData("personalChara", "very passionate");
		InsertData("personalChara", "very enthusiastic");
		InsertData("personalChara", "punctual");
		InsertData("personalChara", "attentive");
		InsertData("personalChara", "polite");
		
		InsertData("academicChara", "submitted well-written assignments");
		InsertData("academicChara", "participated in all of my class activities");
		InsertData("academicChara", "worked hard");
		InsertData("academicChara", "was very well prepared for every exam and assignment");
		InsertData("academicChara", " picked up new skills quickly");
		InsertData("academicChara", " was able to excel academically at the top of my class");

	}
	
	public static void main(String[] args) throws Exception{
		
	}
	
}
