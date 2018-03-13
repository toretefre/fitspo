package tdt4140.gr1806.app.core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
//import java.sql.Statement;

public class Customer {
	
	private int totalSteps, id, height, weight;
	private String name, telephone;
	private String birthdate, registrationDate;
	private Gender gender;

	/**
	 * 
	 * @param id ID corresponding to customer row in DB
	 * @param name Customer name
	 * 
	 * TODO: Maybe this construtor only should take in id and fetch data from the database?
	 */
	public Customer(int id, String telephone, String name, String birthdate, Gender gender, int height, int weight, int totalSteps, String registrationDate) {
		this.id = id;
		this.telephone = telephone;
		this.name = name;
		this.birthdate = birthdate;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.totalSteps = totalSteps;
		this.registrationDate = registrationDate;
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	public int getTotalSteps() {
		return totalSteps;
	}


	public void setTotalSteps(int totalSteps) {
		this.totalSteps = totalSteps;
	}

	
	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}



	public String getTelephone() {
		return telephone;
	}


	//TODO: Validate number
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public int getId() {
		return id;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}
/*
	public String getRegistrationDate() {
		ConnectionManager.connect();
		
		try {
			System.out.println("UserID is " + this.id);
			Statement stmt = ConnectionManager.conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select dateRegistered " + 
					"from Customer " + 
					"where id = " + this.id);

			while(rs.next()) {
				Timestamp registrationDate = rs.getTimestamp("dateRegistered");
				System.out.println(registrationDate.toString());
				return new String(registrationDate.getTime());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	
	/**
	 * Adds a new customer to the database, where only the name is set.
	 * 
	 * @param name; name of a new customer.
	 * @return auto generated id if there is made a new row in the DB, -1 if not.
	 * 
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
	
	
	
	

}
