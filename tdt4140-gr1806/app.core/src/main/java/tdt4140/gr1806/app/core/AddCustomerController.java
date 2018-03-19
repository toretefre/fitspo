package tdt4140.gr1806.app.core;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Implements methods for use on customer objects
 * @author henriette_andersen
 *
 */

public class CustomerController {
	public static void main(String[] args) {
		int yey;
		Connection connection = ConnectionManager.connect();
		String sql = "insert into "
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.executeUpdate();
	}
	
}
