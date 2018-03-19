package tdt4140.gr1806.app.core;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Implements methods for use to add customer.
 * @author henriette_andersen
 *
 */

public class AddCustomerController extends ConnectionManager {
	public static void main(String[] args) {
	}
	
	Customer customer = new Customer();
	
	public AddCustomerController() {
		connect();
	}
	
	public void registerName(String name) {
		customer.setName(name);
	}
	
}
