package application;
import java.sql.*;

import sql.sqliteDemo;

public class LoginModel {
	Connection conn;
	
	public LoginModel() {
		conn = sqliteDemo.connect();
		if (conn == null) 
		{
			System.out.println("DB did not connect.");
			System.exit(1);
		}
	}
	
	public boolean checkLogin(int userID, String password) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		// selects from table where info is our inputed ID and pass
		String query = "SELECT * FROM userInfo WHERE id = ? and info = ?"; 
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, userID);
			pst.setString(2, password);
			rs = pst.executeQuery(); //result of query saved in rs
			
			if (rs.next()) // user exists
			{
				return true;
			}
			else 
			{
				return false;
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			pst.close();
			rs.close();
		}
	}
}
