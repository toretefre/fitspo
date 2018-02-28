package tdt4140.gr1806.app.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
	
	private int id;
	private int totalSteps;
	private String name;
	private String birthDate;
	private String registredDate;
	private String gender;
	private int height;
	private int weight;
	private int phoneNumber;
	private Connection connection = ConnectionManager.connect();
	private Statement stmt;
	
	public static void main(String args[]) {
		//Customer cust1 = new Customer("Per", "99", "M", 180, 200);
		Customer cust2 = new Customer(7);
	}
	
	
	/**
	 * There are two constructors, the first creates a new customer row in the database
	 * while creating a customer object
	 */
	public Customer(String name, String birthDate, String gender, int height, int weight) {
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		// TODO: create customer in db and assign id to java object
		try {
			String sql = "insert into Customer values ("
					+ "null, '"
					+ name + "','"
					+ gender + "',"
					+ "NOW(), null, null, null, null)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This one takes in an id and gets the corresponding information from the database.
	 */
	public Customer(int id) {
		this.id=id;
		// TODO: Get customer data from DB and write to Java-object
		try {
			String sql = "select * from Customer";
					//+ "where id= " + Integer.toString(id);
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				this.name = resultSet.getString("name");
				// TODO: this.totalSteps = getTotalSteps()
				this.birthDate = resultSet.getString("birthDate");
				this.gender = resultSet.getString("gender");
				this.height = resultSet.getInt("height");
				this.weight = resultSet.getInt("weight");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
