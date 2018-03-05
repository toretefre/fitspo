package tdt4140.gr1806.app.core;

import org.junit.Assert;
import org.junit.Test;

public class TrainerTest {
	
	private Trainer t1;
	
	@Test
	public void testConnection() {
		// Possibly being made by Magnus in superclass?
	}
	
	@Test
	public void testArraylist() {
		/**
		 * Testing if Arraylist containing customer data is being returned
		 */
		System.out.println(Trainer.getCustomers());
		Assert.assertNotNull(t1);
		Assert.assertArrayEquals(t1 == t1);
		
	} 
}
