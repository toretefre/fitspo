/*

package tdt4140.gr1806.app.core;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * This is a CI test that tests Customer, ConnectionManager and the DB working together.
 * We check if data is correctly saved and loaded.
 * @author henriette_andersen
 *
 *

public class CustomerPersistenceTest {
	
	private int id1;
	private int id2;
	
	Connection connection = ConnectionManager.connect();
	
	private static String getName(int id){
		String name = null;
		try {
			String sql = "select name from Customer where id='" + id + "'";
			Statement stmt;
			stmt = ConnectionManager.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			name = rs.getString(1);
		} catch (SQLException e) {
			System.err.println("Could not get name from DB.");
			e.printStackTrace();
		}
		return name;
	}
	

	@Before
	public void setUp() {
	}
	
	@Test
	
	public void testAddCustomer() {
		String p1 = "Henriette";
		String p2 = "Ingrid";
		int id1 = Customer.addCustomer(p1);
		int id2 = Customer.addCustomer(p2);
		String name1 = CustomerPersistenceTest.getName(id1);
		String name2 = CustomerPersistenceTest.getName(id2);
		
		assertEquals(p1, name1);
		assertEquals(p2, name2);
		
		this.id1 = id1;
		this.id2 = id2;
		
	}
	
	public void testAddStepsToday() {
		 Customer.addStepsToday(id1, 800);
		 
		
	}
	
	public void testAddSteps() {
		
	}
	
	
	
}

*/
