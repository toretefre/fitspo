package tdt4140.gr1806.app.core;

import org.junit.Test;

import junit.framework.Assert;

/**
 * Tests of Goal class
 * @author Magnus
 *
 */

public class GoalTest {
	CustomerRepository cr = new CustomerRepository();
	//(int customerId, int goal, String deadLineStart, String deadLineEnd) 
	Goal g1 = new Goal(1, 500, "2018-02-02", "2018-04-04");
	Goal g2 = new Goal(2, 1000,"2018-02-02", "2018-04-04");
	
	//Testing getters
	
	@Test
	public void testGetGoalId() {
		Assert.assertEquals(1, g1.getCustomerId());
	}
	
	@Test
	public void testGetGoal() {
		Assert.assertEquals(500, g1.getGoal());
		Assert.assertFalse(1001 == g2.getGoal());
	}
	
	@Test
	public void testGetDeadLineStart() {
		Assert.assertEquals("2018-02-02", g1.getDeadLineStart());
		Assert.assertFalse("2018-02-12" == g2.getDeadLineStart());
	}
	
	@Test
	public void testGetDeadLineEnd() {
		Assert.assertEquals("2018-04-04", g1.getDeadLineEnd());
		Assert.assertFalse("2018-04-05" == g2.getDeadLineEnd());
	}
	
	//Testing setters
	
	@Test
	public void testSetGoal() {
		g1.setGoal(2);
		Assert.assertEquals(2, g1.getGoal());
	}
	
	@Test
	public void testSetCustomerId() {
		g1.setCustomerId(2);
		Assert.assertEquals(2, g1.getCustomerId());
	}

	@Test
	public void testSetDeadLineStart() {
		g1.setDeadLineStart("2018-01-01");
		Assert.assertEquals("2018-01-01", g1.getDeadLineStart());
	}
	
	@Test
	public void testSetDeadLineEnd() {
		g1.setDeadLineEnd("2018-02-02");
		Assert.assertEquals("2018-02-02", g1.getDeadLineEnd());
	}
	
}
