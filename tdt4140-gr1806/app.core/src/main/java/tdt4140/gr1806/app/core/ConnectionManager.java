package tdt4140.gr1806.app.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
	
	Connection conn = null;
	
	public static void main(String args[]) {
		ConnectionManager manager = new ConnectionManager();
		try {
			manager.loadManager();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadManager() throws SQLException {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://mysql.stud.ntnu.no"
					+ "user=matiasre_gruppe6&password=123safari");
        }
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
        catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
	
	private void connect() throws SQLException {
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://mysql.stud.ntnu.no"
					+ "user=matiasre_gruppe6&password=123safari");
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}

	
	
	
	

	
	
	
	