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
					" pass TEXT)";
			
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
	
	public static void InsertData() throws SQLException {
		c = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
		Statement st = null;
		
		String test = "INSERT OR IGNORE INTO userInfo(id, pass) VALUES(1, \"p\")";
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
		InsertData();
//		InsertData("letterTrackerInfo", "userInfo", "id", String.valueOf(1));
//		InsertData("letterTrackerInfo","userInfo", "pass", "\"p\"");
	}
	
	public static void main(String[] args) throws Exception{
		
	}
	
}
