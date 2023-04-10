package application;

import java.sql.*;
import sql.sqliteDemo;

public class PasswordModel {
	private static Connection conn;
	
	public PasswordModel() {
		conn = sqliteDemo.connect();
		if (conn == null) 
		{
			System.out.println("DB did not connect.");
			System.exit(1);
		}
	}
	
	public static void ChangePassword(String password) throws SQLException {
		PreparedStatement pst = null;
		// selects from table where info is our inputed ID and pass
		String query = "UPDATE userInfo SET info = ? WHERE id = 1;"; 
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, password);
			pst.executeUpdate();	
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst.close();
		}
		
	}
}
