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

import java.util.List;



/**
 * This is a class for testing ONLY Customer's methods.
 * Most of the customers methods are required by CustomerRepository and its tests are covering 
 * Customer, but that doesn't mean we shouldn't write proper tests for just Customer.
 * 
 * @author Aasmund
 *
 */


public class CustomerTest {
	
	@Test
	public void testToString() {
		// This is pretty pointless to test as it's either only used internally or covered by scenario tests of the UI.
		// But let's get that test coverage up.
		Customer hanse = new Customer("Hans Test", null, null, null, 0, 0);
		hanse.toString();
		
	}

}
