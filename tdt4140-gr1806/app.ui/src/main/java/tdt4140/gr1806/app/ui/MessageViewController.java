package tdt4140.gr1806.app.ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.CustomerRepository;

public class MessageViewController {
	FitspoAppController fitspo;
	CustomerRepository cr = new CustomerRepository();
	@FXML private ComboBox<Customer> customerComboBox;
	@FXML private Button sendMessageBtn;
	@FXML private Button clearMessageBtn;
	@FXML private TextField messageTextField;
	@FXML private ScrollPane messageBox;
	
	
	public void changeCenterContent(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MessageView.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	
	public void updateDropDown(ArrayList<Customer> customers) {
		customerComboBox = new ComboBox<Customer>();
		
		customerComboBox.setConverter(new StringConverter<Customer>() {
			@Override
			public String toString(Customer cus) {
				return cus.getName();
			}
			
			@Override
			public Customer fromString(String id) {
				//objektet må sendes videre her tror jeg.
				return null;
			}
			
		});
		
		ObservableList<Customer> cust = cr.findAllCustomerObsList();
		customerComboBox.setItems(cust);
		
	
		
	}
	
	
	public void init(FitspoAppController fitspoAppController) {
		fitspo = fitspoAppController;
		System.out.println("hallo");
		updateDropDown(this.cr.findAllCustomers());
	};
	

}
