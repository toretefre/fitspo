package tdt4140.gr1806.app.ui;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

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
import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.CustomerRepository;
import tdt4140.gr1806.app.core.Message;

public class MessageViewController {
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
		Parent root = FXMLLoader.load(getClass().getResource("MessageView.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML public void updateMessageField() {
		ArrayList<Message> messages = cr.getMessages(this.selectedCustomer);
		
		for(Message m : messages) {
			TextArea text = new TextArea();
			text.setText(m.getMessage());
			content.getChildren().add(text);
		}
	}
	
	@FXML public void sendMessage(ActionEvent event) throws SQLException {
		String message = messageTextField.getText();
		Date sqlDate = new Date(System.currentTimeMillis());
		Message m = new Message(sqlDate, this.selectedCustomer.getId(), message);
		cr.saveMessage(m);
		updateMessageField();
	}
	
	@FXML public void clearMessage(ActionEvent event) {
		messageTextField.clear();
	}
	
	public void initialize() {
		// Updates the dropdown (customerComboBox)
		customerComboBox.setItems(cr.findAllCustomerObsList());
		
		// This fires when a field in the dropdown menu is clicked
		customerComboBox.setOnAction(e -> {
			this.selectedCustomer = customerComboBox.getValue();
		});
	}

}
