package tdt4140.gr1806.app.core;

import static junit.framework.TestCase.assertTrue;

import java.sql.Date;
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
	private CustomerRepository customerRepo = new CustomerRepository();

	
	
	/**
	 * @author Aasmund
	 * 
	 */
	@Test
	public void testAddingAndDeletingFromDB() {
		customer1 = new Customer("Hans Persistence Test", "M", "82732132", "1982-03-21", 178, 73.2);
		customerRepo.saveCustomer(customer1);
		Assert.assertTrue(isCustomerInDatabase(customer1.getName()));

		customerRepo.deleteCustomer(customer1);
		Assert.assertFalse(isCustomerInDatabase(customer1.getName()));
		
		
	}
	
	/**
	 * @author Henriette?
	 */
	
	@Test
    public void testGettingSteps() {
		customer1 = new Customer("Hans DateRange Test", "M", "82732132", "1982-03-21", 178, 73.2);
        // Add user with steps on 2 days, test different cases
        int stepsExpected = 500+100;
        
        Date startDate = Date.valueOf("2018-01-02");
        Date endDate = Date.valueOf("2018-02-07");


        try {
        	customerRepo.saveCustomer(customer1);
            customerRepo.addStepsToCustomer(customer1, 500, "2018-02-01");
            customerRepo.addStepsToCustomer(customer1, 100, "2018-02-02");
            customerRepo.addStepsToCustomer(customer1, 1234, "2018-04-02");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Could not add customer or steps to that customer");
        }

        int stepsDateRange = customerRepo.getTotalStepsInDateRange(customer1, startDate, endDate);
        assertTrue("Did not get expected steps in range", stepsExpected == stepsDateRange);
        
        stepsExpected += 1234;
        int stepsTotal = customerRepo.getTotalSteps(customer1);
        Assert.assertEquals(stepsExpected, stepsTotal);


        customerRepo.deleteCustomer(customer1);

    }
	
	
	
	private boolean isCustomerInDatabase(String name) {
		ArrayList<Customer> customerList = customerRepo.findAllCustomers();
		// This should be using Customer.getCustomer(String name), but that doesn't exist in this branch
		for (Customer customer : customerList) {
			if (customer.getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	
}

