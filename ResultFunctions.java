package sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultFunctions {

	
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
}
