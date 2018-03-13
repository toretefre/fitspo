package tdt4140.gr1806.app.core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
//import java.sql.Statement;
import java.time.LocalDate;

public class Customer {
	

	
	public static void main(String args[]) {

		/*LocalDate startDate = LocalDate.of(2018, 3, 6);
		LocalDate endDate = LocalDate.of(2018, 3, 11);
		Customer.getTotalStepsInDateRange(21, startDate, endDate);*/
	}
	
	
	/**
	 * Adds a new customer to the database, where only the name is set.
	 * 
	 * @param name; name of a new customer.
	 * @return auto generated id if there is made a new row in the DB, -1 if not.
	 */
	public static int addCustomer(String name) {
		if (name == null) {
			System.err.println("Customer name cannot be null. No new row was added.");
			return -1;
		}
		try {
			String sql = "insert into Customer (name) values ('" + name + "')";
			Connection connection = ConnectionManager.connect();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			//The next code gets the auto-generated key:
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			int auto_id = rs.getInt(1);
			return auto_id;
		} catch (SQLException e) {
			System.err.println("Could not save to database.");
			e.printStackTrace();
			return -1;
		}	
	}
	
	
	/*
	public static void addCustomer(String name, Gender gender, int telephone, Date birthDate, int height, int weight) {
		try {
			String sql = "insert into Customer values ("
					+ "null,"
					+ "'" + name + "',"
					+ "'" + gender + "',"
					+ "'" + "NOW()" + "',"
					+ "'" + telephone + "',"
					+ "'" + birthDate + "',"
					+ "'" + height + "',"
					+ "'" + weight + "'"
					+ ")";
			Connection connection = ConnectionManager.connect();
			PreparedStatement pstmt = connection.prepareStatement(sql); //, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			// The next code gets the auto-generated key:
			//ResultSet rs = pstmt.getGeneratedKeys();
			//rs.next();
			//int auto_id = rs.getInt(1);
		} catch (SQLException e) {
			System.err.println("Could not save to database.");
			e.printStackTrace();
		}	
	}
	*/
	
	
	public static void addStepsToday(int id, int steps) {
		try {
			Date sqlDate = new Date(System.currentTimeMillis());
			String sql = "insert into StepsOnDay (customerId, steps, walkDay) values ("
					+ "'" + id + "',"
					+ "'" + steps + "',"
					+ "'" + sqlDate + "'"
					+ ")";
			Connection connection = ConnectionManager.connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Could not save to database.");
			e.printStackTrace();
		}	
	}
	
	
	public static void addSteps(int id, int steps, String date) {
		// TODO: Sjekk at string er på riktig form, om vi ikke lar sql ta dette.
		try {
			String sql = "insert into StepsOnDay (customerId, steps, walkDay) values ("
					+ "'" + id + "',"
					+ "'" + steps + "',"
					+ "'" + date + "'"
					+ ")";
			Connection connection = ConnectionManager.connect();
			PreparedStatement pstmt = connection.prepareStatement(sql); 
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Could not save to database.");
			e.printStackTrace();
		}	
	}


	// This method uses an inclusive range
	public static int getTotalStepsInDateRange(int id, LocalDate startDate, LocalDate endDate) {

		String sql = "select SUM(steps) " +
				"from StepsOnDay " +
				"where customerId=? and ?<=walkDay and walkDay<=? " +
				"group by customerId";

		Connection connection = ConnectionManager.connect();
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setObject(2, startDate);
			pstmt.setObject(3, endDate);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("SUM(steps)");
		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
			return -1;
		}
	}

}
