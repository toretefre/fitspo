package tdt4140.gr1806.app.core;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TrainerTest {

	
	/**
	 * Testing if Arraylist containing customer data is being returned
	 */
	
	@Test
	public void testArraylist() {
		ArrayList<Customer> customerList = Trainer.getCustomers();
		Assert.assertEquals(ArrayList.class, customerList.getClass());
		if (customerList.size() > 0) {
			Customer customer = customerList.get(0);
			Assert.assertEquals(String.class, customer.getName().getClass());
			Assert.assertTrue(customer.getSteps() >= 0);
		}
	} 
	
	/**
	 * Tests that NullPpinterException is thrown,
	 * if Arraylist is not existing
	 */
	
	public void testException() {
		// https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests
	}
	
}
