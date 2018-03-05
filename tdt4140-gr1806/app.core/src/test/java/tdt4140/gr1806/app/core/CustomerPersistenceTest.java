package tdt4140.gr1806.app.core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerPersistenceTest {
	
	
	

	@Before
	public void setUp() {
	}
	
	@Test
	
	public void testAddCustomer() {
		String p1 = "Henriette";
		String p2 = "Ingrid";
		int i1 = Customer.addCustomer(p1);
		int i2 = Customer.addCustomer(p2);
		
		
	}
	
	public void testAddStepsToday() {
		
	}
	
	public void testAddSteps() {
		
	}
	
	
	
}
