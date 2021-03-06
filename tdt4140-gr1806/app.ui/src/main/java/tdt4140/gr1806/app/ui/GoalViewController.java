package tdt4140.gr1806.app.ui;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.CustomerRepository;
import tdt4140.gr1806.app.core.Goal;

/**
 * Controls the GoalView
 * @author toretefre
 * Some of the ideas are from the MessageViewController
 */

public class GoalViewController {
	FitspoAppController fitspo;
	CustomerRepository cr = new CustomerRepository();
	Customer selectedCustomer = null;
	@FXML private ComboBox<Customer> customerComboBox;
	@FXML private Button sendMessageBtn;
	@FXML private Button clearMessageBtn;
	@FXML private TextField stepsField;
	@FXML private TextField dateField;
	@FXML private ScrollPane messageBox;
	@FXML private VBox messageContent;
	@FXML private VBox content;
	@FXML private Label statusLabel;

	// Switches to the Goal view
	public void changeCenterContent(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("GoalView.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	// Displays the goal and the deadline
	@FXML public void updateMessageField(ActionEvent event) throws SQLException {
		Goal g = cr.createGoalFromCustomerId(this.selectedCustomer.getId());
		String status = new String("The selected customer needs a new goal, please set one below.");
		if (g != null) {
			status = new String("The selected customer has a goal of " + Integer.toString(g.getGoal()) + " steps, " + System.lineSeparator() + "with the deadline set to " + g.getDeadLineEnd());
		}
		statusLabel.setText(status);	
	}
	
	// Saves the input to a goal object and calls for SaveGoal method in CustomerRepository
	@FXML public void updateGoal(ActionEvent event) throws SQLException {
		String steps = stepsField.getText();
		String deadLine = dateField.getText();
		Goal goal = new Goal(this.selectedCustomer.getId(), Integer.parseInt(steps), "1970-01-01", deadLine);
		cr.saveGoal(goal);
		stepsField.clear();
		dateField.clear();
		updateMessageField(event);
	}
	
	
	public void initialize() {
		// Fetches the Customers from the DB and places them in a observable list
		ObservableList<Customer> observableCustomerList = FXCollections.observableArrayList(cr.findAllCustomers());
		
		// Updates the dropdown (customerComboBox) with the Customers
		customerComboBox.setItems(observableCustomerList);
		
		// This fires when a field in the dropdown menu is clicked
		customerComboBox.setOnAction(e -> {
			this.selectedCustomer = customerComboBox.getValue();
			try {
				updateMessageField(e);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});
		
		// Using a StringConverter to show only Customer names in the dropdown list, and not all the other fields.
		customerComboBox.setConverter(new StringConverter<Customer>() {
			@Override
			public String toString(Customer customer) {
				return customer.getName();
			}
			@Override
			public Customer fromString(String strin) {
				return null;
			}
		});

	}

}
