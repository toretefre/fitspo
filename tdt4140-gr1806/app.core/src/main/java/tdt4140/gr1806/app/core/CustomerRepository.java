package tdt4140.gr1806.app.core;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Implements methods for use to add customer.
 * @author henriette_andersen
 *
 */

public class CustomerRepository extends ConnectionManager {
	
	public CustomerRepository() {
	}
	
	private void setIfNotZero(PreparedStatement p, int index, int integer) throws SQLException {
		if (integer != 0) {
			p.setInt(index, integer);
		}
	}
	public Customer saveCustomer(Customer customer) {
		Customer returnCustomer = null;
		try {
			connect();
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
			returnCustomer = customer;
		} catch (Exception e) {
        	System.out.println("db error during inserting of new customer");
        	System.err.print(e);
        	} finally {
        		try {
        			if (conn!=null) {
        				conn.close();
        			}
        		} catch (Exception e) {
    				System.out.println("db error during closing of connection");
    				System.err.print(e);
        		}
        	}
		return returnCustomer;
	}
	
	
	public void addStepsToCustomer(Customer customer, int steps, String date) {
		//TODO: String format check, not minus int check
		try {
			connect();
			String insert = "insert into StepsOnDay (customerId, steps, walkDay) values (?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, customer.getId());
			pstmt.setInt(2, steps);
			pstmt.setString(3, date);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Could not save to database.");
			e.printStackTrace();
		} finally {
    			try {
    				if (conn!=null) {
    					conn.close();
    					}
    			} catch (Exception e) {
				System.out.println("db error during closing of connection");
				System.err.print(e);
    			}
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
			connect();
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
			} finally {
    				try {
    					if (conn!=null) {
    						conn.close();
    						}
    				} catch (Exception e) {
    					System.out.println("db error during closing of connection");
    					System.err.print(e);
    				}
			}
		return customers;
	}
	
	
	public void deleteCustomer(Customer customer) {
		try {
			connect();
			String delete = "delete from Customer where id=?";
			PreparedStatement pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, customer.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("db error during deletion of customer with id: " + customer.getId());
        		System.err.print(e);
		} finally {
			try {
				if (conn!=null) {
					conn.close();
					}
			} catch (Exception e) {
				System.out.println("db error during closing of connection");
				System.err.print(e);
			}
		}
	}
	
	
	// Or throw something, so we do not return -1 if it doesn't work...
	public int getTotalSteps(Customer customer) {
		int i = -1;
		try {
			connect();
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
		} finally {
			try {
				if (conn!=null) {
					conn.close();
					}
			} catch (Exception e) {
				System.out.println("db error during closing of connection");
				System.err.print(e);
			}
		}
		return i;
	}
	
	
	
	public static int getTotalStepsInDateRange(Customer customer, LocalDate startDate, LocalDate endDate) {
		int steps = -1;
		String sql = "select SUM(steps) " +
				"from StepsOnDay " +
				"where customerId=? and ?<=walkDay and walkDay<=? " +
				"group by customerId";

		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customer.getId());
			pstmt.setObject(2, startDate);
			pstmt.setObject(3, endDate);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				steps = rs.getInt("SUM(steps)");
			}
		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
			System.out.println(steps);
		} finally {
			try {
				if (conn!=null) {
					conn.close();
					}
			} catch (Exception e) {
				System.out.println("db error during closing of connection");
				System.err.print(e);
			}
		}
		return steps;
	}
	
	// TODO: This must be implemented in view
	public Goal saveGoal(Goal goal) {
		try {
			String sql = "UPDATE CustomerGoal SET "
					+ "customerId = ?, "
					+ "stepsGoal = ?, "
					+ "goalDeadline = ?, "
					+ "goalStart = ? "
					+ "WHERE customerId = ?;";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goal.getCustomerId());
			pstmt.setInt(2, goal.getGoal());
			pstmt.setString(3, goal.getDeadLineStart());
			pstmt.setString(4, goal.getDeadLineEnd());
			pstmt.setInt(5, goal.getCustomerId());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Could not save goal to database. ");
			e.printStackTrace();
		}
		return goal;
	}
	

	public ArrayList<Message> getMessages(Customer customer) {
		ArrayList<Message> messages = new ArrayList<>();
		String sql= "select date, message, customerID from Messages where customerID="+customer.getId()+" order by date asc";
		try {
			connect();
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
		} finally {
			try {
				if (conn!=null) {
					conn.close();
					}
			} catch (Exception e) {
				System.out.println("db error during closing of connection");
				System.err.print(e);
			}
		}
		return messages;
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
		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setDate(1, message.getDate());
			pstmt.setInt(2, message.getCusID());
			pstmt.setString(3, message.getMessage());
			System.out.println(pstmt);
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error in saveMessages");
			e.printStackTrace();
		} finally {
			try {
				if (conn!=null) {
					conn.close();
					}
			} catch (Exception e) {
				System.out.println("db error during closing of connection");
				System.err.print(e);
			}
		}
	}
	
	
	public Goal createGoalFromCustomerId(int customerId) throws SQLException {
		String sql = "select * from CustomerGoal where customerId=?";
		
		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			String goalDeadline = "";
			String goalStart = "";
			int stepsGoal = 0;
			pstmt.setInt(1, customerId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				stepsGoal = rs.getInt("stepsGoal");
				goalDeadline = rs.getString("goalDeadline");
				goalStart = rs.getString("goalStart");		
			}

			Goal goal = new Goal(customerId, stepsGoal, goalDeadline, goalStart);
			
			return goal;
		} catch (Exception e) {
			System.out.println("Error in createGoalFromCustomerId");
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (conn!=null) {
					conn.close();
					}
			} catch (Exception e) {
				System.out.println("db error during closing of connection");
				System.err.print(e);
			}
		}
	}
	
	// TODO: nothing below makes sense
	public ArrayList<Goal> getGoals(Customer customer) {
		ArrayList<Goal> goals = new ArrayList<>();
		int customerId = customer.getId();
		try {
			goals.add(createGoalFromCustomerId(customerId));
		}
		catch (Exception e) {
			System.out.println("Error in getMessages");
			e.printStackTrace();
		} finally {
			try {
				if (conn!=null) {
					conn.close();
					}
			} catch (Exception e) {
				System.out.println("db error during closing of connection");
				System.err.print(e);
			}
		}
		return goals;
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
