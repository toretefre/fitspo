package tdt4140.gr1806.app.core;

import static junit.framework.TestCase.assertTrue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Aasmund
 *
 */

public class CustomerPersistenceTest {
	private Customer customer1;
	
	/**
	 * @author Aasmund
	 */
	@Before
	public void makeCustomer() {
		customer1 = new Customer(1, "Hans Persistence Test");
	}
	
	
	/**
	 * @author Aasmund
	 * 
	 */
	@Test
	public void testAddingAndDeletingFromDB() {
		int customerId = Customer.addCustomer(customer1.getName());
		int nullCustomer = Customer.addCustomer(null);
		
		Assert.assertTrue(isCustomerInDatabase(customer1.getName()));
		Assert.assertTrue(nullCustomer == -1);
		
		Customer.removeCustomer(customerId);
		
		Assert.assertFalse(isCustomerInDatabase(customer1.getName()));
		
		
	}
	
	/**
	 * @author Henriette?
	 */
	
	@Test
    public void getTotalStepsInDateRange() {

        // Add user with steps on 5 days, test different cases

        Connection conn = ConnectionManager.connect();

        Integer id = null;
        Integer stepsExpected = 100 + 1500 + 2000 + 10;

        LocalDate startDate = LocalDate.of(2018, 2, 2);
        LocalDate endDate = LocalDate.of(2018, 2, 7);

        try {
            id = Customer.addCustomer("Per Johny Testersen");
            Customer.addSteps(id, 500, "2018-02-1");
            Customer.addSteps(id, 100, "2018-02-2");
            Customer.addSteps(id, 1500, "2018-02-4");
            Customer.addSteps(id, 2000, "2018-02-6");
            Customer.addSteps(id, 10, "2018-02-7");
            Customer.addSteps(id, 3000, "2018-02-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Could not add customer or steps to that customer");
        }

        int stepsReturned = Customer.getTotalStepsInDateRange(id, startDate, endDate);
        assertTrue("Did not get expected steps in range", stepsExpected == stepsReturned);


        // Now delete customer and steps
        try {
            String sqlDeleteCustomer = "delete from Customer where id=?";
            PreparedStatement pstmtDeleteCustomer = conn.prepareStatement(sqlDeleteCustomer);
            pstmtDeleteCustomer.setInt(1, id);
            pstmtDeleteCustomer.execute();

            String sqlDeleteSteps = "delete from StepsOnDay where customerId=?";
            PreparedStatement pstmtDeleteSteps = conn.prepareStatement(sqlDeleteSteps);
            pstmtDeleteSteps.setInt(1, id);
            pstmtDeleteSteps.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Could not delete customer and steps, please delete customer with id " + Integer.toString(id));
        }

    }
	
	private boolean isCustomerInDatabase(String name) {
		ArrayList<Customer> customerList = Trainer.getCustomers();
		// This should be using Customer.getCustomer(String name), but that doesn't exist in this branch
		for (Customer customer : customerList) {
			if (customer.getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	
}

