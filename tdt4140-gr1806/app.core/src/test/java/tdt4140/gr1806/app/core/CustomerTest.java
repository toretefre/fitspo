package tdt4140.gr1806.app.core;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;

import org.junit.Test;


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
		Customer cus = Customer.getCustomer("Berit");
		Assert.assertEquals("Berit", cus.getName());
		Customer cus2 = Customer.getCustomer("12345");
		Assert.assertNull(cus2);
	}

	Customer customer1, customer2;
	
	/**
	 * @author Aasmund
	 */
	@Before
	public void makeCustomer() {
		customer1 = new Customer(1, "Hans");
		customer2 = new Customer(2, "Kaare");
	}
	
	/**
	 * @author Aasmund
	 */
	@Test
	public void testConstructor() {
		Assert.assertTrue(customer1 instanceof Customer);
		Assert.assertEquals(customer1.getClass(), customer2.getClass());
		Assert.assertEquals(1, customer1.getId());
		Assert.assertEquals(2, customer2.getId());
		Assert.assertEquals("Hans", customer1.getName());
		Assert.assertEquals("Kaare", customer2.getName());
	}
	
	/**
	 * @author Aasmund
	 */
	@Test
	public void testGettersAndSetters() {
		customer1.setBirthdate("1996-07-19");
		Assert.assertEquals( "1996-07-19", customer1.getBirthdate());
		
		customer1.setGender(Gender.M);
		Assert.assertEquals(Gender.M, customer1.getGender());
		
		customer1.setHeight(180);
		Assert.assertEquals(180, customer1.getHeight());
		
		customer1.setName("Petter");
		Assert.assertNotEquals("Hans", customer1.getName());
		Assert.assertEquals("Petter", customer1.getName());
		
		customer1.setSteps(1337);
		Assert.assertEquals(1337, customer1.getSteps());
		
		customer1.setTelephone("12345678");
		Assert.assertEquals("12345678", customer1.getTelephone());
		
		customer1.setWeight(80);
		Assert.assertEquals(80, customer1.getWeight());
		
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
		
		Customer.removeCustomer(-20);
		
		Assert.assertFalse(isCustomerInDatabase(customer1.getName()));
		
		
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
