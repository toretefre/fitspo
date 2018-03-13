package tdt4140.gr1806.app.core;
/**
 * Instances of this class will represent a customer
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import com.sun.javafx.image.impl.ByteIndexed.Getter;
//import java.sql.Statement;

public class Customer extends ActiveDomainObject{
	
	private int steps, height;
	private double weight;
	private int id = -1;
	private String name, birthDate, dateRegistered, telephone;
	private Gender gender;

	/**
	 * 
	 * @param id ID corresponding to customer row in DB
	 * @param name Customer name
	 * 
	 * TODO: Maybe this construtor only should take in id and fetch data from the database?
	 */
	
	public static void main(String [ ] args){
		Connection conn = ConnectionManager.connect();
		Customer c = new Customer(19);
		c.init(conn);
		System.out.println(c.getName());
		System.out.println(c.getGender());
		c.setName("Henriette Andersen");
		c.save(conn);
		
		/*Customer n = new Customer();
		n.setName("Testie Mc Test");
		n.setTelephone("12345678");
		n.save(conn); */
	}
	
	public Customer(int id) {
		this.id = id;
	}
	public Customer() {
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getTelephone() {
		return this.telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getBirthdate() {
		return birthDate;
	}
	public void setBirthdate(String birthdate) {
		this.birthDate = birthdate;
	}
	
	public String getDateRegistered() {
		return dateRegistered;
	}
	

	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}




	public Date getRegistrationDate() {
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
				return new Date(registrationDate.getTime());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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


	@Override
	public void init(Connection conn) {
		try {
			String query = "select name, gender, dateRegistered, telephone, birthDate, height, weight from Customer where id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, this.id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				this.name =  rs.getString("name");
				try {
					this.gender = Gender.cast(rs.getString("gender"));
				} catch(Exception e){
				}
				this.dateRegistered = rs.getString("dateRegistered");
				try {
					this.telephone = rs.getString("telephone");
				} catch(Exception e){
				}
				try {
					this.birthDate = rs.getString("birthDate");
				} catch(Exception e){
				}
				try {
					this.height = rs.getInt("height");
				} catch(Exception e){
				}
				try {
					this.weight = rs.getDouble("weight");
				} catch(Exception e){
				}
			}
			} catch (Exception e) {
            	System.out.println("db error during select of customer with id = "+this.id);
            	System.err.print(e);
            	return;
            	}
		}
	

	@Override
	public void refresh(Connection conn) {
		this.init(conn);
	}


	@Override
	public void save(Connection conn) {
		if (this.id == -1) {
			try {
				String update = "insert into Customer (name, gender, telephone, birthDate, height, weight) values (?, ?, ?, ?, ?, ?);";
				PreparedStatement pstmt = conn.prepareStatement(update);
				pstmt.setString(1, this.name);
				pstmt.setObject(2, this.gender);
				pstmt.setString(3, this.telephone);
				pstmt.setString(4, this.birthDate);
				if (this.height == 0) {
					pstmt.setString(5, null);
				} else {
					pstmt.setInt(5, this.height);
				}
				if (this.height == 0.0) {
					pstmt.setString(6, null);
				} else {
					pstmt.setDouble(6, this.weight);
				}
				pstmt.executeUpdate();
			} catch (Exception e) {
	        	System.out.println("db error during inserting of new customer");
	        	System.err.print(e);
	        	return;
	        	}
		} else {
			try {
				String update = "update Customer set name=?, gender=?, telephone=?, birthDate=?, height=?, weight=? where id=?";
				PreparedStatement pstmt = conn.prepareStatement(update);
				pstmt.setString(1, this.name);
				pstmt.setObject(2, this.gender);
				pstmt.setString(3, this.telephone);
				pstmt.setString(4, this.birthDate);
				if (this.height == 0) {
					pstmt.setString(5, null);
				} else {
					pstmt.setInt(5, this.height);
				}
				if (this.height == 0.0) {
					pstmt.setString(6, null);
				} else {
					pstmt.setDouble(6, this.weight);
				}
				pstmt.setInt(7, this.id);
				pstmt.executeUpdate();
			} catch (Exception e) {
	        	System.out.println("db error during insert of customer with id = "+this.id);
	        	System.err.print(e);
	        	return;
	        	}
		}
	}

	

}
