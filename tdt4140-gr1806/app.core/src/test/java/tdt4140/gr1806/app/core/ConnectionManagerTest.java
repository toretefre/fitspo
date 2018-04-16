package tdt4140.gr1806.app.core;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;

import org.junit.Test;

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
	
	@Test
	public void testConnection() {
		ConnectionManager.connect();
		assertTrue(getName("Mikal").equals("Mikal"));
	}
	
}