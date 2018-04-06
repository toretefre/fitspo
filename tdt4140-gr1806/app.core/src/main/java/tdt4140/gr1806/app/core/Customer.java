package tdt4140.gr1806.app.core;
/**
 * Instances of this class will represent a customer
 */

<<<<<<< HEAD
=======
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
//import java.sql.Statement;
import java.time.LocalDate;
>>>>>>> refs/remotes/origin/master


public class Customer{
	
<<<<<<< HEAD
	private int id;
	private int height;
	private double weight;
	private String name;
	private String birthDate;
	private String dateRegistered;
	private String telephone;
	private String gender;
	
	//TODO: Checks for set-methods?
=======
	private int steps, id, height, weight;
	private String name, telephone, birthdate, registrationDate;
	private Gender gender;
>>>>>>> refs/remotes/origin/master

	/**
	 * 
	 * @param name
	 * @param birthDate
	 * @param telephone
	 * @param gender
	 * @param height
	 * @param weight
	 */
<<<<<<< HEAD
	
	public Customer(String name, String gender, String telephone, String birthDate, int height, double weight) {
		this.setName(name);
		this.setGender(gender);
		this.setTelephone(telephone);
		this.setBirthDate(birthDate);
		this.setHeight(height);
		this.setWeight(weight);
=======
	public Customer(int id, String telephone, String name, String birthdate, Gender gender, int height, int weight, int steps, String registrationDate) {
		this.id = id;
		this.telephone = telephone;
		this.name = name;
		this.birthdate = birthdate;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.steps = steps;
		this.registrationDate = registrationDate;
>>>>>>> refs/remotes/origin/master
	}
	
<<<<<<< HEAD
	public Customer(int id, String name, String gender, String dateRegistered, String telephone, String birthDate, int height, double weight) {
		this.setId(id);
		this.setName(name);
		this.setGender(gender);
		this.setTelephone(telephone);
		this.setDateRegistered(dateRegistered);
		this.setBirthDate(birthDate);
		this.setHeight(height);
		this.setWeight(weight);
	}
	
	
	
	private final String illegalNameCharacters = "!$@*^?><&%#/\\}{:\"'+";
	private final String illegalPhoneCharacters = "!$@*^?><&%#/\\}{:\"'´`";
	
	private void checkIllegalCharacters(String string, String illegalCharacters) {
		for (int i=0; i<string.length(); i++) {
			String c = string.substring(i, i+1);
			if (illegalCharacters.contains(c)) {
				throw new IllegalArgumentException(name + "contains the illegal character " + c);
			}
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	// Should only be called via CustomerReposit
	protected void setId(int id) {
		this.id = id;
	}
	
=======

	public Customer(int id, String name) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
	}


>>>>>>> refs/remotes/origin/master
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.checkIllegalCharacters(name, this.illegalNameCharacters);
		if (name.length() > 300) {
			throw new IllegalArgumentException("Name is too long.");
		}
		this.name = name;
<<<<<<< HEAD
	}	
=======
	}

	
	public int getSteps() {
		return steps;
	}


	public void setSteps(int steps) {
		this.steps = steps;
	}

>>>>>>> refs/remotes/origin/master
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		if (height > 500 || height < 10) {
			throw new IllegalArgumentException("The height seems wrong. The integer is in cm.");
		}
		this.height = height;
	}

<<<<<<< HEAD
	public double getWeight() {
=======

	public int getWeight() {
>>>>>>> refs/remotes/origin/master
		return weight;
	}
<<<<<<< HEAD
	public void setWeight(double weight) {
		if (weight > 500 || weight < 10) {
			throw new IllegalArgumentException("The weight seems wrong. The double is in kg.");
		}
=======


	public void setWeight(int weight) {
>>>>>>> refs/remotes/origin/master
		this.weight = weight;
	}

<<<<<<< HEAD
	public String getTelephone() {
		return this.telephone;
=======


	public String getTelephone() {
		return telephone;
>>>>>>> refs/remotes/origin/master
	}
<<<<<<< HEAD
	public void setTelephone(String telephone) {
		if (telephone.length() > 20) {
			throw new IllegalArgumentException("Too long phone number.");
		}
		this.checkIllegalCharacters(telephone, this.illegalPhoneCharacters);
=======


	//TODO: Validate number
	public void setTelephone(String telephone) {
>>>>>>> refs/remotes/origin/master
		this.telephone = telephone;
	}


<<<<<<< HEAD
	public String getBirthDate() {
		return birthDate;
=======
	public String getBirthdate() {
		return birthdate;
>>>>>>> refs/remotes/origin/master
	}
<<<<<<< HEAD
	public void setBirthDate(String birthDate) {
		String[] parts = birthDate.split("-");
		if (parts.length != 3 || parts[0].length() != 4 || parts[1].length() != 2 || parts[2].length() != 2) {
			throw new IllegalArgumentException("The birthDate needs to be of the form \"yyyy-mm-dd\"");
		}
		this.birthDate = birthDate;
=======


	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
>>>>>>> refs/remotes/origin/master
	}
	
	
	public String getDateRegistered() {
		return dateRegistered;
	}
	
	// Should only be called via CustomerReposit
	protected void setDateRegistered(String date) {
		this.dateRegistered = date;
	}
	

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		if (gender != "M" && gender != "F" && gender != "O") {
			throw new IllegalArgumentException("Only \"M\", \"F\", and \"O\" are legal gender inputs. You inserted: " + gender);
		}
		this.gender = gender;
	}

<<<<<<< HEAD
=======

	public int getId() {
		return id;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}
>>>>>>> refs/remotes/origin/master
	
	
<<<<<<< HEAD
	public String toString() {
        return "ID: " + this.id + "\nName: " + this.name + "\nBirth Date: " + this.birthDate + 
        		"\nTelephone: " + this.telephone + "\nGender: " + this.gender + "\nDate Registered: " + this.dateRegistered + 
        		"\nHeight: "+ this.height + "\nWeight: "+ this.weight;
    }
}
=======
	@Override
	public String toString() {
		return name + " " + steps;
	}

	/**
	 * Get a specified customer from the list of customers
	 * @param name
	 * @return specified customer from name
	 */
	
	public static Customer getCustomer(String name) {
		ArrayList<Customer> customers = Trainer.getCustomers();
		
		for (Customer customer : customers) {
			if (name.equals(customer.getName())) {
				return customer;
			}
		}
		
		return null;
	}

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

	
	/**
	 * Deletes specified customer and all associated data in database
	 * @param id 
	 * @exception 
	 */

	
	public static void removeCustomer(int id) {
		
		try {
			String sql = "delete from Customer where id=?";
			Connection connection = ConnectionManager.connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Could not find customer id in database");
			e.printStackTrace();
		}
	}
}

	


>>>>>>> refs/remotes/origin/master
