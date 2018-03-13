package tdt4140.gr1806.app.core;

public class DemoCustomer {
	
	// Run and show database:
	public static void main(String args[]) {
		// This will create a customer with name Bartosz in the Customer table, 
		// auto_generate an id, and assign that id to i.
		int i = Customer.addCustomer("Bartosz");
		// This will add steps at given date:
		Customer.addSteps(i, 8000, "2018-03-14");
		// This will add steps at today's date:
		Customer.addStepsToday(i, 300);
		// This will register another (with unique id) slot with steps walked today:
		Customer.addStepsToday(i, 600);
	}

}
