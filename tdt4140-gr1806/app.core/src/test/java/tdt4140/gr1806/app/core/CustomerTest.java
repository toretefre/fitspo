package tdt4140.gr1806.app.core;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;



/**
 * This is a class for testing ONLY Customer's methods.
 * Most of the customers methods are required by CustomerRepository and its tests are covering 
 * Customer, but that doesn't mean we shouldn't write proper tests for just Customer.
 * 
 * @author Aasmund
 *
 */


public class CustomerTest {
	
	
	Customer customer1, customer2;
	
	/**
	 * @author Aasmund
	 */
	@Before
	public void makeCustomer() {

		customer1 = new Customer("Henriette Andersen", "F", "99352762", "1994-15-02", 172, 69.1);
		customer2 = new Customer(2, "Hans", "M", "1998-01-01", "99999999", "1992-02-02", 192, 85.0);
		//customer3 = new Customer(customer1 = new Customer(String name, String gender, String telephone, String birthDate, int height, double weight);
	}

	
	/**
	 * @author Aasmund
	 */

	@Test
	public void testConstructor() {
		Assert.assertTrue(customer1 instanceof Customer);
		Assert.assertEquals(customer1.getClass(), customer2.getClass());
		Assert.assertEquals(2, customer2.getId());
		Assert.assertEquals("Henriette Andersen", customer1.getName());

	}
	
	/**
	 * @author Aasmund
	 */

	@Test
	public void testGettersAndSetters() {
		customer1.setBirthDate("1996-07-19");
		Assert.assertEquals( "1996-07-19", customer1.getBirthDate());
		
		customer1.setGender("M");
		Assert.assertEquals("M", customer1.getGender());
		
		customer1.setHeight(180);
		Assert.assertEquals(180, customer1.getHeight());
		
		customer1.setName("Petter");
		Assert.assertNotEquals("Hans", customer1.getName());
		Assert.assertEquals("Petter", customer1.getName());
		
		/*
		customer1.setSteps(1337);
		Assert.assertEquals(1337, customer1.getSteps());
		*/
		customer1.setTelephone("12345678");
		Assert.assertEquals("12345678", customer1.getTelephone());
		
		customer1.setWeight(80.0);
		Assert.assertTrue(80.0 == customer1.getWeight());
		
		Assert.assertEquals("1998-01-01", customer2.getDateRegistered());
	}
	
	/**
	 * @author Magnus
	 * 
	 */
	
	@Test
	public void testToString() {
		String expected = "ID: 0\n" + 
				"Name: Henriette Andersen\n" + 
				"Birth Date: 1994-15-02\n" + 
				"Telephone: 99352762\n" + 
				"Gender: F\n" + 
				"Date Registered: null\n" + 
				"Height: 172\n" + 
				"Weight: 69.1";
		Assert.assertEquals(expected, customer1.toString());
	}
	
	
} 
