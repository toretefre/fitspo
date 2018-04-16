package tdt4140.gr1806.app.core;

import java.sql.Date;
import org.junit.Test;
import org.junit.Assert;

/**
 * Test class for MessageClass. 
 * @author Magnus
 *
 */
public class MessageTest {
	CustomerRepository cr = new CustomerRepository();
	
	//Adding new Messages for testing
	Message test1 = new Message(Date.valueOf("2018-02-02"), 6, "This is a test message");
	Message test2 = new Message(Date.valueOf("2018-02-03"), 3, "This is an another test message");

	//Testing getters
	@Test
	public void testGetDate() {
		Assert.assertEquals(Date.valueOf("2018-02-02"), test1.getDate());
	}
	
	@Test
	public void testCustomerID() {
		Assert.assertEquals(6, test1.getCusID());
		Assert.assertEquals(3, test2.getCusID());
	}
	
	@Test
	public void testGetMessage() {
		Assert.assertEquals("This is a test message", test1.getMessage());
		Assert.assertEquals("This is an another test message", test2.getMessage());
	}

	//Testing setters
	@Test
	public void testSetID() {
		test1.setId(4);
		Assert.assertTrue(4 == test1.getId());
	}
	
	@Test
	public void testSetMessage() {
		test1.setMessage("New messagetext");
		Assert.assertEquals("New messagetext", test1.getMessage());
	}
	
	@Test
	public void testSetCusId() {
		test1.setCusID(2);
		Assert.assertEquals(2, test1.getCusID());
	}

	
	
}
