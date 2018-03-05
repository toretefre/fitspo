package tdt4140.gr1806.app.core;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Statement;


public class ConnectionManagerTest {
	

	public String getName(String n) {
		String testPersonName = "";
		try {
			Statement stmt = ConnectionManager.conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select name FROM Customer where name = 'Mikal'");
			while(rs.next()) {
				String res = rs.getString("name");
				testPersonName += res;
			}	
		}
		catch (Exception e) {
			System.err.println(e);
		}
		return testPersonName;
	}
	
	@Rule 
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testException () {
		exception.expect(SQLException.class);

	}
	
	@Test
	public void testConnection() {
		ConnectionManager.connect();
		assertTrue(getName("Mikal").equals("Mikal"));
	}
	
	

	
}