package tdt4140.gr1806.app.core;

import java.sql.*;
import java.time.LocalDate;
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
	private void setIfNotZero(PreparedStatement p, int index, int integer) throws SQLException {
		if (integer != 0) {
			p.setInt(index, integer);
		}
	}
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
			this.setIfNotZero(pstmt, 5, customer.getHeight());
			this.setIfNotZero(pstmt, 6, (int) customer.getWeight());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			customer.setId(rs.getInt(1));
			customer.setDateRegistered(rs.getString(4));
		} catch (Exception e) {
        	System.out.println("db error during inserting of new customer");
        	System.err.print(e);
        	}
		return customer;
	}
	
	
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
			String query = "select sum(steps) from StepsOnDay where id=?";
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
	
	
	
	public static int getTotalStepsInDateRange(Customer customer, LocalDate startDate, LocalDate endDate) {
		int steps = 0;
		String sql = "select SUM(steps) " +
				"from StepsOnDay " +
				"where customerId=? and ?<=walkDay and walkDay<=? " +
				"group by customerId";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customer.getId());
			pstmt.setObject(2, startDate);
			pstmt.setObject(3, endDate);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				steps = rs.getInt("SUM(steps)");
			}
			return steps;
		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
			System.out.println(steps);
			return -1;
		}
	}
	
	public ArrayList<Message> getMessages(Customer customer) {
		ArrayList<Message> messages = new ArrayList<>();
		String sql= "select date, message, customerID from messages where customerID="+customer.getId();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Date date = rs.getDate("date");
				String message = rs.getString("message");
				int customerID = rs.getInt("customerID");
				messages.add(new Message(date, customerID, message));
			}
			return messages;
		}
		catch(Exception e) {
			System.out.println("Error in getMessages");
			e.printStackTrace();
			return messages;
		}
	}
	
	/**
	 * Tested and working
	 * @param message
	 * @throws SQLException
	 */
	
	public void saveMessage(Message message) throws SQLException {
		String update = "insert into Messages"
				+ "(date, "
				+ "customerID, "
				+ "message) "
				+ "values (?, ?, ?);";
				
		PreparedStatement pstmt = conn.prepareStatement(update);
		pstmt.setDate(1, message.getDate());
		pstmt.setInt(2, message.getCusID());
		pstmt.setString(3, message.getMessage());
		System.out.println(pstmt);
		pstmt.executeUpdate();
	}
	
}
