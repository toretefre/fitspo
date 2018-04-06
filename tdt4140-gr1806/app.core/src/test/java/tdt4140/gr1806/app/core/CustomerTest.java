package tdt4140.gr1806.app.core;

import org.junit.Test;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;

/**
 * This is a class for ONLY testing Customer's methods,
 * so we use mock objects for this purpose.
 * 
 * @author henriette_andersen
 *
 */
public class CustomerTest {
	Customer customer1, customer2;
	
	@Before
	public void makeCustomer() {
		customer1 = new Customer("Henriette Andersen", "F", "99352762", "1994-15-02", 172, 69.1);
		customer2 = new Customer(1, "Testie", String gender, String dateRegistered, String telephone, String birthDate, int height, double weight);
		//customer3 = new Customer(customer1 = new Customer(String name, String gender, String telephone, String birthDate, int height, double weight);
	
	@Test
	public void testConstructor() {
		Assert.assertTrue(customer1 instanceof Customer);
		Assert.assertEquals(customer1.getClass(), customer2.getClass());
		Assert.assertEquals(1, customer1.getId());
		Assert.assertEquals(2, customer2.getId());
		Assert.assertEquals("Hans", customer1.getName());
		Assert.assertEquals("K�re", customer2.getName());
	}
	
	@Test
	public void testGettersAndSetters() {
		customer1.setBirthdate(Date.valueOf("1996-07-19"));
		Assert.assertEquals( "1996-07-19", customer1.getBirthdate().toString());
		
		customer1.setGender(Gender.M);
		Assert.assertEquals(Gender.M, customer1.getGender());
		
		customer1.setHeight(180);
		Assert.assertEquals(180, customer1.getHeight());
		
		customer1.setName("Petter");
		Assert.assertNotEquals("Hans", customer1.getName());
		Assert.assertEquals("Petter", customer1.getName());
		
		customer1.setSteps(1337);
		Assert.assertEquals(1337, customer1.getSteps());
		
		customer1.setTelephone(12345678);
		Assert.assertEquals(12345678, customer1.getTelephone());
		
		customer1.setWeight(80);
		Assert.assertEquals(80, customer1.getWeight());
		
		Assert.assertNotNull(customer1.getRegistrationDate());
	}

}
