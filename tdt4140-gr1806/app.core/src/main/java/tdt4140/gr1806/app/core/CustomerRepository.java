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
	
	
//	private void setIfNotZero(PreparedStatement p, int index, int integer) throws SQLException {
//		if (integer != 0) {
//			p.setInt(index, integer);
//		}
//	}
	
	
	public Customer saveCustomer(Customer customer) {
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
			// This sets height if > 0 and sets as null if not
			// Changed this because method kept failing and couldn't find where. 
			pstmt.setInt(5, customer.getHeight() > 0 ? customer.getHeight() : null);
			pstmt.setInt(6, customer.getWeight() > 0 ? customer.getHeight() : null); 
//			this.setIfNotZero(pstmt, 5, customer.getHeight());
//			this.setIfNotZero(pstmt, 6, (int) customer.getWeight());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				customer.setId(rs.getInt(1));
				customer.setDateRegistered(new Date(System.currentTimeMillis()).toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
        	System.out.println("db error during inserting of new customer");
        	System.err.print(e);
        	}
		return customer;
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
	
	
	
}
