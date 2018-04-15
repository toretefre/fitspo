package tdt4140.gr1806.app.core;

import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * This class is acting as a data access class, taking care of
 * all the direct communication with the database, i.e. reading
 * and writing data. It works with predefined objects such as
 * Customer, Message, and Goal, which stores the relevant data
 * in its attributes.
 * 
 * @author henriette_andersen
 *
 */

public class CustomerRepository {
	

	/**
	 * Saves a customer to the database.
	 * @author henriette_andersen
	 * @param customer, customer object to be saved without id or registration date set
	 * @return null or the saved customer object with id and registration set
	 */
	public Customer saveCustomer(Customer customer) {
		try (Connection conn = ConnectionManager.connect()){
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
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db error during inserting of new customer");
        		System.err.print(e);
        		customer = null;
        	}
		return customer;
	}
	
	
	/**
	 * If findAllCustomers() works, this will take in a customer
	 * and return true if a customer with the same id exists in the
	 * database, else false.
	 */
	private boolean isCustomerInDatabase(Customer cus) {
		int id = cus.getId();
		ArrayList<Customer> customerList = this.findAllCustomers();
		// This should be using Customer.getCustomer(String name), but that doesn't exist in this branch
		for (Customer customer : customerList) {
			if (customer.getId()==id) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Makes a Customer-object from the id
	 * @param id Customer ID, all the data is fetched from the user with this id
	 * @return Customer if ID is found in database. Null if not.
	 * @author Aasmund
	 */
	public Customer createCustomerFromId(int id) {
		Customer customer = null;
		try (Connection conn = ConnectionManager.connect()) {
			String query = "select * from Customer where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer = this.createCustomerFromResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		try(Connection conn = ConnectionManager.connect()) {
			if (!this.isCustomerInDatabase(customer)) {
				throw new IllegalArgumentException("The customer is not in the database");
			}
			String insert = "insert into StepsOnDay (customerId, steps, walkDay) values (?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, customer.getId());
			pstmt.setInt(2, steps);
			pstmt.setString(3, date);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("Could not save to database.");
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Help method for when fetching lots of customers from the database.
	 * @author henriette_andersen
	 * @param rs, result set with data to create the customer
	 * @return customer, a Customer object with the data from the result set
	 * @throws SQLException
	 */
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
	

		
	/**
	 * Finding all the customers saved in the database.
	 * @author henriette_andersen
	 * @return customers, an ArrayList of customer-objects representing
	 * each customer row in the Customer table of the database
	 */
	public ArrayList<Customer> findAllCustomers() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try (Connection conn = ConnectionManager.connect()) {
			String query = "select * from Customer";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Customer customer = this.createCustomerFromResultSet(rs);
				customers.add(customer);}
			
			} catch (Exception e) {
				System.out.println("db error during selection of customers");
				System.err.print(e);
				customers = null;
			}
		return customers;
	}
	
	
	/**
	 * Deletes a customer from the database.
	 * @author henriette_andersen
	 * @param customer, a Customer-object representing the customer 
	 * we want to delete. The id needs to be set, so that the database
	 * will delete the customer with this id, if it exists.
	 */
	public void deleteCustomer(Customer customer) {	
		try(Connection conn = ConnectionManager.connect()) {
			if (!this.isCustomerInDatabase(customer)) {
				throw new IllegalArgumentException("The customer is not in the database");
			}
			String delete = "delete from Customer where id=?";
			PreparedStatement pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, customer.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("db error during deletion of customer");
        		System.err.print(e);
		}
	}
	
	
	/**
	 * Gets the total steps from a customer.
	 * @param customer, a Customer-object housing the id of the customer we want steps from
	 * @return i, integer representing total steps, or -1 if exception or the customer
	 * does not exist.
	 */
	public int getTotalSteps(Customer customer) {
		int i = 0;
		try (Connection conn = ConnectionManager.connect()) {
			if (!this.isCustomerInDatabase(customer)) {
				throw new IllegalArgumentException("The customer is not in the database");
			}
			String query = "select sum(steps) from StepsOnDay where customerId=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, customer.getId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("db error during selection of total steps from customer");
    			System.err.print(e);
    			i = -1;
		}
		return i;
	}
	
	

	 /**
	  * Gets the total steps in a given date range for a specific customer.
	  * @param customer, an object housing the id of the customer we want to get the steps associated with
	  * @param startDate, a Date object; the date we start counting steps
	  * @param endDate, a Date object; the date we end counting steps
	  * @return steps, integer representing the amount of steps registered to the customer the given time span,
	  * or -1 if there is an exception or the customer does not exist.
	  */
	public int getTotalStepsInDateRange(Customer customer, Date startDate, Date endDate) {
		int steps = 0;
		String sql = "select SUM(steps) " +
				"from StepsOnDay " +
				"where customerId=? and (walkDay between ? and ?) " +
				"group by customerId";

		try (Connection conn = ConnectionManager.connect()) {
			if (!this.isCustomerInDatabase(customer)) {
				throw new IllegalArgumentException("The customer is not in the database");
			}
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customer.getId());
			pstmt.setDate(2, startDate);
			pstmt.setDate(3, endDate);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				steps = rs.getInt("SUM(steps)");
			}
		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
			steps = -1;
		}
		return steps;
	}
	

	
	/**
	 * 
	 * @param goal
	 * @return
	 */
	public Goal saveGoal(Goal goal) {
		try (Connection conn = ConnectionManager.connect()) {
			if (!this.isCustomerInDatabase(new Customer(goal.getCustomerId(), null, null, null, null, null, 0, 0))) {
				throw new IllegalArgumentException("The customer is not in the database");
			}
			String sql = "insert into CustomerGoal "
					+ "(customerId, "
					+ "stepsGoal, "
					+ "goalDeadline, "
					+ "goalStart) "
					+ "values (?, ?, ?, ?);";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goal.getCustomerId());
			pstmt.setInt(2, goal.getGoal());
			pstmt.setString(3, goal.getDeadLineStart());
			pstmt.setString(4, goal.getDeadLineEnd());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.err.println("Could not save goal to database. ");
			e.printStackTrace();
			goal = null;
		}
		return goal;
	}
	
	
	/**
	 * Updates a goal for a customer.
	 * @author henriette_andersen
	 * @param goal, goal with a customerId that there already is saved a goal.
	 * @return goal, the newly updated goal, or null if it did not work.
	 */
	public Goal updateGoal(Goal goal) {
		try (Connection conn = ConnectionManager.connect()) {
			if (!this.isCustomerInDatabase(new Customer(goal.getCustomerId(), null, null, null, null, null, 0, 0))) {
				throw new IllegalArgumentException("The customer is not in the database");
			}
			String sql = "update CustomerGoal "
					+ "set "
					+ "stepsGoal = ?, "
					+ "goalDeadline = ?, "
					+ "goalStart = ? "
					+ "where customerId = ?;";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goal.getGoal());
			pstmt.setString(2, goal.getDeadLineStart());
			pstmt.setString(3, goal.getDeadLineEnd());
			pstmt.setInt(4, goal.getCustomerId());
			pstmt.executeUpdate();
			if (null == this.createGoalFromCustomerId(goal.getCustomerId())) {
				throw new IllegalArgumentException("Cannot update when nothing is already saved.");
			}
			
		} catch (Exception e) {
			System.err.println("Could not update goal.");
			e.printStackTrace();
			goal = null;
		}
		return goal;
	}
	
	
	
	public Goal createGoalFromCustomerId(int customerId) {
		Goal goal = null;
		String sql = "select * from CustomerGoal where customerId=?";
		try (Connection conn = ConnectionManager.connect()) {
			if (!this.isCustomerInDatabase(new Customer(customerId, null, null, null, null, null, 0, 0))) {
				throw new IllegalArgumentException("The customer is not in the database");
			}
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int stepsGoal = rs.getInt("stepsGoal");
				String goalDeadline = rs.getString("goalDeadline");
				String goalStart = rs.getString("goalStart");
				goal = new Goal(customerId, stepsGoal, goalDeadline, goalStart);
			}
			
		} catch (Exception e) {
			System.out.println("Error in createGoalFromCustomerId");
			e.printStackTrace();
			goal = null;
		}
		return goal;
	}
	
	
	
	/**
	 * Deletes a goal from the database.
	 * @author henriette_andersen
	 * @param goal, a Goal-object representing the goal 
	 * we want to delete. The customerId needs to be set, so that the database
	 * will delete the goal with this id, if it exists.
	 */
	public void deleteGoal(Goal goal) {	
		try(Connection conn = ConnectionManager.connect()) {
			String delete = "delete from CustomerGoal where customerId=?";
			PreparedStatement pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, goal.getCustomerId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("db error during deletion of customer");
        		System.err.print(e);
		}
	}
	
	
	
	

	
	public ArrayList<Message> getMessages(Customer customer) {
		ArrayList<Message> messages = new ArrayList<>();
		String sql= "select date, message, customerID from Messages where customerID="+customer.getId()+" order by date asc";
		try (Connection conn = ConnectionManager.connect()) {
			if (!this.isCustomerInDatabase(customer)) {
				throw new IllegalArgumentException("The customer is not in the database");
			}
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Date date = rs.getDate("date");
				String message = rs.getString("message");
				int customerID = rs.getInt("customerID");
				messages.add(new Message(date, customerID, message));
			}
		}
		catch(Exception e) {
			System.out.println("Error in getMessages");
			e.printStackTrace();
		}
		return messages;
	}
	
	
	/**
	 * Tested and working
	 * @param message
	 */
	
	public Message saveMessage(Message message) {
		String update = "insert into Messages"
				+ "(date, "
				+ "customerID, "
				+ "message) "
				+ "values (?, ?, ?);";
		try (Connection conn = ConnectionManager.connect()) {
			if (!this.isCustomerInDatabase(new Customer(message.getCusID(), null, null, null, null, null, 0, 0))) {
				throw new IllegalArgumentException("The customer is not in the database");
			}
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setDate(1, message.getDate());
			pstmt.setInt(2, message.getCusID());
			pstmt.setString(3, message.getMessage());
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error in saveMessages");
			e.printStackTrace();
			message = null;
		}
		return message;
	}
	
	
	
	
	public static void main(String[] args) {
		// CustomerRepository customerRepo = new CustomerRepository();
		// TEST SAVE GOAL:
		// Goal goal123 = new Goal(1, 69000, "2018-02-02", "2018-03-03");
		// customerRepo.saveGoal(goal123);
		
		// TEST LOAD GOAL:
		// Goal testgoal;
		// try {
		//	testgoal = customerRepo.createGoalFromCustomerId(3);
		//	System.out.println(testgoal);
		//} catch (SQLException e) {
		//	e.printStackTrace();
		//}
	}
	}
