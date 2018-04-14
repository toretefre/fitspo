package tdt4140.gr1806.app.ui;

import java.io.IOException;
import java.sql.Date;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.CustomerRepository;
import tdt4140.gr1806.app.core.Goal;
import tdt4140.gr1806.app.core.Message;

public class GoalViewController {
	FitspoAppController fitspo;
	CustomerRepository cr = new CustomerRepository();
	Customer selectedCustomer = null;
	@FXML private ComboBox<Customer> customerComboBox;
	@FXML private Button sendMessageBtn;
	@FXML private Button clearMessageBtn;
	@FXML private TextField messageTextField;
	@FXML private ScrollPane messageBox;
	@FXML private VBox messageContent;
	@FXML private VBox content;

	
	public void changeCenterContent(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("GoalView.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML public void updateMessageField(ActionEvent event) throws SQLException {
		//Clearing the content first - dont want duplicate messages when using the application.
		content.getChildren().clear();
		
		Goal g = cr.createGoalFromCustomerId(this.selectedCustomer.getId());
		TextArea text = new TextArea();
		text.setId("goalText");
		text.wrapTextProperty();
		text.setText(Integer.toString(g.getGoal()));
		messageBox.setFitToWidth(true);
	}
	
	@FXML public void sendMessage(ActionEvent event) throws SQLException {
		String message = messageTextField.getText();
		Date sqlDate = new Date(System.currentTimeMillis());
		Message m = new Message(sqlDate, this.selectedCustomer.getId(), message);
		cr.saveMessage(m);
		updateMessageField(event);
		messageTextField.clear();
	}
	
	@FXML public void clearMessage(ActionEvent event) {
		messageTextField.clear();
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
