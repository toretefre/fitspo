package tdt4140.gr1806.app.core;

import org.junit.Assert;
import org.junit.Test;

public class TrainerTest {
	
	@Test
	public void testConnection() {
		// Possibly being made by Magnus in superclass?
	}
	
	/**
	 * Testing if Arraylist containing customer data is being returned
	 */
	
	@Test
	public void testArraylist() {
		
		System.out.println(Trainer.getCustomers());
		Assert.assertNotNull(Trainer.getCustomers());
	
	} 
	
	/**
	 * Tests that NullPpinterException is thrown,
	 * if Arraylist is not existing
	 */
	
	public void testException() {
		// https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests
	}
	
}
