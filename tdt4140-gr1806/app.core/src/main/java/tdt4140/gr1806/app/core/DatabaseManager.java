package tdt4140.gr1806.app.core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;


/**
 * Provides methods to save and delete data from the database
 * 
 * @Author Aasmund
 */
public class DatabaseManager {
	
	private static Connection DBConnection = ConnectionManager.connect();
	
	
	/**
	 * Method to save steps to the database. 
	 * @Param id Person ID
	 * @Param steps The amount of steps 
	 * @Param date The date of the steps. Important: this is sql.Date, so the JDBC should identify this as a date.
	 */
	public static void saveSteps(int id, int steps, Date date) {
		String sql = "insert into Customer values ("
				+ id + ","
				+ steps + ","
				+ date
				+ ")";
		
		try {
			PreparedStatement pstmt = DBConnection.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
