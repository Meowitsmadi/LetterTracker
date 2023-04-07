package application;

import java.sql.*;
import sql.sqliteDemo;

public class PasswordModel {
	Connection conn;
	
	public PasswordModel() {
		conn = sqliteDemo.connect();
		if (conn == null) 
		{
			System.out.println("DB did not connect.");
			System.exit(1);
		}
	}
	
	public void ChangePassword(String password) throws SQLException {
		PreparedStatement pst = null;
		
		// selects from table where info is our inputed ID and pass
		String query = "UPDATE userInfo SET pass = ? WHERE id = 1;"; 
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, password);
			pst.executeUpdate();	
//			if (rs.next())
//			{
//				return true;
//			}
//			else
//			{
//				return false;
//			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
			//return false;
		} finally {
			pst.close();
			//rs.close();
		}
	}
}
