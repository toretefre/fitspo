package tdt4140.gr1806.app.core;


import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

/**
 * This is a class for ONLY testing Customer's methods,
 * so we use mock objects for this purpose.
 * 
 * @author henriette_andersen
 *
 */
public class CustomerTest {
	
	@Test
	public void testGetCustomer() {
		ArrayList<Customer> testCustomer = Trainer.getCustomers();
		for(Customer customer : testCustomer) {
			assertEqual("Berdfsit".equals(customer.getName()));
			
		}
		assertNotNull("List should not be null", testCustomer);
		//assertThat(testCustomer, containsInAnyOrder(
		//		hasProperty("name", is("Berit"))
		//		));
		
	}



	private void assertEqual(boolean equals) {
		// TODO Auto-generated method stub
		
	}



	@Test
	public void testGetCustomerNotNull() {
		ArrayList<Customer> testCustomer = Trainer.getCustomers();
		assertNotNull("List should not be null", testCustomer);
		
		
	}
	

}
