package tdt4140.gr1806.app.core;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;

/**
 * 
 * @author ingriddomben
 *
 */

	public class Trainer {		
		
		/**
		 *  Get a list of all customers and total number of steps taken by each customer
		 * 
		 * @return 		list of customer-objects from database
		 */
		public static ArrayList<Customer> getCustomers() {
			ConnectionManager.connect();

			ArrayList<Customer> customers = new ArrayList<>();
			
			/**
			 * Connects to database, 
			 * Joins steps for each customer with an SQL query
			 */
			
			try {
				Statement stmt = ConnectionManager.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT name, SUM(steps) AS steps FROM StepsOnDay "
						+ "JOIN Customer ON Customer.id = StepsOnDay.customerId GROUP BY name");
				
				/**
				 * The steps from each customer is put into nested Arraylists 
				 * and added into the main Arraylist.
				 */
				
				while(rs.next()) {
					String name = rs.getString("name");
					int steps = rs.getInt("steps");
					Customer cus = new Customer(name, steps);
					customers.add(cus);
				}}
				catch (Exception e) {
					System.err.println(e);
				}
				return customers;
			
		}
	}

