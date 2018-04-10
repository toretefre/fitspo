package tdt4140.gr1806.app.core;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implements methods for use to add customer.
 * @author henriette_andersen
 *
 */

public class CustomerRepository extends ConnectionManager {
	
	public CustomerRepository() {
		connect();
	}
	
	
	public Customer saveCustomer(Customer customer) {
		Customer nullCus = null;
		try {
			String update = "insert into Customer "
					+ "(name, "
					+ "gender, "
					+ "telephone, "
					+ "birthDate, "
					+ "height, "
					+ "weight) "
					+ "values (?, ?, ?, ?, ?, ?);";

			PreparedStatement pstmt = conn.prepareStatement(update,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getGender());
			pstmt.setString(3, customer.getTelephone());
			pstmt.setString(4, customer.getBirthDate());
			pstmt.setInt(5, customer.getHeight());
			pstmt.setDouble(6, customer.getWeight());
			pstmt.executeUpdate();
			
			// Get generated id and set to Customer:
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			customer.setId(rs.getInt(1));
			
			// Get the generated datetime and set to Customer:
			String query = "select dateRegistered from Customer"
					+ " where id = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(query);
			pstmt2.setInt(1, customer.getId());
			ResultSet rs2 = pstmt2.executeQuery();
			rs2.next();
			customer.setDateRegistered(rs2.getString("dateRegistered"));
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
        	System.out.println("db error during inserting of new customer");
        	System.err.print(e);
        	return nullCus;
        	}
	}
	
	
	/**
	 * Adds steps to the StepsOnDay table on the DB. 
	 * @param customer Customer to add steps to
	 * @param steps Number of steps on given date
	 * @param date Date of steps
	 */
	public void addStepsToCustomer(Customer customer, int steps, String date) {
		//TODO: String format check, not minus int check
		try {
			String insert = "insert into StepsOnDay (customerId, steps, walkDay) values (?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, customer.getId());
			pstmt.setInt(2, steps);
			pstmt.setString(3, date);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Could not save to database.");
			e.printStackTrace();
		}	
	}
	
	
	
	private Customer createCustomerFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String gender = rs.getString("gender");
		String date = rs.getString("dateRegistered");
		String telephone = rs.getString("telephone");
		String bDate = rs.getString("birthDate");
		int height = rs.getInt("height");
		double weight = rs.getInt("weight");
		
		
		Customer customer = new Customer(id, name, gender, date, telephone, bDate, height, weight);
		return customer;
	}
		
		
	
	public ArrayList<Customer> findAllCustomers() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			String query = "select * from Customer";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Customer customer = this.createCustomerFromResultSet(rs);
				customers.add(customer);
			}
			} catch (Exception e) {
				System.out.println("db error during selection of customers");
				System.err.print(e);
            	}
		return customers;
	}
	
	
	
	public void deleteCustomer(Customer customer) {
		try {
			String delete = "delete from Customer where id=?";
			PreparedStatement pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, customer.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("db error during deletion of customer with id: " + customer.getId());
        		System.err.print(e);
		}
	}
	
	
	// Or throw something, so we do not return -1 if it doesn't work...
	public int getTotalSteps(Customer customer) {
		int i = -1;
		try {
			String query = "select sum(steps) from StepsOnDay where customerId=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, customer.getId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("db error during selection of total steps from customer with id: " + customer.getId());
    			System.err.print(e);
		}
		return i;
	}
	
	
	
	public int getTotalStepsInDateRange(Customer customer, Date startDate, Date endDate) {
		int steps = -1;
		String sql = "select SUM(steps) " +
				"from StepsOnDay " +
				"where customerId=? and (walkDay between ? and ?) " +
				"group by customerId";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customer.getId());
			pstmt.setDate(2, startDate);
			pstmt.setDate(3, endDate);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				steps = rs.getInt("SUM(steps)");
			}
			return steps;
		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
			return steps;
		}
	}
	
	
	/**
	 * Makes a Customer-object from the id
	 * @param id Customer ID, all the data is fetched from the user with this id
	 * @return Customer if ID is found in database. Null if not.
	 * @author Aasmund
	 */
	public Customer createCustomerFromId(int id) {
		Customer customer = null;
		try {
			String query = "select * from Customer where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer = this.createCustomerFromResultSet(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
}
