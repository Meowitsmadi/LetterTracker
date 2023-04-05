package sql;

import java.sql.*;

public class sqliteDemo {
	
	// connect to database given when method is called.
	public static void connect(String dbName) {
		Connection c = null;
		
		try {
			Class.forName("org.sqlite.JDBC"); // loading the driver
			c = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
			System.out.println("SQLite DB connected");
		}
		catch(Exception e){
			System.out.println(e);
		}
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
		Connection c = null;
		Statement st = null;
		try {
			String sql = "CREATE TABLE IF NOT EXISTS " + tableName +
					"(id INTEGER PRIMARY KEY," +
					" pass TEXT NOT NULL)";
			
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

	
	public static void main(String[] args) throws Exception{
		//createDatabase("users.db");
		//sqliteDemo.connect("eclipseSQLtest.db");
		//createTable("users", "test1");
	}
	
}