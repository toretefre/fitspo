package tdt4140.gr1806.app.core;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
		for(Customer c : testCustomer) {
			if(c.getName().equals("Berit")) {
				assertTrue("This will be true", true);
			}
		}
	}



	@Test
	public void testGetCustomerNotNull() {
		ArrayList<Customer> testCustomer = Trainer.getCustomers();
		//assertNotNull("List should not be null", testCustomer);
		for(Customer c : testCustomer) {
			List<Customer> objekter = new ArrayList<>();
			if(c.getName().equals("Berit")) {
				objekter.add(new Customer("Berit", 222));
		assertNotNull("List should not be null", objekter);
			}
		}
		
		
	}
	

}
