package tdt4140.gr1806.app.core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;


/*
 * Provides methods to save and delete data from the database
 * 
 * @Author Aasmund
 */
public class DatabaseManager {
	
	private static Connection DBConnection = ConnectionManager.connect();
	
	
	/*
	 * @Param id Person ID
	 * @Param steps The amount of steps 
	 */
	public static void saveSteps(int id, int steps, Date date) {
		try {
			Statement query = DBConnection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
