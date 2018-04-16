package tdt4140.gr1806.app.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;
import org.junit.Assert;

public class ConnectionManagerTest {
	
	
	@Test
	public void testConnection() {
		try {
			Connection conn = ConnectionManager.connect();
			String sqlString = "SELECT * FROM Customer LIMIT 1";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				// Check if name is String, will fail if resultSet is empty
				Assert.assertEquals(String.class, rs.getString("name").getClass());
			}
		} catch (Exception e) {
			// If exception is thrown, the connection can be considered a fail
			System.err.println("Cant fetch from database");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
}